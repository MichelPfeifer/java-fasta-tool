package com.oopis;

/**
 * Repräsentiert DNA-Sequenzen und weist diesen spezifische Eigenschaften zu.
 * Beinhaltet zudem Methoden zur Berechnung von Parametern.
 */

public class FastaEntryDna extends FastaEntry{

    //Attributes
    private int length;
    private int aCount;
    private int cCount;
    private int gCount;
    private int tCount;


    /**
     * Konstruktor weist bei Aufruf den Attributen der geerbten Superklasse Werte zu
     * Zählt Anzahl der einzelnen Nukleotide in der DNA-Sequenz und weist die Anzahl
     * zusätzlichen Objekten zu (über countElements()).
     * @param id - Header des FastaEntries
     * @param sequence - DNA-Sequenz des FastaEntries
     * @param type - Sequenztyp (DNA)
     */
    // Constructor
    public FastaEntryDna(String id, String sequence, String type){
        super(id, sequence, type);
        this.length = sequence.length();
        this.aCount = countElements(sequence, 'A');
        this.cCount = countElements(sequence, 'C');
        this.gCount = countElements(sequence, 'G');
        this.tCount = countElements(sequence, 'T');
    }

    // Methods

    /**
     * Hier ist die Logik drin geschrieben, nach welcher die Methode countElements auf Korrektheit der Sequenz prüft
     * @param nucleotide
     * @return boolean
     */
    private boolean isValidNucleotide(char nucleotide) {
        nucleotide = Character.toUpperCase(nucleotide);
        // Check if the nucleotide is one of the valid characters (A, C, G, T)
        return nucleotide == 'A' || nucleotide == 'C' || nucleotide == 'G' || nucleotide == 'T';
    }

    /**
     * Zählt die Anzahl der Nukleotide in der DNA-Sequenz
     * @param sequence - Enthält die Inputsequenz
     * @param element - Inputsequenz wird mit diesem Character abgeglichen. Soll
     *                  idealerweise Bestandteil der Sequenz sein.
     * @return - Gibt Integer zurück, welcher die Anzahl des Characters in der Sequenz
     * widerspiegelt
     */
    // Count Nucleotides
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

    /**
     * FastaEntryDna und FastaEntryRna spezifische Methode, welche den GC-Gehalt
     * der Sequenz berechnet. Greift auf Attribute der Klasse zurück
     * @return- GC-Gehalt der übergebenen Sequenz
     */
    // GC content
    double calculateGCContent(){
        return ((double) (gCount + cCount)) / (aCount + cCount + gCount + tCount) * 100;
    }

    /**
     * Berechnet das Molekulargewicht der übergebenen Sequenz. Greift auf Attribute
     * der Klasse zurück
     * @return - Molekulargewicht
     */
    // Molecular Weight
    double calculateMolecularWeight(){
        double aWeight = this.aCount * 313.21;
        double cWeight = this.cCount * 289.18;
        double gWeight = this.gCount * 329.21;
        double tWeight = this.tCount * 304.20;
        return aWeight + cWeight + gWeight + tWeight - 61.96;
    }


    // Metlting Temperature
    /**
     * Soll die Schmelztemperatur der an das Programm übergebenen Sequenzen
     * berechnen. Greift auf Attribute der FastaEntry-Klassen zurück (sequence)
     * @return - Berechnete Schmelztemperatur
     */
    double calculateMeltingTemperature(){
        if(length < 14){
            return (aCount + tCount) * 2 + (gCount + cCount) * 4;
        } else{
            return 64.9 + (41 * (gCount + gCount - 16.4) / (aCount + tCount + gCount + cCount));
        }
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

    public int gettCount() {
        return tCount;
    }

    public void settCount(int tCount) {
        this.tCount = tCount;
    }
}
