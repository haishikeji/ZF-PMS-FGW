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
import com.px.system.domain.ProjectImplementationFund;
import com.px.system.service.IProjectImplementationFundService;
import com.px.common.utils.poi.ExcelUtil;
import com.px.common.core.page.TableDataInfo;

/**
 * 项目资金情况Controller
 * 
 * @author 品讯科技
 * @date 2024-08
 */
@RestController
@RequestMapping("/system/implementationFund")
public class ProjectImplementationFundController extends BaseController
{
    @Autowired
    private IProjectImplementationFundService projectImplementationFundService;

    /**
     * 查询项目资金情况列表
     */
    @PreAuthorize("@ss.hasPermi('system:implementationFund:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectImplementationFund projectImplementationFund)
    {
        startPage();
        List<ProjectImplementationFund> list = projectImplementationFundService.selectProjectImplementationFundList(projectImplementationFund);
        return getDataTable(list);
    }

    /**
     * 导出项目资金情况列表
     */
    @PreAuthorize("@ss.hasPermi('system:implementationFund:export')")
    @Log(title = "项目资金情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectImplementationFund projectImplementationFund)
    {
        List<ProjectImplementationFund> list = projectImplementationFundService.selectProjectImplementationFundList(projectImplementationFund);
        ExcelUtil<ProjectImplementationFund> util = new ExcelUtil<ProjectImplementationFund>(ProjectImplementationFund.class);
        util.exportExcel(response, list, "项目资金情况数据");
    }

    /**
     * 获取项目资金情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:implementationFund:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(projectImplementationFundService.selectProjectImplementationFundById(id));
    }

    /**
     * 新增项目资金情况
     */
    @PreAuthorize("@ss.hasPermi('system:implementationFund:add')")
    @Log(title = "项目资金情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectImplementationFund projectImplementationFund)
    {
        return toAjax(projectImplementationFundService.insertProjectImplementationFund(projectImplementationFund));
    }

    /**
     * 修改项目资金情况
     */
    @PreAuthorize("@ss.hasPermi('system:implementationFund:edit')")
    @Log(title = "项目资金情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectImplementationFund projectImplementationFund)
    {
        return toAjax(projectImplementationFundService.updateProjectImplementationFund(projectImplementationFund));
    }

    /**
     * 删除项目资金情况
     */
    @PreAuthorize("@ss.hasPermi('system:implementationFund:remove')")
    @Log(title = "项目资金情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(projectImplementationFundService.deleteProjectImplementationFundByIds(ids));
    }
}
