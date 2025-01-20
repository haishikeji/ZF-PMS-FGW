package com.px.system.domain;

import com.px.common.annotation.Excel;
import com.px.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 项目每日巡查对象 project_daily_inspection
 * 
 * @author 品讯科技
 * @date 2024-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectDailyInspection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String id;

    /** 申请库项目id */
    @Excel(name = "申请库项目id")
    private String applyProjectId;

    /** 申请库项目名称 */
    @Excel(name = "申请库项目名称")
    private String applyProjectName;

    /** 申报日期 */
    @Excel(name = "申报日期")
    private String declarationDate;

    /** 上报人id */
    @Excel(name = "上报人id")
    private String userId;

    /** 上报人名称 */
    @Excel(name = "上报人名称")
    private String userName;

    /** 施工人数 */
    @Excel(name = "施工人数")
    private Integer numberOfWorkers;

    /** 现场照片路径 */
    @Excel(name = "现场照片路径")
    private String scenePhotoPath;

    /** 发生时间 */
    @Excel(name = "发生时间")
    private String occurrenceTime;

    /** 问题类型 */
    @Excel(name = "问题类型")
    private String problemType;

    /** 问题来源 */
    @Excel(name = "问题来源")
    private String problemSource;

    /** 责任部门 */
    @Excel(name = "责任部门")
    private String responsibleDeptName;

    /** 责任部门id */
    private String responsibleDeptId;

    /** 调度领导姓名 */
    @Excel(name = "调度领导姓名")
    private String dispatchLeaderName;

    /** 调度领导id */
    private String dispatchLeaderId;

    /** 配合部门 */
    @Excel(name = "配合部门")
    private String cooperateWithTheDeptName;

    /** 配合部门id */
    private String cooperateWithTheDeptId;

    /** 项目问题上报 */
    private List<ProjectProblemReporting> projectProblemReportingList;

    /** 项目物料进场信息 */
    private List<ProjectMaterialApproach> projectMaterialApproachList;

    @Excel(name = "现场照片路径2")
    private String scenePhotoPath2;

    private Date beginDate;

    private Date endDate;

}
