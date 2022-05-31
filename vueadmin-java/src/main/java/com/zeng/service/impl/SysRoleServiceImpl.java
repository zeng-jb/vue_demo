package com.zeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zeng.entity.SysRole;
import com.zeng.mapper.SysRoleMapper;
import com.zeng.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Override
    public List<SysRole> listRolesByUserId(Long userId) {
        List<SysRole> sysRoles = this.list(new QueryWrapper<SysRole>()
                .inSql("id", "select role_id from sys_user_role where user_id =" + userId));

        return sysRoles;
    }
}
