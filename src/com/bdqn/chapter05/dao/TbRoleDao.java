package com.bdqn.chapter05.dao;

import com.bdqn.chapter05.domain.TbRole;
import com.bdqn.chapter05.utils.Page;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: 赖榕
 * @date: 2019/10/11
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.chapter05.dao
 */
public interface TbRoleDao {
    List<TbRole> findAllRole() throws SQLException;

    void update(TbRole role) throws SQLException;

    void delete(Integer roleId) throws SQLException;

    TbRole findRoleByRoleId(Integer roleId) throws Exception;

    Page<TbRole> findRoleByPage(Page<TbRole> page) throws Exception;
}
