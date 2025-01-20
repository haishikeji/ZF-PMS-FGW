package com.px.system.service.impl;

import com.px.common.core.domain.model.LoginUser;
import com.px.common.utils.DateUtils;
import com.px.common.utils.uuid.UUID;
import com.px.system.domain.ApplyProject;
import com.px.system.domain.ProjectPlanInfo;
import com.px.system.domain.vo.MoneyRecordTotalVo;
import com.px.system.domain.vo.ProjectPlanInfoVo;
import com.px.system.mapper.ProjectPlanInfoMapper;
import com.px.system.mapper.ProjectRecordInfoMapper;
import com.px.system.service.IApplyProjectService;
import com.px.system.service.IProjectPlanInfoService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 全链条一览Service业务层处理
 *
 * @author 品讯科技
 * @date 2024-08
 */
@Service
public class ProjectPlanInfoServiceImpl implements IProjectPlanInfoService {
    @Autowired
    private ProjectPlanInfoMapper projectPlanInfoMapper;
    @Autowired
    private IApplyProjectService applyProjectService;
    @Autowired
    private ProjectRecordInfoMapper projectRecordInfoMapper;

    @Override
    public BigDecimal getDqjhzj(String projectId, Date date) {
        return projectPlanInfoMapper.getDqjhzj(projectId, date);
    }

    /**
     * 投资情况统计
     *
     * @param projectId 全链条一览主键
     * @return 全链条一览
     */
    @Override
    public ProjectPlanInfo sumYearInvestment(String projectId) {
        return projectPlanInfoMapper.sumYearInvestment(projectId);
    }

    /**
     * 查询全链条一览
     *
     * @param id 全链条一览主键
     * @return 全链条一览
     */
    @Override
    public ProjectPlanInfo selectProjectPlanInfoById(String id) {
        return projectPlanInfoMapper.selectProjectPlanInfoById(id);
    }

    /**
     * 投资情况合计
     *
     * @param projectId 申请库主键
     * @return 投资情况合计
     */
    @Override
    public MoneyRecordTotalVo selectTotalProjectPlanInfoByProjectId(String projectId) {
        MoneyRecordTotalVo moneyRecordTotalVo = projectPlanInfoMapper.selectTotalProjectPlanInfoByProjectId(projectId);
        moneyRecordTotalVo.setYearInvestment(projectRecordInfoMapper.getJzbqljwctz(projectId, DateUtils.getNowDate()));
        return moneyRecordTotalVo;
    }

    /**
     * 查询全链条一览列表
     *
     * @param projectPlanInfo 全链条一览
     * @return 全链条一览
     */
    @Override
    public List<ProjectPlanInfo> selectProjectPlanInfoList(ProjectPlanInfo projectPlanInfo) {
        return projectPlanInfoMapper.selectProjectPlanInfoList(projectPlanInfo);
    }

    @Override
    public List<ProjectPlanInfoVo> selectProjectPlanInfo(String projectId) {
        return projectPlanInfoMapper.selectProjectPlanInfo(projectId);
    }

    /**
     * 新增全链条一览
     *
     * @param projectPlanInfo 全链条一览
     * @return 结果
     */
    @Override
    public int insertProjectPlanInfo(ProjectPlanInfo projectPlanInfo) {
        projectPlanInfo.setId(UUID.randomUUID().toString());
        return projectPlanInfoMapper.insertProjectPlanInfo(projectPlanInfo);
    }

    @Override
    public boolean add(LoginUser loginUser, List<ProjectPlanInfo> projectPlanInfoList) {
        if (loginUser.getUser() != null && loginUser.getUser().getDept() != null && ObjectUtils.isEmpty(projectPlanInfoList)) {
            return false;
        }
        projectPlanInfoList.stream().forEach(projectPlanInfo -> {
            projectPlanInfo.setDeptId(loginUser.getUser().getDept().getDeptId());
            projectPlanInfo.setUserId(loginUser.getUser().getUserId());
            projectPlanInfo.setUserName(loginUser.getUser().getNickName());
            projectPlanInfo.setRecordTime(new Date());
            if (StringUtils.isBlank(projectPlanInfo.getId())) {
                insertProjectPlanInfo(projectPlanInfo);
            } else {
                updateProjectPlanInfo(projectPlanInfo);
            }
        });
        ProjectPlanInfo projectPlanInfo = projectPlanInfoList.get(0);
        ApplyProject applyProject = new ApplyProject();
        applyProject.setId(projectPlanInfo.getProjectId());
        applyProject.setPlanInfoStatus(true);
        applyProject.setPlanAuditStatus(2);
        applyProjectService.updateApplyProject(applyProject);
        return true;
    }

    /**
     * 修改全链条一览
     *
     * @param projectPlanInfo 全链条一览
     * @return 结果
     */
    @Override
    public int updateProjectPlanInfo(ProjectPlanInfo projectPlanInfo) {
        return projectPlanInfoMapper.updateProjectPlanInfo(projectPlanInfo);
    }

    @Override
    public boolean edit(LoginUser loginUser, List<ProjectPlanInfo> projectPlanInfoList) {
        projectPlanInfoList.stream().filter(projectPlanInfo -> loginUser.getUser() != null && loginUser.getUser().getDept() != null).forEach(projectPlanInfo -> {
            projectPlanInfo.setDeptId(loginUser.getUser().getDept().getDeptId());
            projectPlanInfo.setUserId(loginUser.getUser().getUserId());
            projectPlanInfo.setUserName(loginUser.getUser().getNickName());
            projectPlanInfo.setRecordTime(new Date());
            projectPlanInfo.setStatus(null);
            updateProjectPlanInfo(projectPlanInfo);
        });
        ProjectPlanInfo projectPlanInfo = projectPlanInfoList.get(0);
        ApplyProject applyProject = new ApplyProject();
        applyProject.setId(projectPlanInfo.getProjectId());
        applyProject.setPlanAuditStatus(2);
        applyProjectService.updateApplyProject(applyProject);
        return true;
    }

    /**
     * 批量删除全链条一览
     *
     * @param ids 需要删除的全链条一览主键
     * @return 结果
     */
    @Override
    public int deleteProjectPlanInfoByIds(String[] ids) {
        return projectPlanInfoMapper.deleteProjectPlanInfoByIds(ids);
    }

    /**
     * 删除全链条一览信息
     *
     * @param id 全链条一览主键
     * @return 结果
     */
    @Override
    public int deleteProjectPlanInfoById(String id) {
        return projectPlanInfoMapper.deleteProjectPlanInfoById(id);
    }

    @Override
    public long countProjectPlan(String deptId) {
        return projectPlanInfoMapper.countProjectPlan(deptId);
    }

    @Override
    public boolean auditOpinion(ProjectPlanInfo projectPlanInfo, LoginUser loginUser) {
        projectPlanInfo.setAuditUserId(loginUser.getUserId().toString());
        projectPlanInfo.setAuditUserName(loginUser.getUser().getNickName());
        projectPlanInfo.setOperatingTime(DateUtils.getTime());
        projectPlanInfoMapper.auditOpinion(projectPlanInfo);
        ApplyProject applyProject = new ApplyProject();
        applyProject.setId(projectPlanInfo.getProjectId());
        applyProject.setPlanAuditStatus(projectPlanInfo.getStatus());
        applyProjectService.updateApplyProject(applyProject);
        return true;
    }
}
