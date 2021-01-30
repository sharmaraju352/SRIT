package com.srit.raju.assignment.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.srit.raju.assignment.dao.RateDao;
import com.srit.raju.assignment.dto.RateWithSurcharge;
import com.srit.raju.assignment.model.Rate;
import com.srit.raju.assignment.model.Surcharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;
import static com.srit.raju.assignment.constant.RateConstants.SURCHARGE_URL;

@Service
public class RateService {
    @Autowired
    private RateDao rateDao;
    @Autowired
    private RestTemplate restTemplate;

    private static final String SERVER_DOWN_MESSAGE = "Server is down. Please try again after some time";

    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public ResponseEntity<Object> addRate(Rate rate){
        return new ResponseEntity<>(rateDao.save(rate), HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public ResponseEntity<Object> searchRate(Long rateId) {
        Optional<Rate> rate = rateDao.findById(rateId);
        if(rate.isPresent()){
            Surcharge surcharge = restTemplate.getForObject(SURCHARGE_URL, Surcharge.class);
            RateWithSurcharge rateWithSurcharge = new RateWithSurcharge(rate.get(), surcharge);
            return new ResponseEntity<>(Optional.of(rateWithSurcharge), HttpStatus.OK);
        }
        return new ResponseEntity<>("Requested rate not found", HttpStatus.NOT_FOUND);
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public ResponseEntity<Object> updateRate(Long rateId, Rate rate) {
        Optional<Rate> rateById = rateDao.findById(rateId);
        if(rateById.isPresent()){
            rate.setId(rateById.get().getId());
            return new ResponseEntity<>(Optional.of(rateDao.save(rate)), HttpStatus.OK);
        }
        return new ResponseEntity<>("Rate not found", HttpStatus.NOT_FOUND);
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public ResponseEntity<Object> deleteRate(Long rateId) {
        Optional<Rate> rateById = rateDao.findById(rateId);
        if(rateById.isPresent()){
            rateDao.deleteById(rateId);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>("Rate not found", HttpStatus.NOT_FOUND);
    }

    // Fallback methods
    public ResponseEntity<Object> fallbackMethod(Rate rate){
        return new ResponseEntity<>(SERVER_DOWN_MESSAGE, HttpStatus.SERVICE_UNAVAILABLE);
    }
    public ResponseEntity<Object> fallbackMethod(Long rateId){
        return new ResponseEntity<>(SERVER_DOWN_MESSAGE, HttpStatus.SERVICE_UNAVAILABLE);
    }
    public ResponseEntity<Object> fallbackMethod(Long rateId, Rate rate){
        return new ResponseEntity<>(SERVER_DOWN_MESSAGE, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
