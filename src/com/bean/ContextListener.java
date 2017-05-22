package com.bean;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import db.AdminMsDAO;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener, ServletContextAttributeListener {

    /**
     * Default constructor. 
     */
    public ContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	 System.out.println("应用已经启动");
    	 ServletContext application = arg0.getServletContext();
    	 if(application.getAttribute("PrivilegeModel")==null&&application.getAttribute("AuthorityModel")==null){
    		 try{
    		    AdminMsDAO addao = new AdminMsDAO();
	 			application.setAttribute("PrivilegeModel", addao.getPrivilegeModel());
	 			System.out.println("PrivilegeModel " + application.getAttribute("PrivilegeModel"));
	 			application.setAttribute("AuthorityModel", addao.getAuthorityModel());
	 			application.setAttribute("LimitModel", addao.getLimitModel());
	 			System.out.println("AuthorityModel " + application.getAttribute("AuthorityModel"));
	 			addao.close();
    		 }catch(SQLException e){
    			 System.out.println("fail");
    		 }
 		}
    }
	
}
