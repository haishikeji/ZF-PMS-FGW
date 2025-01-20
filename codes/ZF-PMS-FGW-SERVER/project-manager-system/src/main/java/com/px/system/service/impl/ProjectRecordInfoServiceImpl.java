package com.px.system.service.impl;

import com.px.common.core.domain.AjaxResult;
import com.px.common.core.domain.model.LoginUser;
import com.px.common.utils.uuid.UUID;
import com.px.system.domain.ApplyProject;
import com.px.system.domain.ProjectImplementationFund;
import com.px.system.domain.ProjectRecordInfo;
import com.px.system.domain.dto.ProjectRecordInfoDto;
import com.px.system.mapper.ApplyProjectMapper;
import com.px.system.mapper.ProjectRecordInfoMapper;
import com.px.system.service.IApplyProjectService;
import com.px.system.service.IProjectImplementationFundService;
import com.px.system.service.IProjectPlanInfoService;
import com.px.system.service.IProjectRecordInfoService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * 项目实施Service业务层处理
 *
 * @author 品讯科技
 * @date 2024-08
 */
@Service
public class ProjectRecordInfoServiceImpl implements IProjectRecordInfoService {
    @Autowired
    private ProjectRecordInfoMapper projectRecordInfoMapper;
    @Autowired
    private ApplyProjectMapper applyProjectMapper;
    @Autowired
    private IProjectImplementationFundService projectImplementationFundService;
    @Autowired
    private IApplyProjectService applyProjectService;
    @Autowired
    private IProjectPlanInfoService projectPlanInfoService;

    @Override
    public AjaxResult addTaskRecord(ProjectRecordInfo projectRecordInfo) {
        if (projectRecordInfo.getProjectId() == null) {
            return AjaxResult.error("参数错误");
        }
        ApplyProject applyProject = applyProjectService.selectApplyProjectById(projectRecordInfo.getProjectId());
        if (applyProject == null) {
            return AjaxResult.error("获取申报库失败");
        }
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        String reportMonth = String.format("%4d%02d", year, month);
        ProjectRecordInfo query = new ProjectRecordInfo();
        query.setProjectId(projectRecordInfo.getProjectId());
        query.setReportMonth(reportMonth);
        List<ProjectRecordInfo> projectRecordInfos = selectProjectRecordInfoList(query);
        if (com.px.common.utils.StringUtils.isNotEmpty(projectRecordInfos)) {
            return AjaxResult.error("当月已存在填报任务");
        }
        ProjectRecordInfo info = new ProjectRecordInfo();
        info.setReportMonth(reportMonth);
        info.setProjectId(projectRecordInfo.getProjectId());
        info.setFillFormInfo("未填写");
        info.setTaskName(String.format("%4d年重点项目月报", year));
        info.setTaskNo(String.format("%s期", reportMonth));
        info.setProjectType(applyProject.getProjectType());
        String dateString = String.format("%4d-%02d-15", year, month);
        String endString = String.format("%4d-%02d-25", year, month);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        Date date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        info.setTaskBeginTime(date1);
        LocalDate endDate = LocalDate.parse(endString, formatter);
        Date date2 = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        info.setTaskEndTime(date2);
        info.setMonthlyReportingSwitch(true);
        insertProjectRecordInfo(info);
        return AjaxResult.success("添加成功");
    }

    @Transactional
    @Override
    public boolean add(LoginUser loginUser, ProjectRecordInfo projectRecordInfo) {
        if (loginUser.getUser() != null && loginUser.getUser().getDept() != null) {
            projectRecordInfo.setDeptId(loginUser.getUser().getDept().getDeptId());
            projectRecordInfo.setUserId(loginUser.getUser().getUserId());
            projectRecordInfo.setUserName(loginUser.getUser().getNickName());
        }
        projectRecordInfo.setId(UUID.randomUUID().toString());
        List<ProjectImplementationFund> projectImplementationFundList = projectRecordInfo.getProjectImplementationFundList();
        if (ObjectUtils.isNotEmpty(projectImplementationFundList)) {
            projectImplementationFundList.forEach(projectImplementationFund -> {
                projectImplementationFund.setId(UUID.randomUUID().toString());
                projectImplementationFund.setProjectRecordId(projectRecordInfo.getId());
                projectImplementationFundService.insertProjectImplementationFund(projectImplementationFund);
            });
        }
        setProjectEndStatus(projectRecordInfo);
        insertProjectRecordInfo(projectRecordInfo);
        return true;
    }

