package com.donation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DonationProcessorTest {

    private DonationProcessor processor;

    @BeforeEach
    public void setUp() {
        processor = new DonationProcessor();
    }

    @Test
    public void testValidDonation() {
        Donation donation = new Donation("John Doe", 100.0, "USD");
        assertTrue(processor.processDonation(donation), "Valid donation should be processed successfully");
    }

    @Test
    public void testZeroAmountDonation() {
        Donation donation = new Donation("Jane Doe", 0.0, "USD");
        assertFalse(processor.processDonation(donation), "Zero amount donation should fail");
    }

    @Test
    public void testNegativeAmountDonation() {
        Donation donation = new Donation("Bob", -50.0, "EUR");
        assertFalse(processor.processDonation(donation), "Negative amount donation should fail");
    }

    @Test
    public void testEmptyDonorName() {
        Donation donation = new Donation("", 100.0, "USD");
        assertFalse(processor.processDonation(donation), "Empty donor name should fail");
    }

    @Test
    public void testNullDonation() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            processor.processDonation(null);
        });
        assertEquals("Donation cannot be null", exception.getMessage());
    }
}
