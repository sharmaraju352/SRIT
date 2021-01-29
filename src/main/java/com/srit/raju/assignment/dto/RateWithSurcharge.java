package com.srit.raju.assignment.dto;

import com.srit.raju.assignment.model.Rate;
import com.srit.raju.assignment.model.Surcharge;

import java.util.Date;

public class RateWithSurcharge {
    private Long id;
    private String description;
    private Date effectiveDate;
    private Date expirationDate;
    private Integer amount;
    private Surcharge surcharge;

    public RateWithSurcharge() {
    }

    public RateWithSurcharge(Rate rate, Surcharge surcharge) {
        this.id = rate.getId();
        this.description = rate.getDescription();
        this.effectiveDate = rate.getEffectiveDate();
        this.expirationDate = rate.getExpirationDate();
        this.amount = rate.getAmount();
        this.surcharge = surcharge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Surcharge getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(Surcharge surcharge) {
        this.surcharge = surcharge;
    }

    @Override
    public String toString() {
        return "RateWithSurcharge{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", effectiveDate=" + effectiveDate +
                ", expirationDate=" + expirationDate +
                ", amount=" + amount +
                ", surcharge=" + surcharge +
                '}';
    }
}
