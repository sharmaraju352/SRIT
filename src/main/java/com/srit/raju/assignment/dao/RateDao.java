package com.srit.raju.assignment.dao;

import com.srit.raju.assignment.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateDao extends JpaRepository<Rate, Long> {
}
