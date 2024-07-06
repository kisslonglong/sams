package com.ruoyi.system.utils;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;

//权限判断
public class RoleUtils {


    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }


    public static boolean RoleKeyIsUser(String roleKey) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (user.getRoles().stream().anyMatch(role -> role.getRoleKey().equals(roleKey))) {
            return true;
        }
        return false;
    }
}
