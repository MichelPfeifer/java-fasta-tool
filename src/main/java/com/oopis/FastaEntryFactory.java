package com.oopis;

/**
 * Klasse nach dem Factory-Pattern Entwurfsmuster, welche dazu da ist, alle
 * unterschiedlichen FastaEntry-Instanzen zu erzeugen.
 */

public class FastaEntryFactory {

    // Factory Method
    /**
     * Erzeugt unterschiedliche FastaEntry-Instanzen je nach übergebenem Sequenztyp
     * @param header - Header des Fasta-Eintrags
     * @param sequence - Sequenz des Fasta-Eintrags
     * @param sequenceType - Sequenztyp des Fasta-Eintrags
     * @return - FastaEntry Objekt entsprechend des Sequenztyps
     */
    public static FastaEntry createFastaEntry(String header, String sequence, String sequenceType){
        if(sequenceType.equalsIgnoreCase("dna")){
            return new FastaEntryDna(header, sequence, sequenceType);
        } else if(sequenceType.equalsIgnoreCase("rna")){
            return new FastaEntryRna(header, sequence, sequenceType);
        } else if(sequenceType.equalsIgnoreCase("peptide")){
            return new FastaEntryAminoAcid(header, sequence, sequenceType);
        } else if(sequenceType.equalsIgnoreCase("ambiguous")){
            return new FastaEntryAmbiguous(header, sequence, sequenceType);
        } else{
            throw new InvalidSequenceTypeException("Invalid sequence type: " + sequenceType);
        }
    }
}
