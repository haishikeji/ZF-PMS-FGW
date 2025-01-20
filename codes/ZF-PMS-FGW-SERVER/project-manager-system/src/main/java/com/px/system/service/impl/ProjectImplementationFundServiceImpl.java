package com.px.system.service.impl;

import java.util.List;

import com.px.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.px.system.mapper.ProjectImplementationFundMapper;
import com.px.system.domain.ProjectImplementationFund;
import com.px.system.service.IProjectImplementationFundService;

/**
 * 项目资金情况Service业务层处理
 * 
 * @author 品讯科技
 * @date 2024-08
 */
@Service
public class ProjectImplementationFundServiceImpl implements IProjectImplementationFundService 
{
    @Autowired
    private ProjectImplementationFundMapper projectImplementationFundMapper;

    @Override
    public List<ProjectImplementationFund> selectProjectImplementationFundByRecordInfoId(String id) {
        return projectImplementationFundMapper.selectProjectImplementationFundByRecordInfoId(id);
    }

    /**
     * 查询项目资金情况
     * 
     * @param id 项目资金情况主键
     * @return 项目资金情况
     */
    @Override
    public ProjectImplementationFund selectProjectImplementationFundById(String id)
    {
        return projectImplementationFundMapper.selectProjectImplementationFundById(id);
    }

    /**
     * 查询项目资金情况列表
     * 
     * @param projectImplementationFund 项目资金情况
     * @return 项目资金情况
     */
    @Override
    public List<ProjectImplementationFund> selectProjectImplementationFundList(ProjectImplementationFund projectImplementationFund)
    {
        return projectImplementationFundMapper.selectProjectImplementationFundList(projectImplementationFund);
    }

    /**
     * 新增项目资金情况
     * 
     * @param projectImplementationFund 项目资金情况
     * @return 结果
     */
    @Override
    public int insertProjectImplementationFund(ProjectImplementationFund projectImplementationFund)
    {
        projectImplementationFund.setCreationTime(DateUtils.getTime());
        return projectImplementationFundMapper.insertProjectImplementationFund(projectImplementationFund);
    }

    /**
     * 修改项目资金情况
     * 
     * @param projectImplementationFund 项目资金情况
     * @return 结果
     */
    @Override
    public int updateProjectImplementationFund(ProjectImplementationFund projectImplementationFund)
    {
        projectImplementationFund.setModificationTime(DateUtils.getTime());
        return projectImplementationFundMapper.updateProjectImplementationFund(projectImplementationFund);
    }

    /**
     * 批量删除项目资金情况
     * 
     * @param ids 需要删除的项目资金情况主键
     * @return 结果
     */
    @Override
    public int deleteProjectImplementationFundByIds(String[] ids)
    {
        return projectImplementationFundMapper.deleteProjectImplementationFundByIds(ids);
    }

    /**
     * 删除项目资金情况信息
     * 
     * @param id 项目资金情况主键
     * @return 结果
     */
    @Override
    public int deleteProjectImplementationFundById(String id)
    {
        return projectImplementationFundMapper.deleteProjectImplementationFundById(id);
    }

    @Override
    public int deleteProjectImplementationFundByProjectId(String projectId)
    {
        return projectImplementationFundMapper.deleteProjectImplementationFundByProjectId(projectId);
    }

    /**
     * 查询项目累计资金情况
     *
     * @param applyProjectId 申请库主键
     * @return 项目资金情况
     */
    @Override
    public ProjectImplementationFund getSumCompletionOfInvestment(String applyProjectId) {
        return projectImplementationFundMapper.getSumCompletionOfInvestment(applyProjectId);
    }

    /**
     * 查询项目年初至今累计资金情况
     *
     * @param applyProjectId 申请库主键
     * @return 项目资金情况
     */
    @Override
    public ProjectImplementationFund getSumCompletionOfInvestmentByYear(String applyProjectId) {
        return projectImplementationFundMapper.getSumCompletionOfInvestmentByYear(applyProjectId);
    }

    /**
     * 查询项目资金情况 中央
     *
     * @param applyProjectId 申请库主键
     * @return 项目资金情况
     */
    @Override
    public ProjectImplementationFund getZyFund(String applyProjectId) {
        return projectImplementationFundMapper.getZyFund(applyProjectId);
    }

    /**
     * 查询项目资金情况 市本级
     *
     * @param applyProjectId 申请库主键
     * @return 项目资金情况
     */
    @Override
    public ProjectImplementationFund getBsFund(String applyProjectId) {
        return projectImplementationFundMapper.getBsFund(applyProjectId);
    }

    /**
     * 查询项目资金情况 市场化及其他
     *
     * @param applyProjectId 申请库主键
     * @return 项目资金情况
     */
    @Override
    public ProjectImplementationFund getScFund(String applyProjectId) {
        return projectImplementationFundMapper.getScFund(applyProjectId);
    }
}
