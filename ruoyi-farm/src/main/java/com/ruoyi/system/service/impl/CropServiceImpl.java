package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CropMapper;
import com.ruoyi.system.domain.Crop;
import com.ruoyi.system.service.ICropService;

/**
 * 作物管理Service业务层处理
 * 
 * @author aw
 * @date 2024-07-03
 */
@Service
public class CropServiceImpl implements ICropService 
{
    @Autowired
    private CropMapper cropMapper;

    /**
     * 查询作物管理
     * 
     * @param id 作物管理主键
     * @return 作物管理
     */
    @Override
    public Crop selectCropById(Long id)
    {
        return cropMapper.selectCropById(id);
    }

    /**
     * 查询作物管理列表
     * 
     * @param crop 作物管理
     * @return 作物管理
     */
    @Override
    public List<Crop> selectCropList(Crop crop)
    {
        return cropMapper.selectCropList(crop);
    }

    /**
     * 新增作物管理
     * 
     * @param crop 作物管理
     * @return 结果
     */
    @Override
    public int insertCrop(Crop crop)
    {
        return cropMapper.insertCrop(crop);
    }

    /**
     * 修改作物管理
     * 
     * @param crop 作物管理
     * @return 结果
     */
    @Override
    public int updateCrop(Crop crop)
    {
        return cropMapper.updateCrop(crop);
    }

    /**
     * 批量删除作物管理
     * 
     * @param ids 需要删除的作物管理主键
     * @return 结果
     */
    @Override
    public int deleteCropByIds(Long[] ids)
    {
        return cropMapper.deleteCropByIds(ids);
    }

    /**
     * 删除作物管理信息
     * 
     * @param id 作物管理主键
     * @return 结果
     */
    @Override
    public int deleteCropById(Long id)
    {
        return cropMapper.deleteCropById(id);
    }
}
