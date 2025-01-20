package com.px.system.service.impl;

import com.px.common.core.domain.entity.SysDictData;
import com.px.common.core.domain.model.LoginUser;
import com.px.common.utils.DateUtils;
import com.px.common.utils.uuid.UUID;
import com.px.system.domain.*;
import com.px.system.mapper.ApplyProjectMapper;
import com.px.system.service.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 申请库Service业务层处理
 *
 * @author 品讯科技
 * @date 2024-08
 */
@Service
public class ApplyProjectServiceImpl implements IApplyProjectService {
    @Autowired
    private IdGeneratorSerivce idGeneratorSerivce;
    @Autowired
    private ApplyProjectMapper applyProjectMapper;
    @Autowired
    private IApplyProjectService applyProjectService;
    @Autowired
    private IProjectUserRecordService projectUserRecordService;
    @Autowired
    private IFileUploadInfoService fileUploadInfoService;
    @Autowired
    private IProjectFileInfoService projectFileInfoService;
    @Autowired
    private IProjectFundService projectFundService;
    @Autowired
    private ISysDictDataService dictDataService;
    @Autowired
    private IProjectDivisionLeaderService projectDivisionLeaderService;
    @Autowired
    private IProjectResponsibleUnitService projectResponsibleUnitService;
    @Autowired
    private IProjectLandAreaService projectLandAreaService;

    @Transactional
    @Override
    public boolean audit(AuditDTO auditDTO, LoginUser loginUser) {
        ApplyProject apply = applyProjectService.selectApplyProjectById(auditDTO.getId());
        if (ObjectUtils.isEmpty(apply)) {
            return false;
        }
        ProjectUserRecord recordInfo = setProjectUserRecord(apply, loginUser);
        recordInfo.setProjectId(auditDTO.getId());
        recordInfo.setRemark(auditDTO.getRemark());
        if (auditDTO.getAgree() != null && auditDTO.getAgree()) {
            apply.setProjectStatus(ProjectAuditedTypeEnum.审核通过.getCode());
            recordInfo.setRecordType(ProjectAuditedTypeEnum.审核通过.getValue());
            apply.setProjectNature(auditDTO.getProjectNature());
            if (apply.getProjectProgress() <= 1) {
                apply.setProjectProgress(1);
                apply.setProgressStatus(1);
            }
            List<ProjectFileInfo> projectFileInfoList = apply.getProjectFileInfoList();
            // 批复文件处理
            if (ObjectUtils.isNotEmpty(projectFileInfoList)) {
                projectFileInfoList.stream().forEach(projectFileInfo -> {
                    if (9 == projectFileInfo.getApprovalFileType() || 10 == projectFileInfo.getApprovalFileType()) {
                        Boolean handlingStatus = projectFileInfo.getHandlingStatus();
                        if (Boolean.TRUE.equals(handlingStatus)) {
                            apply.setProjectProgress(2);
                            apply.setWorkRecordTime(new Date());
                            apply.setProgressStatus(1);
                        }
                    }
                });
            }
        } else {
            apply.setProjectStatus(ProjectAuditedTypeEnum.审核不通过.getCode());
            recordInfo.setRecordType(ProjectAuditedTypeEnum.审核不通过.getValue());
        }
        applyProjectService.updateApplyProject(apply);
        projectUserRecordService.insertProjectUserRecord(recordInfo);
        return true;
    }

    @Transactional
    @Override
    public boolean submitAudited(ApplyProject applyProject, LoginUser loginUser) {
        ApplyProject apply = applyProjectService.selectApplyProjectById(applyProject.getId());
        if (ObjectUtils.isEmpty(apply)) {
            return false;
        }
        apply.setProjectStatus(ProjectAuditedTypeEnum.待审核.getCode());
        applyProjectService.updateApplyProject(apply);
        ProjectUserRecord recordInfo = setProjectUserRecord(applyProject, loginUser);
        recordInfo.setRecordType(ProjectAuditedTypeEnum.待审核.getValue());
        recordInfo.setRemark("提交审核");
        projectUserRecordService.insertProjectUserRecord(recordInfo);
        return true;
    }

