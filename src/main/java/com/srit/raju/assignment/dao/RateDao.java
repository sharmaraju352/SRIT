package com.srit.raju.assignment.dao;

import com.srit.raju.assignment.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateDao extends JpaRepository<Rate, Long> {
}
