package com.px.system.mapper;

import java.util.List;
import com.px.system.domain.ProjectResponsibleUnit;

/**
 * 项目责任单位Mapper接口
 * 
 * @author 品讯科技
 * @date 2024-08
 */
public interface ProjectResponsibleUnitMapper 
{
    /**
     * 查询项目责任单位
     * 
     * @param id 项目责任单位主键
     * @return 项目责任单位
     */
    public ProjectResponsibleUnit selectProjectResponsibleUnitById(String id);

    public String getZrdw(String id);

    public String getZrrxm(String id);

    public String getZrrdh(String id);

    /**
     * 查询项目责任单位列表
     * 
     * @param projectResponsibleUnit 项目责任单位
     * @return 项目责任单位集合
     */
    public List<ProjectResponsibleUnit> selectProjectResponsibleUnitList(ProjectResponsibleUnit projectResponsibleUnit);

    /**
     * 新增项目责任单位
     * 
     * @param projectResponsibleUnit 项目责任单位
     * @return 结果
     */
    public int insertProjectResponsibleUnit(ProjectResponsibleUnit projectResponsibleUnit);

    /**
     * 修改项目责任单位
     * 
     * @param projectResponsibleUnit 项目责任单位
     * @return 结果
     */
    public int updateProjectResponsibleUnit(ProjectResponsibleUnit projectResponsibleUnit);

    /**
     * 删除项目责任单位
     * 
     * @param id 项目责任单位主键
     * @return 结果
     */
    public int deleteProjectResponsibleUnitById(String id);

    /**
     * 批量删除项目责任单位
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectResponsibleUnitByIds(String[] ids);
}
