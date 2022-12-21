package lab01.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import lab01.utils.HibernateUtils;

@WebListener
public class SessionFactoryListenerCreator implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("Waiting...");
    	HibernateUtils.getSessionFactory().close();
    	System.out.println("Connection closed");
    }


    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("Start Init...");
    	HibernateUtils.getSessionFactory();
    }
	
}
