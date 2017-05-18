package com.fanwei;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {
	//相当于是项目环境，可以对配置文件进行获取
	@Autowired
	private Environment env;

	@Bean(destroyMethod = "close")
	public DataSource dataSource(){
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.name"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		//初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
		dataSource.setInitialSize(2);
		//最大连接池数量
		dataSource.setMaxActive(200);
		//最小连接池数量
		dataSource.setMinIdle(0);
		//获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
		dataSource.setMaxWait(60000);
		//用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。
		dataSource.setValidationQuery("SELECT 1");
		//申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
		dataSource.setTestOnBorrow(false);
		//建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
		dataSource.setTestWhileIdle(true);
		//是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
		dataSource.setPoolPreparedStatements(false);
		return dataSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
