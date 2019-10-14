package com.bdqn.chapter05.web.servlets;

import com.bdqn.chapter05.domain.TbRole;
import com.bdqn.chapter05.service.TbRoleService;
import com.bdqn.chapter05.service.impl.TbRoleServiceImpl;
import com.bdqn.chapter05.utils.Page;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: 赖榕
 * @date: 2019/10/11
 * @description: ${description}
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: ${PACKAGE_NAME}
 */
public class RoleServlet extends HttpServlet {

    private TbRoleService service = new TbRoleServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("findAll".equals(action)) {
            findAllRole(request, response);
        } else if ("delete".equals(action)) {
            delete(request, response);
        } else if ("update".equals(action)) {
            update(request, response);
        } else if ("findUser".equals(action)) {
            findRole(request, response);
        }
    }

    private void findRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer roleId = Integer.parseInt(request.getParameter("roleId"));
        TbRole role = null;
        try {
            role = service.findRoleByRoleId(roleId);
            request.setAttribute("role",role);
        } catch (Exception e) {
            request.getSession().setAttribute("flag",e.getMessage());
        }
        request.getRequestDispatcher("roleUpdate.jsp").forward(request,response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        String roleName = request.getParameter("roleName");
        try {
            service.updateRoleNameById(roleId,roleName);
            request.getSession().setAttribute("flag","修改成功");
        } catch (Exception e) {
            request.getSession().setAttribute("flag",e.getMessage());
        }
        response.sendRedirect("role?action=findAll");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer roleId = Integer.parseInt(request.getParameter("roleId"));
        try {
            service.delete(roleId);
        } catch (Exception e) {
            request.getSession().setAttribute("flag", e.getMessage());
        }
        response.sendRedirect("role?action=findAll");
    }

    private void findAllRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Page<TbRole> role=new Page<>();
        String pageNo = request.getParameter("pageNo");
        role.setPageNo(new Integer(pageNo));
        try {
            Page<TbRole> page = service.findRoleByPage(role);
            request.setAttribute("page",page);
        } catch (Exception e) {
            request.getSession().setAttribute("flag",e.getMessage());
        }
        request.getRequestDispatcher("roleList.jsp").forward(request, response);
    }
}
