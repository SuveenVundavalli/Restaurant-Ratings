package com.ts.us.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ts.us.exception.UrbanspoonException;

public class DAOUtility {
	
	private static final String INVALID_ARGUMENT_EXCEPTION = "Invalid Argument to close() method of class DAOUtility !!!";
	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String DATABASE_NAME = "urbanspoon";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	
	
	public static Connection getConnection() throws UrbanspoonException{		
		Connection connection = null;		
		try {
			Class.forName(DRIVER_NAME);
			connection = DriverManager.getConnection(URL+DATABASE_NAME,USER_NAME, PASSWORD);
		} catch (ClassNotFoundException e) {			
			throw new UrbanspoonException(e.toString());
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		}		
		return connection;		
	}

	public  static void close(Object... objects ) throws UrbanspoonException{
		if(null != objects && objects.length != 0){		
			try{
				for (Object object : objects) {
					if(null != object){
						if(object instanceof Connection){
							((Connection)object).close();
						} else if(object instanceof Statement){
							((Statement)object).close();
						} else if(object instanceof PreparedStatement){
							((PreparedStatement)object).close();
						} else if(object instanceof CallableStatement){
							((CallableStatement)object).close();
						} else if(object instanceof ResultSet){
							((ResultSet)object).close();
						} else {						
							throw new UrbanspoonException(INVALID_ARGUMENT_EXCEPTION);
						}
					}
				}				
			}catch (SQLException e) {
				throw new UrbanspoonException(e.toString());
			}
		}
	}
	
	public static int getLatestId(String table) throws UrbanspoonException{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DAOUtility.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select max(id) from "+table);
			if(resultSet.next()){
				return resultSet.getInt(1);
			}			
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(resultSet,statement,connection);
		}
		return -1;
	}	
}
