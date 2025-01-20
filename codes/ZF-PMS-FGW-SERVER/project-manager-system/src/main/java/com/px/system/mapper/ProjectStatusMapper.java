package com.px.system.mapper;

import java.util.List;
import com.px.system.domain.ProjectStatus;

/**
 * 申请库工期状态Mapper接口
 * 
 * @author 品讯科技
 * @date 2024-08
 */
public interface ProjectStatusMapper
{
    /**
     * 查询申请库工期状态
     * 
     * @param id 申请库工期状态主键
     * @return 申请库工期状态
     */
    public ProjectStatus selectProjectStatusById(String id);

    /**
     * 查询申请库工期状态列表
     * 
     * @param projectStatus 申请库工期状态
     * @return 申请库工期状态集合
     */
    public List<ProjectStatus> selectProjectStatusList(ProjectStatus projectStatus);

    /**
     * 新增申请库工期状态
     * 
     * @param projectStatus 申请库工期状态
     * @return 结果
     */
    public int insertProjectStatus(ProjectStatus projectStatus);

    /**
     * 修改申请库工期状态
     * 
     * @param projectStatus 申请库工期状态
     * @return 结果
     */
    public int updateProjectStatus(ProjectStatus projectStatus);

    /**
     * 删除申请库工期状态
     * 
     * @param id 申请库工期状态主键
     * @return 结果
     */
    public int deleteProjectStatusById(String id);

    /**
     * 批量删除申请库工期状态
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectStatusByIds(String[] ids);
}
