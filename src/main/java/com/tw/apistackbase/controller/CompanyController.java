package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(companyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id) {
//        Company company = companies.get(id);
        Company company = companyService.getById(id);
        return ResponseEntity.ok().body(company);
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity getEmployees(@PathVariable int id) {
//        Company company = companies.get(id);
//        List<Employee> employees = company.getEmployees();
        Company company = companyService.getById(id);
        List<Employee> employees = company.getEmployees();
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping(params = {"page", "pageSize"})
    public ResponseEntity getCompaniesByPage(@RequestParam(name = "page", required = false) Integer page,
                                             @RequestParam(name = "pageSize", required = false) Integer pageSize) {

        Collection<Company> companies = companyService.getAll();
        List<Company> companyPage = companies.stream().skip((page - 1)*pageSize).limit(pageSize).collect(Collectors.toList());
        return ResponseEntity.ok().body(companyPage);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Company company){
//        this.companies.put(company.getId(), company);
        companyService.save(company);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody Company company){
        companyService.update(id, company);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
//        this.companies.remove(id);
        companyService.remove(id);
        return ResponseEntity.ok().build();
    }

}
