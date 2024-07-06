package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CheckMapper;
import com.ruoyi.system.domain.Check;
import com.ruoyi.system.service.ICheckService;

/**
 * 员工打卡记录Service业务层处理
 * 
 * @author aw
 * @date 2024-07-04
 */
@Service
public class CheckServiceImpl implements ICheckService 
{
    @Autowired
    private CheckMapper checkMapper;

    /**
     * 查询员工打卡记录
     * 
     * @param id 员工打卡记录主键
     * @return 员工打卡记录
     */
    @Override
    public Check selectCheckById(Long id)
    {
        return checkMapper.selectCheckById(id);
    }

    /**
     * 查询员工打卡记录列表
     * 
     * @param check 员工打卡记录
     * @return 员工打卡记录
     */
    @Override
    public List<Check> selectCheckList(Check check)
    {
        return checkMapper.selectCheckList(check);
    }

    /**
     * 新增员工打卡记录
     * 
     * @param check 员工打卡记录
     * @return 结果
     */
    @Override
    public int insertCheck(Check check)
    {
        return checkMapper.insertCheck(check);
    }

    /**
     * 修改员工打卡记录
     * 
     * @param check 员工打卡记录
     * @return 结果
     */
    @Override
    public int updateCheck(Check check)
    {
        return checkMapper.updateCheck(check);
    }

    /**
     * 批量删除员工打卡记录
     * 
     * @param ids 需要删除的员工打卡记录主键
     * @return 结果
     */
    @Override
    public int deleteCheckByIds(Long[] ids)
    {
        return checkMapper.deleteCheckByIds(ids);
    }

    /**
     * 删除员工打卡记录信息
     * 
     * @param id 员工打卡记录主键
     * @return 结果
     */
    @Override
    public int deleteCheckById(Long id)
    {
        return checkMapper.deleteCheckById(id);
    }
}
