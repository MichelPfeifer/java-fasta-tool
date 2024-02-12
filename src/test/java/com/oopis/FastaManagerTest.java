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
        fastaManager.parseFastaFile(
                "/home/michel/IdeaProjects/java-fasta-tool/test_data/test_fasta_dna_1.fasta",
                "DNA");
        ArrayList<FastaEntryDna> dnaEntries = fastaManager.getDnaEntries();
        assertEquals(4, dnaEntries.size());
    }


    @Test
    public void testParseFastaFile_ValidFileRna() throws IOException {
        fastaManager.parseFastaFile(
                "/home/michel/IdeaProjects/java-fasta-tool/test_data/test_fasta_rna_1.fasta",
                "RNA");
        ArrayList<FastaEntryRna> rnaEntries = fastaManager.getRnaEntries();
        assertEquals(2, rnaEntries.size());
    }

    @Test
    public void testParseFastaFile_ValidFilePeptide() throws IOException {
        fastaManager.parseFastaFile(
                "/home/michel/IdeaProjects/java-fasta-tool/test_data/test_fasta_peptide_1.fasta",
                "PEPTIDE");
        ArrayList<FastaEntryAminoAcid> aaEntries = fastaManager.getAaEntries();
        assertEquals(3, aaEntries.size());
    }

}
