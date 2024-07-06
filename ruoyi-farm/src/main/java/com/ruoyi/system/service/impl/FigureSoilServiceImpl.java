package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FigureSoilMapper;
import com.ruoyi.system.domain.FigureSoil;
import com.ruoyi.system.service.IFigureSoilService;

/**
 * 土壤管理Service业务层处理
 * 
 * @author aw
 * @date 2024-07-02
 */
@Service
public class FigureSoilServiceImpl implements IFigureSoilService 
{
    @Autowired
    private FigureSoilMapper figureSoilMapper;

    /**
     * 查询土壤管理
     * 
     * @param id 土壤管理主键
     * @return 土壤管理
     */
    @Override
    public FigureSoil selectFigureSoilById(Long id)
    {
        return figureSoilMapper.selectFigureSoilById(id);
    }

    /**
     * 查询土壤管理列表
     * 
     * @param figureSoil 土壤管理
     * @return 土壤管理
     */
    @Override
    public List<FigureSoil> selectFigureSoilList(FigureSoil figureSoil)
    {
        return figureSoilMapper.selectFigureSoilList(figureSoil);
    }

    /**
     * 新增土壤管理
     * 
     * @param figureSoil 土壤管理
     * @return 结果
     */
    @Override
    public int insertFigureSoil(FigureSoil figureSoil)
    {
        return figureSoilMapper.insertFigureSoil(figureSoil);
    }

    /**
     * 修改土壤管理
     * 
     * @param figureSoil 土壤管理
     * @return 结果
     */
    @Override
    public int updateFigureSoil(FigureSoil figureSoil)
    {
        return figureSoilMapper.updateFigureSoil(figureSoil);
    }

    /**
     * 批量删除土壤管理
     * 
     * @param ids 需要删除的土壤管理主键
     * @return 结果
     */
    @Override
    public int deleteFigureSoilByIds(Long[] ids)
    {
        return figureSoilMapper.deleteFigureSoilByIds(ids);
    }

    /**
     * 删除土壤管理信息
     * 
     * @param id 土壤管理主键
     * @return 结果
     */
    @Override
    public int deleteFigureSoilById(Long id)
    {
        return figureSoilMapper.deleteFigureSoilById(id);
    }
}
