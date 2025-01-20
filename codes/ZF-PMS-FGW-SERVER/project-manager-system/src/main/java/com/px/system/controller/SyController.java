package com.px.system.controller;

import com.px.common.core.controller.BaseController;
import com.px.common.core.domain.AjaxResult;
import com.px.system.service.IApplyProjectService;
import com.px.system.service.IProjectPlanInfoService;
import com.px.system.service.IProjectRecordInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页Controller
 *
 * @author 品讯科技
 * @date 2024-08
 */
@RestController
@RequestMapping("/system/sy")
public class SyController extends BaseController {
    @Autowired
    private IApplyProjectService applyProjectService;
    @Autowired
    private IProjectPlanInfoService projectPlanInfoService;
    @Autowired
    private IProjectRecordInfoService projectRecordInfoService;

    /**
     * 查询首页列表
     */
    @GetMapping("/list")
    public AjaxResult list() {
        Map<String, Long> result = new HashMap<>();
        String deptId = getLoginUser().getDeptId().toString();
        // 项目报送
        result.put("projectSubmission", applyProjectService.countProjectSubmission(deptId));
        // 项目调度
        result.put("projectScheduling", projectRecordInfoService.countProjectRecord(deptId));
        // 全链路填报
        result.put("fullLink", projectPlanInfoService.countProjectPlan(deptId));
        // 项目完成
        result.put("projectCompletion", applyProjectService.countProjectCompletion(deptId));
        return success(result);
    }
}
