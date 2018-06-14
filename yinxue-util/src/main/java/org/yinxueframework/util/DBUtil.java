package org.yinxueframework.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.yinxueframework.util.CloseUtil.close;

public abstract class DBUtil {
	
	private final static Logger LOG = LoggerFactory.getLogger(DBUtil.class);
	
	private static Properties pro;
	
	/**
	 * 加载根目录下的jdbc连接文件
	 */
	static {
		pro = new Properties();
		InputStream is = DBUtil.class.getResourceAsStream("/config/jdbc.properties");
		try {
			pro.load(is);
		} catch (IOException e) {
			LOG.error("加载失败");
		} finally {
			close(is);
		}
	}
	
	/**
	 * 获得数据库连接
	 * @return
	 */
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserName(), getPassword());
		} catch (Exception e) {
			LOG.error("未找到数据库驱动类");
		}
		return con;
	}
	

	/**
	 * 获得数据库连接地址
	 * @return
	 */
	private static String getUrl() {
		return pro.getProperty("url");
	}
	
	/**
	 * 获得数据库访问用户名
	 * @return
	 */
	private static String getUserName() {
		return pro.getProperty("username");
	}
	
	/**
	 * 获得数据访问用户密码
	 * @return
	 */
	private static String getPassword() {
		return pro.getProperty("password");
	}
	
	/**
	 * 获得数据库驱动程序
	 * @return
	 */
	private static String getDriver() {
		return pro.getProperty("driver");
	}
	
}
