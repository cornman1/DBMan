package com.DBMan.Backend.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class WriteToApplicationLog {
	/*
	 *Author:Nam Dinh
	 *Date: 2/24/2015
	 *Note: this is logging class
	 *All statement to databases need to be logged but for now islogging is off by default
	 * set IsLogging to true to start logging statements
	 */
	public static boolean IsLogging=false;
	private static WriteToApplicationLog instance=null;
	private static  Logger applicationLog = Logger.getLogger(WriteToApplicationLog.class);
	private static String LogPathLocation="";
	public static void InitializeApplicationLog(String LogPath)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
		Date date = new Date();
		LogPathLocation=LogPath;
		String LogFile=LogPath+"ApplicationLog.log";
		Properties applicationLogProperties = new Properties();
		applicationLogProperties.setProperty("log4j.rootLogger", "DEBUG, FILE");
		applicationLogProperties.setProperty("log4j.appender.FILE", "org.apache.log4j.RollingFileAppender");
		//applicationLogProperties.setProperty("log4j.appender.FILE.File", "${catalina.home}/logs/applicationlog.log");
		applicationLogProperties.setProperty("log4j.appender.FILE.File", LogFile);
		//applicationLogProperties.setProperty("log4j.appender.FILE.DatePattern", "'.'yyy-mmm-dd");
		applicationLogProperties.setProperty("log4j.appender.FILE.ImmediateFlush", "true");
		applicationLogProperties.setProperty("log4j.appender.FILE.Threshold", "debug");
		applicationLogProperties.setProperty("log4j.appender.FILE.Append", "true");
		applicationLogProperties.setProperty("log4j.appender.FILE.MaxFileSize", "1MB");
		applicationLogProperties.setProperty("log4j.appender.FILE.MaxBackupIndex", "10");
		applicationLogProperties.setProperty("log4j.appender.FILE.layout", "org.apache.log4j.PatternLayout");
		applicationLogProperties.setProperty("log4j.appender.FILE.layout.conversionPattern", "%d{yyy-MM-dd HH:mm:ss}: %m%n");
		PropertyConfigurator.configure(applicationLogProperties);
		applicationLog.info("Application Start time:"+dateFormat.format(date));
	}
	
	public static void WriteInfo(String message)
	{
		if (IsLogging==true)
		{
			applicationLog.info(message);
		}
		
	}
	public static void WriteError(Exception error)
	{
		applicationLog.error(error);
		applicationLog.error(Arrays.toString(error.getStackTrace()).replaceAll(",", "\n"));
		
	}
	public static WriteToApplicationLog getInstance()
	{
		if (instance ==null)
		{
			instance = new WriteToApplicationLog();
		}
		return instance;
	}
	public static void WriteToCSV(ResultSet rs)
	{
		String CSVFile= LogPathLocation+"Table.csv";
		try {
			PrintWriter CSVWriter = new PrintWriter(new File(CSVFile));
			ResultSetMetaData meta = rs.getMetaData();
			int ColumnNumber = meta.getColumnCount();
			String ColumnHeader ="\""+meta.getColumnName(1)+"\"";
			for (int i = 2; i<=ColumnNumber;i++)
			{
				ColumnHeader = ColumnHeader +",\""+meta.getColumnName(i)+"\"";				
			}
			CSVWriter.println(ColumnHeader);
			while (rs.next())
			{
				
				String row = "\""+rs.getString(1)+"\"";
				for (int i =2;i<=ColumnNumber;i++)
				{
					row+=",\""+rs.getString(i)+"\"";
				}
				//WriteInfo(row);
				CSVWriter.println(row);
			}
			CSVWriter.close();
		} catch (Exception e) {
			WriteError(e);
		}
		
	}
	
}