    @Override
    public boolean edit(LoginUser loginUser, ProjectRecordInfo projectRecordInfo) {
        if (loginUser.getUser() != null && loginUser.getUser().getDept() != null) {
            projectRecordInfo.setDeptId(loginUser.getUser().getDept().getDeptId());
            projectRecordInfo.setUserId(loginUser.getUser().getUserId());
            projectRecordInfo.setUserName(loginUser.getUser().getNickName());
        }
        projectRecordInfo.setRecordTime(new Date());
        projectRecordInfo.setFillFormInfo("已填报");
        List<ProjectImplementationFund> projectImplementationFundList = projectRecordInfo.getProjectImplementationFundList();
        if (ObjectUtils.isNotEmpty(projectImplementationFundList)) {
            projectImplementationFundList.forEach(projectImplementationFund -> {
                if (StringUtils.isBlank(projectImplementationFund.getId())) {
                    projectImplementationFund.setId(UUID.randomUUID().toString());
                    projectImplementationFund.setProjectRecordId(projectRecordInfo.getId());
                    projectImplementationFundService.insertProjectImplementationFund(projectImplementationFund);
                } else {
                    projectImplementationFundService.updateProjectImplementationFund(projectImplementationFund);
                }
            });
        }
        setProjectEndStatus(projectRecordInfo);
        updateProjectRecordInfo(projectRecordInfo);
        return true;
    }

    /**
     * 设置项目结束状态
     * @param projectRecordInfo
     */
    private void setProjectEndStatus(ProjectRecordInfo projectRecordInfo){
        ApplyProject applyProject = applyProjectMapper.selectApplyProjectById(projectRecordInfo.getProjectId());
        applyProject.setProjectEnd(projectRecordInfo.getProjectEnd());
        applyProjectService.updateApplyProject(applyProject);
    }

