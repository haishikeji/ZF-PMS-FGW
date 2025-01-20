package com.px.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.px.system.mapper.MoneyRecordInfoMapper;
import com.px.system.domain.MoneyRecordInfo;
import com.px.system.service.IMoneyRecordInfoService;

/**
 * 投资情况Service业务层处理
 * 
 * @author 品讯科技
 * @date 2024-08
 */
@Service
public class MoneyRecordInfoServiceImpl implements IMoneyRecordInfoService 
{
    @Autowired
    private MoneyRecordInfoMapper moneyRecordInfoMapper;

    /**
     * 查询投资情况
     * 
     * @param id 投资情况主键
     * @return 投资情况
     */
    @Override
    public MoneyRecordInfo selectMoneyRecordInfoById(Long id)
    {
        return moneyRecordInfoMapper.selectMoneyRecordInfoById(id);
    }

    /**
     * 查询投资情况列表
     * 
     * @param moneyRecordInfo 投资情况
     * @return 投资情况
     */
    @Override
    public List<MoneyRecordInfo> selectMoneyRecordInfoList(MoneyRecordInfo moneyRecordInfo)
    {
        return moneyRecordInfoMapper.selectMoneyRecordInfoList(moneyRecordInfo);
    }

    /**
     * 新增投资情况
     * 
     * @param moneyRecordInfo 投资情况
     * @return 结果
     */
    @Override
    public int insertMoneyRecordInfo(MoneyRecordInfo moneyRecordInfo)
    {
        return moneyRecordInfoMapper.insertMoneyRecordInfo(moneyRecordInfo);
    }

    /**
     * 修改投资情况
     * 
     * @param moneyRecordInfo 投资情况
     * @return 结果
     */
    @Override
    public int updateMoneyRecordInfo(MoneyRecordInfo moneyRecordInfo)
    {
        return moneyRecordInfoMapper.updateMoneyRecordInfo(moneyRecordInfo);
    }

    /**
     * 批量删除投资情况
     * 
     * @param ids 需要删除的投资情况主键
     * @return 结果
     */
    @Override
    public int deleteMoneyRecordInfoByIds(Long[] ids)
    {
        return moneyRecordInfoMapper.deleteMoneyRecordInfoByIds(ids);
    }

    /**
     * 删除投资情况信息
     * 
     * @param id 投资情况主键
     * @return 结果
     */
    @Override
    public int deleteMoneyRecordInfoById(Long id)
    {
        return moneyRecordInfoMapper.deleteMoneyRecordInfoById(id);
    }
}
