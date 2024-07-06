package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.HarvestRecords;

/**
 * 农场信息Service接口
 * 
 * @author aw
 * @date 2024-07-03
 */
public interface IHarvestRecordsService 
{
    /**
     * 查询农场信息
     * 
     * @param id 农场信息主键
     * @return 农场信息
     */
    public HarvestRecords selectHarvestRecordsById(Long id);

    /**
     * 查询农场信息列表
     * 
     * @param harvestRecords 农场信息
     * @return 农场信息集合
     */
    public List<HarvestRecords> selectHarvestRecordsList(HarvestRecords harvestRecords);

    /**
     * 新增农场信息
     * 
     * @param harvestRecords 农场信息
     * @return 结果
     */
    public int insertHarvestRecords(HarvestRecords harvestRecords);

    /**
     * 修改农场信息
     * 
     * @param harvestRecords 农场信息
     * @return 结果
     */
    public int updateHarvestRecords(HarvestRecords harvestRecords);

    /**
     * 批量删除农场信息
     * 
     * @param ids 需要删除的农场信息主键集合
     * @return 结果
     */
    public int deleteHarvestRecordsByIds(Long[] ids);

    /**
     * 删除农场信息信息
     * 
     * @param id 农场信息主键
     * @return 结果
     */
    public int deleteHarvestRecordsById(Long id);
}
