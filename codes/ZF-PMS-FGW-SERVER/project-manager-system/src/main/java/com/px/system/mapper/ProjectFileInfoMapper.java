package com.px.system.mapper;

import java.util.List;
import com.px.system.domain.ProjectFileInfo;

/**
 * 项目批复文件详情Mapper接口
 * 
 * @author 品讯科技
 * @date 2024-08
 */
public interface ProjectFileInfoMapper 
{
    /**
     * 查询项目批复文件详情
     * 
     * @param id 项目批复文件详情主键
     * @return 项目批复文件详情
     */
    public ProjectFileInfo selectProjectFileInfoById(String id);

    /**
     * 查询项目批复文件详情列表
     * 
     * @param projectFileInfo 项目批复文件详情
     * @return 项目批复文件详情集合
     */
    public List<ProjectFileInfo> selectProjectFileInfoList(ProjectFileInfo projectFileInfo);

    /**
     * 新增项目批复文件详情
     * 
     * @param projectFileInfo 项目批复文件详情
     * @return 结果
     */
    public int insertProjectFileInfo(ProjectFileInfo projectFileInfo);

    /**
     * 修改项目批复文件详情
     * 
     * @param projectFileInfo 项目批复文件详情
     * @return 结果
     */
    public int updateProjectFileInfo(ProjectFileInfo projectFileInfo);

    /**
     * 删除项目批复文件详情
     * 
     * @param id 项目批复文件详情主键
     * @return 结果
     */
    public int deleteProjectFileInfoById(String id);

    /**
     * 批量删除项目批复文件详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectFileInfoByIds(String[] ids);
}
