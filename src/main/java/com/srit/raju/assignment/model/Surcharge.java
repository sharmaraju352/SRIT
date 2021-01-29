package com.srit.raju.assignment.model;

public class Surcharge {
    private int surchargeRate;
    private String surchargeDescription;

    public int getSurchargeRate() {
        return surchargeRate;
    }

    public void setSurchargeRate(int surchargeRate) {
        this.surchargeRate = surchargeRate;
    }

    public String getSurchargeDescription() {
        return surchargeDescription;
    }

    public void setSurchargeDescription(String surchargeDescription) {
        this.surchargeDescription = surchargeDescription;
    }

    @Override
    public String toString() {
        return "Surcharge{" +
                "surchargeRate=" + surchargeRate +
                ", surchargeDescription='" + surchargeDescription + '\'' +
                '}';
    }
}