    @Transactional
    @Override
    public String add(ApplyProject applyProject, LoginUser loginUser) {
        applyProject.setDeptId(loginUser.getUser().getDept().getDeptId());
        applyProject.setProprietorDeptName(loginUser.getUser().getDept().getDeptName());
        applyProject.setProprietorDeptId(loginUser.getUser().getDept().getDeptId());
        applyProject.setUserId(loginUser.getUser().getUserId());
        applyProject.setUserName(loginUser.getUser().getNickName());
        String projectId = UUID.randomUUID().toString();
        applyProject.setId(projectId);
        applyProject.setProjectCode(getProjectNo());
        applyProject.setRecordTime(new Date());
        applyProject.setProjectProgress(1);
        applyProject.setProgressStatus(0);
        applyProject.setProjectStatus(ProjectAuditedTypeEnum.草稿.getCode());
        // 项目资金情况
        List<ProjectFund> projectFundList = applyProject.getProjectFundList();
        if (ObjectUtils.isNotEmpty(projectFundList)) {
            projectFundList.stream().forEach(projectFund -> {
                projectFund.setId(UUID.randomUUID().toString());
                projectFund.setApplyProjectId(projectId);
                applyProject.setTypeOfFunds(Integer.valueOf(projectFund.getTypeOfFunds()));
                projectFundService.insertProjectFund(projectFund);
            });
        }
        // 项目责任单位对象
        List<ProjectResponsibleUnit> projectResponsibleUnitList = applyProject.getProjectResponsibleUnitList();
        if (ObjectUtils.isNotEmpty(projectResponsibleUnitList)) {
            projectResponsibleUnitList.stream().forEach(projectResponsibleUnit -> {
                projectResponsibleUnit.setId(UUID.randomUUID().toString());
                projectResponsibleUnit.setProjectId(applyProject.getId());
                projectResponsibleUnit.setProjectName(applyProject.getProjectName());
                projectResponsibleUnitService.insertProjectResponsibleUnit(projectResponsibleUnit);
            });
        }
        // 分管区领导对象
        List<ProjectDivisionLeader> projectDivisionLeaderList = applyProject.getProjectDivisionLeaderList();
        if (ObjectUtils.isNotEmpty(projectDivisionLeaderList)) {
            projectDivisionLeaderList.stream().forEach(projectDivisionLeader -> {
                projectDivisionLeader.setId(UUID.randomUUID().toString());
                projectDivisionLeader.setProjectId(applyProject.getId());
                projectDivisionLeader.setProjectName(applyProject.getProjectName());
                projectDivisionLeaderService.insertProjectDivisionLeader(projectDivisionLeader);
            });
        }
        // 项目用地
        List<ProjectLandArea> projectLandAreaList = applyProject.getProjectLandAreaList();
        if (ObjectUtils.isNotEmpty(projectLandAreaList)) {
            projectLandAreaList.stream().forEach(projectLandArea -> {
                projectLandArea.setId(UUID.randomUUID().toString());
                projectLandArea.setProjectId(applyProject.getId());
                projectLandArea.setProjectName(applyProject.getProjectName());
                projectLandAreaService.insertProjectLandArea(projectLandArea);
            });
        }
        applyProjectService.insertApplyProject(applyProject);
        ProjectUserRecord recordInfo = setProjectUserRecord(applyProject, loginUser);
        recordInfo.setRecordTime(applyProject.getRecordTime());
        recordInfo.setRecordType(ProjectAuditedTypeEnum.草稿.getValue());
        projectUserRecordService.insertProjectUserRecord(recordInfo);
        return projectId;
    }

