package com.px.system.mapper;

import java.util.List;
import com.px.system.domain.MoneyRecordInfo;

/**
 * 投资情况Mapper接口
 * 
 * @author 品讯科技
 * @date 2024-08
 */
public interface MoneyRecordInfoMapper 
{
    /**
     * 查询投资情况
     * 
     * @param id 投资情况主键
     * @return 投资情况
     */
    public MoneyRecordInfo selectMoneyRecordInfoById(Long id);

    /**
     * 查询投资情况列表
     * 
     * @param moneyRecordInfo 投资情况
     * @return 投资情况集合
     */
    public List<MoneyRecordInfo> selectMoneyRecordInfoList(MoneyRecordInfo moneyRecordInfo);

    /**
     * 新增投资情况
     * 
     * @param moneyRecordInfo 投资情况
     * @return 结果
     */
    public int insertMoneyRecordInfo(MoneyRecordInfo moneyRecordInfo);

    /**
     * 修改投资情况
     * 
     * @param moneyRecordInfo 投资情况
     * @return 结果
     */
    public int updateMoneyRecordInfo(MoneyRecordInfo moneyRecordInfo);

    /**
     * 删除投资情况
     * 
     * @param id 投资情况主键
     * @return 结果
     */
    public int deleteMoneyRecordInfoById(Long id);

    /**
     * 批量删除投资情况
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMoneyRecordInfoByIds(Long[] ids);
}
