package com.ruoyi.system.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.mapper.EmployeeInformationMapper;
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
import com.ruoyi.system.domain.EmployeeInformation;
import com.ruoyi.system.service.IEmployeeInformationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 职工信息Controller
 * 
 * @author aw
 * @date 2024-07-03
 */
@RestController
@RequestMapping("/system/zg_information")
public class EmployeeInformationController extends BaseController
{
    @Autowired
    private IEmployeeInformationService employeeInformationService;

    @Autowired
    private EmployeeInformationMapper employeeInformationMapper;

    @Autowired
    private ISysUserService sysUserService;


    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response){
        ExcelUtil<EmployeeInformation> util = new ExcelUtil<EmployeeInformation>(EmployeeInformation.class);
         util.importTemplateExcel(response, "用户数据");
    }

    @PreAuthorize("@ss.hasPermi('system:zg_information:add')")

    @PostMapping("/import")
    public AjaxResult  importEmployeeInformation(MultipartFile file) throws IOException {
        ExcelUtil<EmployeeInformation> util = new ExcelUtil<EmployeeInformation>(EmployeeInformation.class);
        List<EmployeeInformation> list = util.importExcel(file.getInputStream());
        AtomicLong count = new AtomicLong();
        list.forEach(employeeInformation1 -> {
            if (employeeInformationService.insertEmployeeInformation(employeeInformation1)>0){
                count.getAndIncrement();
            }

        });
        if (count.get()>0){
            return AjaxResult.success("导入"+count+"条成功");
        }else {
            return AjaxResult.error("导入失败");
        }



    }

    @GetMapping("/staffList")
    @PreAuthorize("@ss.hasPermi('system:zg_information:query')")
    public AjaxResult staffList(){
      return AjaxResult.success(sysUserService.selectByRoleId(102L));
    }
    /**
     * 查询职工信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:zg_information:list')")
    @GetMapping("/list")
    public TableDataInfo list(EmployeeInformation employeeInformation)
    {
        startPage();
        if (RoleUtils.RoleKeyIsUser("jf")){
            employeeInformation.setUserId(getUserId());
        }
        List<EmployeeInformation> list = employeeInformationService.selectEmployeeInformationList(employeeInformation);
        TableDataInfo tableDataInfo =   getDataTable(list);

        list.forEach(employeeInformation1 -> {
            employeeInformation1.setWorkExperienceList(employeeInformationMapper.selectWorkExperienceList(employeeInformation1.getId()));
        });
        return tableDataInfo;
    }

    /**
     * 导出职工信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:zg_information:export')")
    @Log(title = "职工信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EmployeeInformation employeeInformation)
    {
        List<EmployeeInformation> list = employeeInformationService.selectEmployeeInformationList(employeeInformation);
        ExcelUtil<EmployeeInformation> util = new ExcelUtil<EmployeeInformation>(EmployeeInformation.class);
        util.exportExcel(response, list, "职工信息数据");
    }

    /**
     * 获取职工信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:zg_information:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(employeeInformationService.selectEmployeeInformationById(id));
    }

    /**
     * 新增职工信息
     */
    @PreAuthorize("@ss.hasPermi('system:zg_information:add')")
    @Log(title = "职工信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EmployeeInformation employeeInformation)
    {
        SysUser sysUser = sysUserService.selectUserById(employeeInformation.getUserId());
        employeeInformation.setNickName(sysUser.getNickName());
        return toAjax(employeeInformationService.insertEmployeeInformation(employeeInformation));
    }

    /**
     * 修改职工信息
     */
    @PreAuthorize("@ss.hasPermi('system:zg_information:edit')")
    @Log(title = "职工信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EmployeeInformation employeeInformation)
    {
        return toAjax(employeeInformationService.updateEmployeeInformation(employeeInformation));
    }

    /**
     * 删除职工信息
     */
    @PreAuthorize("@ss.hasPermi('system:zg_information:remove')")
    @Log(title = "职工信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(employeeInformationService.deleteEmployeeInformationByIds(ids));
    }
}
