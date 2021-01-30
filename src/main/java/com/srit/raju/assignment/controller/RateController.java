package com.srit.raju.assignment.controller;

import com.srit.raju.assignment.dto.RateWithSurcharge;
import com.srit.raju.assignment.model.Rate;
import com.srit.raju.assignment.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/rate")
public class RateController {

    @Autowired
    RateService rateService;

    @GetMapping("/{rateId}")
    public ResponseEntity<Object> searchRate(@PathVariable Long rateId){
        return rateService.searchRate(rateId);
    }

    @PostMapping
    public ResponseEntity<Object> addRate(@RequestBody Rate rate){
        return rateService.addRate(rate);
    }

    @PutMapping("{rateId}")
    public ResponseEntity<Object> updateRate(@PathVariable Long rateId, @RequestBody Rate rate){
        return rateService.updateRate(rateId, rate);
    }

    @DeleteMapping("{rateId}")
    public ResponseEntity<Object> deleteRate(@PathVariable Long rateId){
        return rateService.deleteRate(rateId);
    }

}
