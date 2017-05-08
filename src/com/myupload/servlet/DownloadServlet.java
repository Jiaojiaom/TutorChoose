package com.myupload.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Java Web ʵ���ļ����صļ��ַ�ʽ
// http://www.cnblogs.com/chanshuyi/articles/3848650.html
/*
 �� Java �У�Ҫʵ�����ع���һ��������ʵ�ַ�ʽ��
1��ʹ�� Servlet ʵ���ļ�����
2���� Struts ���� Servlet ʵ������
3��ʹ�� Struts ����ṩ���ļ����ع���
��ʵ������ʵ�ַ�ʽ��ԭ��һ������������ InputStream ���ļ��ж�ȡ���ݣ�
Ȼ������ OutputStream �����ݽ��뵽���ؿͻ��˵� response ��

���е�һ���� Servlet ��ʵ���ļ���������ԭʼ�����ط�ʽ��
���ڶ����� Struts ���� Servlet ʵ���ļ�����Ҳ����ԭʼ�ķ�ʽ��ֻ������������ Struts �� Action �н��в���������ȥ���� Servlet��
������������ Struts ���ļ����ع��ܽ����˷�װ������ֻҪ������涨�����þͿ���ֱ��ʹ��
*/
/**
 * Servlet implementation class DownLoadServlet2
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// ��request�л�ȡ��filename���������ֵ
		String fname = request.getParameter("filename");
		fname=new String(fname.getBytes("ISO-8859-1"),"UTF-8");//��������
        String n=URLDecoder.decode(fname,"UTF-8");
      
        // ��ȡ�������ļ�·��
        String fullFilePath = this.getServletContext().getRealPath("/upload") +"/"+ fname;
        //����һ���ļ�����
        File file = new File(fullFilePath);
        
        /*
         * ���ó���ʵ��������Ҫ���� 2 ����ͷ��
         * 1. Web ��������Ҫ���������������������ݵ����Ͳ�����ͨ���ı��ļ��� HTML �ļ�������һ��Ҫ���浽���ص������ļ���
         * ���� Content-Type ��ֵΪ��application/x-msdownload
         * 2. Web ������ϣ���������ֱ�Ӵ�����Ӧ��ʵ�����ݣ��������û�ѡ����Ӧ��ʵ�����ݱ��浽һ���ļ��У�
         * ����Ҫ���� Content-Disposition ��ͷ���ñ�ͷָ���˽��ճ������������ݵķ�ʽ��
         * �� HTTP Ӧ����ֻ�� attachment �Ǳ�׼��ʽ��attachment ��ʾҪ���û���Ԥ��
         * �� attachment ���滹����ָ�� filename �������ò����Ƿ����������������ʵ�����ݱ��浽�ļ��е��ļ����ơ�
         * ������ Content-Dispostion ֮ǰһ��Ҫָ�� Content-Type.
         */
        if (file.exists()) {
        	System.out.println("File exists!");
            String filename = URLEncoder.encode(file.getName(), "UTF-8");
            response.reset();//��ջ���
            response.setContentType("application/x-application/x-msdownload");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
           
            /*
             * �����ļ����Ĵ���
             */
            // ��ȡ�ļ�����
            int fileLength = (int) file.length();
            response.setContentLength(fileLength);
            //����ļ����ȴ���0
            if (fileLength != 0) {
                try{
                	// ����������
	                InputStream inStream = new FileInputStream(file);
	                byte[] buf = new byte[4096];
	                // ���������
	                ServletOutputStream servletOS = response.getOutputStream();
	                int readLength;
	                while ((readLength = inStream.read(buf)) != -1) {
	                    servletOS.write(buf, 0, readLength);
	                }
	                inStream.close();
	                servletOS.flush();
	                servletOS.close();
                }
                catch(Exception e){
                	e.printStackTrace();
                }
            }
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
