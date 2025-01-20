package com.px.system.controller;

import com.px.common.annotation.Log;
import com.px.common.core.controller.BaseController;
import com.px.common.core.domain.AjaxResult;
import com.px.common.core.domain.model.LoginUser;
import com.px.common.core.page.TableDataInfo;
import com.px.common.enums.BusinessType;
import com.px.common.utils.poi.ExcelUtil;
import com.px.system.domain.ProjectPlanInfo;
import com.px.system.domain.vo.ProjectPlanInfoVo;
import com.px.system.service.IProjectPlanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 全链条一览Controller
 * 
 * @author 品讯科技
 * @date 2024-08
 */
@RestController
@RequestMapping("/system/projectPlan")
public class ProjectPlanInfoController extends BaseController
{
    @Autowired
    private IProjectPlanInfoService projectPlanInfoService;

    /**
     * 查询全链条一览列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectPlan:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectPlanInfo projectPlanInfo)
    {
        startPage();
        List<ProjectPlanInfo> list = projectPlanInfoService.selectProjectPlanInfoList(projectPlanInfo);
        return getDataTable(list);
    }

    /**
     * 查询全链条一览列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectPlan:query')")
    @GetMapping("/query/{projectId}")
    public TableDataInfo query(@PathVariable("projectId") String projectId)
    {
        startPage();
        List<ProjectPlanInfoVo> list = projectPlanInfoService.selectProjectPlanInfo(projectId);
        return getDataTable(list);
    }

    /**
     * 获取全链条一览详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectPlan:query')")
    @GetMapping(value = "/total/{projectId}")
    public AjaxResult total(@PathVariable("projectId") String projectId)
    {
        return success(projectPlanInfoService.selectTotalProjectPlanInfoByProjectId(projectId));
    }

    /**
     * 导出全链条一览列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectPlan:export')")
    @Log(title = "全链条一览", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectPlanInfo projectPlanInfo)
    {
        List<ProjectPlanInfo> list = projectPlanInfoService.selectProjectPlanInfoList(projectPlanInfo);
        ExcelUtil<ProjectPlanInfo> util = new ExcelUtil<ProjectPlanInfo>(ProjectPlanInfo.class);
        util.exportExcel(response, list, "全链条一览数据");
    }

    /**
     * 获取全链条一览详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectPlan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(projectPlanInfoService.selectProjectPlanInfoById(id));
    }

    /**
     * 新增全链条一览
     */
    @PreAuthorize("@ss.hasPermi('system:projectPlan:add')")
    @Log(title = "全链条一览", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<ProjectPlanInfo> projectPlanInfoList)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return error("未获取到用户信息。");
        }
        return toAjax(projectPlanInfoService.add(loginUser, projectPlanInfoList));
    }

    /**
     * 修改全链条一览
     */
    @PreAuthorize("@ss.hasPermi('system:projectPlan:edit')")
    @Log(title = "全链条一览", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<ProjectPlanInfo> projectPlanInfoList)
    {
        return toAjax(projectPlanInfoService.edit(getLoginUser(), projectPlanInfoList));
    }

    /**
     * 删除全链条一览
     */
    @PreAuthorize("@ss.hasPermi('system:projectPlan:remove')")
    @Log(title = "全链条一览", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(projectPlanInfoService.deleteProjectPlanInfoByIds(ids));
    }

    /**
     * 审核及原因
     *
     * @param projectPlanInfo
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:projectPlan:auditOpinion')")
    @PostMapping("/auditOpinion")
    public AjaxResult auditOpinion(@RequestBody ProjectPlanInfo projectPlanInfo)
    {
        return success(projectPlanInfoService.auditOpinion(projectPlanInfo, getLoginUser()));
    }

}
