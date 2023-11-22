package com.oopis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FastaEntryFactoryTest {
    @Test
    public void testCreateFastaEntry_InvalidSequenceType() {
        String header = "testHeader";
        String sequence = "ATCG";
        String sequenceType = "invalid";

        assertThrows(InvalidSequenceTypeException.class, () -> {
            FastaEntryFactory.createFastaEntry(header, sequence, sequenceType);
        });
    }
}