package com.px.system.service;

import com.px.common.core.domain.model.LoginUser;
import com.px.system.domain.export.ExportApplyProject;

import java.util.List;

/**
 * 申请库Service接口
 * 
 * @author 品讯科技
 * @date 2024-08
 */
public interface IProjectExportService
{

    public List<ExportApplyProject> exportProjectRecordInfo(LoginUser loginUser, ExportApplyProject exportApplyProject);

    /**
     * 导出项目列表模板
     *
     * @param loginUser
     * @param exportApplyProject
     * @return
     */
    public List<ExportApplyProject> exportApplyProjectList(LoginUser loginUser, ExportApplyProject exportApplyProject);
}
