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

// Java Web 实现文件下载的几种方式
// http://www.cnblogs.com/chanshuyi/articles/3848650.html
/*
 在 Java 中，要实现下载功能一般有三种实现方式：
1、使用 Servlet 实现文件下载
2、在 Struts 中用 Servlet 实现下载
3、使用 Struts 框架提供的文件下载功能
其实这三种实现方式的原理都一样，都是利用 InputStream 从文件中读取数据，
然后利用 OutputStream 将数据接入到返回客户端的 response 中

其中第一种在 Servlet 中实现文件下载是最原始的下载方式。
而第二种在 Struts 中用 Servlet 实现文件下载也是最原始的方式，只不过我们是在 Struts 的 Action 中进行操作，不用去创建 Servlet。
而第三种则是 Struts 对文件下载功能进行了封装，我们只要按照其规定的配置就可以直接使用
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
		// 从request中获取到filename这个变量的值
		String fname = request.getParameter("filename");
		fname=new String(fname.getBytes("ISO-8859-1"),"UTF-8");//中文乱码
        String n=URLDecoder.decode(fname,"UTF-8");
      
        // 获取完整的文件路径
        String fullFilePath = this.getServletContext().getRealPath("/upload") +"/"+ fname;
        //创建一个文件对象
        File file = new File(fullFilePath);
        
        /*
         * 利用程序实现下载需要设置 2 个报头：
         * 1. Web 服务器需要告诉浏览器其所输出的内容的类型不是普通的文本文件或 HTML 文件，而是一个要保存到本地的下载文件。
         * 设置 Content-Type 的值为：application/x-msdownload
         * 2. Web 服务器希望浏览器不直接处理相应的实体内容，而是由用户选择将相应的实体内容保存到一个文件中，
         * 这需要设置 Content-Disposition 报头。该报头指定了接收程序处理数据内容的方式，
         * 在 HTTP 应用中只有 attachment 是标准方式，attachment 表示要求用户干预。
         * 在 attachment 后面还可以指定 filename 参数，该参数是服务器建议浏览器将实体内容保存到文件中的文件名称。
         * 在设置 Content-Dispostion 之前一定要指定 Content-Type.
         */
        if (file.exists()) {
        	System.out.println("File exists!");
            String filename = URLEncoder.encode(file.getName(), "UTF-8");
            response.reset();//清空缓存
            response.setContentType("application/x-application/x-msdownload");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
           
            /*
             * 下载文件核心代码
             */
            // 获取文件长度
            int fileLength = (int) file.length();
            response.setContentLength(fileLength);
            //如果文件长度大于0
            if (fileLength != 0) {
                try{
                	// 创建输入流
	                InputStream inStream = new FileInputStream(file);
	                byte[] buf = new byte[4096];
	                // 创建输出流
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
