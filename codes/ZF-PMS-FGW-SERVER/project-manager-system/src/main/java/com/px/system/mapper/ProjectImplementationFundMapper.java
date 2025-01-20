package com.px.system.mapper;

import java.util.List;
import com.px.system.domain.ProjectImplementationFund;

/**
 * 项目资金情况Mapper接口
 * 
 * @author 品讯科技
 * @date 2024-08
 */
public interface ProjectImplementationFundMapper 
{

    /**
     * 查询项目资金情况
     *
     * @param id 项目资金情况主键
     * @return 项目资金情况
     */
    public List<ProjectImplementationFund> selectProjectImplementationFundByRecordInfoId(String id);

    /**
     * 查询项目资金情况
     *
     * @param id 项目资金情况主键
     * @return 项目资金情况
     */
    public ProjectImplementationFund selectProjectImplementationFundById(String id);

    /**
     * 查询项目资金情况列表
     * 
     * @param projectImplementationFund 项目资金情况
     * @return 项目资金情况集合
     */
    public List<ProjectImplementationFund> selectProjectImplementationFundList(ProjectImplementationFund projectImplementationFund);

    /**
     * 新增项目资金情况
     * 
     * @param projectImplementationFund 项目资金情况
     * @return 结果
     */
    public int insertProjectImplementationFund(ProjectImplementationFund projectImplementationFund);

    /**
     * 修改项目资金情况
     * 
     * @param projectImplementationFund 项目资金情况
     * @return 结果
     */
    public int updateProjectImplementationFund(ProjectImplementationFund projectImplementationFund);

    /**
     * 删除项目资金情况
     * 
     * @param id 项目资金情况主键
     * @return 结果
     */
    public int deleteProjectImplementationFundById(String id);

    public int deleteProjectImplementationFundByProjectId(String id);

    /**
     * 批量删除项目资金情况
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectImplementationFundByIds(String[] ids);

    /**
     * 查询项目累计资金情况
     *
     * @param applyProjectId 申请库主键
     * @return 项目资金情况
     */
    public ProjectImplementationFund getSumCompletionOfInvestment(String applyProjectId);

    /**
     * 查询项目年初至今累计资金情况
     *
     * @param applyProjectId 申请库主键
     * @return 项目资金情况
     */
    public ProjectImplementationFund getSumCompletionOfInvestmentByYear(String applyProjectId);

    /**
     * 查询项目资金情况
     *
     * @param applyProjectId 申请库主键
     * @return 项目资金情况
     */
    public ProjectImplementationFund getZyFund(String applyProjectId);

    /**
     * 查询项目资金情况
     *
     * @param applyProjectId 申请库主键
     * @return 项目资金情况
     */
    public ProjectImplementationFund getBsFund(String applyProjectId);

    /**
     * 查询项目资金情况
     *
     * @param applyProjectId 申请库主键
     * @return 项目资金情况
     */
    public ProjectImplementationFund getScFund(String applyProjectId);
}
