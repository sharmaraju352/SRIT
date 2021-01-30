package com.srit.raju.assignment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srit.raju.assignment.dto.RateWithSurcharge;
import com.srit.raju.assignment.model.Rate;
import com.srit.raju.assignment.model.Surcharge;
import com.srit.raju.assignment.service.RateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@WebMvcTest(RateController.class)
public class RateControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RateService rateService;

    @Test
    public void testSearchRate() throws Exception {
        RateWithSurcharge mockRateWithSurcharge = getMockRateWithSurcharge();
        Mockito.when(rateService.searchRate(Mockito.anyLong())).thenReturn(Optional.of(mockRateWithSurcharge));

        String URI = "/api/rate/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(mockRateWithSurcharge);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    @Test
    public void testAddRate() throws Exception {
        Rate mockRate = getMockRate();
        Mockito.when(rateService.addRate(Mockito.any(Rate.class))).thenReturn(mockRate);

        String inputInJson = this.mapToJson(mockRate);
        String URI = "/api/rate";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testUpdateRate() throws Exception {
        Rate mockRate = getMockRate();
        Mockito.when(rateService.updateRate(anyLong(), Mockito.any(Rate.class))).thenReturn(Optional.of(mockRate));

        String inputInJson = this.mapToJson(mockRate);
        String URI = "/api/rate/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testDeleteRate() throws Exception {
        String URI = "/api/rate/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private Rate getMockRate() {
        try {
            return new Rate(1L, "Rate Description", new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-15"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-15"), 100);
        } catch (ParseException pe) {
        }
        return null;
    }

    private Surcharge getMockSurcharge(){
        return new Surcharge(100, "VAT");
    }

    private RateWithSurcharge getMockRateWithSurcharge(){
        return new RateWithSurcharge(getMockRate(), getMockSurcharge());
    }

    private String mapToJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
            objectMapper.setDateFormat(df);
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException je) {
        }
        return null;
    }
}