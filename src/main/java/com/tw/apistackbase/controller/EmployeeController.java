package com.tw.apistackbase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping
//    public ResponseEntity getAll(){
//        return ResponseEntity.ok().body(employees);
//    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id){
        Employee e = employees.stream().filter(employee -> employee.getId() == id).findFirst().get();
        return ResponseEntity.ok().body(e);
    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam(name = "page", required = false) Integer page,
                                 @RequestParam(name = "pageSize", required = false) Integer pageSize){
        if(page == null && pageSize == null) {
            return ResponseEntity.ok().body(employees);
        }
        // TODO implements real page business
        return ResponseEntity.ok().body(employees);
    }
}
