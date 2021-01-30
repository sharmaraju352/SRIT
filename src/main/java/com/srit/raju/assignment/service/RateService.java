package com.srit.raju.assignment.service;

import com.srit.raju.assignment.dao.RateDao;
import com.srit.raju.assignment.dto.RateWithSurcharge;
import com.srit.raju.assignment.model.Rate;
import com.srit.raju.assignment.model.Surcharge;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Rate addRate(Rate rate){
        return rateDao.save(rate);
    }

    public Optional<RateWithSurcharge> searchRate(Long rateId) {
        Optional<Rate> rate = rateDao.findById(rateId);
        if(rate.isPresent()){
            Surcharge surcharge = restTemplate.getForObject(SURCHARGE_URL, Surcharge.class);
            RateWithSurcharge rateWithSurcharge = new RateWithSurcharge(rate.get(), surcharge);
            return Optional.of(rateWithSurcharge);
        }
        return Optional.empty();
    }

    public Optional<Rate> updateRate(Long rateId, Rate rate) {
        Optional<Rate> rateById = rateDao.findById(rateId);
        if(rateById.isPresent()){
            rate.setId(rateById.get().getId());
            return Optional.of(rateDao.save(rate));
        }
        return Optional.empty();
    }

    public void deleteRate(Long rateId) {
        rateDao.deleteById(rateId);
    }

}
