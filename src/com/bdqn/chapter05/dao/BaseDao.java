package com.bdqn.chapter05.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.Properties;

/**
 * @author: 赖榕
 * @date: 2019/10/11
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.chapter05.dao
 */
public class BaseDao {

    protected static Connection connection;
    protected static PreparedStatement ps;
    protected static ResultSet rs;
    protected static DataSource ds;
    static {
        try {
            Context context=new InitialContext();
            BaseDao.ds = (DataSource) context.lookup("java:comp/env/jndi/test");
            connection=ds.getConnection();
        } catch (NamingException e) {
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 填充占位符
     * @param ps
     * @param params
     */
    private void full(PreparedStatement ps,Object[] params){
        if (params==null || params.length==0){
            return;
        }
        for (int i = 0; i < params.length; i++) {
            try {
                ps.setObject(i+1,params[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void close(ResultSet rs, Statement stmt,Connection connection){

        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        if (connection!=null){
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

    }

    public void update(String sql,Object[] params) throws SQLException {
        ps = BaseDao.connection.prepareStatement(sql);
        this.full(ps,params);
        ps.executeUpdate();
        this.close(rs,ps,connection);
    }

    public ResultSet query(String sql,Object[] params) throws SQLException {
        ps = connection.prepareStatement(sql);
        this.full(ps,params);
        rs=ps.executeQuery();
        return rs;
    }
}
