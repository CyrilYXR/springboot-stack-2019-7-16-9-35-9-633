package com.tw.apistackbase.controller;

import com.alibaba.fastjson.JSON;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllCompanies() throws Exception {
        String except = "{\"id\":1,\"companyName\":\"alibaba\",\"employeesNumber\":200,\"employees\":[{\"id\":1,\"name\":\"zhangsan\",\"age\":20,\"gender\":\"male\",\"salary\":8888.0}]}";

        MvcResult mvcResult = this.mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(1, jsonArray.getJSONObject(0).getInt("id"));
        assertEquals("alibaba", jsonArray.getJSONObject(0).getString("companyName"));
        assertEquals(200, jsonArray.getJSONObject(0).getInt("employeesNumber"));
        assertEquals(1, jsonArray.getJSONObject(0).getJSONArray("employees")
                .getJSONObject(0).getInt("id"));
        assertEquals(6, jsonArray.length());

    }

    @Test
    public void shouldReturnACompanyIdIs1() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(get("/companies/1"))
                .andExpect(status().isOk())
                .andReturn();

        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals(1, jsonObject.getInt("id"));
        assertEquals("alibaba", jsonObject.getString("companyName"));
        assertEquals(200, jsonObject.getInt("employeesNumber"));
        assertEquals(1, jsonObject.getJSONArray("employees")
                .getJSONObject(0).getInt("id"));
    }

    @Test
    public void shouldReturnAllEmployeesOfACompany() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(get("/companies/1/employees"))
                .andExpect(status().isOk())
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(1, jsonArray.getJSONObject(0).getInt("id"));
        assertEquals("zhangsan", jsonArray.getJSONObject(0).getString("name"));
        assertEquals(20, jsonArray.getJSONObject(0).getInt("age"));
        assertEquals("male", jsonArray.getJSONObject(0).getString("gender"));
        assertEquals(8888, jsonArray.getJSONObject(0).getDouble("salary"));
    }

    @Test
    public void shouldReturnCompaniesByPage() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(get("/companies?page=1&pageSize=5"))
                .andExpect(status().isOk())
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(1, jsonArray.getJSONObject(0).getInt("id"));
        assertEquals("alibaba", jsonArray.getJSONObject(0).getString("companyName"));
        assertEquals(200, jsonArray.getJSONObject(0).getInt("employeesNumber"));
        assertEquals(1, jsonArray.getJSONObject(0).getJSONArray("employees")
                .getJSONObject(0).getInt("id"));
        assertEquals(5, jsonArray.length());

    }

    @Test
    public void shouldReturnRightStatus() throws Exception {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "zhangsan", 20, "male", 8888));
        Company company = new Company(7, "abc", 200, employees);

        this.mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(company)))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult mvcResult = this.mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());

        assertEquals(7, jsonArray.length());

    }
}