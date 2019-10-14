package com.bdqn.chapter05.web.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.CheckedOutputStream;

/**
 * @author: 赖榕
 * @date: 2019/10/12
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.chapter05.web.servlets
 */
public class UpLoad extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload fileUpload=new ServletFileUpload(factory);
        fileUpload.setFileSizeMax(1024*1024*5);
        try {
            List<FileItem> items= fileUpload.parseRequest(request);
            ServletContext context = request.getServletContext();
            for (FileItem item : items) {
                if (!item.isFormField()){

                    InputStream inputStream = item.getInputStream();
                    String realPath = context.getRealPath("WEB-INF\\resources");
                    File file=new File(realPath+"/"+item.getName());
                    OutputStream out=new FileOutputStream(file);
                    int count=0;
                    byte[] bytes=new byte[1024*8];
                    while ((count=inputStream.read(bytes))!=-1){
                        out.write(bytes,0, count);
                    }
                    request.getSession().setAttribute("flag","上传成功");
                }
            }
        } catch (FileUploadException e) {
            request.getSession().setAttribute("flag","上传失败");
        }
        response.sendRedirect("upLoad.jsp");
    }
}
