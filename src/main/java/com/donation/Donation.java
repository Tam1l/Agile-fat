package com.donation;

public class Donation {
    private String donorName;
    private double amount;
    private String currency;

    public Donation(String donorName, double amount, String currency) {
        this.donorName = donorName;
        this.amount = amount;
        this.currency = currency;
    }

    public String getDonorName() { return donorName; }
    public void setDonorName(String donorName) { this.donorName = donorName; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}
