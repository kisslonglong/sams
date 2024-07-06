package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.ruoyi.system.domain.Check;
import com.ruoyi.system.service.ICheckService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 员工打卡记录Controller
 * 
 * @author aw
 * @date 2024-07-04
 */
@RestController
@RequestMapping("/system/checkin")
public class CheckController extends BaseController
{
    @Autowired
    private ICheckService checkService;

    /**
     * 查询员工打卡记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:check:list')")
    @GetMapping("/list")
    public TableDataInfo list(Check check)
    {
        startPage();
        if (RoleUtils.RoleKeyIsUser("jf")){
            check.setUserId(getUserId());
        }
        List<Check> list = checkService.selectCheckList(check);
        return getDataTable(list);
    }

    /**
     * 导出员工打卡记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:check:export')")
    @Log(title = "员工打卡记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Check check)
    {
        List<Check> list = checkService.selectCheckList(check);
        ExcelUtil<Check> util = new ExcelUtil<Check>(Check.class);
        util.exportExcel(response, list, "员工打卡记录数据");
    }

    /**
     * 获取员工打卡记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:check:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(checkService.selectCheckById(id));
    }

    /**
     * 新增员工打卡记录
     */
    @PreAuthorize("@ss.hasPermi('system:check:add')")
    @Log(title = "员工打卡记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Check check)
    {
        return toAjax(checkService.insertCheck(check));
    }

    /**
     * 修改员工打卡记录
     */
    @PreAuthorize("@ss.hasPermi('system:check:edit')")
    @Log(title = "员工打卡记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Check check)
    {
        return toAjax(checkService.updateCheck(check));
    }

    /**
     * 删除员工打卡记录
     */
    @PreAuthorize("@ss.hasPermi('system:check:remove')")
    @Log(title = "员工打卡记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(checkService.deleteCheckByIds(ids));
    }
}
