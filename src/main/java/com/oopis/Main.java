package com.oopis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Die Main-Klasse bekommt Kommandozeilenargumente übergeben und gibt den
 * Startstoß für das Programm.
 */

public class Main {

    /**
     * Main-Methode ist dafür zuständig, eine FastaManager Instanz zu erzeugen
     * und dessen Methoden (Parser, Writer und Calculations) anzuwenden
     * @param args - Kommandozeilenargumente in Form eines String-Arrays
     */

    public static void main(String[] args) {
        // Try and catch block for exceptions
        try {
            // Control Statement
            if (args.length < 1) {
                System.out.println("Es wurde kein Argument übergeben");
                return;
            }

            // Standardmäßig 3/4 der verfügbaren Kerne verwenden
            int availableProcessors = Runtime.getRuntime().availableProcessors();
            int numThreads = Math.max(availableProcessors * 3 / 4, 1);


            // Falls der optionale Parameter numThreads übergeben wurde, diesen verwenden
            if (args.length > 3) {
                try {
                    numThreads = Integer.parseInt(args[3]);
                    if (numThreads <= 0 || numThreads > availableProcessors) {
                        System.err.println("Ungültige Anzahl von Threads.");
                        numThreads = Math.max(availableProcessors * 3 / 4, 1);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Ungültiger numThreads-Wert. Verwende " + numThreads + " Threads.");
                }
            }

            // FastaManager Instance to start
            FastaManager manager = FastaManager.getInstance();

            // Check for upper and lower case + Enums
            SequenceType type = SequenceType.convertString(args[1]);
            if(type == null){
                System.out.println("Sequenztyp ungültig");
                return;
            }

            // Fasta File parsing and adding entries to Entry List
            manager.parseFastaFile(args[0], type.toString());
            // manager.parseFastaFile("C:/Users/pfeif/Desktop/fasta_test2.txt", "DNA");
            // manager.parseFastaFile("C:/Users/pfeif/Desktop/fasta_test_peptide.txt", "PEPTIDE");




            // Perform calculations

            //manager.performCalculations(type.toString());
            //manager.performCalculations("PEPTIDE");
            // manager.performCalculations(type.toString(), args[2]);
            manager.performCalculationsThreads(type.toString(), args[2], numThreads);

            // Fasta File writing
            // Method tbd
            manager.writeFastaFile(args[1], args[2]);


            // General Testing
            // Get every entry from FastaManager instance manager
            ArrayList<FastaEntryDna> entriesDna = manager.getDnaEntries();
            ArrayList<FastaEntryAminoAcid> entriesPeptide = manager.getAaEntries();

            // Print attribute ID and sequence with for-loop from
            // every entry out of entries (DnaEntry example file)
            //for (FastaEntryDna entry : entriesDna) {
              //  System.out.println("ID: " + entry.getId() + "Sequence: " + entry.getSequence());
                //System.out.println("Sequence: " + entry.getSequence());
            //}

            // Print attribute ID and sequence with for-loop from
            // every entry out of entries (AminoAcidEntry example file)
            //for (FastaEntryAminoAcid entry : entriesPeptide){
              //  System.out.println("ID: " + entry.getId());
              //  System.out.println("Sequence: " + entry.getSequence());
              //  System.out.println("Hat geklappt!");
            //}

            // Print getter and setter methods of AminoAcidEntry Objects
            //for (FastaEntryAminoAcid entry: entriesPeptide){
              //  System.out.println("c count: " + entry.getcCount());
              //  System.out.println("y count: " + entry.getyCount());
              //  System.out.println("d count: " + entry.getdCount());
              //  System.out.println("h count: " + entry.gethCount());
            //}


        } catch (FileNotFoundException e) {
            System.out.println("Datei wurde nicht gefunden: " + e.getMessage());
        } catch (AccessDeniedException e) {
            System.out.println("Zugriff auf die Datei verweigert: " + e.getMessage());
        } catch (InvalidPathException e) {
            System.out.println("Ungültiger Dateipfad: " + e.getMessage());
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Ungültige Anzahl von Kommandozeilenargumenten: " + e.getMessage());
        } catch(IOException e) {
            System.out.println("Fehler beim Lesen oder Schreiben der Datei: " + e.getMessage());
        } catch(Exception e){
            System.out.println("Allgemeiner Fehlerfall: " + e.getMessage());
        }
    }
}
