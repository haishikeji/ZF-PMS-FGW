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
import com.px.system.domain.ProjectDivisionLeader;
import com.px.system.service.IProjectDivisionLeaderService;
import com.px.common.utils.poi.ExcelUtil;
import com.px.common.core.page.TableDataInfo;

/**
 * 分管区领导Controller
 * 
 * @author 品讯科技
 * @date 2024-08
 */
@RestController
@RequestMapping("/system/divisionLeader")
public class ProjectDivisionLeaderController extends BaseController
{
    @Autowired
    private IProjectDivisionLeaderService projectDivisionLeaderService;

    /**
     * 查询分管区领导列表
     */
    @PreAuthorize("@ss.hasPermi('system:divisionLeader:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectDivisionLeader projectDivisionLeader)
    {
        startPage();
        List<ProjectDivisionLeader> list = projectDivisionLeaderService.selectProjectDivisionLeaderList(projectDivisionLeader);
        return getDataTable(list);
    }

    /**
     * 导出分管区领导列表
     */
    @PreAuthorize("@ss.hasPermi('system:divisionLeader:export')")
    @Log(title = "分管区领导", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectDivisionLeader projectDivisionLeader)
    {
        List<ProjectDivisionLeader> list = projectDivisionLeaderService.selectProjectDivisionLeaderList(projectDivisionLeader);
        ExcelUtil<ProjectDivisionLeader> util = new ExcelUtil<ProjectDivisionLeader>(ProjectDivisionLeader.class);
        util.exportExcel(response, list, "分管区领导数据");
    }

    /**
     * 获取分管区领导详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:divisionLeader:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(projectDivisionLeaderService.selectProjectDivisionLeaderById(id));
    }

    /**
     * 新增分管区领导
     */
    @PreAuthorize("@ss.hasPermi('system:divisionLeader:add')")
    @Log(title = "分管区领导", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectDivisionLeader projectDivisionLeader)
    {
        return toAjax(projectDivisionLeaderService.insertProjectDivisionLeader(projectDivisionLeader));
    }

    /**
     * 修改分管区领导
     */
    @PreAuthorize("@ss.hasPermi('system:divisionLeader:edit')")
    @Log(title = "分管区领导", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectDivisionLeader projectDivisionLeader)
    {
        return toAjax(projectDivisionLeaderService.updateProjectDivisionLeader(projectDivisionLeader));
    }

    /**
     * 删除分管区领导
     */
    @PreAuthorize("@ss.hasPermi('system:divisionLeader:remove')")
    @Log(title = "分管区领导", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(projectDivisionLeaderService.deleteProjectDivisionLeaderByIds(ids));
    }
}
