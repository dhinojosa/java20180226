package com.xyzcorp;

//import sun.jvm.hotspot.oops.ReceiverTypeData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class EmployeeDAO {
    private final DataSource ds;

    @Autowired
    public EmployeeDAO(DataSource ds) {
        this.ds = ds;
    }

    public boolean persist(Employee employee) throws SQLException {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT " +
                     "INTO Employee (FIRSTNAME, LASTNAME, SSN) VALUES (?, ?, ?)")) {
             ps.setString(1, employee.getFirstName());
             ps.setString(2, employee.getLastName());
             ps.setString(3, employee.getSSN());
             return ps.execute();
        }
    }

    public Optional<Employee> getEmployeeBySSN(String ssn) throws SQLException {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * " +
                     "FROM EMPLOYEE WHERE ssn = ?")) {
            ps.setString(1, ssn);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {  //?
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                return Optional.of(new Employee(firstName, lastName));
            }
            return Optional.empty();
        }
    }
}
