package com.bean;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter(urlPatterns = {"/*"}, initParams = {
		@WebInitParam(name = "encode", value = "UTF-8"),
		@WebInitParam(name = "ignore", value = "false") })
public class EncodingFilter implements Filter {

	private String encode;
	private boolean ignore = false; // ¹ýÂËÆ÷¿ª¹Ø

	/**
	 * Default constructor.
	 */
	public EncodingFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */

	public void destroy() {
		encode = null;
		ignore = false;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("×Ö·û±àÂë¹ýÂËÆ÷");
		if (!ignore) {
			if (null == request.getCharacterEncoding()) {
				request.setCharacterEncoding(encode);
				System.out.println("setting encode: "+encode);
			}
		}
		// pass the request along the filter chain

		chain.doFilter(request, response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		String encode = fConfig.getInitParameter("encode");
		String ignore = fConfig.getInitParameter("ignore");
		if (this.encode == null&&encode!=null){
			this.encode = encode;
		}else{
			this.encode="UTF-8";
		}
		if ("1".equals(ignore) || "yes".equals(ignore)) {
			this.ignore = true;
		}

	}

}
