package com.bdqn.chapter05.dao;

import com.bdqn.chapter05.domain.TbUser;
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
public interface TbUserDao {

    /**
     * 查询所有用户
     * @return
     */
    List<TbUser> findAllUser() throws Exception;


    /**
     * 根据用户id删除用户
     * @param username
     * */
    void delete(String username) throws Exception;

    TbUser findUserByUserName(String userName) throws Exception;

    void updateByUserName(TbUser tbUser) throws SQLException;

    List<TbUser> findUserByRoleId(Integer roleId) throws Exception;

    Page<TbUser> findUserByPage(Page<TbUser> page) throws SQLException;

    void addUser(TbUser user) throws Exception;
}
