package com.bdqn.chapter05.dao.impl;

import com.bdqn.chapter05.dao.BaseDao;
import com.bdqn.chapter05.dao.TbUserDao;
import com.bdqn.chapter05.domain.TbUser;
import com.bdqn.chapter05.utils.Page;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 赖榕
 * @date: 2019/10/11
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.chapter05.dao.impl
 */
public class TbUserDaoImpl extends BaseDao implements TbUserDao {
    @Override
    public List<TbUser> findAllUser() throws Exception {
        String sql="select * from tb_user";
        List<TbUser> userList=new ArrayList<TbUser>();
        try {
            BaseDao.rs = super.query(sql,null);
            while (rs.next()){
                userList.add(this.getInfo(rs));
            }
        } catch (SQLException e) {
            throw new Exception("查询失败，服务器繁忙");
        }finally {
            super.close(rs,ps,connection);
        }


        return userList;
    }

    private TbUser getInfo(ResultSet rs) throws SQLException {
        TbUser user=new TbUser();
        user.setUserName(rs.getString("username"));
        user.setPwd(rs.getString("pwd"));
        user.setMessageSrc(rs.getString("messagesrc"));
        return user;
    }

    @Override
    public void addUser(TbUser user) throws Exception {
        String sql="insert into tb_user (userName,pwd,messageSrc,roleId,pic)values(?,?,?,?,?)";
        super.update(sql,new Object[]{user.getUserName(),user.getPwd(),user.getMessageSrc(),1,user.getPic()});
    }

    @Override
    public Page<TbUser> findUserByPage(Page<TbUser> page) throws SQLException {
        String sql="select * from tb_user limit ?,?";
        rs = super.query(sql, new Object[]{(page.getPageNo() - 1) * page.getPageCount(), page.getPageCount()});
        List<TbUser> resultList =new ArrayList<>();
        while (rs.next()) {
            resultList.add(this.getInfo(rs));
        }
        page.setResultList(resultList);
        page.setTotalCount(this.findUserCount());
        return page;
    }

    private long findUserCount() throws SQLException {
        String sql="select count(*) as count from tb_user";
        rs = super.query(sql, null);
        if (rs.next()){
            return rs.getLong("count");
        }
        return 0;
    }

    @Override
    public List<TbUser> findUserByRoleId(Integer roleId) throws Exception {
        String sql="select * from tb_user where roleId = ?";
        List<TbUser> users=new ArrayList<>();
        try {
            rs = super.query(sql, new Object[]{roleId});
            while (rs.next()) {
                users.add(this.getInfo(rs));
            }
        } catch (SQLException e) {
            throw new Exception("查询失败");
        }finally {
            super.close(rs,ps,connection);
        }
        return users;
    }

    @Override
    public void updateByUserName(TbUser tbUser) throws SQLException {
        String sql="update tb_user set pwd=?,messageSrc=? where username = ?";
        super.update(sql,new Object[]{tbUser.getPwd(),tbUser.getMessageSrc(),tbUser.getUserName()});
    }

    @Override
    public TbUser findUserByUserName(String userName) throws Exception {
        String sql="select * from tb_user where username = ?";
        try {
            rs = super.query(sql, new Object[]{userName});
            if(rs.next()){
                return this.getInfo(rs);
            }
        } catch (SQLException e) {
            throw new Exception("系统繁忙");
        } finally {
            super.close(rs,ps,connection);
        }
        return null;
    }

    @Override
    public void delete(String username) throws Exception {
        String sql="delete from tb_user where username = ?";
        try {
            super.update(sql,new Object[]{username});
        } catch (SQLException e) {
            throw new Exception("删除用户失败，服务器繁忙");
        }
    }
}
