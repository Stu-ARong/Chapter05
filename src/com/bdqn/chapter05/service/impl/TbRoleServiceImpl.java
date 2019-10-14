package com.bdqn.chapter05.service.impl;

import com.bdqn.chapter05.dao.TbRoleDao;
import com.bdqn.chapter05.dao.impl.TbRoleDaoImpl;
import com.bdqn.chapter05.dao.impl.TbUserDaoImpl;
import com.bdqn.chapter05.domain.TbRole;
import com.bdqn.chapter05.domain.TbUser;
import com.bdqn.chapter05.service.TbRoleService;
import com.bdqn.chapter05.utils.Page;
import com.sun.org.apache.regexp.internal.REUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: 赖榕
 * @date: 2019/10/11
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.chapter05.service.impl
 */
public class TbRoleServiceImpl implements TbRoleService {
    private TbRoleDao dao = new TbRoleDaoImpl();

    @Override
    public Page<TbRole> findRoleByPage(Page<TbRole> page) throws Exception {
        return dao.findRoleByPage(page);
    }

    @Override
    public void updateRoleNameById(Integer roleId, String roleName) throws Exception {
        TbRole role=new TbRole();
        role.setRoleId(roleId);
        role.setRoleName(roleName);
        try {
            dao.update(role);
        } catch (SQLException e) {
            throw new Exception("修改失败，服务器繁忙");
        }
    }

    @Override
    public TbRole findRoleByRoleId(Integer roleId) throws Exception {
        try {
            TbRole role = dao.findRoleByRoleId(roleId);
            return role;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void delete(Integer roleId) throws Exception {
        List<TbUser> users = new TbUserDaoImpl().findUserByRoleId(roleId);
        if (users.size() != 0) {
            throw new Exception("该角色有用户，无法删除");
        }
        try {
            dao.delete(roleId);
        } catch (SQLException e) {
            throw new Exception("删除失败，服务器繁忙");
        }
    }

    @Override
    public List<TbRole> findAllRole() throws Exception {
        List<TbRole> roles = null;
        roles = dao.findAllRole();
        if (roles == null || roles.size() == 0) {
            throw new Exception("未查找到数据");
        }
        return roles;
    }
}
