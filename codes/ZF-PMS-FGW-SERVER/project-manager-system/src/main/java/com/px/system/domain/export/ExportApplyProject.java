package com.px.system.domain.export;

import com.px.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 申请库对象 apply_project
 *
 * @author 品讯科技
 * @date 2024-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExportApplyProject {
    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @Excel(name = "序号")
    private Integer index;

    /**
     * 主键Id
     */
    private String id;

    private String[] ids;

    /**
     * 项目审批代码
     */
    @Excel(name = "项目审批代码")
    private String projectCode;

    /**
     * 项目进度
     */
    @Excel(name = "项目进度")
    private String projectProgress;

    /**
     * 项目进展情况
     */
    @Excel(name = "项目进展情况")
    private String projectProgressInfo;

    /**
     * 进度状态
     */
    @Excel(name = "进度状态")
    private String progressStatus;

    /**
     * 项目结束
     */
    @Excel(name = "项目结束")
    private Integer projectEnd;

    /**
     * 项目结束日期
     */
    @Excel(name = "项目结束日期")
    private String projectEndDate;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String projectName;

    /**
     * 项目状态
     */
    @Excel(name = "项目状态")
    private String projectStatus;

    /**
     * 项目性质
     */
    @Excel(name = "项目性质")
    private String projectNature;

    /**
     * 项目业主单位
     */
    @Excel(name = "项目业主单位")
    private String proprietorDeptName;

    @Excel(name = "项目业主单位id")
    private String proprietorDeptId;

    /**
     * 项目隶属区域
     */
    @Excel(name = "项目隶属区域")
    private String areaName;

    @Excel(name = "项目隶属区域id")
    private String areaId;

    /**
     * 申报年份
     */
    @Excel(name = "申报年份")
    private String applyYear;

    /**
     * 争资争项意向
     */
    @Excel(name = "争资争项意向")
    private String applyIntention;

    /**
     * 总投资（万元）
     */
    @Excel(name = "总投资", readConverterExp = "万=元")
    private BigDecimal totalInvestment;

    /**
     * 计划投资
     */
    @Excel(name = "计划投资")
    private BigDecimal planInvestment;

    /**
     * 中央预算内资金
     */
    @Excel(name = "中央预算内资金")
    private BigDecimal cneterInverstment;

    /**
     * 责任单位
     */
    @Excel(name = "责任单位")
    private String mangerDeptName;

    /**
     * 建设地点
     */
    @Excel(name = "建设地点")
    private String projectAddr;

    /**
     * 立项类型
     */
    @Excel(name = "立项类型")
    private String projectApproval;

    /**
     * 所属行业
     */
    @Excel(name = "所属行业")
    private String industry;

    private String beginTime;

    /**
     * 计划开始时间
     */
    @Excel(name = "计划开始时间")
    private String planBeginTime;

    /**
     * 计划竣工时间
     */
    @Excel(name = "计划竣工时间")
    private String planEndTime;

    /**
     * 招标方式
     */
    @Excel(name = "招标方式")
    private String projectType;

    /**
     * 立项(核准/备案)审批文号
     */
    @Excel(name = "立项(核准/备案)审批文号")
    private String filingsCode;

    /**
     * 可研审批文号
     */
    @Excel(name = "可研审批文号")
    private String feasibilityStudyCode;

    /**
     * 初设审批文号
     */
    @Excel(name = "初设审批文号")
    private String approvalCode;

    /**
     * 用地性质
     */
    @Excel(name = "用地性质")
    private String landUseNature;

    /**
     * 市自然资源部门审核意见
     */
    @Excel(name = "市自然资源部门审核意见")
    private String checkLandFlag;

    /**
     * 建设规模及内容
     */
    @Excel(name = "建设规模及内容")
    private String projectInfo;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String projectRemark;

    /**
     * 项目联系人姓名
     */
    @Excel(name = "项目联系人姓名")
    private String contactsName;

    /**
     * 项目联系人电话
     */
    @Excel(name = "项目联系人电话")
    private String contactsPhone;

    /**
     * 责任单位联系人姓名(县区发改部门)
     */
    @Excel(name = "责任单位联系人姓名(县区发改部门)")
    private String managerContactsName;

    /**
     * 责任单位联系人手机号(县区发改部门)
     */
    @Excel(name = "责任单位联系人手机号(县区发改部门)")
    private String managerContactsPhone;

    /**
     * 包保责任领导姓名
     */
    @Excel(name = "包保责任领导姓名")
    private String managerUserName;

    /**
     * 包保责任领导职务
     */
    @Excel(name = "包保责任领导职务")
    private String managerUserDuties;

    /**
     * 资金类别
     */
    @Excel(name = "资金类别")
    private String moneyType;

    /**
     * 年度投资
     */
    @Excel(name = "年度投资")
    private BigDecimal annualInvestment;

    /**
     * 资金类型
     */
    @Excel(name = "资金类型")
    private String typeOfFunds;

    /**
     * 资金来源
     */
    @Excel(name = "资金来源")
    private String sourceOfFunds;

    /**
     * 建设性质
     */
    @Excel(name = "建设性质")
    private String natureOfConstruction;

    /**
     * 提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordTime;

    /**
     * 提交人姓名
     */
    @Excel(name = "提交人姓名")
    private String userName;

    /**
     * 建设阶段
     */
    @Excel(name = "建设阶段")
    private String constructionPhase;

    /**
     * 总投资-合计
     */
    @Excel(name = "总投资-合计")
    private BigDecimal ztzHj = BigDecimal.valueOf(0);


    @Excel(name = "年初至今累计完成投资额")
    private BigDecimal nczjljwctze = BigDecimal.valueOf(0);

    @Excel(name = "自开工累计完成投资")
    private BigDecimal zkgljwctz = BigDecimal.valueOf(0);

    @Excel(name = "年度计划投资")
    private BigDecimal yearInvestment = BigDecimal.valueOf(0);

    /**
     * 总投资-中央财政资金
     */
    @Excel(name = "总投资-中央财政资金")
    private BigDecimal ztzZy;

    /**
     * 总投资-申请市本级筹措
     */
    @Excel(name = "总投资-申请市本级筹措")
    private BigDecimal ztzBs;

    /**
     * 总投资-市场化及其他
     */
    @Excel(name = "总投资-市场化及其他")
    private BigDecimal ztzSc;

    /**
     * 当年计划-合计
     */
    @Excel(name = "当年计划-合计")
    private BigDecimal dnjhHj = BigDecimal.valueOf(0);

    /**
     * 累计到位-合计
     */
    @Excel(name = "累计到位-合计")
    private BigDecimal ljdwHj = BigDecimal.valueOf(0);

    /**
     * 累计到位-中央财政资金
     */
    @Excel(name = "累计到位-中央财政资金")
    private BigDecimal ljdwZy;

    /**
     * 累计到位-申请市本级筹措
     */
    @Excel(name = "累计到位-申请市本级筹措")
    private BigDecimal ljdwBs;

    /**
     * 累计到位-市场化及其他
     */
    @Excel(name = "累计到位-市场化及其他")
    private BigDecimal ljdwSc;

    /**
     * 累计完成-合计
     */
    @Excel(name = "累计完成-合计")
    private BigDecimal ljwcHj = BigDecimal.valueOf(0);

    /**
     * 预算内-合计
     */
    @Excel(name = "预算内-合计")
    private BigDecimal ysnHj = BigDecimal.valueOf(0);

    /**
     * 预算内-中央财政资金
     */
    @Excel(name = "预算内-中央财政资金")
    private BigDecimal ysnZy;

    /**
     * 预算内-申请市本级筹措
     */
    @Excel(name = "预算内-申请市本级筹措")
    private BigDecimal ysnBs;

    /**
     * 预算内-市场化及其他
     */
    @Excel(name = "预算内-市场化及其他")
    private BigDecimal ysnSc;

    private String reportMonth;

    private BigDecimal kgljztzbl = BigDecimal.valueOf(0);

    private BigDecimal completionOfInvestment;

    private BigDecimal ncljztzbl = BigDecimal.valueOf(0);

    private String problemsAndDifficulties;

    private Long planPeopleNum;

    private Long realPeopleNum;

    private String remark;

    private String xmjzqk;

    private String qldddqk;
}
