package com.tw.apistackbase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    Map<Integer, Company> companies = new HashMap<>();
    {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "zhangsan", 20, "male", 8888));
        companies.put(1, new Company(1, "alibaba", 200, employees));
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(companies.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Company company = companies.get(id);
        return ResponseEntity.ok().body(company);
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity getEmployees(@PathVariable int id) {
        Company company = companies.get(id);
        List<Employee> employees = company.getEmployees();
        return ResponseEntity.ok().body(employees);
    }

}