    @Transactional
    @Override
    public boolean edit(ApplyProject applyProject, LoginUser loginUser) {
        String projectNo = applyProject.getId();
        // 批复文件处理
        projectFileInfoListHandle(applyProject, loginUser);
        // 项目资金情况
        List<ProjectFund> projectFundList = applyProject.getProjectFundList();
        if (ObjectUtils.isNotEmpty(projectFundList)) {
            projectFundList.stream().forEach(projectFund -> {
                if (StringUtils.isBlank(projectFund.getId())) {
                    projectFund.setId(UUID.randomUUID().toString());
                    projectFund.setApplyProjectId(projectNo);
                    projectFundService.insertProjectFund(projectFund);
                } else {
                    projectFundService.updateProjectFund(projectFund);
                }
                applyProject.setTypeOfFunds(Integer.valueOf(projectFund.getTypeOfFunds()));
            });
        }
        // 分管区领导对象
        List<ProjectDivisionLeader> projectDivisionLeaderList = applyProject.getProjectDivisionLeaderList();
        if (ObjectUtils.isNotEmpty(projectDivisionLeaderList)) {
            projectDivisionLeaderList.stream().forEach(projectDivisionLeader -> {
                if (StringUtils.isBlank(projectDivisionLeader.getId())) {
                    projectDivisionLeader.setId(UUID.randomUUID().toString());
                    projectDivisionLeader.setProjectId(projectNo);
                    projectDivisionLeader.setProjectName(applyProject.getProjectName());
                    projectDivisionLeaderService.insertProjectDivisionLeader(projectDivisionLeader);
                } else {
                    projectDivisionLeaderService.updateProjectDivisionLeader(projectDivisionLeader);
                }
            });
        }
        // 项目责任单位对象
        List<ProjectResponsibleUnit> projectResponsibleUnitList = applyProject.getProjectResponsibleUnitList();
        if (ObjectUtils.isNotEmpty(projectResponsibleUnitList)) {
            projectResponsibleUnitList.stream().forEach(projectResponsibleUnit -> {
                if (StringUtils.isBlank(projectResponsibleUnit.getId())) {
                    projectResponsibleUnit.setId(UUID.randomUUID().toString());
                    projectResponsibleUnit.setProjectId(projectNo);
                    projectResponsibleUnit.setProjectName(applyProject.getProjectName());
                    projectResponsibleUnitService.insertProjectResponsibleUnit(projectResponsibleUnit);
                } else {
                    projectResponsibleUnitService.updateProjectResponsibleUnit(projectResponsibleUnit);
                }
            });
        }
        // 项目用地
        List<ProjectLandArea> projectLandAreaList = applyProject.getProjectLandAreaList();
        if (ObjectUtils.isNotEmpty(projectLandAreaList)) {
            projectLandAreaList.stream().forEach(projectLandArea -> {
                if (StringUtils.isBlank(projectLandArea.getId())) {
                    projectLandArea.setId(UUID.randomUUID().toString());
                    projectLandArea.setProjectId(projectNo);
                    projectLandArea.setProjectName(applyProject.getProjectName());
                    projectLandAreaService.insertProjectLandArea(projectLandArea);
                } else {
                    projectLandAreaService.updateProjectLandArea(projectLandArea);
                }
            });
        }
        applyProjectService.updateApplyProject(applyProject);
        ProjectUserRecord recordInfo = setProjectUserRecord(applyProject, loginUser);
        recordInfo.setRecordType(ProjectAuditedTypeEnum.草稿.getValue());
        projectUserRecordService.insertProjectUserRecord(recordInfo);
        return true;
    }

