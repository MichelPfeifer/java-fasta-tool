package com.oopis;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class FastaEntryRnaTest {

    @Test
    public void testCalculateGCContent() {
        FastaEntryRna fastaEntryRna = new FastaEntryRna("id", "AUCG", "RNA");
        double expectedGCContent = 50.0;
        double actualGCContent = fastaEntryRna.calculateGCContent();
        assertEquals(expectedGCContent, actualGCContent, 0.01);
    }

    @Test
    public void testCalculateMolecularWeight() {
        FastaEntryRna fastaEntryRna = new FastaEntryRna("id", "AUCG", "RNA");
        double expectedMolecularWeight = 1444.8;
        double actualMolecularWeight = fastaEntryRna.calculateMolecularWeight();
        assertEquals(expectedMolecularWeight, actualMolecularWeight, 0.01);
    }

    @Test
    public void testCountElements() {
        String sequence = "AUCG1234";
        char invalidNucleotide = '1';

        assertThrows(IllegalArgumentException.class, () -> {
            new FastaEntryRna("testID", sequence, "RNA");
        });
    }

}
