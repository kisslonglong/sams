package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.HarvestRecordsMapper;
import com.ruoyi.system.domain.HarvestRecords;
import com.ruoyi.system.service.IHarvestRecordsService;

/**
 * 农场信息Service业务层处理
 * 
 * @author aw
 * @date 2024-07-03
 */
@Service
public class HarvestRecordsServiceImpl implements IHarvestRecordsService 
{
    @Autowired
    private HarvestRecordsMapper harvestRecordsMapper;

    /**
     * 查询农场信息
     * 
     * @param id 农场信息主键
     * @return 农场信息
     */
    @Override
    public HarvestRecords selectHarvestRecordsById(Long id)
    {
        return harvestRecordsMapper.selectHarvestRecordsById(id);
    }

    /**
     * 查询农场信息列表
     * 
     * @param harvestRecords 农场信息
     * @return 农场信息
     */
    @Override
    public List<HarvestRecords> selectHarvestRecordsList(HarvestRecords harvestRecords)
    {
        return harvestRecordsMapper.selectHarvestRecordsList(harvestRecords);
    }

    /**
     * 新增农场信息
     * 
     * @param harvestRecords 农场信息
     * @return 结果
     */
    @Override
    public int insertHarvestRecords(HarvestRecords harvestRecords)
    {
        return harvestRecordsMapper.insertHarvestRecords(harvestRecords);
    }

    /**
     * 修改农场信息
     * 
     * @param harvestRecords 农场信息
     * @return 结果
     */
    @Override
    public int updateHarvestRecords(HarvestRecords harvestRecords)
    {
        return harvestRecordsMapper.updateHarvestRecords(harvestRecords);
    }

    /**
     * 批量删除农场信息
     * 
     * @param ids 需要删除的农场信息主键
     * @return 结果
     */
    @Override
    public int deleteHarvestRecordsByIds(Long[] ids)
    {
        return harvestRecordsMapper.deleteHarvestRecordsByIds(ids);
    }

    /**
     * 删除农场信息信息
     * 
     * @param id 农场信息主键
     * @return 结果
     */
    @Override
    public int deleteHarvestRecordsById(Long id)
    {
        return harvestRecordsMapper.deleteHarvestRecordsById(id);
    }
}
