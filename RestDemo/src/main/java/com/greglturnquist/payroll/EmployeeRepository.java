package com.greglturnquist.payroll;

import org.springframework.data.repository.CrudRepository;

// JPA CRUD operations
// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}