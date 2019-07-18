package com.tw.apistackbase.service;

import com.tw.apistackbase.entity.Company;

import java.util.Collection;

public interface CompanyService {
    Company getById(int id);
    Collection<Company> getAll();
    Company save(Company company);

    void update(int id, Company company);

    void remove(int id);
}
