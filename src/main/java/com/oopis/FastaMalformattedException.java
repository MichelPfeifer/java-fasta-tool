package com.oopis;

/**
 * Hier wird eine Custom Exception definiert, welche dann geworfen wird, wenn eine fehlerhaft formatierte Fasta-Datei
 * übergeben wird. Hierbei handelt es sich um eine unchecked Exception, da diese von RuntimeException erbt.
 */
public class FastaMalformattedException extends RuntimeException{

    // Constructor
    /**
     * Standard vererbter Konstruktor der Superklasse RuntimeException
     * @param message
     */
    public FastaMalformattedException(String message){
        super(message);
    }

}
