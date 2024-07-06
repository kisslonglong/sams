package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Farmland;

/**
 * 农田信息管理Mapper接口
 * 
 * @author aw
 * @date 2024-07-02
 */
public interface FarmlandMapper 
{
    /**
     * 查询农田信息管理
     * 
     * @param id 农田信息管理主键
     * @return 农田信息管理
     */
    public Farmland selectFarmlandById(Long id);

    /**
     * 查询农田信息管理列表
     * 
     * @param farmland 农田信息管理
     * @return 农田信息管理集合
     */
    public List<Farmland> selectFarmlandList(Farmland farmland);

    /**
     * 新增农田信息管理
     * 
     * @param farmland 农田信息管理
     * @return 结果
     */
    public int insertFarmland(Farmland farmland);

    /**
     * 修改农田信息管理
     * 
     * @param farmland 农田信息管理
     * @return 结果
     */
    public int updateFarmland(Farmland farmland);

    /**
     * 删除农田信息管理
     * 
     * @param id 农田信息管理主键
     * @return 结果
     */
    public int deleteFarmlandById(Long id);

    /**
     * 批量删除农田信息管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFarmlandByIds(Long[] ids);
}
