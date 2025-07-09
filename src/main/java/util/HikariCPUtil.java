package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;



public class HikariCPUtil {
	private static HikariDataSource dataSource;
	static {
		HikariConfig config = new HikariConfig();
		
		// Properties <String, String>
		// 설정 정보 관리, 파일
		Properties props = PropsLoaderUtil.getProperties("secret/db.properties");
		
		config.setJdbcUrl(props.getProperty("jdbc.url"));
		config.setUsername(props.getProperty("jdbc.username"));
		config.setPassword(props.getProperty("jdbc.password"));
		config.setDriverClassName(props.getProperty("jdbc.driver.classname"));
		
		config.setMaximumPoolSize(10);
		config.setMinimumIdle(5);
		config.setIdleTimeout(30000);
		config.setConnectionTimeout(30000);
		config.setPoolName("MyHikariCP");
		
		dataSource = new HikariDataSource(config);
	}
	public static DataSource getDataSource() {
		return dataSource;
	}
	public static void main(String[] args) {
		System.out.println(getDataSource());
		System.out.println(dataSource);
	}
}
