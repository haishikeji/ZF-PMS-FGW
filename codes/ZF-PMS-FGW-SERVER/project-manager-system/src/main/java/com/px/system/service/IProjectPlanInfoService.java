package com.px.system.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.px.common.core.domain.model.LoginUser;
import com.px.system.domain.ProjectPlanInfo;
import com.px.system.domain.vo.MoneyRecordTotalVo;
import com.px.system.domain.vo.ProjectPlanInfoVo;

/**
 * 全链条一览Service接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface IProjectPlanInfoService {

    public BigDecimal getDqjhzj(String projectId, Date date);

    /**
     * 投资情况统计
     *
     * @param projectId 全链条一览主键
     * @return 全链条一览
     */
    public ProjectPlanInfo sumYearInvestment(String projectId);


    /**
     * 查询全链条一览
     *
     * @param id 全链条一览主键
     * @return 全链条一览
     */
    public ProjectPlanInfo selectProjectPlanInfoById(String id);

    /**
     * 投资情况合计
     *
     * @param projectId 申请库主键
     * @return 全链条一览
     */
    public MoneyRecordTotalVo selectTotalProjectPlanInfoByProjectId(String projectId);

    /**
     * 查询全链条一览列表
     *
     * @param projectPlanInfo 全链条一览
     * @return 全链条一览集合
     */
    public List<ProjectPlanInfo> selectProjectPlanInfoList(ProjectPlanInfo projectPlanInfo);

    /**
     * 查询全链条一览
     *
     * @param projectId
     * @return 全链条一览
     */
    public List<ProjectPlanInfoVo> selectProjectPlanInfo(String projectId);

    /**
     * 新增全链条一览
     *
     * @param projectPlanInfo 全链条一览
     * @return 结果
     */
    public int insertProjectPlanInfo(ProjectPlanInfo projectPlanInfo);

    /**
     * 新增全链条一览列表
     *
     * @param projectPlanInfoList 全链条一览列表
     * @return 结果
     */
    public boolean add(LoginUser loginUser, List<ProjectPlanInfo> projectPlanInfoList);

    /**
     * 修改全链条一览
     *
     * @param projectPlanInfo 全链条一览
     * @return 结果
     */
    public int updateProjectPlanInfo(ProjectPlanInfo projectPlanInfo);

    public boolean edit(LoginUser loginUser, List<ProjectPlanInfo> projectPlanInfoList);

    /**
     * 批量删除全链条一览
     *
     * @param ids 需要删除的全链条一览主键集合
     * @return 结果
     */
    public int deleteProjectPlanInfoByIds(String[] ids);

    /**
     * 删除全链条一览信息
     *
     * @param id 全链条一览主键
     * @return 结果
     */
    public int deleteProjectPlanInfoById(String id);

    long countProjectPlan(String deptId);

    boolean auditOpinion(ProjectPlanInfo projectPlanInfo, LoginUser loginUser);
}
