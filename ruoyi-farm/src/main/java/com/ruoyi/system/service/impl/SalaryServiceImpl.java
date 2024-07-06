package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SalaryMapper;
import com.ruoyi.system.domain.Salary;
import com.ruoyi.system.service.ISalaryService;

/**
 * 员工薪资管理Service业务层处理
 * 
 * @author aw
 * @date 2024-07-04
 */
@Service
public class SalaryServiceImpl implements ISalaryService 
{
    @Autowired
    private SalaryMapper salaryMapper;

    /**
     * 查询员工薪资管理
     * 
     * @param id 员工薪资管理主键
     * @return 员工薪资管理
     */
    @Override
    public Salary selectSalaryById(Long id)
    {
        return salaryMapper.selectSalaryById(id);
    }

    /**
     * 查询员工薪资管理列表
     * 
     * @param salary 员工薪资管理
     * @return 员工薪资管理
     */
    @Override
    public List<Salary> selectSalaryList(Salary salary)
    {
        return salaryMapper.selectSalaryList(salary);
    }

    /**
     * 新增员工薪资管理
     * 
     * @param salary 员工薪资管理
     * @return 结果
     */
    @Override
    public int insertSalary(Salary salary)
    {
        return salaryMapper.insertSalary(salary);
    }

    /**
     * 修改员工薪资管理
     * 
     * @param salary 员工薪资管理
     * @return 结果
     */
    @Override
    public int updateSalary(Salary salary)
    {
        return salaryMapper.updateSalary(salary);
    }

    /**
     * 批量删除员工薪资管理
     * 
     * @param ids 需要删除的员工薪资管理主键
     * @return 结果
     */
    @Override
    public int deleteSalaryByIds(Long[] ids)
    {
        return salaryMapper.deleteSalaryByIds(ids);
    }

    /**
     * 删除员工薪资管理信息
     * 
     * @param id 员工薪资管理主键
     * @return 结果
     */
    @Override
    public int deleteSalaryById(Long id)
    {
        return salaryMapper.deleteSalaryById(id);
    }
}
