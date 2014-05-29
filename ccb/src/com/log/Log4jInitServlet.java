package com.log;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

public class Log4jInitServlet  extends HttpServlet  {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -1292276900633479012L;

	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		    System.out.println("log services");
	    }   
	       
	    public void init() throws ServletException {
	    	try
	    	{
	    	    //System.out.println("servlet initialize");
	            System.setProperty("loghome", getServletContext().getRealPath("/"));
	            String logCfgPath = getServletContext().getRealPath("/") + getInitParameter("log4jconfigfile");
	            PropertyConfigurator.configure(logCfgPath); 	        
	            System.out.println(logCfgPath);
	    	}
	    	catch(RuntimeException ex)
	    	{
	    		
	    	}
	    }   

}
