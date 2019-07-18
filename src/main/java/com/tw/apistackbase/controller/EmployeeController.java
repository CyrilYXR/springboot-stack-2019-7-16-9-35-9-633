package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<Employee> e = employees.stream().filter(employee -> employee.getId() == id).findFirst();
        if(e.isPresent()){
            return ResponseEntity.ok().body(e.get());
        }
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(params = {"page", "pageSize"})
    public ResponseEntity getAll(@RequestParam(name = "page", required = false) Integer page,
                                 @RequestParam(name = "pageSize", required = false) Integer pageSize){
        if(page == null && pageSize == null) {
            return ResponseEntity.ok().body(employees);
        }
        // TODO implements real page business
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping(params = {"gender"})
    public ResponseEntity search(@RequestParam(name = "gender", required = false) String gender){
//        if(gender != null ) {
            List<Employee> employeeList = employees.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
            return ResponseEntity.ok().body(employeeList);
//        }
//        if(page != null && pageSize != null) {
//            // TODO implements real page business
//            return ResponseEntity.ok().body(employees);
//        }
//        return ResponseEntity.ok().body(employees);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Employee employee){
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody Employee employee) {
        employees.stream().forEach(e -> {
            if(e.getId() == id){
                e.setAge(employee.getAge());
                e.setGender(employee.getGender());
                e.setName(employee.getName());
                e.setSalary(employee.getSalary());
            }
        });
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        for(Employee employee : employees){
            if(employee.getId() == id) {
                employees.remove(employee);
                break;
            }
        }
        return ResponseEntity.ok().build();
    }
}
