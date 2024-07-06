package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.utils.RoleUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Salary;
import com.ruoyi.system.service.ISalaryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 员工薪资管理Controller
 * 
 * @author aw
 * @date 2024-07-04
 */
@RestController
@RequestMapping("/system/salary")
public class SalaryController extends BaseController
{
    @Autowired
    private ISalaryService salaryService;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询员工薪资管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:salary:list')")
    @GetMapping("/list")
    public TableDataInfo list(Salary salary)
    {
        startPage();
        if (RoleUtils.RoleKeyIsUser("jf")){
            salary.setUserId(getUserId());
        }
        List<Salary> list = salaryService.selectSalaryList(salary);
        return getDataTable(list);
    }

    /**
     * 导出员工薪资管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:salary:export')")
    @Log(title = "员工薪资管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Salary salary)
    {
        List<Salary> list = salaryService.selectSalaryList(salary);
        ExcelUtil<Salary> util = new ExcelUtil<Salary>(Salary.class);
        util.exportExcel(response, list, "员工薪资管理数据");
    }

    /**
     * 获取员工薪资管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:salary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(salaryService.selectSalaryById(id));
    }

    /**
     * 新增员工薪资管理
     */
    @PreAuthorize("@ss.hasPermi('system:salary:add')")
    @Log(title = "员工薪资管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Salary salary)
    {
        SysUser user = sysUserService.selectUserById(salary.getUserId());
        salary.setUserId(user.getUserId());
        salary.setNickName(user.getNickName());
        return toAjax(salaryService.insertSalary(salary));
    }

    /**
     * 修改员工薪资管理
     */
    @PreAuthorize("@ss.hasPermi('system:salary:edit')")
    @Log(title = "员工薪资管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Salary salary)
    {
        return toAjax(salaryService.updateSalary(salary));
    }

    /**
     * 删除员工薪资管理
     */
    @PreAuthorize("@ss.hasPermi('system:salary:remove')")
    @Log(title = "员工薪资管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(salaryService.deleteSalaryByIds(ids));
    }
}
