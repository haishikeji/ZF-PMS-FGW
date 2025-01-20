package com.px.system.controller;

import com.px.common.annotation.Log;
import com.px.common.core.controller.BaseController;
import com.px.common.core.domain.AjaxResult;
import com.px.common.core.domain.model.LoginUser;
import com.px.common.core.page.TableDataInfo;
import com.px.common.enums.BusinessType;
import com.px.common.utils.poi.ExcelUtil;
import com.px.system.domain.ProjectRecordInfo;
import com.px.system.service.IProjectRecordInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 项目实施Controller
 *
 * @author 品讯科技
 * @date 2024-08
 */
@RestController
@RequestMapping("/system/projectRecord")
public class ProjectRecordInfoController extends BaseController {
    @Autowired
    private IProjectRecordInfoService projectRecordInfoService;

    @PreAuthorize("@ss.hasPermi('system:projectRecord:add')")
    @PostMapping("/addTaskRecord")
    public AjaxResult addTaskRecord(@RequestBody ProjectRecordInfo projectRecordInfo) {
        return projectRecordInfoService.addTaskRecord(projectRecordInfo);
    }

    /**
     * 查询项目实施列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectRecordInfo projectRecordInfo) {
        startPage();
        List<ProjectRecordInfo> list = projectRecordInfoService.selectProjectRecordInfoList(projectRecordInfo);
        return getDataTable(list);
    }

    /**
     * 查询项目实施历史调度列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectRecord:query')")
    @GetMapping("/history")
    public TableDataInfo history(ProjectRecordInfo projectRecordInfo) {
        startPage();
        List<ProjectRecordInfo> list = projectRecordInfoService.selectProjectRecordInfoHistoryList(projectRecordInfo);
        return getDataTable(list);
    }

    /**
     * 导出项目实施列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectRecord:export')")
    @Log(title = "项目实施", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectRecordInfo projectRecordInfo) {
        List<ProjectRecordInfo> list = projectRecordInfoService.selectProjectRecordInfoList(projectRecordInfo);
        ExcelUtil<ProjectRecordInfo> util = new ExcelUtil<ProjectRecordInfo>(ProjectRecordInfo.class);
        util.exportExcel(response, list, "项目实施数据");
    }

    /**
     * 获取项目实施详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(projectRecordInfoService.selectProjectRecordInfoById(id));
    }

    /**
     * 获取项投资金额详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectRecord:query')")
    @GetMapping(value = "/getFundInfo/{projectId}/{id}")
    public AjaxResult getFundInfo(@PathVariable("projectId") String projectId, @PathVariable("id") String id) {
        return success(projectRecordInfoService.getFundInfo(projectId, id));
    }

    /**
     * 新增项目实施
     */
    @PreAuthorize("@ss.hasPermi('system:projectRecord:add')")
    @Log(title = "项目实施", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectRecordInfo projectRecordInfo) {
        return toAjax(projectRecordInfoService.add(getLoginUser(), projectRecordInfo));
    }

    /**
     * 修改项目实施
     */
    @PreAuthorize("@ss.hasPermi('system:projectRecord:edit')")
    @Log(title = "项目实施", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectRecordInfo projectRecordInfo) {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return error("未获取到登录信息");
        }
        return toAjax(projectRecordInfoService.edit(loginUser, projectRecordInfo));
    }

    /**
     * 删除项目实施
     */
    @PreAuthorize("@ss.hasPermi('system:projectRecord:remove')")
    @Log(title = "项目实施", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(projectRecordInfoService.deleteProjectRecordInfoByIds(ids));
    }
}
