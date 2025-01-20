package com.px.system.mapper;

import com.px.system.domain.vo.Metadata;

import java.util.List;
import java.util.Map;

/**
 * 大屏Mapper接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface LargeScreenMapper {

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
    List<Metadata> xmtjByMonth();

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
     * 单位投资金额top10排行榜(列表)
     *
     * @return
     */
    List<Map<String, Object>> xmyjtj();

    Map<String, Object> getYjxmsByXz(String deptId);

    /**
     * 单位投资金额top10排行榜(列表)
     *
     * @return
     */
    List<Map<String, Object>> lxwzfjl();

    /**
     * 单位投资金额top10排行榜(列表)
     *
     * @return
     */
    List<Map<String, Object>> sswzfjl();
    List<Long> ptxmByMonth();
    List<Long> quzdxmByMonth();
    List<Long> shizdxmByMonth();
    List<Long> shengzdxmByMonth();
    List<Map<String, Object>> ndqqqk();
}
