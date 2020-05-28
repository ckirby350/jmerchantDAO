package com.radsyn.jmerchdao;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import java.sql.Connection;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class BeanConfig {
	
	/*** To Force HTTPS access only
	@Bean
	public ServletWebServerFactory servletContainer() {
	    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
	        @Override
	        protected void postProcessContext(Context context) {
	            SecurityConstraint securityConstraint = new SecurityConstraint();
	            securityConstraint.setUserConstraint("CONFIDENTIAL");
	            SecurityCollection collection = new SecurityCollection();
	            collection.addPattern("/*");
	            securityConstraint.addCollection(collection);
	            context.addConstraint(securityConstraint);
	        }
	    };
	    tomcat.addAdditionalTomcatConnectors(redirectConnector());
	    return tomcat;
	}

	private Connector redirectConnector() {
	    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	    connector.setScheme("http");
	    connector.setPort(8080);
	    connector.setSecure(false);
	    connector.setRedirectPort(8443);
	    return connector;
	}
	***/
	
	
	
	    @Bean(name="entityManagerFactory")
	    public LocalSessionFactoryBean sessionFactory() {
	        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource());
	        sessionFactory.setPackagesToScan("com.radsyn.jmerchdao.model");
	        sessionFactory.setHibernateProperties(hibernateProperties());	 
	        return sessionFactory;
	    }	  
	 
	     
	    @Bean
	    public DataSource dataSource() {
	        BasicDataSource dataSource = new BasicDataSource();
	        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://localhost:3306/jmerchant");
	        dataSource.setUsername("root");
	        dataSource.setPassword("R@dsyn01");
	        dataSource.setMaxTotal(100);
	        dataSource.setMaxIdle(0);
	        dataSource.setMaxWaitMillis(10000);
	        dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
	        
	        return dataSource;
	    }

	    /***
	    @Bean(name="transactionManager")
	    public PlatformTransactionManager hibernateTransactionManager() {
	        HibernateTransactionManager transactionManager
	          = new HibernateTransactionManager();
	        transactionManager.setSessionFactory(sessionFactory().getObject());	        
	        return transactionManager;
	    }
	    ***/
	    
	    @Bean
	    public PlatformTransactionManager transactionManager() {
	        JpaTransactionManager transactionManager = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(sessionFactory().getObject());
	     
	        return transactionManager;
	    }
	     
	    @Bean
	    public org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	        return new org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor();
	    }
	     
	        
	 
	    private final Properties hibernateProperties() {
	        Properties hibernateProperties = new Properties();
	               
	        hibernateProperties.setProperty(
	          "hibernate.dialect", "org.hibernate.dialect.MariaDB103Dialect");
	        hibernateProperties.setProperty(
	          "hibernate.show_sql", "false");
	        hibernateProperties.setProperty(
	  	          "cache.provider_class", "org.hibernate.cache.NoCacheProvider");
	        return hibernateProperties;
	    }
	    
	    /***
	    @Bean
	    NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
	        return new NamedParameterJdbcTemplate(dataSource);
	    }
	    ****/
	     
	
	/***
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Bean
	public SessionFactory getSessionFactory() {
	    if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
	        throw new NullPointerException("factory is not a hibernate factory");
	    }
	    return entityManagerFactory.unwrap(SessionFactory.class);
	}
	/***/

}
