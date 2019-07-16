package com.tw.apistackbase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController()
@RequestMapping("/companies")
public class CompanyController {

    Map<Integer, Company> companies = new HashMap<>();
    {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "zhangsan", 20, "male", 8888));
        companies.put(1, new Company(1, "alibaba", 200, employees));
        companies.put(2, new Company(2, "baidu", 200, employees));
        companies.put(3, new Company(3, "google", 200, employees));
        companies.put(4, new Company(4, "oocl", 200, employees));
        companies.put(5, new Company(5, "cargosmall", 200, employees));
        companies.put(6, new Company(6, "aaa", 200, employees));
    }

//    @GetMapping
//    public ResponseEntity getAll() {
//        return ResponseEntity.ok().body(companies.values());
//    }

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

    @GetMapping
    public ResponseEntity getCompaniesByPage(@RequestParam(name = "page", required = false) Integer page,
                                             @RequestParam(name = "pageSize", required = false) Integer pageSize) {

        if(page == null && pageSize == null) {
            // get all companies list
            return ResponseEntity.ok().body(companies.values());
        }

        List<Company> companyPage = new ArrayList<>();
        for(int i=(page-1)*pageSize; i<companies.size() && i<pageSize+(page-1)*pageSize; i++) {
            companyPage.add(companies.get(i+1));
        }
        return ResponseEntity.ok().body(companyPage);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Company company){
        this.companies.put(company.getId(), company);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody Company company){
        this.companies.put(id, company);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        this.companies.remove(id);
        return ResponseEntity.ok().build();
    }

}
