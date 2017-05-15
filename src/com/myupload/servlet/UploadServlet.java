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
// ������Ӧ����
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
		String fileDir = getServletContext().getRealPath("/upload");// ָ���ϴ��ļ��ı����ַ
		String message = "�ļ��ϴ��ɹ�";
		String address = "";
		String filename=null;//�ļ���
		String filepath=null;
		int i=0;
		request.setCharacterEncoding("UTF-8");
		if (ServletFileUpload.isMultipartContent(request)) { // �ж��Ƿ����ϴ��ļ�
			System.out.println("��ʼ�ϴ��ļ�");
			// ���ڴ����ļ���Ŀ����һ����������
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// ����һ���µ��ļ��ϴ�����
			factory.setSizeThreshold(20 * 1024); // �����ڴ�������洢���ֽ���
			ServletFileUpload upload = new ServletFileUpload(factory); // �����µ��ϴ��ļ����
            
			int size = 200 * 1024 * 1024; // ָ���ϴ��ļ��Ĵ�С
			List formlists = null; // ���������ϴ��ļ��ļ��϶���
			try {
				formlists = upload.parseRequest(request); // ��ȡ�ϴ��ļ����ϣ�Ҳ���ǻ�ȡȫ���ı���
			} catch (FileUploadException e) {// �ļ��ϴ��쳣
				e.printStackTrace();
			}
			Iterator iter = formlists.iterator(); // ��ȡ�ϴ��ļ�������
			while (iter.hasNext()) {
				FileItem formitem = (FileItem) iter.next(); // ��ȡÿ���ϴ��ļ�
				if (!formitem.isFormField()) { // ���Բ����ϴ��ļ��ı���
					String name = formitem.getName(); // ��ȡ�ϴ��ļ�������, ֻ�е�����ʱ�ļ����ʱ�����Ч
					System.out.println(formitem.getSize());
					if (formitem.getSize() > size) { // ����ϴ��ļ����ڹ涨���ϴ��ļ��Ĵ�С
						message = "���ϴ����ļ�̫����ѡ�񲻳���200M���ļ�";
						break; // �˳�����
					}
					// ��ȡ�ϴ��ļ��Ĵ�С
					String adjunctsize = new Long(formitem.getSize()).toString();
					// ����ϴ��ļ�Ϊ��
					if ((name == null) || (name.equals("")) && (adjunctsize.equals("0")))
						continue; // �˳�����
					
					filename = name.substring(name.lastIndexOf("\\") + 1,name.length());
					address = fileDir + "\\" + filename; // �����ϴ��ļ��ı����ַ
					File saveFile = new File(address); //����һ���ļ�
					System.out.println(address);
					String type = "";
					try {
						formitem.write(saveFile);
						// ����ReadExcel����ж���excel
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
		request.setAttribute("result", message); // ����ʾ��Ϣ������request������
		System.out.println("����");
    	response.sendRedirect(request.getContextPath()+"/admin/homepage.jsp");
	}

	public void init() throws ServletException {
		// Put your code here
	}
}
