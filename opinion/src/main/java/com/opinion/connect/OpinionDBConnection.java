package com.opinion.connect;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OpinionDBConnection {
	private static final Logger logger = Logger.getLogger(OpinionDBConnection.class.getName());

	public Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
		Connection con = null;
		InputStream fis = getClass().getClassLoader().getResourceAsStream("database.properties");
		Properties prop = new Properties();
		if (fis != null) {
			prop.load(fis);
			String dname = prop.getProperty("dbname");
			String url = prop.getProperty("url");
			String uname = prop.getProperty("username");
			String pass = prop.getProperty("password");
			try {
				Class.forName(dname);
				con = DriverManager.getConnection(url, uname, pass);
			}catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		} else {
			logger.log(Level.SEVERE, "Properties file not loading| reading");
		}

		return con;

	}

}
