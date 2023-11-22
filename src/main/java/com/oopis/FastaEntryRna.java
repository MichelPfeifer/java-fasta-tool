package com.oopis;

/**
 * Repräsentiert RNA-Sequenzen und weist diesen spezifische Eigenschaften zu.
 * Beinhaltet zudem Methoden zur Berechnung von Parametern.
 */

public class FastaEntryRna extends FastaEntry{

    //Attributes
    private int length;
    private int aCount;
    private int cCount;
    private int gCount;
    private int uCount;

    // Constructor
    /**
     * Konstruktor weist bei Aufruf den Attributen der geerbten Superklasse Werte zu
     * Zählt Anzahl der einzelnen Nukleotide in der RNA-Sequenz und weist die Anzahl
     * zusätzlichen Objekten zu (über countElements()).
     * @param id - Header des FastaEntries
     * @param sequence - RNA-Sequenz des FastaEntries
     * @param type - Sequenztyp (RNA)
     */
    public FastaEntryRna(String id, String sequence, String type){
        super(id, sequence, type);
        this.length = sequence.length();
        this.aCount = countElements(sequence, 'A');
        this.cCount = countElements(sequence, 'C');
        this.gCount = countElements(sequence, 'G');
        this.uCount = countElements(sequence, 'U');
    }

    // Methods
    /**
     * Hier ist die Logik drin geschrieben, nach welcher die Methode countElements auf Korrektheit der Sequenz prüft
     * @param nucleotide
     * @return boolean
     */
    private boolean isValidNucleotide(char nucleotide) {
        nucleotide = Character.toUpperCase(nucleotide);
        // Check if the nucleotide is one of the valid characters (A, C, G, U)
        return nucleotide == 'A' || nucleotide == 'C' || nucleotide == 'G' || nucleotide == 'U';
    }
    // Count Nucleotides
    /**
     * Zählt die Anzahl der Nukleotide in der RNA-Sequenz
     * @param sequence - Enthält die Inputsequenz
     * @param element - Inputsequenz wird mit diesem Character abgeglichen. Soll
     *                  idealerweise Bestandteil der Sequenz sein.
     * @return - Gibt Integer zurück, welcher die Anzahl des Characters in der Sequenz
     * widerspiegelt
     */
    @Override
    int countElements(String sequence, char element) {
        int count = 0;
        for (int i = 0; i < sequence.length(); i++) {
            char nucleotide = sequence.charAt(i);
            if (nucleotide == element) {
                count++;
            } else if (!isValidNucleotide(nucleotide)) {
                throw new IllegalArgumentException("Invalid nucleotide found in sequence: " + nucleotide);
            }
        }
        return count;
    }

    // GC content
    /**
     * FastaEntryDna und FastaEntryRna spezifische Methode, welche den GC-Gehalt
     * der Sequenz berechnet. Greift auf Attribute der Klasse zurück
     * @return- GC-Gehalt der übergebenen Sequenz
     */
    double calculateGCContent(){
        return ((double) (gCount + cCount)) / (aCount + cCount + gCount + uCount) * 100;
    }

    // Molecular Weight
    /**
     * Berechnet das Molekulargewicht der übergebenen Sequenz. Greift auf Attribute
     * der Klasse zurück
     * @return - Molekulargewicht
     */
    double calculateMolecularWeight(){
        double aWeight = this.aCount * 329.2;
        double cWeight = this.cCount * 305.2;
        double gWeight = this.gCount * 345.2;
        double uWeight = this.uCount * 306.2;
        return aWeight + cWeight + gWeight + uWeight + 159;
    }



    // Getter and Setter
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getaCount() {
        return aCount;
    }

    public void setaCount(int aCount) {
        this.aCount = aCount;
    }

    public int getcCount() {
        return cCount;
    }

    public void setcCount(int cCount) {
        this.cCount = cCount;
    }

    public int getgCount() {
        return gCount;
    }

    public void setgCount(int gCount) {
        this.gCount = gCount;
    }

    public int getuCount() {
        return uCount;
    }

    public void setuCount(int uCount) {
        this.uCount = uCount;
    }
}
