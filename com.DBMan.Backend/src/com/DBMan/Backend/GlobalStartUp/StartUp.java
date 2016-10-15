package com.DBMan.Backend.GlobalStartUp;
import java.sql.ResultSet;

import javax.servlet.*;
import javax.servlet.http.*;

import com.DBMan.Backend.Util.*;
import com.sun.research.ws.wadl.Application;

@SuppressWarnings("serial")
public class StartUp  extends HttpServlet{
	public void init(ServletConfig servletConfig) throws ServletException
	{
		/********************************************
		 * Author: Nam Dinh
		 * Date:2/24/2015
		 * Initialize all environment variables
		 * System starts first with this class
		 ********************************************/

		
		System.out.println("\n........................................");
		System.out.println("Starting up...............................");
		System.out.println("Setting up variables.....................");
		String ApplicationName=servletConfig.getInitParameter("ApplicationName");
		String ConnectionDatabaseServerType=servletConfig.getInitParameter("ConnectionDatabaseServerType");
		String ConnectionDatabaseServer=servletConfig.getInitParameter("ConnectionDatabaseServer");
		String ConnectionDatabase=servletConfig.getInitParameter("ConnectionDatabase");
		String ConnectionUser=servletConfig.getInitParameter("ConnectionUser");
		String ConnectionPassword=servletConfig.getInitParameter("ConnectionPassword");		
		String UserHome=System.getProperty("user.home")+"\\";
		String ApplicationHome=UserHome+ApplicationName+"\\";
		String LogPath=ApplicationHome+"logs\\";	
		System.out.println("ApplicationName:"+ApplicationName);
		System.out.println("Log Path:"+LogPath);
		System.out.println("Connection Type:"+ConnectionDatabaseServerType);
		System.out.println("ConnectionDatabaseServer:"+ConnectionDatabaseServer);
		System.out.println("ConnectionDatabase:"+ConnectionDatabase);
		System.out.println("ConnectionUser:"+ConnectionUser);
		System.out.println("ConnectionPassword:"+ConnectionPassword);
		System.out.println("Initialize logwriter.....................");
		WriteToApplicationLog.InitializeApplicationLog(LogPath);

		/*
		 * Initialize Connection pool
		 */
		//con.InitializeClientConnectionPool();

		
		
		
		
		System.out.println("\n If there is no error, then system is online ....");
		System.out.println("\n........................................");
		
		
		
		
	}
	
}
