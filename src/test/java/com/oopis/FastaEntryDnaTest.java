package com.oopis;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FastaEntryDnaTest {
    @Test
    public void testCalculateGCContent() {
        FastaEntryDna fastaEntryDna = new FastaEntryDna("testID", "ATCGGCAT", "DNA");
        double expectedGCContent = 50.0;
        double actualGCContent = fastaEntryDna.calculateGCContent();
        assertEquals(expectedGCContent, actualGCContent, 0.01);
    }

    @Test
    public void testCalculateMolecularWeight() {
        FastaEntryDna fastaEntryDna = new FastaEntryDna("testID", "ATCG", "DNA");
        double expectedMolecularWeight = 1173.84;
        double actualMolecularWeight = fastaEntryDna.calculateMolecularWeight();
        assertEquals(expectedMolecularWeight, actualMolecularWeight, 0.01);
    }

    @Test
    public void testCalculateMeltingTemperature() {
        FastaEntryDna fastaEntryDna = new FastaEntryDna("testID", "ATCG", "DNA");
        double expectedMeltingTemperature = 12;
        double actualMeltingTemperature = fastaEntryDna.calculateMeltingTemperature();
        assertEquals(expectedMeltingTemperature, actualMeltingTemperature, 0.01);
    }

    @Test
    public void testCountElements_InvalidNucleotide() {
        String sequence = "ATCG1234";
        char invalidNucleotide = '1';

        assertThrows(IllegalArgumentException.class, () -> {
            new FastaEntryDna("testID", sequence, "DNA");
        });
    }


}
