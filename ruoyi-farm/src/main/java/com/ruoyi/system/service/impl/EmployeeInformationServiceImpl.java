package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.WorkExperience;
import com.ruoyi.system.mapper.EmployeeInformationMapper;
import com.ruoyi.system.domain.EmployeeInformation;
import com.ruoyi.system.service.IEmployeeInformationService;

/**
 * 职工信息Service业务层处理
 * 
 * @author aw
 * @date 2024-07-03
 */
@Service
public class EmployeeInformationServiceImpl implements IEmployeeInformationService 
{
    @Autowired
    private EmployeeInformationMapper employeeInformationMapper;

    /**
     * 查询职工信息
     * 
     * @param id 职工信息主键
     * @return 职工信息
     */
    @Override
    public EmployeeInformation selectEmployeeInformationById(Long id)
    {
        return employeeInformationMapper.selectEmployeeInformationById(id);
    }

    /**
     * 查询职工信息列表
     * 
     * @param employeeInformation 职工信息
     * @return 职工信息
     */
    @Override
    public List<EmployeeInformation> selectEmployeeInformationList(EmployeeInformation employeeInformation)
    {
        return employeeInformationMapper.selectEmployeeInformationList(employeeInformation);
    }

    /**
     * 新增职工信息
     * 
     * @param employeeInformation 职工信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertEmployeeInformation(EmployeeInformation employeeInformation)
    {
        int rows = employeeInformationMapper.insertEmployeeInformation(employeeInformation);
        insertWorkExperience(employeeInformation);
        return rows;
    }

    /**
     * 修改职工信息
     * 
     * @param employeeInformation 职工信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateEmployeeInformation(EmployeeInformation employeeInformation)
    {
        employeeInformationMapper.deleteWorkExperienceByEid(employeeInformation.getId());
        insertWorkExperience(employeeInformation);
        return employeeInformationMapper.updateEmployeeInformation(employeeInformation);
    }

    /**
     * 批量删除职工信息
     * 
     * @param ids 需要删除的职工信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteEmployeeInformationByIds(Long[] ids)
    {
        employeeInformationMapper.deleteWorkExperienceByEids(ids);
        return employeeInformationMapper.deleteEmployeeInformationByIds(ids);
    }

    /**
     * 删除职工信息信息
     * 
     * @param id 职工信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteEmployeeInformationById(Long id)
    {
        employeeInformationMapper.deleteWorkExperienceByEid(id);
        return employeeInformationMapper.deleteEmployeeInformationById(id);
    }

    /**
     * 新增${subTable.functionName}信息
     * 
     * @param employeeInformation 职工信息对象
     */
    public void insertWorkExperience(EmployeeInformation employeeInformation)
    {
        List<WorkExperience> workExperienceList = employeeInformation.getWorkExperienceList();
        Long id = employeeInformation.getId();
        if (StringUtils.isNotNull(workExperienceList))
        {
            List<WorkExperience> list = new ArrayList<WorkExperience>();
            for (WorkExperience workExperience : workExperienceList)
            {
                workExperience.setEid(id);
                list.add(workExperience);
            }
            if (list.size() > 0)
            {
                employeeInformationMapper.batchWorkExperience(list);
            }
        }
    }
}
