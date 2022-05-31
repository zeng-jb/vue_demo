package com.zeng.service;

import com.zeng.common.dto.SysMenuDto;
import com.zeng.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengjiabin
 * @since 2022-02-09
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenuDto> getCurrnetUserNav();

    List<SysMenu> tree();
}
