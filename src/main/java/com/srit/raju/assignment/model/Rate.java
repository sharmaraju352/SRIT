package com.srit.raju.assignment.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RATE")
public class Rate {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "RateId")
    private Long id;

    @Column(name = "RateDescription")
    private String description;

    @Column(name = "RateEffectiveDate", nullable = false)
    @NonNull
    private Date effectiveDate;

    @Column(name = "RateExpirationDate", nullable = false)
    @NonNull
    private Date expirationDate;

    @Column(name = "Amount", nullable = false)
    @NonNull
    private Integer amount;

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

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", effectiveDate=" + effectiveDate +
                ", expirationDate=" + expirationDate +
                ", amount=" + amount +
                '}';
    }
}
