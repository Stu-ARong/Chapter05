package com.bdqn.chapter05.service;

import com.bdqn.chapter05.domain.TbRole;
import com.bdqn.chapter05.utils.Page;

import javax.management.relation.Role;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: 赖榕
 * @date: 2019/10/11
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.chapter05.service
 */
public interface TbRoleService {
    List<TbRole> findAllRole() throws Exception;

    void delete(Integer roleId) throws Exception;

    TbRole findRoleByRoleId(Integer roleId) throws Exception;

    void updateRoleNameById(Integer roleId,String roleName) throws Exception;
    Page<TbRole> findRoleByPage(Page<TbRole> page) throws Exception;
}
