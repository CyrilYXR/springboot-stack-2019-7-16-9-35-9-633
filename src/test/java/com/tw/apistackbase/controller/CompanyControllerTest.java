package com.tw.apistackbase.controller;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    }
}