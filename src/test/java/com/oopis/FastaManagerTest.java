package com.oopis;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FastaManagerTest {

    private FastaManager fastaManager;

    @BeforeEach
    public void setUp() {
        fastaManager = FastaManager.getInstance();
    }

    @Test
    public void testParseFastaFile_ValidFileDna() throws IOException {
        fastaManager.parseFastaFile("C:/Users/pfeif/Desktop/fasta_test_Kopie_v5Kopie.txt", "DNA");
        ArrayList<FastaEntryDna> dnaEntries = fastaManager.getDnaEntries();
        assertEquals(4, dnaEntries.size());
    }


    @Test
    public void testParseFastaFile_ValidFileRna() throws IOException {
        fastaManager.parseFastaFile("C:/Users/pfeif/Desktop/fasta_test_v2_rna.txt", "RNA");
        ArrayList<FastaEntryRna> rnaEntries = fastaManager.getRnaEntries();
        assertEquals(2, rnaEntries.size());
    }

    @Test
    public void testParseFastaFile_ValidFilePeptide() throws IOException {
        fastaManager.parseFastaFile("C:/Users/pfeif/Desktop/fasta_test_peptide.txt", "PEPTIDE");
        ArrayList<FastaEntryAminoAcid> aaEntries = fastaManager.getAaEntries();
        assertEquals(3, aaEntries.size());
    }

}
