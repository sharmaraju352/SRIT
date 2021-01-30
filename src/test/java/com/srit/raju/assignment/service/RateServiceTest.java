package com.srit.raju.assignment.service;

import com.srit.raju.assignment.dao.RateDao;
import com.srit.raju.assignment.dto.RateWithSurcharge;
import com.srit.raju.assignment.model.Rate;
import com.srit.raju.assignment.model.Surcharge;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RateServiceTest {

    @Autowired
    private RateService rateService;
    @MockBean
    private RateDao rateDao;
    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testAddRate(){
        Rate mockRate = getMockRate();
        Mockito.when(rateDao.save(mockRate)).thenReturn(mockRate);
        assertThat(rateService.addRate(mockRate)).isEqualTo(mockRate);
    }

    @Test
    public void testSearchRate(){
        Rate mockRate = getMockRate();
        RateWithSurcharge mockRateWithSurcharge = getMockRateWithSurcharge();
        Mockito.when(rateDao.findById(anyLong())).thenReturn(Optional.of(mockRate));
        Mockito.when(restTemplate.getForObject(any(String.class), eq(Surcharge.class))).thenReturn(getMockSurcharge());
        assertThat(rateService.searchRate(1L).get()).isEqualTo(mockRateWithSurcharge);
    }

    @Test
    public void testUpdateRate(){
        Rate mockRate = getMockRate();
        Mockito.when(rateDao.findById(anyLong())).thenReturn(Optional.of(mockRate));
        Mockito.when(rateDao.save(mockRate)).thenReturn(mockRate);
        assertThat(rateService.updateRate(1L, mockRate).get()).isEqualTo(mockRate);
    }

    @Test
    public void testDeleteRate(){
        rateService.deleteRate(1L);
        verify(rateDao, times(1)).deleteById(1L);
    }

    private Rate getMockRate(){
        try {
            return new Rate(1L, "Rate Description", new SimpleDateFormat("yyyy-mm-dd").parse("2018-10-15"), new SimpleDateFormat("yyyy-mm-dd").parse("2021-10-15"), 100);
        }catch (ParseException pe){}
        return null;
    }

    private RateWithSurcharge getMockRateWithSurcharge(){
        return new RateWithSurcharge(getMockRate(), getMockSurcharge());
    }

    private Surcharge getMockSurcharge(){
        return new Surcharge(100, "VAT");
    }
}