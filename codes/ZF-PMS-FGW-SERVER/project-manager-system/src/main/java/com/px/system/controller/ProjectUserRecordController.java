package com.px.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.px.common.annotation.Log;
import com.px.common.core.controller.BaseController;
import com.px.common.core.domain.AjaxResult;
import com.px.common.enums.BusinessType;
import com.px.system.domain.ProjectUserRecord;
import com.px.system.service.IProjectUserRecordService;
import com.px.common.utils.poi.ExcelUtil;
import com.px.common.core.page.TableDataInfo;

/**
 * 编报履历Controller
 * 
 * @author 品讯科技
 * @date 2024-08
 */
@RestController
@RequestMapping("/system/projectUserRecord")
public class ProjectUserRecordController extends BaseController
{
    @Autowired
    private IProjectUserRecordService projectUserRecordService;

    /**
     * 查询编报履历列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectUserRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectUserRecord projectUserRecord)
    {
        startPage();
        List<ProjectUserRecord> list = projectUserRecordService.selectProjectUserRecordList(projectUserRecord);
        return getDataTable(list);
    }

    /**
     * 导出编报履历列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectUserRecord:export')")
    @Log(title = "编报履历", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectUserRecord projectUserRecord)
    {
        List<ProjectUserRecord> list = projectUserRecordService.selectProjectUserRecordList(projectUserRecord);
        ExcelUtil<ProjectUserRecord> util = new ExcelUtil<ProjectUserRecord>(ProjectUserRecord.class);
        util.exportExcel(response, list, "编报履历数据");
    }

    /**
     * 获取编报履历详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectUserRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(projectUserRecordService.selectProjectUserRecordById(id));
    }

    /**
     * 新增编报履历
     */
    @PreAuthorize("@ss.hasPermi('system:projectUserRecord:add')")
    @Log(title = "编报履历", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectUserRecord projectUserRecord)
    {
        return toAjax(projectUserRecordService.insertProjectUserRecord(projectUserRecord));
    }

    /**
     * 修改编报履历
     */
    @PreAuthorize("@ss.hasPermi('system:projectUserRecord:edit')")
    @Log(title = "编报履历", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectUserRecord projectUserRecord)
    {
        return toAjax(projectUserRecordService.updateProjectUserRecord(projectUserRecord));
    }

    /**
     * 删除编报履历
     */
    @PreAuthorize("@ss.hasPermi('system:projectUserRecord:remove')")
    @Log(title = "编报履历", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(projectUserRecordService.deleteProjectUserRecordByIds(ids));
    }
}
