package com.zeng.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zeng.common.dto.SysMenuDto;
import com.zeng.entity.SysMenu;
import com.zeng.entity.SysUser;
import com.zeng.mapper.SysMenuMapper;
import com.zeng.mapper.SysUserMapper;
import com.zeng.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zeng.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengjiabin
 * @since 2022-02-09
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public List<SysMenuDto> getCurrnetUserNav() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        SysUser sysUser = sysUserService.getByUsername(username);

        List<Long> menuIds = sysUserMapper.getNavMenuIds(sysUser.getId());
        List<SysMenu> menus = this.listByIds(menuIds);

        //装成树状结构
        List<SysMenu> menuTree = buildTreeMenu(menus);
        //实体转dto
        return convert(menuTree);
    }

    private List<SysMenuDto> convert(List<SysMenu> menuTree) {
        List<SysMenuDto> menuDtos = new ArrayList<>();

        menuTree.forEach(m -> {
            SysMenuDto dto = new SysMenuDto();

            dto.setId(m.getId());
            dto.setName(m.getPerms());
            dto.setTitle(m.getName());
            dto.setIcon(m.getIcon());
            dto.setPath(m.getPath());
            dto.setComponent(m.getComponent());

            if(m.getChildren().size() > 0){
                dto.setChildren(convert(m.getChildren()));
            }

            menuDtos.add(dto);
        });

        return menuDtos;
    }

    private List<SysMenu> buildTreeMenu(List<SysMenu> menus) {
        List<SysMenu> finalMenus = new ArrayList<>();

        //找出各自的子节点
        for(SysMenu menu : menus){
            for(SysMenu e : menus){
                if(menu.getId() == e.getParentId()){
                    menu.getChildren().add(e);
                }
            }
            //提取父结点
            if(menu.getParentId() == 0L){
                finalMenus.add(menu);
            }
        }

        System.out.println(JSONUtil.toJsonStr(finalMenus));

        return finalMenus;
    }


    @Override
    public List<SysMenu> tree() {
        //获取所有菜单信息
        List<SysMenu> sysMenuList = this.list(new QueryWrapper<SysMenu>().orderByAsc("orderNum"));
        //转成树状结构

        return buildTreeMenu(sysMenuList);
    }
}
