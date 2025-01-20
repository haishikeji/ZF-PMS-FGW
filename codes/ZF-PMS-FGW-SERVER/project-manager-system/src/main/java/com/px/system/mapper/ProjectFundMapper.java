package com.px.system.mapper;

import java.util.List;
import com.px.system.domain.ProjectFund;

/**
 * 项目资金情况Mapper接口
 * 
 * @author 品讯科技
 * @date 2024-08
 */
public interface ProjectFundMapper 
{
    /**
     * 查询项目资金情况
     * 
     * @param id 项目资金情况主键
     * @return 项目资金情况
     */
    public ProjectFund selectProjectFundById(String id);

    /**
     * 查询项目资金情况列表
     * 
     * @param projectFund 项目资金情况
     * @return 项目资金情况集合
     */
    public List<ProjectFund> selectProjectFundList(ProjectFund projectFund);

    /**
     * 新增项目资金情况
     * 
     * @param projectFund 项目资金情况
     * @return 结果
     */
    public int insertProjectFund(ProjectFund projectFund);

    /**
     * 修改项目资金情况
     * 
     * @param projectFund 项目资金情况
     * @return 结果
     */
    public int updateProjectFund(ProjectFund projectFund);

    /**
     * 删除项目资金情况
     * 
     * @param id 项目资金情况主键
     * @return 结果
     */
    public int deleteProjectFundById(String id);

    /**
     * 批量删除项目资金情况
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectFundByIds(String[] ids);

    /**
     * 查询项目资金情况
     *
     * @param applyProjectId 项目资金情况主键
     * @return 项目资金情况
     */
    public ProjectFund getZyFund(String applyProjectId);

    /**
     * 查询项目资金情况
     *
     * @param applyProjectId 项目资金情况主键
     * @return 项目资金情况
     */
    public ProjectFund getBsFund(String applyProjectId);

    /**
     * 查询项目资金情况
     *
     * @param applyProjectId 项目资金情况主键
     * @return 项目资金情况
     */
    public ProjectFund getScFund(String applyProjectId);
}
