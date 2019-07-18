package com.tw.apistackbase.service.impl;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company getById(int id) {
        return companyRepository.getById(id);
    }

    @Override
    public Collection<Company> getAll() {
        return companyRepository.getAll();
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void update(int id, Company company) {
        companyRepository.update(id, company);
    }

    @Override
    public void remove(int id) {
        companyRepository.remove(id);
    }
}
