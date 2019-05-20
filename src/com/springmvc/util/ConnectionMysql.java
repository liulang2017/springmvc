package com.springmvc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import wfc.service.config.Config;

public class ConnectionMysql {
	//与数据库建立连接
	public Connection getConn() {
	    String driver = Config.get("wfc.service.jdbc.driver");
	    String url = Config.get("wfc.service.connection.string");
	    String username = Config.get("wfc.service.connection.user");
	    String password = Config.get("wfc.service.connection.password");
	    Connection conn = null;
	    try {
	        Class.forName(driver); 
	        conn = DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
}
