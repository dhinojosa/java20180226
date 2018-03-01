package com.xyzcorp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Optional;

@Component
public class EmployeeService {


    private final EmployeeDAO dao;

    @Autowired
    public EmployeeService(EmployeeDAO dao) {
        this.dao = dao;
    }

    public void hireEmployee(Employee employee) throws SQLException, EmployeeExistsException {
        Optional<Employee> employeeOptional =
                dao.getEmployeeBySSN(employee.getSSN());
        if (employeeOptional.isPresent()) {
            throw new EmployeeExistsException("Employee exists");
        } else {
            dao.persist(employee);
        }
    }
}
