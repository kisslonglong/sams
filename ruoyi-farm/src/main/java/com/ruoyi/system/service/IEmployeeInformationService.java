package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.EmployeeInformation;

/**
 * 职工信息Service接口
 * 
 * @author aw
 * @date 2024-07-03
 */
public interface IEmployeeInformationService 
{
    /**
     * 查询职工信息
     * 
     * @param id 职工信息主键
     * @return 职工信息
     */
    public EmployeeInformation selectEmployeeInformationById(Long id);

    /**
     * 查询职工信息列表
     * 
     * @param employeeInformation 职工信息
     * @return 职工信息集合
     */
    public List<EmployeeInformation> selectEmployeeInformationList(EmployeeInformation employeeInformation);

    /**
     * 新增职工信息
     * 
     * @param employeeInformation 职工信息
     * @return 结果
     */
    public int insertEmployeeInformation(EmployeeInformation employeeInformation);

    /**
     * 修改职工信息
     * 
     * @param employeeInformation 职工信息
     * @return 结果
     */
    public int updateEmployeeInformation(EmployeeInformation employeeInformation);

    /**
     * 批量删除职工信息
     * 
     * @param ids 需要删除的职工信息主键集合
     * @return 结果
     */
    public int deleteEmployeeInformationByIds(Long[] ids);

    /**
     * 删除职工信息信息
     * 
     * @param id 职工信息主键
     * @return 结果
     */
    public int deleteEmployeeInformationById(Long id);
}
