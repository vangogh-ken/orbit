package org.cc.automate.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.cc.automate.db.entity.BasisAttribute;
import org.cc.automate.db.entity.BasisSubstance;
import org.cc.automate.db.entity.BasisSubstanceType;
import org.cc.automate.db.entity.BasisValue;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author Administrator
 * 数据库相关配置
 */
@Configuration
public class DBConfig {
	private static final Logger LOG = LoggerFactory.getLogger(DBConfig.class);
	/*
	 * 数据库属性
	 */
	@Value("${jdbc.driver}")
	public String driverClass;
	@Value("${jdbc.url}")
	public String url;
	@Value("${jdbc.username}")
	public String username;
	@Value("${jdbc.password}")
	public String password;
	
	/*
	 * mybatis属性
	 */
	@Value("${mybatis.cacheEnabled}")
	public String cacheEnabled;
	@Value("${mybatis.lazyLoadingEnabled}")
	public String lazyLoadingEnabled;
	@Value("${mybatis.aggressiveLazyLoading}")
	public String aggressiveLazyLoading;
	@Value("${mybatis.defaultStatementTimeout}")
	public int defaultStatementTimeout;
	
	
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			/*dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/automate?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true");
			dataSource.setUser("root");
			dataSource.setPassword("root");*/
			
			dataSource.setDriverClass(driverClass);
			dataSource.setJdbcUrl(url);
			dataSource.setUser(username);
			dataSource.setPassword(password);
			
			dataSource.setCheckoutTimeout(3000);
			dataSource.setAcquireRetryAttempts(0);
			dataSource.setAutomaticTestTable("test");
			dataSource.setIdleConnectionTestPeriod(60);
			dataSource.setBreakAfterAcquireFailure(false);
			
			dataSource.setMaxStatements(2000);
			dataSource.setMaxStatementsPerConnection(2000);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		LOG.info("init DataSource");
		return dataSource;
	}
	
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}
	
	@Bean(name = "mybatisProperties")
	public Properties mybatisProperties(){
		Properties properties = new Properties();
		/*properties.put("cacheEnabled", true);
		properties.put("lazyLoadingEnabled", true);
		properties.put("aggressiveLazyLoading", false);
		properties.put("defaultStatementTimeout", 1000);*/
		
		properties.put("cacheEnabled", cacheEnabled);
		properties.put("lazyLoadingEnabled", lazyLoadingEnabled);
		properties.put("aggressiveLazyLoading", aggressiveLazyLoading);
		properties.put("defaultStatementTimeout", defaultStatementTimeout);
		
		//properties.put("defaultExecutorType", "BATCH");
		//<!-- 配置默认的执行器。SIMPLE执行器没有什么特别之处。REUSE执行器重用预处 理语句。BATCH执行器重用语句和批量更新 -->
		return properties;
	}
	/**
	 * @return
	 * 
	 */
	@Bean(name = "sqlSessionFactoryBean")
	public SqlSessionFactoryBean sqlSessionFactoryBean(){
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		//sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("classpath:spring/mybatis.xml"));
		sqlSessionFactoryBean.setMapperLocations(new Resource[]{
				new ClassPathResource("org/cc/automate/db/entity/basis-attribute-mapper.xml"),
				new ClassPathResource("org/cc/automate/db/entity/basis-substance-mapper.xml"),
				new ClassPathResource("org/cc/automate/db/entity/basis-substance-type-mapper.xml"),
				new ClassPathResource("org/cc/automate/db/entity/basis-value-mapper.xml"),
				});
		sqlSessionFactoryBean.setConfigurationProperties(mybatisProperties());
		//sqlSessionFactoryBean.setPlugins(plugins);
		sqlSessionFactoryBean.setTypeAliases(new Class<?>[]{BasisSubstanceType.class, BasisSubstance.class, BasisAttribute.class, BasisValue.class});
		sqlSessionFactoryBean.setTypeAliasesPackage("org.cc.automate.db.entity");
		
		
		return sqlSessionFactoryBean;
	}
	
	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(){
		SqlSessionTemplate sqlSessionTemplate = null;
		try {
			sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean().getObject());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlSessionTemplate;
	}
	
	/**
	 * @return
	 * 事务管理器
	 */
	@Bean(name = "dataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }
}
