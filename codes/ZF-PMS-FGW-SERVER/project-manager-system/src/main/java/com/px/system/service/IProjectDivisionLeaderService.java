package com.px.system.service;

import java.util.List;
import com.px.system.domain.ProjectDivisionLeader;

/**
 * 分管区领导Service接口
 * 
 * @author 品讯科技
 * @date 2024-08
 */
public interface IProjectDivisionLeaderService 
{
    /**
     * 查询分管区领导
     * 
     * @param id 分管区领导主键
     * @return 分管区领导
     */
    public ProjectDivisionLeader selectProjectDivisionLeaderById(String id);

    /**
     * 查询分管区领导列表
     * 
     * @param projectDivisionLeader 分管区领导
     * @return 分管区领导集合
     */
    public List<ProjectDivisionLeader> selectProjectDivisionLeaderList(ProjectDivisionLeader projectDivisionLeader);

    /**
     * 新增分管区领导
     * 
     * @param projectDivisionLeader 分管区领导
     * @return 结果
     */
    public int insertProjectDivisionLeader(ProjectDivisionLeader projectDivisionLeader);

    /**
     * 修改分管区领导
     * 
     * @param projectDivisionLeader 分管区领导
     * @return 结果
     */
    public int updateProjectDivisionLeader(ProjectDivisionLeader projectDivisionLeader);

    /**
     * 批量删除分管区领导
     * 
     * @param ids 需要删除的分管区领导主键集合
     * @return 结果
     */
    public int deleteProjectDivisionLeaderByIds(String[] ids);

    /**
     * 删除分管区领导信息
     * 
     * @param id 分管区领导主键
     * @return 结果
     */
    public int deleteProjectDivisionLeaderById(String id);
}
