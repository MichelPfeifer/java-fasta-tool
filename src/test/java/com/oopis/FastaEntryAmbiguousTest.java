package com.oopis;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FastaEntryAmbiguousTest {

    @Test
    public void testCountElements() {
        String sequence = "ATGCGCTAAATGGGC";
        FastaEntryAmbiguous entry = new FastaEntryAmbiguous("ID1", sequence, "AMBIGUOUS");

        int countA = entry.countElements(sequence, 'A');
        int countT = entry.countElements(sequence, 'T');
        int countG = entry.countElements(sequence, 'G');
        int countC = entry.countElements(sequence, 'C');

        assertEquals(4, countA);
        assertEquals(3, countT);
        assertEquals(5, countG);
        assertEquals(3, countC);
    }
}
