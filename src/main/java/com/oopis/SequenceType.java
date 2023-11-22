package com.oopis;

/**
 * Das Enum SequenceType speichert mögliche Zustände des von der Kommandozeile übergebenen Sequenztyps ab. Dadurch soll
 * erreicht werden, dass wirklich nur gültige Sequenztypen übergeben werden
 */

public enum SequenceType {
    DNA,
    RNA,
    PEPTIDE,
    AMBIGUOUS;

    // Upper and lower case
    /**
     * Diese Methode ist dazu da, den von der Kommandozeile übergebenen String des Sequenztyps als Input zu erhalten,
     * diesen mit den Namen der Values des Enums abzugleichen und das passende Enum als Sequenztyp zurückzugeben. Im
     * Falle eines Mismatches wird kein Sequenztyp zurückgegeben.
     * @param inputString - Von der Kommandozeile übergebener String des Sequenztyps
     * @return - Sequenztyp type, wenn Match. Null, wenn Mismatch
     */
    public static SequenceType convertString(String inputString){
        for(SequenceType type : SequenceType.values()){
            if(type.name().equalsIgnoreCase(inputString)) {
                return type;
            }
        }
        throw new InvalidSequenceTypeException("Invalid sequence type: " + inputString);
    }
}