    /**
     * 批复文件处理
     *
     * @param applyProject
     */
    private void projectFileInfoListHandle(ApplyProject applyProject, LoginUser loginUser) {
        if (applyProject.getDeptId() == null || applyProject.getProprietorDeptId() == null) {
            applyProject.setDeptId(loginUser.getUser().getDept().getDeptId());
            applyProject.setProprietorDeptName(loginUser.getUser().getDept().getDeptName());
            applyProject.setProprietorDeptId(loginUser.getUser().getDept().getDeptId());
        }
        if (applyProject.getUserId() == null) {
            applyProject.setUserId(loginUser.getUser().getUserId());
            applyProject.setUserName(loginUser.getUser().getNickName());
        }
        List<ProjectFileInfo> projectFileInfoList = applyProject.getProjectFileInfoList();
        // 批复文件处理
        if (ObjectUtils.isNotEmpty(projectFileInfoList)) {
            projectFileInfoList.stream().forEach(projectFileInfo -> {
                if (StringUtils.isBlank(projectFileInfo.getId())) {
                    projectFileInfo.setId(UUID.randomUUID().toString());
                    projectFileInfo.setApplyProjectId(applyProject.getId());
                    // 保存批复文件
                    projectFileInfoService.insertProjectFileInfo(projectFileInfo);
                } else {
                    // 修改批复文件
                    projectFileInfoService.updateProjectFileInfo(projectFileInfo);
                }
                // 可研文件类型
                if (0 == projectFileInfo.getApprovalFileType()) {
                    applyProject.setFeasibilityStudyFlag(true);
                    applyProject.setFeasibilityStudyCode(projectFileInfo.getApprovalNumber());
                }
                // 初设文件类型
                else if (4 == projectFileInfo.getApprovalFileType()) {
                    applyProject.setApprovalFlag(true);
                    applyProject.setApprovalCode(projectFileInfo.getApprovalNumber());
                }
                // 施工许可证类型
                else if (9 == projectFileInfo.getApprovalFileType()) {
                    Boolean handlingStatus = projectFileInfo.getHandlingStatus();
                    applyProject.setConstructionPermitFlag(handlingStatus);
                    if (Boolean.TRUE.equals(handlingStatus) && StringUtils.equals(ProjectAuditedTypeEnum.审核通过.getCode(), applyProject.getProjectStatus())) {
                        applyProject.setProjectProgress(2);
                        applyProject.setWorkRecordTime(new Date());
                        applyProject.setProgressStatus(1);
                    }
                }
                // 立项文件类型 修改项目进度
                else if (10 == projectFileInfo.getApprovalFileType()) {
                    Boolean handlingStatus = projectFileInfo.getHandlingStatus();
                    applyProject.setFilingsFlag(handlingStatus);
                    applyProject.setFilingsCode(projectFileInfo.getApprovalNumber());
                    if (Boolean.TRUE.equals(handlingStatus) && StringUtils.equals(ProjectAuditedTypeEnum.审核通过.getCode(), applyProject.getProjectStatus())) {
                        applyProject.setProjectProgress(2);
                        applyProject.setWorkRecordTime(new Date());
                        applyProject.setProgressStatus(1);
                    }
                }
                // 根据项目id及批复文件类型删除旧附件
                FileUploadInfo deleteFileUploadInfo = new FileUploadInfo();
                deleteFileUploadInfo.setApplyProjectId(applyProject.getId());
                deleteFileUploadInfo.setApprovalFileType(String.valueOf(projectFileInfo.getApprovalFileType()));
                fileUploadInfoService.deleteFileUploadInfo(deleteFileUploadInfo);
                // 保存附件信息
                projectFileInfo.getFileUploadInfoList().stream().forEach(fileUploadInfo -> {
                    fileUploadInfo.setId(UUID.randomUUID().toString());
                    fileUploadInfo.setApplyProjectId(applyProject.getId());
                    fileUploadInfo.setProjectFileId(projectFileInfo.getId());
                    fileUploadInfo.setApprovalFileType(String.valueOf(projectFileInfo.getApprovalFileType()));
                    fileUploadInfo.setUploadUserId(applyProject.getUserId().toString());
                    fileUploadInfo.setUploadTime(DateUtils.getTime());
                    fileUploadInfoService.insertFileUploadInfo(fileUploadInfo);
                });
            });
        }
    }

    private ProjectUserRecord setProjectUserRecord(ApplyProject applyProject, LoginUser loginUser) {
        ProjectUserRecord recordInfo = new ProjectUserRecord();
        recordInfo.setRecordTime(new Date());
        recordInfo.setId(UUID.randomUUID().toString());
        recordInfo.setRemark(applyProject.getRemark());
        recordInfo.setProjectId(applyProject.getId());
        recordInfo.setDeptId(loginUser.getUser().getDept().getDeptId());
        recordInfo.setDeptName(loginUser.getUser().getDept().getDeptName());
        recordInfo.setUserId(loginUser.getUser().getUserId());
        recordInfo.setUserName(loginUser.getUser().getNickName());
        return recordInfo;
    }

    /**
     * 查询申请库
     *
     * @param id 申请库主键
     * @return 申请库
     */
    @Override
    public ApplyProject selectApplyProjectById(String id) {
        ApplyProject applyProject = applyProjectMapper.selectApplyProjectById(id);
        // 获取项目资金信息
        ProjectFund projectFund = new ProjectFund();
        projectFund.setApplyProjectId(applyProject.getId());
        applyProject.setProjectFundList(projectFundService.selectProjectFundList(projectFund));
        // 分管区领导对象
        ProjectDivisionLeader projectDivisionLeader = new ProjectDivisionLeader();
        projectDivisionLeader.setProjectId(applyProject.getId());
        applyProject.setProjectDivisionLeaderList(projectDivisionLeaderService.selectProjectDivisionLeaderList(projectDivisionLeader));
        // 项目责任单位对象
        ProjectResponsibleUnit projectResponsibleUnit = new ProjectResponsibleUnit();
        projectResponsibleUnit.setProjectId(applyProject.getId());
        applyProject.setProjectResponsibleUnitList(projectResponsibleUnitService.selectProjectResponsibleUnitList(projectResponsibleUnit));
        // 项目用地
        ProjectLandArea projectLandArea = new ProjectLandArea();
        projectLandArea.setProjectId(applyProject.getId());
        applyProject.setProjectLandAreaList(projectLandAreaService.selectProjectLandAreaList(projectLandArea));
        // 获取全部类型批复文件信息
        ProjectFileInfo projectFileInfo = new ProjectFileInfo();
        projectFileInfo.setApplyProjectId(applyProject.getId());
        List<ProjectFileInfo> projectFileInfoList = projectFileInfoService.selectProjectFileInfoList(projectFileInfo);
        for (ProjectFileInfo fileInfo : projectFileInfoList) {
            // 附件信息
            FileUploadInfo fileUploadInfo = new FileUploadInfo();
            fileUploadInfo.setApplyProjectId(applyProject.getId());
            fileUploadInfo.setApprovalFileType(String.valueOf(fileInfo.getApprovalFileType()));
            List<FileUploadInfo> fileUploadInfoList = fileUploadInfoService.selectFileUploadInfoByApplyProjectId(fileUploadInfo);
            fileInfo.setFileUploadInfoList(fileUploadInfoList);
        }
        applyProject.setProjectFileInfoList(projectFileInfoList);
        return applyProject;
    }

