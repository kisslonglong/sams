package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.EmployeeInformation;
import com.ruoyi.system.domain.WorkExperience;

/**
 * 职工信息Mapper接口
 * 
 * @author aw
 * @date 2024-07-03
 */
public interface EmployeeInformationMapper 
{

    public List<WorkExperience> selectWorkExperienceList(Long eid);

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
     * 删除职工信息
     * 
     * @param id 职工信息主键
     * @return 结果
     */
    public int deleteEmployeeInformationById(Long id);

    /**
     * 批量删除职工信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEmployeeInformationByIds(Long[] ids);

    /**
     * 批量删除${subTable.functionName}
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkExperienceByEids(Long[] ids);
    
    /**
     * 批量新增${subTable.functionName}
     * 
     * @param workExperienceList ${subTable.functionName}列表
     * @return 结果
     */
    public int batchWorkExperience(List<WorkExperience> workExperienceList);
    

    /**
     * 通过职工信息主键删除${subTable.functionName}信息
     * 
     * @param id 职工信息ID
     * @return 结果
     */
    public int deleteWorkExperienceByEid(Long id);
}
