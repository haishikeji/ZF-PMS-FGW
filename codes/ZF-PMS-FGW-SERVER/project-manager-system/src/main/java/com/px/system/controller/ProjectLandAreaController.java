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
import com.px.system.domain.ProjectLandArea;
import com.px.system.service.IProjectLandAreaService;
import com.px.common.utils.poi.ExcelUtil;
import com.px.common.core.page.TableDataInfo;

/**
 * 项目用地Controller
 * 
 * @author 品讯科技
 * @date 2024-08
 */
@RestController
@RequestMapping("/system/landArea")
public class ProjectLandAreaController extends BaseController
{
    @Autowired
    private IProjectLandAreaService projectLandAreaService;

    /**
     * 查询项目用地列表
     */
    @PreAuthorize("@ss.hasPermi('system:landArea:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectLandArea projectLandArea)
    {
        startPage();
        List<ProjectLandArea> list = projectLandAreaService.selectProjectLandAreaList(projectLandArea);
        return getDataTable(list);
    }

    /**
     * 导出项目用地列表
     */
    @PreAuthorize("@ss.hasPermi('system:landArea:export')")
    @Log(title = "项目用地", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectLandArea projectLandArea)
    {
        List<ProjectLandArea> list = projectLandAreaService.selectProjectLandAreaList(projectLandArea);
        ExcelUtil<ProjectLandArea> util = new ExcelUtil<ProjectLandArea>(ProjectLandArea.class);
        util.exportExcel(response, list, "项目用地数据");
    }

    /**
     * 获取项目用地详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:landArea:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(projectLandAreaService.selectProjectLandAreaById(id));
    }

    /**
     * 新增项目用地
     */
    @PreAuthorize("@ss.hasPermi('system:landArea:add')")
    @Log(title = "项目用地", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectLandArea projectLandArea)
    {
        return toAjax(projectLandAreaService.insertProjectLandArea(projectLandArea));
    }

    /**
     * 修改项目用地
     */
    @PreAuthorize("@ss.hasPermi('system:landArea:edit')")
    @Log(title = "项目用地", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectLandArea projectLandArea)
    {
        return toAjax(projectLandAreaService.updateProjectLandArea(projectLandArea));
    }

    /**
     * 删除项目用地
     */
    @PreAuthorize("@ss.hasPermi('system:landArea:remove')")
    @Log(title = "项目用地", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(projectLandAreaService.deleteProjectLandAreaByIds(ids));
    }
}
