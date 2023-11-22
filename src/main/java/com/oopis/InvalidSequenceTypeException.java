package com.oopis;

/**
 * Hierbei handelt es sich um eine unchecked Custom Exception, welcher überü´prüft, ob der korrekte Sequenztyp
 * übergeben wurde.
 */
public class InvalidSequenceTypeException extends RuntimeException{

    /**
     * Standard vererbter Konstruktor der Superklasse RuntimeException
     * @param message
     */
    public InvalidSequenceTypeException(String message){
        super(message);
    }
}