    /**
     * 查询项目实施
     *
     * @param id 项目实施主键
     * @return 项目实施
     */
    @Override
    public ProjectRecordInfo selectProjectRecordInfoById(String id) {
        ProjectRecordInfo projectRecordInfo = projectRecordInfoMapper.selectProjectRecordInfoById(id);
        projectRecordInfo.setProjectImplementationFundList(projectImplementationFundService.selectProjectImplementationFundByRecordInfoId(projectRecordInfo.getId()));
        BigDecimal jzbqljwctz = projectRecordInfoMapper.getJzbqljwctz(projectRecordInfo.getProjectId(), projectRecordInfo.getTaskEndTime());
        BigDecimal dnljwctz = projectRecordInfoMapper.getDnljwctz(projectRecordInfo.getProjectId(), projectRecordInfo.getTaskEndTime());
        BigDecimal bndjhwctzje = projectRecordInfoMapper.getBndjhwctzje(projectRecordInfo.getProjectId());
        projectRecordInfo.setJzbqljwctz(jzbqljwctz);
        projectRecordInfo.setDnljwctz(dnljwctz);
        projectRecordInfo.setBndjhwctzje(bndjhwctzje);
        if (jzbqljwctz != null && jzbqljwctz.compareTo(BigDecimal.ZERO) > 0 && dnljwctz != null && dnljwctz.compareTo(BigDecimal.ZERO) > 0) {
            projectRecordInfo.setDntzwcl(dnljwctz.divide(bndjhwctzje, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
        }
        return projectRecordInfo;
    }

    /**
     * 获取资金情况
     *
     * @param id
     * @return
     */
    @Override
    public ProjectRecordInfoDto getFundInfo(String projectId, String id) {
        ProjectRecordInfo projectRecordInfo = selectProjectRecordInfoById(id);
        ProjectRecordInfoDto projectRecordInfoDto = new ProjectRecordInfoDto();
        ApplyProject applyProject = applyProjectService.selectApplyProjectById(projectId);
        // 是否复工
        projectRecordInfoDto.setProjectStartFlag(applyProject.getStoppageOrNot());
        // 是否完成可研审批
        projectRecordInfoDto.setFeasibilityStudyFlag(applyProject.getFeasibilityStudyFlag());
        // 是否完成初设审批
        projectRecordInfoDto.setApprovalFlag(applyProject.getApprovalFlag());
        // 是否完成立项(核准/备案)审批
        projectRecordInfoDto.setFilingsFlag(applyProject.getFilingsFlag());
        // 总投资
        projectRecordInfoDto.setTotalInvestment(applyProject.getTotalInvestment());
        BigDecimal jzbqljwctz = projectRecordInfoMapper.getJzbqljwctz(projectRecordInfo.getProjectId(), projectRecordInfo.getTaskEndTime());
        BigDecimal dnljwctz = projectRecordInfoMapper.getDnljwctz(projectRecordInfo.getProjectId(), projectRecordInfo.getTaskEndTime());
        BigDecimal bndjhwctzje = projectRecordInfoMapper.getBndjhwctzje(projectRecordInfo.getProjectId());
        // 自开工累计完成投资
        projectRecordInfoDto.setCompletionOfInvestment(jzbqljwctz);
        // 年初至今累计完成投资
        projectRecordInfoDto.setCompletionOfInvestmentByYear(dnljwctz);
        // 当年计划投资
        projectRecordInfoDto.setAnnualInvestment(bndjhwctzje);
        // 计划投资
        if (jzbqljwctz != null && jzbqljwctz.compareTo(BigDecimal.ZERO) > 0 && dnljwctz != null && dnljwctz.compareTo(BigDecimal.ZERO) > 0) {
            // 占年度投资计划比例
            projectRecordInfoDto.setAnnualRatio(dnljwctz.divide(bndjhwctzje, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
            // 本月目标进度
            BigDecimal investment = projectRecordInfo.getCompletionOfInvestment();
            BigDecimal dqjhzj = projectPlanInfoService.getDqjhzj(projectId, projectRecordInfo.getTaskEndTime());
            if (investment != null && investment.compareTo(BigDecimal.ZERO) > 0 && dqjhzj != null && dqjhzj.compareTo(BigDecimal.ZERO) > 0) {
                projectRecordInfoDto.setTargetProgress(investment.divide(dqjhzj,4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
            } else {
                projectRecordInfoDto.setTargetProgress(BigDecimal.ZERO);
            }
        } else {
            projectRecordInfoDto.setAnnualRatio(BigDecimal.ZERO);
            projectRecordInfoDto.setTargetProgress(BigDecimal.ZERO);
        }
        return projectRecordInfoDto;
    }

    /**
     * 查询项目实施列表
     *
     * @param projectRecordInfo 项目实施
     * @return 项目实施
     */
    @Override
    public List<ProjectRecordInfo> selectProjectRecordInfoList(ProjectRecordInfo projectRecordInfo) {
        return projectRecordInfoMapper.selectProjectRecordInfoList(projectRecordInfo);
    }

    /**
     * 查询项目实施历史调度列表
     *
     * @param projectRecordInfo 项目实施
     * @return 项目实施
     */
    @Override
    public List<ProjectRecordInfo> selectProjectRecordInfoHistoryList(ProjectRecordInfo projectRecordInfo) {
        return projectRecordInfoMapper.selectProjectRecordInfoHistoryList(projectRecordInfo);
    }

    /**
     * 新增项目实施
     *
     * @param projectRecordInfo 项目实施
     * @return 结果
     */
    @Override
    public int insertProjectRecordInfo(ProjectRecordInfo projectRecordInfo) {
        projectRecordInfo.setId(UUID.randomUUID().toString());
        return projectRecordInfoMapper.insertProjectRecordInfo(projectRecordInfo);
    }

    /**
     * 修改项目实施
     *
     * @param projectRecordInfo 项目实施
     * @return 结果
     */
    @Override
    public int updateProjectRecordInfo(ProjectRecordInfo projectRecordInfo) {
        return projectRecordInfoMapper.updateProjectRecordInfo(projectRecordInfo);
    }

    /**
     * 批量删除项目实施
     *
     * @param ids 需要删除的项目实施主键
     * @return 结果
     */
    @Override
    public int deleteProjectRecordInfoByIds(String[] ids) {
        return projectRecordInfoMapper.deleteProjectRecordInfoByIds(ids);
    }

    /**
     * 删除项目实施信息
     *
     * @param id 项目实施主键
     * @return 结果
     */
    @Override
    public int deleteProjectRecordInfoById(String id) {
        return projectRecordInfoMapper.deleteProjectRecordInfoById(id);
    }

    /**
     * 统计项目实施数量
     *
     * @return
     */
    @Override
    public long countProjectRecord(String deptId) {
        return projectRecordInfoMapper.countProjectRecord(deptId);
    }

    @Override
    public boolean monthlyReportingSwitch(int status) {
        projectRecordInfoMapper.monthlyReportingSwitch(status);
        return true;
    }
}
