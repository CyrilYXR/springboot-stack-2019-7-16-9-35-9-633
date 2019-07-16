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

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllEmployees() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(1, jsonArray.getJSONObject(0).getInt("id"));
        assertEquals("lisi", jsonArray.getJSONObject(0).getString("name"));
        assertEquals(20, jsonArray.getJSONObject(0).getInt("age"));
        assertEquals("male", jsonArray.getJSONObject(0).getString("gender"));
        assertEquals(8888, jsonArray.getJSONObject(0).getDouble("salary"));
        assertEquals(2, jsonArray.length());
    }

    @Test
    public void shouldReturnTheEmployeeWhichIdIs1() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andReturn();

        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals(1, jsonObject.getInt("id"));
        assertEquals("lisi", jsonObject.getString("name"));
        assertEquals(20, jsonObject.getInt("age"));
        assertEquals("male", jsonObject.getString("gender"));
        assertEquals(8888, jsonObject.getDouble("salary"));
    }

    @Test
    public void shouldReturnTheEmployeesPage() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(get("/employees?page=1&pageSize=5"))
                .andExpect(status().isOk())
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(1, jsonArray.getJSONObject(0).getInt("id"));
        assertEquals("lisi", jsonArray.getJSONObject(0).getString("name"));
        assertEquals(20, jsonArray.getJSONObject(0).getInt("age"));
        assertEquals("male", jsonArray.getJSONObject(0).getString("gender"));
        assertEquals(8888, jsonArray.getJSONObject(0).getDouble("salary"));
        assertEquals(2, jsonArray.length());
    }

    @Test
    public void shouldReturnAllMaleEmployees() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(get("/employees?gender=male"))
                .andExpect(status().isOk())
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(1, jsonArray.getJSONObject(0).getInt("id"));
        assertEquals("lisi", jsonArray.getJSONObject(0).getString("name"));
        assertEquals(20, jsonArray.getJSONObject(0).getInt("age"));
        assertEquals("male", jsonArray.getJSONObject(0).getString("gender"));
        assertEquals(8888, jsonArray.getJSONObject(0).getDouble("salary"));
        assertEquals(2, jsonArray.length());
    }
}
