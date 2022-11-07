package com.example.application.controller;

import com.example.application.Application;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
class TarifaControllerIT {

    private String date1 = LocalDateTime.of(2020, 6, 14, 10, 0, 0 ).format(DateTimeFormatter.ISO_DATE_TIME);
    private String date2 = LocalDateTime.of(2020, 6, 14, 16, 0, 0 ).format(DateTimeFormatter.ISO_DATE_TIME);
    private String date3 = LocalDateTime.of(2020, 6, 14, 21, 0, 0 ).format(DateTimeFormatter.ISO_DATE_TIME);
    private String date4 = LocalDateTime.of(2020, 6, 15, 10, 0, 0 ).format(DateTimeFormatter.ISO_DATE_TIME);
    private String date5 = LocalDateTime.of(2020, 6, 16, 21, 0, 0 ).format(DateTimeFormatter.ISO_DATE_TIME);

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPrices() throws Exception {
        testPriceList(date1, 1);
        testPriceList(date2, 2);
        testPriceList(date3, 1);
        testPriceList(date4, 3);
        testPriceList(date5, 4);
    }

    private void testPriceList(String date, Integer priceList) throws Exception {
        String dateParam = "?date="+date;
        String url = "/api/brands/1/products/35455/tarifa"+dateParam;
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultPrice = result.getResponse().getContentAsString();

        DocumentContext json = JsonPath.parse(resultPrice);
        assertEquals(priceList, json.read("$.priceList"));
    }
}
