package com.myupload.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
// 导入相应的类
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 7042756416806244618L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileDir = getServletContext().getRealPath("/upload");// 指定上传文件的保存地址
		String message = "文件上传成功";
		String address = "";
		String filename=null;//文件名
		String filepath=null;
		int i=0;
		request.setCharacterEncoding("UTF-8");
		if (ServletFileUpload.isMultipartContent(request)) { // 判断是否是上传文件
			System.out.println("开始上传文件");
			// 基于磁盘文件项目创建一个工厂对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 创建一个新的文件上传对象
			factory.setSizeThreshold(20 * 1024); // 设置内存中允许存储的字节数
			ServletFileUpload upload = new ServletFileUpload(factory); // 创建新的上传文件句柄
            
			int size = 200 * 1024 * 1024; // 指定上传文件的大小
			List formlists = null; // 创建保存上传文件的集合对象
			try {
				formlists = upload.parseRequest(request); // 获取上传文件集合，也就是获取全部的表单项
			} catch (FileUploadException e) {// 文件上传异常
				e.printStackTrace();
			}
			Iterator iter = formlists.iterator(); // 获取上传文件迭代器
			while (iter.hasNext()) {
				FileItem formitem = (FileItem) iter.next(); // 获取每个上传文件
				if (!formitem.isFormField()) { // 忽略不是上传文件的表单域
					String name = formitem.getName(); // 获取上传文件的名称, 只有当表单域时文件域的时候才有效
					System.out.println(formitem.getSize());
					if (formitem.getSize() > size) { // 如果上传文件大于规定的上传文件的大小
						message = "您上传的文件太大，请选择不超过200M的文件";
						break; // 退出程序
					}
					// 获取上传文件的大小
					String adjunctsize = new Long(formitem.getSize()).toString();
					// 如果上传文件为空
					if ((name == null) || (name.equals("")) && (adjunctsize.equals("0")))
						continue; // 退出程序
					
					filename = name.substring(name.lastIndexOf("\\") + 1,name.length());
					address = fileDir + "\\" + filename; // 创建上传文件的保存地址
					File saveFile = new File(address); //创建一个文件
					System.out.println(address);
					String type = "";
					try {
						formitem.write(saveFile);
						// 调用ReadExcel类进行读出excel
						if(filename.equals("studentMsg.xls")){
						   type = "stuExcel";
						} else if(filename.equals("TeacherMsg.xls")){
						   type = "teacherExcel";
						} else if(filename.equals("DeptMsg.xls")){
						   type = "deptExcel";
						} else if(filename.equals("ClassMsg.xls")){
						   type = "classExcel";
						}
						ReadExcel.readExcel(address, type);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		request.setAttribute("result", message); // 将提示信息保存在request对象中
		System.out.println("插入");
    	response.sendRedirect(request.getContextPath()+"/admin/homepage.jsp");
	}

	public void init() throws ServletException {
		// Put your code here
	}
}
