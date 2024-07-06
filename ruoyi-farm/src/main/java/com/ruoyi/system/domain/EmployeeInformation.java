package com.ruoyi.system.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 职工信息对象 employee_information
 * 
 * @author aw
 * @date 2024-07-03
 */
public class EmployeeInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickName;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contactInformation;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    @Excel(name = "姓名")
    private String name;


    private String resumeAttachments;


    public String getResumeAttachments() {
        return resumeAttachments;
    }

    public void setResumeAttachments(String resumeAttachments) {
        this.resumeAttachments = resumeAttachments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** $table.subTable.functionName信息 */
    private List<WorkExperience> workExperienceList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setContactInformation(String contactInformation) 
    {
        this.contactInformation = contactInformation;
    }

    public String getContactInformation() 
    {
        return contactInformation;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public List<WorkExperience> getWorkExperienceList()
    {
        return workExperienceList;
    }

    public void setWorkExperienceList(List<WorkExperience> workExperienceList)
    {
        this.workExperienceList = workExperienceList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("nickName", getNickName())
            .append("sex", getSex())
            .append("contactInformation", getContactInformation())
            .append("userId", getUserId())
            .append("workExperienceList", getWorkExperienceList())
            .toString();
    }
}
