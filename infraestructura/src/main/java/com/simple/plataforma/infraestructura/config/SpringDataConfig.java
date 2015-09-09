package com.simple.plataforma.infraestructura.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.simple.plataforma.infraestructura.data.repository.base.BaseRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by ecandela on 30/08/2015.
 */



@Configuration
@ComponentScan({"com.simple.plataforma.infraestructura.data"}) // search the com.company package for @Component classes
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.simple.plataforma.infraestructura.data.repository", repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EnableJpaAuditing(auditorAwareRef = "customAuditorAware")
public class SpringDataConfig {


    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //dataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setUser(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setAcquireIncrement(Integer.parseInt(env.getProperty("c3p0.acquire_increment")));
        dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("c3p0.min_size")));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("c3p0.max_size")));
        dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("c3p0.max_idle_time")));
        dataSource.setUnreturnedConnectionTimeout(Integer.parseInt(env.getProperty("c3p0.unreturned_connection_timeout")));

        return dataSource ;

    }



    @Bean public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(Boolean.TRUE);
        vendorAdapter.setShowSql(Boolean.TRUE);

        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(vendorAdapter);
       // factory.setPackagesToScan("com.simple.plataforma.infraestructura.data.entity");
        factory.setPackagesToScan("com.simple.plataforma.dominio.modelo");



        Properties jpaProperties = new Properties();

        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.connection.autocommit", env.getProperty("hibernate.connection.autocommit"));
        jpaProperties.put("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
        jpaProperties.put("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));
        jpaProperties.put("hibernate.cache.provider_class", env.getProperty("hibernate.cache.provider_class"));
        jpaProperties.put("hibernate.cache.region.factory_class", env.getProperty("hibernate.cache.region.factory_class"));
        //<!--useful for debugging-->
        jpaProperties.put("hibernate.generate_statistics", env.getProperty("hibernate.generate_statistics"));

        factory.setJpaProperties(jpaProperties);
        factory.afterPropertiesSet();
        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        return factory;
    }


    @Bean public PlatformTransactionManager transactionManager() {
        EntityManagerFactory factory = entityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }


}
