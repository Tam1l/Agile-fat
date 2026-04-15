package com.donation;

public class DonationProcessor {

    public boolean processDonation(Donation donation) {
        if (donation == null) {
            throw new IllegalArgumentException("Donation cannot be null");
        }
        
        if (donation.getAmount() <= 0) {
            System.out.println("Invalid donation amount.");
            return false;
        }

        if (donation.getDonorName() == null || donation.getDonorName().trim().isEmpty()) {
            System.out.println("Invalid donor name.");
            return false;
        }

        // Simulate successful processing
        System.out.println("Successfully processed donation of " + donation.getAmount() + " " + donation.getCurrency() + " from " + donation.getDonorName());
        return true;
    }
}
