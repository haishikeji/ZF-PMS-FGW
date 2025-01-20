package com.px.system.domain;

import com.px.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 全链条一览对象 project_plan_info
 * 
 * @author 品讯科技
 * @date 2024-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectPlanInfo {
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String id;

    /** 项目id */
    @Excel(name = "项目id")
    private String projectId;

    /** 资金来源 */
    @Excel(name = "资金来源")
    private String sourceOfFunds;

    /** 投资金额(万元) */
    @Excel(name = "投资金额(万元)")
    private BigDecimal yearInvestment;

    /** 内容 */
    @Excel(name = "内容")
    private String planInfo;

    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM")
    private Date planTime;

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

    /**
     * 状态0不同意1同意
     */
    @Excel(name = "状态0不同意1同意")
    private Integer status;

    /**
     * 审核意见
     */
    @Excel(name = "审核意见")
    private String auditOpinion;

    /**
     * 审核用户
     */
    @Excel(name = "审核用户")
    private String auditUserName;

    /**
     * 审核用户id
     */
    @Excel(name = "审核用户id")
    private String auditUserId;

    /**
     * 审核操作时间
     */
    @Excel(name = "审核操作时间")
    private String operatingTime;
}
