package com.px.system.domain;

import com.px.common.annotation.Excel;
import com.px.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 项目问题上报对象 project_problem_reporting
 *
 * @author 品讯科技
 * @date 2024-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectProblemReporting extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 每日巡查id
     */
    @Excel(name = "每日巡查id")
    private String dailyInspectionId;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String projectName;

    /**
     * 问题
     */
    @Excel(name = "问题")
    private String problem;

    /**
     * 问题图片路径
     */
    @Excel(name = "问题图片路径")
    private String problemPicturePath;

    /** 责任部门 */
    @Excel(name = "责任部门")
    private String responsibleDeptName;

    /** 责任部门id */
    @Excel(name = "责任部门id")
    private String responsibleDeptId;

    /**
     * 责任部门处理问题信息
     */
    @Excel(name = "责任部门处理问题信息")
    private String responsibleDeptProblemInformation;

    @Excel(name = "问题图片路径1")
    private String problemPicturePath2;
}
