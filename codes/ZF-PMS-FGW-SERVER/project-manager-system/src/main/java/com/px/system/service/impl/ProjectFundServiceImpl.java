package com.px.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.px.system.mapper.ProjectFundMapper;
import com.px.system.domain.ProjectFund;
import com.px.system.service.IProjectFundService;

/**
 * 项目资金情况Service业务层处理
 * 
 * @author 品讯科技
 * @date 2024-08
 */
@Service
public class ProjectFundServiceImpl implements IProjectFundService 
{
    @Autowired
    private ProjectFundMapper projectFundMapper;

    /**
     * 查询项目资金情况
     * 
     * @param id 项目资金情况主键
     * @return 项目资金情况
     */
    @Override
    public ProjectFund selectProjectFundById(String id)
    {
        return projectFundMapper.selectProjectFundById(id);
    }

    /**
     * 查询项目资金情况列表
     * 
     * @param projectFund 项目资金情况
     * @return 项目资金情况
     */
    @Override
    public List<ProjectFund> selectProjectFundList(ProjectFund projectFund)
    {
        return projectFundMapper.selectProjectFundList(projectFund);
    }

    /**
     * 新增项目资金情况
     * 
     * @param projectFund 项目资金情况
     * @return 结果
     */
    @Override
    public int insertProjectFund(ProjectFund projectFund)
    {
        return projectFundMapper.insertProjectFund(projectFund);
    }

    /**
     * 修改项目资金情况
     * 
     * @param projectFund 项目资金情况
     * @return 结果
     */
    @Override
    public int updateProjectFund(ProjectFund projectFund)
    {
        return projectFundMapper.updateProjectFund(projectFund);
    }

    /**
     * 批量删除项目资金情况
     * 
     * @param ids 需要删除的项目资金情况主键
     * @return 结果
     */
    @Override
    public int deleteProjectFundByIds(String[] ids)
    {
        return projectFundMapper.deleteProjectFundByIds(ids);
    }

    /**
     * 删除项目资金情况信息
     * 
     * @param id 项目资金情况主键
     * @return 结果
     */
    @Override
    public int deleteProjectFundById(String id)
    {
        return projectFundMapper.deleteProjectFundById(id);
    }

    @Override
    public ProjectFund getZyFund(String applyProjectId) {
        return projectFundMapper.getZyFund(applyProjectId);
    }

    @Override
    public ProjectFund getBsFund(String applyProjectId) {
        return projectFundMapper.getBsFund(applyProjectId);
    }

    @Override
    public ProjectFund getScFund(String applyProjectId) {
        return projectFundMapper.getScFund(applyProjectId);
    }
}
