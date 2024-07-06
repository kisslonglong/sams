package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Check;

/**
 * 员工打卡记录Mapper接口
 * 
 * @author aw
 * @date 2024-07-04
 */
public interface CheckMapper 
{
    /**
     * 查询员工打卡记录
     * 
     * @param id 员工打卡记录主键
     * @return 员工打卡记录
     */
    public Check selectCheckById(Long id);

    /**
     * 查询员工打卡记录列表
     * 
     * @param check 员工打卡记录
     * @return 员工打卡记录集合
     */
    public List<Check> selectCheckList(Check check);

    /**
     * 新增员工打卡记录
     * 
     * @param check 员工打卡记录
     * @return 结果
     */
    public int insertCheck(Check check);

    /**
     * 修改员工打卡记录
     * 
     * @param check 员工打卡记录
     * @return 结果
     */
    public int updateCheck(Check check);

    /**
     * 删除员工打卡记录
     * 
     * @param id 员工打卡记录主键
     * @return 结果
     */
    public int deleteCheckById(Long id);

    /**
     * 批量删除员工打卡记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCheckByIds(Long[] ids);
}
