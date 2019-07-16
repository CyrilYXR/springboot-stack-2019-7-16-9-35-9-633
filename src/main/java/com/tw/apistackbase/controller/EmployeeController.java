package com.tw.apistackbase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/employees")
public class EmployeeController {

    List<Employee> employees = new ArrayList<>();
    {
        employees.add(new Employee(1,"lisi", 20, "male", 8888));
        employees.add(new Employee(2,"wangwu", 20, "male", 8888));
    }

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id){
        Employee e = employees.stream().filter(employee -> employee.getId() == id).findFirst().get();
        return ResponseEntity.ok().body(e);
    }
}
