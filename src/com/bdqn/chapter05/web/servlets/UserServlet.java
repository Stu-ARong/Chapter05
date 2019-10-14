package com.bdqn.chapter05.web.servlets;

import com.bdqn.chapter05.domain.TbUser;
import com.bdqn.chapter05.service.TbUserService;
import com.bdqn.chapter05.service.impl.TbUserServiceImpl;
import com.bdqn.chapter05.utils.BeanUtils;
import com.bdqn.chapter05.utils.Page;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;

import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;


/**
 * @author: 赖榕
 * @date: 2019/10/11
 * @description: ${description}
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: ${PACKAGE_NAME}
 */
public class UserServlet extends HttpServlet {

    private TbUserService service = new TbUserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("findAll".equals(action)) {
            findAll(request, response);
        } else if ("delete".equals(action)) {
            delete(request, response);
        } else if ("findUser".equals(action)) {
            findUser(request, response);
        } else if ("update".equals(action)) {
            update(request, response);
        } else if ("addUser".equals(action)) {
            addUser(request, response);
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        //创建User对象
        TbUser user = new TbUser();


         // * 创建硬盘工厂对象
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 创建文件上传对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> list = upload.parseRequest(request);

            // 为user对象赋值
            for (FileItem item : list) {
                String value = item.getString("utf-8");
                if (!item.isFormField()) {
                    String path = request.getServletContext().getRealPath("/photo");
                    //获取相对路径+fileName
                    service.savePhoto(path, item);
                    user.setPic(item.getName());
                } else {
                    BeanUtils.property(user,item.getFieldName(),value);
                }
            }

            service.addUser(user);
            request.getSession().setAttribute("flag", "添加成功");
        } catch (FileUploadException | NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            request.getSession().setAttribute("flag", e.getMessage());
        }
        response.sendRedirect("addUser.jsp");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        String messageSrc = request.getParameter("messageSrc");
        TbUser tbUser = new TbUser(userName, pwd, messageSrc);
        try {
            service.update(tbUser);
            request.getSession().setAttribute("flag", "修改成功");
        } catch (Exception e) {
            request.getSession().setAttribute("flag", e.getMessage());
        }
        response.sendRedirect("user?action=findAll");
    }

    private void findUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        TbUser user = null;
        try {
            user = service.findUserByUserName(userName);
            request.setAttribute("user", user);
        } catch (Exception e) {
            request.getSession().setAttribute("flag", e.getMessage());
        }
        request.getRequestDispatcher("updateUser.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String flag = "";
        try {
            service.deleteUser(userName);
            flag = "删除成功";
        } catch (Exception e) {
            flag = e.getMessage();
        }
        request.getSession().setAttribute("flag", flag);
        response.sendRedirect("user?action=findAll");

    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            List<TbUser> allUser = service.findAllUser();
//            request.setAttribute("userList" ,allUser);
//        } catch (Exception e) {
//
//            request.getSession().setAttribute("flag",e.getMessage());
//        }
        String pageNoStr = request.getParameter("pageNo");
        Integer pageNo = pageNoStr != null ? new Integer(pageNoStr) : 1;
        Page<TbUser> page = new Page<TbUser>();
        page.setPageNo(pageNo);
        try {
            Page<TbUser> users = service.findUserByPage(page);
            request.setAttribute("users", users);
        } catch (SQLException e) {
            request.getSession().setAttribute("flag", e.getMessage());
        }
        request.getRequestDispatcher("userList.jsp").forward(request, response);
    }
}
