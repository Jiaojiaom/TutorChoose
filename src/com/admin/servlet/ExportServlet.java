package com.admin.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WriteException;

/**
 * Servlet implementation class DownLoadServlet2
 */
@WebServlet("/admin/ExportServlet")
public class ExportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dataType = request.getParameter("datatype");
		String filename = dataType+".xls";
		String pathname = this.getServletContext().getRealPath("/upload") +"/"+ filename;
        try {
     		System.out.println(pathname);
			ExportExcel.exportExcel(pathname, dataType);
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("成功导出");
		// 从服务器下载文件
		downloadFile(request, response, filename);
	}
	
	private void downloadFile(HttpServletRequest request, HttpServletResponse response, String fname) throws IOException {
        String n=URLDecoder.decode(fname,"UTF-8");
      
        String fullFilePath = this.getServletContext().getRealPath("/upload") +"\\"+ fname;
        System.out.println(fullFilePath);
        
        File file = new File(fullFilePath);
       
        if (file.exists()) {
        	System.out.println("File exists!");
            String filename = URLEncoder.encode(file.getName(), "UTF-8");
            response.reset();
            response.setContentType("application/x-msdownloade");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            int fileLength = (int) file.length();
            response.setContentLength(fileLength);
            //如果文件长度大于0
            if (fileLength != 0) {
                //创建输入流
                InputStream inStream = new FileInputStream(file);
                byte[] buf = new byte[4096];
                //*创建输出流
                ServletOutputStream servletOS = response.getOutputStream();
                int readLength;
                while ((readLength = inStream.read(buf)) != -1) {
                    servletOS.write(buf, 0, readLength);
                }
                inStream.close();
                servletOS.flush();
                servletOS.close();
            }
        }
        file.delete();
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
