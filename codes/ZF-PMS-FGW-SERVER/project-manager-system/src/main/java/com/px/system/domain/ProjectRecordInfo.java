package com.px.system.domain;

import com.px.common.annotation.Excel;
import com.px.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 项目实施对象 project_record_info
 * 
 * @author 品讯科技
 * @date 2024-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectRecordInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String id;

    /** 项目id */
    @Excel(name = "项目id")
    private String projectId;

    /** 项目结束 */
    @Excel(name = "项目结束")
    private Boolean projectEnd;

    /** 调度计划名称 */
    @Excel(name = "调度计划名称")
    private String taskName;

    /** 调度任务期号 */
    @Excel(name = "调度任务期号")
    private String taskNo;

    /** 填报情况 */
    @Excel(name = "填报情况")
    private String fillFormInfo;

    /** 填报开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "填报开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskBeginTime;

    /** 填报截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "填报截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskEndTime;

    /** 开工情况 */
    @Excel(name = "开工情况")
    private String projectInfo;

    /** 截止本期累计完成投资 */
    @Excel(name = "截止本期累计完成投资")
    private BigDecimal jzbqljwctz = BigDecimal.valueOf(0);

    /** 当年累计完成投资 */
    @Excel(name = "当年累计完成投资")
    private BigDecimal dnljwctz = BigDecimal.valueOf(0);

    /** 当年投资完成率 */
    @Excel(name = "当年投资完成率")
    private BigDecimal dntzwcl = BigDecimal.valueOf(0);

    /** 本年度计划完成投资金额 */
    @Excel(name = "本年度计划完成投资金额")
    private BigDecimal bndjhwctzje = BigDecimal.valueOf(0);

    /** 本期完成投资 */
    @Excel(name = "本期完成投资")
    private BigDecimal completionOfInvestment;

    /** 申报重点项目 */
    @Excel(name = "申报重点项目")
    private String projectType;

    /**
     * 月度填报开关0否1是
     */
    private Boolean monthlyReportingSwitch;

    /** 是否完成立项(核准/备案)审批 */
    @Excel(name = "是否完成立项(核准/备案)审批")
    private Boolean filingsFlag;

    /** 是否完成可研审批 */
    @Excel(name = "是否完成可研审批")
    private Boolean feasibilityStudyFlag;

    /** 是否完成初设审批 */
    @Excel(name = "是否完成初设审批")
    private Boolean approvalFlag;

    /** 是否复工 */
    @Excel(name = "是否复工")
    private Boolean projectStartFlag;

    /** 应出勤施工人数 */
    @Excel(name = "应出勤施工人数")
    private Long planPeopleNum;

    /** 实际现场施工人数 */
    @Excel(name = "实际现场施工人数")
    private Long realPeopleNum;

    /** 存在问题及困难 */
    @Excel(name = "存在问题及困难")
    private String problemsAndDifficulties;

    /** 建设阶段 */
    @Excel(name = "建设阶段")
    private String constructionPhase;

    /** 项目进展情况 */
    @Excel(name = "项目进展情况")
    private String projectProgress;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 调度图片 */
    @Excel(name = "调度图片")
    private String attachmentInfo;

    /** 提交人id */
    @Excel(name = "提交人id")
    private Long userId;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordTime;

    /** 填报月份 */
    @Excel(name = "填报月份")
    private String reportMonth;

    /** 提交人姓名 */
    @Excel(name = "提交人姓名")
    private String userName;

    /** 提交人部门id */
    @Excel(name = "提交人部门id")
    private Long deptId;

    /** 项目实施资金 */
    private List<ProjectImplementationFund> projectImplementationFundList;
}
