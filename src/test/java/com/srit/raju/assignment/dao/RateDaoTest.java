package com.srit.raju.assignment.dao;

import com.srit.raju.assignment.model.Rate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RateDaoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RateDao rateDao;

    @Test
    public void testAddRate() {
        Rate rate = getMockRate();
        Rate savedInDb = entityManager.merge(rate);
        Rate getFromDb = rateDao.findById(savedInDb.getId()).get();

        assertThat(getFromDb).isEqualTo(savedInDb);
    }

    @Test
    public void testSearchRate(){
        Rate rate = getMockRate();
        Rate rateSavedInDb = entityManager.merge(rate);
        Rate rateFromInDb = rateDao.findById(rateSavedInDb.getId()).get();

        assertThat(rateSavedInDb).isEqualTo(rateFromInDb);
    }

    @Test
    public void testUpdateRate(){
        Rate rate1 = new Rate(1L, "desc1", new Date(), new Date(), 100);
        Rate rate2 = new Rate(1L, "desc2", new Date(), new Date(), 100);
        entityManager.merge(rate1);
        Rate savedRate = entityManager.merge(rate2);
        Rate rateFromInDb = rateDao.findById(savedRate.getId()).get();
        assertThat(rateFromInDb.getDescription()).isEqualTo("desc2");
    }

    @Test
    public void testDeleteRate(){
        Rate rate1 = getMockRate();
        Rate rate2 = new Rate(2L, null, new Date(), new Date(), 100);
        Rate persist = entityManager.merge(rate1);
        entityManager.merge(rate2);
        entityManager.remove(persist);
        List<Rate> allRates = rateDao.findAll();
        assertThat(allRates.size()).isEqualTo(1);
    }

    private Rate getMockRate() {
        try {
            return new Rate(1L, "Rate Description", new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-15"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-15"), 100);
        } catch (ParseException pe) {
        }
        return null;
    }
}