    /**
     * 查询申请库列表
     *
     * @param applyProject 申请库
     * @return 申请库
     */
    @Override
    public List<ApplyProject> selectApplyProjectList(LoginUser loginUser, ApplyProject applyProject) {
        loginUser.getUser().getRoles().stream().forEach(role -> {
            if (role.getRoleId() == 3 || role.getRoleId() == 10) {
                applyProject.setProprietorDeptId(loginUser.getDeptId());
            }
        });
        return applyProjectMapper.selectApplyProjectList(applyProject);
    }

    @Override
    public List<ApplyProject> selectApplyProjectListByApp(LoginUser loginUser, ApplyProject applyProject) {
        loginUser.getUser().getRoles().stream().forEach(role -> {
            if (role.getRoleId() == 3 || role.getRoleId() == 10) {
                applyProject.setProprietorDeptId(loginUser.getDeptId());
            }
        });
        return applyProjectMapper.selectApplyProjectListByApp(applyProject);
    }

    /**
     * 获取项目类别
     *
     * @param dictType
     * @param dictValue
     * @return
     */
    private String getSysDictData(String dictType, String dictValue) {
        if (StringUtils.isBlank(dictValue)) {
            return "";
        }
        SysDictData dictData = new SysDictData();
        dictData.setDictType(dictType);
        dictData.setDictValue(dictValue);
        return dictDataService.selectDictData(dictData).getDictLabel();
    }

    /**
     * 新增申请库
     *
     * @param applyProject 申请库
     * @return 结果
     */
    @Transactional
    @Override
    public int insertApplyProject(ApplyProject applyProject) {
        return applyProjectMapper.insertApplyProject(applyProject);
    }

    @Override
    public List<AppCountInfo> selectProjectCount(Map<String, Object> map) {
        return applyProjectMapper.selectProjectCount(map);
    }

    /**
     * 修改申请库
     *
     * @param applyProject 申请库
     * @return 结果
     */
    @Transactional
    @Override
    public int updateApplyProject(ApplyProject applyProject) {
        return applyProjectMapper.updateApplyProject(applyProject);
    }

    @Override
    public void updatePlanInfoStatus() {
        applyProjectMapper.updatePlanInfoStatus();
    }

    /**
     * 批量删除申请库
     *
     * @param ids 需要删除的申请库主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteApplyProjectByIds(String[] ids) {
        return applyProjectMapper.deleteApplyProjectByIds(ids);
    }

    /**
     * 删除申请库信息
     *
     * @param id 申请库主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteApplyProjectById(String id) {
        return applyProjectMapper.deleteApplyProjectById(id);
    }

    @Override
    public long countProjectSubmission(String deptId) {
        return applyProjectMapper.countProjectSubmission(deptId);
    }

    @Override
    public long countProjectCompletion(String deptId) {
        return applyProjectMapper.countProjectCompletion(deptId);
    }

    private String getProjectNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDate = sdf.format(Calendar.getInstance().getTime());
        long l = idGeneratorSerivce.generateId(currentDate + ":" + "projectNo");
        return String.format("No%s%06d", currentDate, l);
    }

    private void export11111(String id) {
        ApplyProject apply = applyProjectService.selectApplyProjectById(id);
        if (ObjectUtils.isNotEmpty(apply)) {

        }
    }
}
