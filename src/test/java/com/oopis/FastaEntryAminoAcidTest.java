package com.oopis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FastaEntryAminoAcidTest {

    @Test
    void testCalculateNetCharge() {
        // Test case 1
        FastaEntryAminoAcid entry1 = new FastaEntryAminoAcid("testID", "ARNDC", "PEPTIDE");
        double netCharge1 = entry1.calculateNetCharge(7.0);
        assertEquals(0.9539776359779892, netCharge1, 0.001);

        // Test case 2
        FastaEntryAminoAcid entry2 = new FastaEntryAminoAcid("testID", "AAAEEDD", "PEPTIDE");
        double netCharge2 = entry2.calculateNetCharge(7.0);
        assertEquals(-2.997061386325815, netCharge2, 0.001);

        // Test case 3
        FastaEntryAminoAcid entry3 = new FastaEntryAminoAcid("testID", "KRKH", "PEPTIDE");
        double netCharge3 = entry3.calculateNetCharge(7.0);
        assertEquals(4.088256257022341, netCharge3, 0.001);
    }

    @Test
    void testCalculateIsoelectricPoint() {
        FastaEntryAminoAcid entry = new FastaEntryAminoAcid("testID", "ARNDC", "PEPTIDE");
        double isoelectricPoint1 = entry.calculateIsoelectricPoint();
        assertEquals(9.009765625, isoelectricPoint1, 0.001);
    }

    @Test
    public void testCountElements() {
        String sequence = "RQLS1234";
        char invalidNucleotide = '1';

        assertThrows(IllegalArgumentException.class, () -> {
            new FastaEntryAminoAcid("testID", sequence, "PEPTIDE");
        });
    }
}