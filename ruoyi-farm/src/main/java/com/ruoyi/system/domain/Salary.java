package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工薪资管理对象 salary
 * 
 * @author aw
 * @date 2024-07-04
 */
public class Salary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickName;

    /** 基础工资 */
    @Excel(name = "基础工资")
    private BigDecimal baseWage;

    /** 绩效工资 */
    @Excel(name = "绩效工资")
    private BigDecimal performanceSalary;

    /** 五险一金 */
    @Excel(name = "五险一金")
    private BigDecimal fiveInsurance;

    /** 合计 */
    @Excel(name = "合计")
    private BigDecimal sum;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setBaseWage(BigDecimal baseWage) 
    {
        this.baseWage = baseWage;
    }

    public BigDecimal getBaseWage() 
    {
        return baseWage;
    }
    public void setPerformanceSalary(BigDecimal performanceSalary) 
    {
        this.performanceSalary = performanceSalary;
    }

    public BigDecimal getPerformanceSalary() 
    {
        return performanceSalary;
    }
    public void setFiveInsurance(BigDecimal fiveInsurance) 
    {
        this.fiveInsurance = fiveInsurance;
    }

    public BigDecimal getFiveInsurance() 
    {
        return fiveInsurance;
    }
    public void setSum(BigDecimal sum) 
    {
        this.sum = sum;
    }

    public BigDecimal getSum() 
    {
        return sum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("nickName", getNickName())
            .append("baseWage", getBaseWage())
            .append("performanceSalary", getPerformanceSalary())
            .append("fiveInsurance", getFiveInsurance())
            .append("sum", getSum())
            .toString();
    }
}
