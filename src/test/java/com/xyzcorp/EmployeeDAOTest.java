package com.xyzcorp;

import org.hsqldb.jdbc.JDBCDataSource;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeDAOTest {

    @Test
    public void testPersistIntegrationTest() throws SQLException {
        JDBCDataSource dataSource = new JDBCDataSource();
        dataSource.setURL("jdbc:hsqldb:hsql://localhost:9001/mydb");
        dataSource.setUser("SA");
        dataSource.setPassword("");

        EmployeeDAO employeeDAO = new EmployeeDAO(dataSource);

        Employee employee = new Employee("Rick", "Farah");
        employeeDAO.persist(employee);
    }

    @Test
    public void testExistsIntegrationTest() throws SQLException {
        JDBCDataSource dataSource = new JDBCDataSource();
        dataSource.setURL("jdbc:hsqldb:hsql://localhost:9001/mydb");
        dataSource.setUser("SA");
        dataSource.setPassword("");

        EmployeeDAO employeeDAO = new EmployeeDAO(dataSource);

        Optional<Employee> found = employeeDAO.getEmployeeBySSN
                ("234-12-4043");

        assertThat(found.isPresent()).isTrue();
        Employee employee = found.orElseGet(() -> new Employee("Sad", "Sam"));
        System.out.println(employee);
    }

}