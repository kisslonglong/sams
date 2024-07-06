package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Salary;

/**
 * 员工薪资管理Service接口
 * 
 * @author aw
 * @date 2024-07-04
 */
public interface ISalaryService 
{
    /**
     * 查询员工薪资管理
     * 
     * @param id 员工薪资管理主键
     * @return 员工薪资管理
     */
    public Salary selectSalaryById(Long id);

    /**
     * 查询员工薪资管理列表
     * 
     * @param salary 员工薪资管理
     * @return 员工薪资管理集合
     */
    public List<Salary> selectSalaryList(Salary salary);

    /**
     * 新增员工薪资管理
     * 
     * @param salary 员工薪资管理
     * @return 结果
     */
    public int insertSalary(Salary salary);

    /**
     * 修改员工薪资管理
     * 
     * @param salary 员工薪资管理
     * @return 结果
     */
    public int updateSalary(Salary salary);

    /**
     * 批量删除员工薪资管理
     * 
     * @param ids 需要删除的员工薪资管理主键集合
     * @return 结果
     */
    public int deleteSalaryByIds(Long[] ids);

    /**
     * 删除员工薪资管理信息
     * 
     * @param id 员工薪资管理主键
     * @return 结果
     */
    public int deleteSalaryById(Long id);
}
