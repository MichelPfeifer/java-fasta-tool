package com.oopis;

/**
 * Die FastaEntry-Klasse dient als Superklasse der FastaEntry-Subklassen und legt
 * deren Grundstruktur vor. Sie wird dementsprechend an die FastaEntry-Subklassen
 * vererbt.
 */

public abstract class FastaEntry {

    // Attributes
    protected String id;
    protected String sequence;
    protected String type;

    /**
     * Der Konstruktor weist bei Aufruf den Attributen der Klasse Werte zu
     * @param id - Header des jeweiligen FastaEntries
     * @param sequence - Sequenz des FastaEntries
     * @param type - Sequenztyp des FastaEntries
     */
    // Constructor
    public FastaEntry(String id, String sequence, String type){
        this.id = id;
        this.sequence = sequence;
        this.type = type;
    }

    /**
     * Zählt die Elemente des Inputstrings (Sequenz)
     * @param string - Enthält die Inputsequenz
     * @param character - Inputsequenz wird mit diesem Character abgeglichen. Soll
     *                  idealerweise Bestandteil der Sequenz sein.
     * @return - Gibt Integer zurück, welcher die Anzahl des übergebenen Characters
     *          in der übergebenen Sequenz zurückgibt.
     */
    abstract int countElements(String string, char character);



    // Getter and Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
