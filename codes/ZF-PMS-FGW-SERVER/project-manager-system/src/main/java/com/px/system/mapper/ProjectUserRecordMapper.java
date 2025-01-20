package com.px.system.mapper;

import java.util.List;
import com.px.system.domain.ProjectUserRecord;

/**
 * 编报履历Mapper接口
 * 
 * @author 品讯科技
 * @date 2024-08
 */
public interface ProjectUserRecordMapper 
{
    /**
     * 查询编报履历
     * 
     * @param id 编报履历主键
     * @return 编报履历
     */
    public ProjectUserRecord selectProjectUserRecordById(Long id);

    /**
     * 查询编报履历列表
     * 
     * @param projectUserRecord 编报履历
     * @return 编报履历集合
     */
    public List<ProjectUserRecord> selectProjectUserRecordList(ProjectUserRecord projectUserRecord);

    /**
     * 新增编报履历
     * 
     * @param projectUserRecord 编报履历
     * @return 结果
     */
    public int insertProjectUserRecord(ProjectUserRecord projectUserRecord);

    /**
     * 修改编报履历
     * 
     * @param projectUserRecord 编报履历
     * @return 结果
     */
    public int updateProjectUserRecord(ProjectUserRecord projectUserRecord);

    /**
     * 删除编报履历
     * 
     * @param id 编报履历主键
     * @return 结果
     */
    public int deleteProjectUserRecordById(Long id);

    /**
     * 批量删除编报履历
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectUserRecordByIds(Long[] ids);
}
