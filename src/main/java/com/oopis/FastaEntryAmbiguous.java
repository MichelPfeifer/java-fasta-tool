package com.oopis;

/**
 * Repräsentiert Sequenzen, welche nicht DNA-, RNA- oder Peptidsequenzen sind
 * und weist diesen spezifische Eigenschaften zu.
 * Beinhaltet zudem Methoden zur Berechnung von Parametern.
 */

public class FastaEntryAmbiguous extends FastaEntry{

    // Attributes
    private int length;

    // Constructor
    /**
     * Konstruktor weist bei Aufruf den Attributen der geerbten Superklasse Werte zu
     * Zählt Anzahl der einzelnen Nukleotide in der Sequenz und weist die Anzahl
     * zusätzlichen Objekten zu (über countElements()).
     * @param id - Header des FastaEntries
     * @param sequence - Sequenz des FastaEntries
     * @param type - Sequenztyp (AMBIGUOUS)
     */
    public FastaEntryAmbiguous(String id, String sequence, String type){
        super(id, sequence, type);
        this.length = sequence.length();
    }

    // Methods

    // Count Elements
    /**
     * Zählt die Anzahl der Elemente in der Sequenz
     * @param sequence - Enthält die Inputsequenz
     * @param element - Inputsequenz wird mit diesem Character abgeglichen. Soll
     *                  idealerweise Bestandteil der Sequenz sein.
     * @return - Gibt Integer zurück, welcher die Anzahl des Characters in der Sequenz
     * widerspiegelt
     */
    @Override
    int countElements(String sequence, char element){
        int count = 0;
        for(int i = 0; i < sequence.length(); i++){
            if(sequence.charAt(i) == element){
                count++;
            }
        }
        return count;
    }

    // Add calculation methods that make sense
    // tbd


    // Getter and Setter
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
