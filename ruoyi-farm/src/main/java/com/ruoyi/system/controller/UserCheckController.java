package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.Check;
import com.ruoyi.system.service.ICheckService;
import com.ruoyi.system.utils.RoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/UserCheck")
public class UserCheckController {

    @Autowired
    private ICheckService checkService;

    @GetMapping("/checkIn")
    public AjaxResult checkIn(Integer type) {
        Check check = new Check();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        check.setUserId(user.getUserId());
        check.setDate(new java.sql.Date(System.currentTimeMillis()));

        if (user.isAdmin()) {
            return AjaxResult.error("管理员不能打卡");
        }
        if (RoleUtils.RoleKeyIsUser("userAdmin")){
            return AjaxResult.error("管理员不能打卡");
        }
        if (type == 1) {
            // 查询当天上午是否已打卡
            List<Check> checks = checkService.selectCheckList(check);
            if (checks.isEmpty() || checks.stream().noneMatch(c -> c.getStartDate() != null)) {
                check.setNickName(user.getNickName());
                check.setStartDate(new java.sql.Date(System.currentTimeMillis()));
                check.setIsAsk(0L);
                checkService.insertCheck(check);
                return AjaxResult.success("上午打卡成功");
            } else {
                return AjaxResult.error("今天上午已经打卡过了");
            }
        } else if (type == 2) {
            // 查询当天的打卡记录
            List<Check> checks = checkService.selectCheckList(check);
            if (checks.isEmpty()) {
                check.setEndDate(new java.sql.Date(System.currentTimeMillis()));
                check.setNickName(user.getNickName());
                checkService.insertCheck(check);
                return AjaxResult.success("下午打卡成功");
            } else {
                Check check1 = checks.get(0);
                if (check1.getEndDate() == null) {
                    check1.setEndDate(new java.sql.Date(System.currentTimeMillis()));
                    checkService.updateCheck(check1);
                    return AjaxResult.success("下午打卡成功");
                } else {
                    return AjaxResult.error("今天下午已经打卡过了");
                }
            }
        }

        return AjaxResult.warn("打卡异常，请稍后再试");
    }

    @GetMapping("/askLever")

    public AjaxResult askLeave(String reason) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (user.isAdmin()) {
            return AjaxResult.error("管理员不能请假");
        }
        Check check = new Check();
        check.setDate(new java.sql.Date(System.currentTimeMillis()));
        if (checkService.selectCheckList(check).isEmpty()) {
            check.setUserId(user.getUserId());
            check.setNickName(user.getNickName());
            check.setReasonLeave(reason);
            checkService.insertCheck(check);
        }else {
            check.setIsAsk(1L);
            check.setReasonLeave(reason);
            checkService.updateCheck(check);

        }
        if (checkService.selectCheckList(check).stream().anyMatch(c -> c.getIsAsk() == 1)) {
            return AjaxResult.success("请假成功");
        }
        return null;
    }

}
