package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Crop;

/**
 * 作物管理Mapper接口
 * 
 * @author aw
 * @date 2024-07-03
 */
public interface CropMapper 
{
    /**
     * 查询作物管理
     * 
     * @param id 作物管理主键
     * @return 作物管理
     */
    public Crop selectCropById(Long id);

    /**
     * 查询作物管理列表
     * 
     * @param crop 作物管理
     * @return 作物管理集合
     */
    public List<Crop> selectCropList(Crop crop);

    /**
     * 新增作物管理
     * 
     * @param crop 作物管理
     * @return 结果
     */
    public int insertCrop(Crop crop);

    /**
     * 修改作物管理
     * 
     * @param crop 作物管理
     * @return 结果
     */
    public int updateCrop(Crop crop);

    /**
     * 删除作物管理
     * 
     * @param id 作物管理主键
     * @return 结果
     */
    public int deleteCropById(Long id);

    /**
     * 批量删除作物管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCropByIds(Long[] ids);
}
