package services;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBInfo
{
	public  Connection con=null;
	public DBInfo ()
	{
	try
	{

	 	Class.forName("com.mysql.jdbc.Driver");
    	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ohd","root","rat");
	 }	
	catch(Exception e)
	{
		e.printStackTrace();
	}
       
	}
}
