package com.xyzcorp;

import org.hsqldb.jdbc.JDBCDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataSourceFactory {

    @Bean
    public static JDBCDataSource create() {
        JDBCDataSource ds = new JDBCDataSource();
        ds.setUser("SA");
        ds.setPassword("");
        ds.setURL("jdbc:hsqldb:hsql://localhost:9001/mydb");
        return ds;
    }
}
