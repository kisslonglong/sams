package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.HarvestRecords;

/**
 * 农场信息Mapper接口
 * 
 * @author aw
 * @date 2024-07-03
 */
public interface HarvestRecordsMapper 
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
     * 删除农场信息
     * 
     * @param id 农场信息主键
     * @return 结果
     */
    public int deleteHarvestRecordsById(Long id);

    /**
     * 批量删除农场信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHarvestRecordsByIds(Long[] ids);
}
