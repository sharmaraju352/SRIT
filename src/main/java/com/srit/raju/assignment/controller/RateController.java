package com.srit.raju.assignment.controller;

import com.srit.raju.assignment.dto.RateWithSurcharge;
import com.srit.raju.assignment.model.Rate;
import com.srit.raju.assignment.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/rate")
public class RateController {

    @Autowired
    RateService rateService;

    @GetMapping("/{rateId}")
    public Optional<RateWithSurcharge> searchRate(@PathVariable Long rateId){
        return rateService.searchRate(rateId);
    }

    @PostMapping
    public void addRate(@RequestBody Rate rate){
        rateService.addRate(rate);
    }

    @PutMapping("{rateId}")
    public void updateRate(@PathVariable Long rateId, @RequestBody Rate rate){
        rateService.updateRate(rateId, rate);
    }

    @DeleteMapping("{rateId}")
    public void deleteRate(@PathVariable Long rateId){
        rateService.deleteRate(rateId);
    }

}
