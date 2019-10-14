package com.bdqn.chapter05.service;

import com.bdqn.chapter05.domain.TbUser;
import com.bdqn.chapter05.utils.Page;
import org.apache.commons.fileupload.FileItem;

import java.io.IOException;
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
public interface TbUserService {

    /**
     * 返回所有的用户
     * @return
     */
   List<TbUser> findAllUser() throws Exception;

    void deleteUser(String userName) throws Exception;

    TbUser findUserByUserName(String userName) throws Exception;

    void update(TbUser tbUser) throws Exception;

    Page<TbUser> findUserByPage(Page<TbUser> page) throws SQLException;

    void savePhoto(String path,FileItem item) throws Exception;

    void addUser(TbUser user) throws Exception;
}
