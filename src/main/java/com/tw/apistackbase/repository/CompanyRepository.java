package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompanyRepository {

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

    public Collection<Company> getAll() {
        return companies.values();
    }

    public Company getById(int id) {
        return companies.get(id);
    }

    public Company save(Company company) {
        companies.put(company.getId(), company);
        return companies.get(company.getId());
    }

    public void update(int id, Company company) {
        companies.put(id, company);
    }

    public void remove(int id) {
        companies.remove(id);
    }
}
