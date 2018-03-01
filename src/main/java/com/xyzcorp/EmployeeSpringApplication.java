package com.xyzcorp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation
        .AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

@ComponentScan(value = {"com.xyzcorp"})
public class EmployeeSpringApplication {
    public static void main(String[] args) throws EmployeeExistsException, SQLException {
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext();

        annotationConfigApplicationContext.register(EmployeeSpringApplication
                .class);

        annotationConfigApplicationContext.refresh();
        EmployeeService service = annotationConfigApplicationContext
                .getBean("employeeService", EmployeeService.class);

        service.hireEmployee(new Employee("Matt", "Lagrotte", "125-44-1133"));
    }
}
