package com.px.system.service;

import com.px.common.core.domain.model.LoginUser;

import java.util.List;
import java.util.Map;

/**
 * 大屏Service接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface ILargeScreenService {

    /**
     * 三个库数量统计(饼状图)库名+数量
     *
     * @return
     */
    List<Map<String, Long>> xmkCount();

    /**
     * 资金使用类型占比统计(饼状图)
     *
     * @return
     */
    List<Map<String, Long>> zjsylx();

    /**
     * 普通、区重点、市重点、省重点项目统计(柱状图)总数
     *
     * @return
     */
    List<Map<String, Long>> xmtj();


    /**
     * 每月各类型项目趋势图(放中间)
     *
     * @return
     */
    Map<String, List> xmtjByMonth();

    /**
     * 单位投资土地top10排行榜(列表)
     *
     * @return
     */
    List<Map<String, Long>> dwtztd();

    /**
     * 单位投资金额top10排行榜(列表)
     *
     * @return
     */
    List<Map<String, Long>> dwtzje();

    /**
     * 项目预警统计
     */
    List<Map<String, Object>> xmyjtj();

    /**
     * 连续三天无走访记录列表
     */
    List<Map<String, Object>> lxwzfjl();

    /**
     * 实时走访记录列表
     */
    List<Map<String, Object>> sswzfjl();

    /**
     * 12个月完成项目数
     */
    List<Map<String, Object>> wcxms();

    /**
     * 项目完成情况统计
     */
    List<Map<String, Object>> xmwcqktj(LoginUser loginUser);

    /**
     * 新建县乡镇项目统计
     */
    List<Map<String, Object>> xzxmqktj(LoginUser loginUser);

    /**
     * 年度全区情况
     *
     * @return
     */
    List<Map<String, Object>> ndqqqk();
}
