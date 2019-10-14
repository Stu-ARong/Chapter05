package com.bdqn.chapter05.dao.impl;

import com.bdqn.chapter05.dao.BaseDao;
import com.bdqn.chapter05.dao.TbRoleDao;
import com.bdqn.chapter05.domain.TbRole;
import com.bdqn.chapter05.utils.Page;
import com.sun.corba.se.spi.transport.ReadTimeouts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.List;

/**
 * @author: 赖榕
 * @date: 2019/10/11
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.chapter05.dao.impl
 */
public class TbRoleDaoImpl extends BaseDao implements TbRoleDao {
    @Override
    public Page<TbRole> findRoleByPage(Page<TbRole> page) throws Exception {
        String sql = "select * from tb_role limit ?,?";
        try {
            ResultSet rs = super.query(sql, new Object[]{(page.getPageNo() - 1) * page.getPageCount(), page.getPageCount()});
            List<TbRole> resultList = new ArrayList<TbRole>();
            while (rs.next()) {
                resultList.add(this.getInfo(rs));
            }
            page.setResultList(resultList);
            page.setTotalCount(this.roleCount());
        } catch (SQLException e) {
            throw new Exception("服务器繁忙");
        }finally {
            super.close(rs,ps,connection);
        }
        return page;
    }

    @Override
    public TbRole findRoleByRoleId(Integer roleId) throws Exception {
        String sql = "select * from tb_role where rolreId = ?";
        try {
            rs = super.query(sql, new Object[]{roleId});
            if (rs.next()) {
                return this.getInfo(rs);
            }
        } catch (SQLException e) {
            throw new Exception("服务器繁忙");
        } finally {
            super.close(rs, ps, connection);
        }
        return null;
    }

    @Override
    public void delete(Integer roleId) throws SQLException {
        String sql = "delete from tb_role where rolreId = ?";
        super.update(sql, new Object[]{roleId});
    }

    @Override
    public void update(TbRole role) throws SQLException {
        String sql = "update tb_role set roleName = ? where rolreId = ?";
        super.update(sql, new Object[]{role.getRoleName(), role.getRoleId()});
    }

    @Override
    public List<TbRole> findAllRole() throws SQLException {
        String sql = "select * from tb_role";
        rs = super.query(sql, null);
        List<TbRole> roles = new ArrayList<>();
        while (rs.next()) {
            roles.add(this.getInfo(rs));
        }
        return roles;
    }

    private Long roleCount() throws SQLException {
        String sql="select count(*) as count from tb_role";
        ResultSet query = super.query(sql, null);
        while (query.next()) {
            return query.getLong("count");
        }
        return new Long(0);
    }

    private TbRole getInfo(ResultSet rs) throws SQLException {
        TbRole role = new TbRole();
        role.setRoleId(rs.getInt("rolreId"));
        role.setRoleName(rs.getString("roleName"));
        return role;
    }
}
