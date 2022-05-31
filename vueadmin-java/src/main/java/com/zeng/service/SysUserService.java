package com.zeng.service;

import com.zeng.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengjiabin
 * @since 2022-02-09
 */
public interface SysUserService extends IService<SysUser> {


    SysUser getByUsername(String s);

    String getUserAuthorityInfo(Long userId);


    void clearUserAuthorityInfo(String username);

    void clearUserAuthorityInfoByRoleId(Long roleId);

    void clearUserAuthorityInfoByMenuId(Long menuId);
}
