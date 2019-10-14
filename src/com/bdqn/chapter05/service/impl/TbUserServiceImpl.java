package com.bdqn.chapter05.service.impl;

import com.bdqn.chapter05.dao.TbUserDao;
import com.bdqn.chapter05.dao.impl.TbUserDaoImpl;
import com.bdqn.chapter05.domain.TbUser;
import com.bdqn.chapter05.service.TbUserService;
import com.bdqn.chapter05.utils.Page;
import com.sun.corba.se.spi.transport.ReadTimeouts;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOExceptionWithCause;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 赖榕
 * @date: 2019/10/11
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.chapter05.service.impl
 */
public class TbUserServiceImpl implements TbUserService {

    private TbUserDao dao = new TbUserDaoImpl();

    @Override
    public void update(TbUser tbUser) throws Exception {
        dao.updateByUserName(tbUser);
    }

    @Override
    public TbUser findUserByUserName(String userName) throws Exception {
        TbUser user = dao.findUserByUserName(userName);
        if (user == null || user.getUserName() == null || "".equals(user.getUserName())) {
            throw new Exception("未查找到用户");
        }
        return user;
    }

    @Override
    public void deleteUser(String userName) throws Exception {
        try {
            dao.delete(userName);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<TbUser> findAllUser() throws Exception {
        List<TbUser> allUser = null;
        try {
            allUser = dao.findAllUser();
        } catch (Exception e) {
            throw e;
        }
        if (allUser == null || allUser.size() == 0) {
            throw new Exception("没有查询到数据");
        }
        return allUser;
    }

    @Override
    public void addUser(TbUser user) throws Exception {

        try {
            dao.addUser(user);
        } catch (Exception e) {
            throw new Exception("添加失败");
        }
    }

    @Override
    public void savePhoto(String path,FileItem item) throws Exception {
        if (item.getSize()>1024*1024*5){
            throw new Exception("头像不能大于5MB");
        }
        List<String> types=new ArrayList<String>();
        types.add("jpeg");
        types.add("jpg");
        types.add("gif");
        types.add("ico");
        String type = item.getName().substring(item.getName().indexOf(".") + 1);

        if (!types.contains(type)){
            throw new Exception("只能上传.jpeg、.jpg、.gif格式的文件");
        }

        // 获取输入流
        InputStream inputStream = item.getInputStream();
        FileOutputStream fos=new FileOutputStream(path+"\\"+item.getName());
        int count=0;
        byte[] bytes=new byte[1024*1024*5];
        while ((count=inputStream.read(bytes))!=-1){
            fos.write(bytes,0,count);
        }

    }

    @Override
    public Page<TbUser> findUserByPage(Page<TbUser> page) throws SQLException {
        return dao.findUserByPage(page);
    }
}
