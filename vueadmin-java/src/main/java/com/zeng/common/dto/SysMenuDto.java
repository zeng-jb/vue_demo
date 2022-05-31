package com.zeng.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *     let nav = [
 *         {
 *             "id": 1,
 *             "title": "系统管理",
 *             "icon": "el-icon-s-operation",
 *             "path": "",
 *             "name": "sys:manage",
 *             "component": "",
 *             "children":
 */
@Data
public class SysMenuDto implements Serializable {
    private Long id;
    private String title;
    private String icon;
    private String path;
    private String name;
    private String component;
    List<SysMenuDto> children = new ArrayList<>();
}
