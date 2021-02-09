package ru.magenta.distance_calculate.configurations;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class DBConnectionConf {

    @Bean
    @Primary
    public DataSource getDataSource() throws NamingException {
        //Если запускать war на tomcat. У меня не получилось поменять стандарт. начало ссылки на java:magenta/
//        Context initCtx = new InitialContext();
//        return  (DataSource) initCtx.lookup("java:comp/env/datasource/test-distance-calculator");

        //Если запускать как SpringBootApplication
        HikariDataSource dSource = new HikariDataSource();
        dSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dSource.setJdbcUrl("jdbc:mysql://localhost:3306/DISTANCE-CALCULATOR");
        dSource.setUsername("root");
        dSource.setPassword("root");
        dSource.setMaximumPoolSize(10);
        dSource.setAutoCommit(false);
        return dSource;
    }

    @Bean
    public JdbcTemplate getTemplate(@Autowired DataSource dSource){
        return new JdbcTemplate(dSource);
    }
}
