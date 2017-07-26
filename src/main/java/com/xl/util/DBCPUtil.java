package com.xl.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DBCPUtil {
	private static Properties properties = new Properties();
	private static DataSource dataSource;

	static {
		try {
			FileInputStream is = new FileInputStream("src/main/resources/dbcp.properties");
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			dataSource = BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
