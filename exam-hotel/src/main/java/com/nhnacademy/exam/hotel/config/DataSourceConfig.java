package com.nhnacademy.exam.hotel.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.dbcp2.initial-size}")
	private int initialSize;

	@Value("${spring.datasource.dbcp2.max-total}")
	private int maxTotal;

	@Value("${spring.datasource.dbcp2.max-idle}")
	private int maxIdle;

	@Value("${spring.datasource.dbcp2.min-idle}")
	private int minIdle;

	@Value("${spring.datasource.dbcp2.max-wait-millis}")
	private int maxWaitMillis;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setInitialSize(initialSize);
		dataSource.setMaxTotal(maxTotal);
		dataSource.setMaxIdle(maxIdle);
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxWaitMillis(maxWaitMillis);
		return dataSource;
	}
}
