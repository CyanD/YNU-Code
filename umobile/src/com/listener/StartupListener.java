package com.listener;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class StartupListener implements ServletContextListener{
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }
	@Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext application = event.getServletContext();
        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
       
    }
}
