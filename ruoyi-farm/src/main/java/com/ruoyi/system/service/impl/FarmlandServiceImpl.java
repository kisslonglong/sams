package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FarmlandMapper;
import com.ruoyi.system.domain.Farmland;
import com.ruoyi.system.service.IFarmlandService;

/**
 * 农田信息管理Service业务层处理
 * 
 * @author aw
 * @date 2024-07-02
 */
@Service
public class FarmlandServiceImpl implements IFarmlandService 
{
    @Autowired
    private FarmlandMapper farmlandMapper;

    /**
     * 查询农田信息管理
     * 
     * @param id 农田信息管理主键
     * @return 农田信息管理
     */
    @Override
    public Farmland selectFarmlandById(Long id)
    {
        return farmlandMapper.selectFarmlandById(id);
    }

    /**
     * 查询农田信息管理列表
     * 
     * @param farmland 农田信息管理
     * @return 农田信息管理
     */
    @Override
    public List<Farmland> selectFarmlandList(Farmland farmland)
    {
        return farmlandMapper.selectFarmlandList(farmland);
    }

    /**
     * 新增农田信息管理
     * 
     * @param farmland 农田信息管理
     * @return 结果
     */
    @Override
    public int insertFarmland(Farmland farmland)
    {
        return farmlandMapper.insertFarmland(farmland);
    }

    /**
     * 修改农田信息管理
     * 
     * @param farmland 农田信息管理
     * @return 结果
     */
    @Override
    public int updateFarmland(Farmland farmland)
    {
        return farmlandMapper.updateFarmland(farmland);
    }

    /**
     * 批量删除农田信息管理
     * 
     * @param ids 需要删除的农田信息管理主键
     * @return 结果
     */
    @Override
    public int deleteFarmlandByIds(Long[] ids)
    {
        return farmlandMapper.deleteFarmlandByIds(ids);
    }

    /**
     * 删除农田信息管理信息
     * 
     * @param id 农田信息管理主键
     * @return 结果
     */
    @Override
    public int deleteFarmlandById(Long id)
    {
        return farmlandMapper.deleteFarmlandById(id);
    }
}
