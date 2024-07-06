package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FigureSoil;

/**
 * 土壤管理Mapper接口
 * 
 * @author aw
 * @date 2024-07-02
 */
public interface FigureSoilMapper 
{
    /**
     * 查询土壤管理
     * 
     * @param id 土壤管理主键
     * @return 土壤管理
     */
    public FigureSoil selectFigureSoilById(Long id);

    /**
     * 查询土壤管理列表
     * 
     * @param figureSoil 土壤管理
     * @return 土壤管理集合
     */
    public List<FigureSoil> selectFigureSoilList(FigureSoil figureSoil);

    /**
     * 新增土壤管理
     * 
     * @param figureSoil 土壤管理
     * @return 结果
     */
    public int insertFigureSoil(FigureSoil figureSoil);

    /**
     * 修改土壤管理
     * 
     * @param figureSoil 土壤管理
     * @return 结果
     */
    public int updateFigureSoil(FigureSoil figureSoil);

    /**
     * 删除土壤管理
     * 
     * @param id 土壤管理主键
     * @return 结果
     */
    public int deleteFigureSoilById(Long id);

    /**
     * 批量删除土壤管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFigureSoilByIds(Long[] ids);
}
