package com.oopis;

// Imports
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Die FastaManager-Klasse besitzt die Parser- und Writer-Methode für Fasta-Dateien
 * als Input. Sie erzeugt mit der Parser-Methode Instanzen der Fasta-Entry Klassen,
 * um diese zu verwalten. Mit der Writer-Methode soll sie Berechnungen an den
 * Fasta-Dateien in separate Dateien schreiben und speichern.
 */

public class FastaManager {


    // Single private static class variable
    private static FastaManager managerInstance;

    // Attributes
    private ArrayList<FastaEntryDna> dnaEntries;
    private ArrayList<FastaEntryRna> rnaEntries;
    private ArrayList<FastaEntryAminoAcid> aaEntries;
    private ArrayList<FastaEntryAmbiguous> ambEntries;

    // Constructor
    /**
     * Der FastaManager Konstruktor bekommt keine Argumente übergeben und erzeugt
     * beim Aufruf vier separate Array Listen, welche Objekte der jeweiligen
     * FastaEntry-Klasse speichern und verwalten.
     */
    private FastaManager(){
        dnaEntries = new ArrayList<>();
        rnaEntries = new ArrayList<>();
        aaEntries = new ArrayList<>();
        ambEntries = new ArrayList<>();
    }

    // Parser method
    /**
     * Die parseFastaFile Methode bekommt als Input einen filepath zu einer Fasta-
     * Inputdatei und liest diese ein. Dabei wird für jeden einzelnen Fasta-
     * Eintrag (FastaEntry) ein separates FastaEntry-Objekt erzeugt. Die Parser-Methode
     * bedient sich bei der Erzeugung dieser Objekte an der Factory-Klasse
     * FastaEntryFactory.
     * @param filepath - Input filepath der Fasta-Datei. Sollte der Methode
     *                 mit Slashes anstatt Backslashes übergeben werden
     * @param sequenceType - Zur Differenzierung, welche FastaEntry-Objekte später
     *                     erzeugt werden soll. Akzeptable Werte: "DNA", "RNA,
     *                     "PEPTIDE", "AMBIGUOUS"
     * @throws IOException - Falls falsche Inputparameter (bspw. ungültiger filepath)
     *                     übergeben werden.
     */
    public void parseFastaFile(String filepath, String sequenceType) throws IOException{
        // Open filepath
        File file = new File(filepath);
        // Scanner
        Scanner scanner = new Scanner(file);
        // Header
        String header = "";
        // StringBuilder to perform some actions on Strings
        StringBuilder sequence = new StringBuilder();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith(">")) {
                if (!header.isEmpty() && sequence.length() > 0) {
                    //if (!isValidFastaEntry(header, sequence.toString())) {
                      //  scanner.close();
                        //throw new FastaMalformattedException("Falsch formatierter Fasta-Eintrag: " + header);
                    //}
                    FastaEntry entry = FastaEntryFactory.createFastaEntry(header, sequence.toString(), sequenceType);
                    if (entry instanceof FastaEntryDna){
                        dnaEntries.add((FastaEntryDna) entry);
                    } else if(entry instanceof FastaEntryRna){
                        rnaEntries.add((FastaEntryRna) entry);
                    } else if(entry instanceof FastaEntryAminoAcid){
                        aaEntries.add((FastaEntryAminoAcid) entry);
                    } else if(entry instanceof FastaEntryAmbiguous){
                        ambEntries.add((FastaEntryAmbiguous) entry);
                    }
                    sequence = new StringBuilder();
                }
                // Update header
                header = line.substring(1).trim();
                //sequence = new StringBuilder();
            } else {
                sequence.append(line.trim());
            }
        }
        if (!header.isEmpty() && sequence.length() > 0) {
            //if (!isValidFastaEntry(header, sequence.toString())) {
              //  scanner.close();
                //throw new FastaMalformattedException("Falsch formatierter Fasta-Eintrag: " + header);
            //}
            FastaEntry entry = FastaEntryFactory.createFastaEntry(header, sequence.toString(), sequenceType);
            if (entry instanceof FastaEntryDna){
                dnaEntries.add((FastaEntryDna) entry);
            } else if(entry instanceof FastaEntryRna){
                rnaEntries.add((FastaEntryRna) entry);
            } else if(entry instanceof FastaEntryAminoAcid){
                aaEntries.add((FastaEntryAminoAcid) entry);
            } else if(entry instanceof FastaEntryAmbiguous){
                ambEntries.add((FastaEntryAmbiguous) entry);
            }
        }
        scanner.close();
    }

    /**
     * Diese Methode ist die Logik hinter der Custom Exception FastaMalformattedException und wird vom Parser
     * verwendet. Sie prüft auf die Bedingungen, ob der Header mit ">" beginnt und die Sequenz nicht leer ist.
     * @param header
     * @param sequence
     * @return boolean
     */
    private boolean isValidFastaEntry(String header, String sequence) {
        // Check if the header starts with ">"
        if (!header.startsWith(">")) {
            return false;
        }

        // Check if the sequence is not empty
        if (sequence.isEmpty()) {
            return false;
        }

        return true;
    }

    /**
     * Die Writer-Methode schreibt alle Berechnungen die an FastaEntry-Objekten
     * durchgeführt werden, in eine separate Datei und speichert diese Ergebnisse
     * ab.
     * @param outputPath - Gibt den Output-Pfad an, in welchem die oben genannten
     *                   Berechnungen gespeichert werden sollen
     */
    // Write method
    public void writeFastaFile(String sequenceType, String outputPath){

    }

    // Calculation method
    /**
     * Die Methode performCalculations hat die Aufgabe, die internen Berechnungen jeder FastaEntry Klasse durchzuführen.
     * Je nach Sequenztyp geht diese Methode das jeweilige Array an FastaEntries durch und führt für jeden Eintrag die
     * entsprechenden Berechnungen durch.
     *
     * @param sequenceType - Von der Kommandozeile übergebener Sequenztyp als String
     * @param outputPath - Von der Kommandozeile übergebener Path einer Textdatei, in welcher die Ergebnisse der
     *                   Berechnungen geschrieben werden.
     */
    public void performCalculations(String sequenceType, String outputPath){
        try (FileWriter writer = new FileWriter(outputPath)){
            if(sequenceType.equalsIgnoreCase("DNA")){
                for(FastaEntryDna dnaEntry : dnaEntries){
                    // GC Content
                    System.out.println("ID: " + dnaEntry.getId());
                    writer.write("ID: " + dnaEntry.getId());
                    double gcContent = dnaEntry.calculateGCContent();
                    System.out.println("GC content: " + gcContent);
                    writer.write("GC content: " + gcContent);
                    // Molecular Weight
                    double molecularWeight = dnaEntry.calculateMolecularWeight();
                    System.out.println("Mol weight: " + molecularWeight);
                    writer.write("Mol weight: " + molecularWeight);
                    // Melting Temperature
                    double meltingTemperature = dnaEntry.calculateMeltingTemperature();
                    System.out.println("Melt temp: " + meltingTemperature);
                    writer.write("Melting temp: " + meltingTemperature);
                }
            } else if(sequenceType.equalsIgnoreCase("RNA")){
                for(FastaEntryRna rnaEntry : rnaEntries){
                    System.out.println("ID: " + rnaEntry.getId());
                    writer.write("ID: " + rnaEntry.getId());
                    // GC Content
                    double gcContent = rnaEntry.calculateGCContent();
                    writer.write("GC content: " + gcContent);
                    // Molecular Weight
                    double molecularWeight = rnaEntry.calculateMolecularWeight();
                    writer.write("Mol weight: " + molecularWeight);
                }
            } else if(sequenceType.equalsIgnoreCase("PEPTIDE")){
                for(FastaEntryAminoAcid aaEntry : aaEntries){
                    // Net Charge
                    System.out.println("ID: " + aaEntry.getId());
                    writer.write("ID: " + aaEntry.getId());
                    double netCharge = aaEntry.calculateNetCharge(7.0);
                    // Print just for testing
                    System.out.println("Net Charge at pH = 7.0: " + netCharge);
                    writer.write("Net Charge at pH = 7.0: " + netCharge);
                    // Isoelectric Point
                    double isoelectricPoint = aaEntry.calculateIsoelectricPoint();
                    // Print just for testing
                    System.out.println("Isoelectric Point: " + isoelectricPoint);
                    writer.write("Isoelectric Point: " + isoelectricPoint);
                }
            } else if(sequenceType.equalsIgnoreCase("AMBIGUOUS")){
                System.out.println("No Calculations for sequence type AMBIGUOUS available");
            } else{
                System.out.println("Invalid sequence type: " + sequenceType);
            }
        } catch (NullPointerException e){
            System.err.println("Null reference: " + e.getMessage());
        } catch (IndexOutOfBoundsException e){
            System.err.println("Index out of bound: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * @param sequenceType - Von der Kommandozeile übergebener Sequenztyp als String
     * @param outputPath - Von der Kommandozeile übergebener Path einer Textdatei, in welcher die Ergebnisse der
     *                   Berechnungen geschrieben werden.
     * @param numThreads - Von der Kommandozeile übergebene Anzahl an Threads. Diese sollten 3/4 der verügbaren Kerne
     *                   entsprechen
     */
    // Neue performCalculationsMethode nur mit Aufteilung auf Threads
    public void performCalculationsThreads(String sequenceType, String outputPath, int numThreads) {
        try (FileWriter writer = new FileWriter(outputPath)) {

            ExecutorService executor = Executors.newFixedThreadPool(numThreads);

            if (sequenceType.equalsIgnoreCase("DNA")) {
                for (FastaEntryDna dnaEntry : dnaEntries) {
                    executor.submit(() -> {
                        try {
                            // GC Content calculation
                            synchronized (writer) {
                                writer.write("ID: " + dnaEntry.getId());
                                double gcContent = dnaEntry.calculateGCContent();
                                writer.write("\tGC content: " + gcContent);
                                writer.write("\n");
                                System.out.println("ID: " + dnaEntry.getId());
                                System.out.println("GC content: " + gcContent);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    executor.submit(() -> {
                        try {
                            // Molecular Weight calculation
                            synchronized (writer) {
                                writer.write("ID: " + dnaEntry.getId());
                                double molecularWeight = dnaEntry.calculateMolecularWeight();
                                writer.write("\tMol weight: " + molecularWeight);
                                writer.write("\n");
                                System.out.println("ID: " + dnaEntry.getId());
                                System.out.println("Molecular weight: " + molecularWeight);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    executor.submit(() -> {
                        try {
                            // Melting Temperature calculation
                            synchronized (writer) {
                                writer.write("ID: " + dnaEntry.getId());
                                double meltingTemperature = dnaEntry.calculateMeltingTemperature();
                                writer.write("\tMelting temp: " + meltingTemperature);
                                writer.write("\n");
                                System.out.println("ID: " + dnaEntry.getId());
                                System.out.println("Metling temperature: " + meltingTemperature);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } else if (sequenceType.equalsIgnoreCase("RNA")) {
                // Similar structure for RNA calculations
                // ...
                for (FastaEntryRna rnaEntry : rnaEntries) {
                    executor.submit(() -> {
                        try {
                            // GC Content calculation
                            synchronized (writer) {
                                writer.write("ID: " + rnaEntry.getId());
                                double gcContent = rnaEntry.calculateGCContent();
                                writer.write("\tGC content: " + gcContent);
                                writer.write("\n");
                                System.out.println("ID: " + rnaEntry.getId());
                                System.out.println("GC content: " + gcContent);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    executor.submit(() -> {
                        try {
                            // Molecular Weight calculation
                            synchronized (writer) {
                                writer.write("ID: " + rnaEntry.getId());
                                double molecularWeight = rnaEntry.calculateMolecularWeight();
                                writer.write("\tMol weight: " + molecularWeight);
                                writer.write("\n");
                                System.out.println("ID: " + rnaEntry.getId());
                                System.out.println("Molecular weight: " + molecularWeight);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } else if (sequenceType.equalsIgnoreCase("PEPTIDE")) {
                // Similar structure for Peptide calculations
                // ...
                for (FastaEntryAminoAcid aaEntry : aaEntries) {
                    executor.submit(() -> {
                        try {
                            // Net charge calculation
                            synchronized (writer) {
                                writer.write("ID: " + aaEntry.getId());
                                double netCharge = aaEntry.calculateNetCharge(7.0);
                                writer.write("\tNet charge at pH 7.0: " + netCharge);
                                writer.write("\n");
                                System.out.println("ID: " + aaEntry.getId());
                                System.out.println("Net charge at pH 7.0: " + netCharge);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    executor.submit(() -> {
                        try {
                            // Isoelectric Point calculation
                            synchronized (writer) {
                                writer.write("ID: " + aaEntry.getId());
                                double isoelectricPoint = aaEntry.calculateIsoelectricPoint();
                                writer.write("\tIsoelectric Point: " + isoelectricPoint);
                                writer.write("\n");
                                System.out.println("ID: " + aaEntry.getId());
                                System.out.println("Isoelectric Point: " + isoelectricPoint);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } else {
                System.out.println("Invalid sequence type: " + sequenceType);
            }

            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }




    // static getInstance Method (Singleton Pattern)
    /**
     * Die getInstance Methode ist charakteristisch für das Singleton Pattern, nach welchem die FastaManager Klasse
     * aufgebaut ist. Sie sorgt dafür, dass in der Klasse, in welcher die FastaManager Klasse instanziiert wird (Main)
     * wirklich nur ein einziges Objekt erstellt wird.
     * @return - Return ist ein Objekt der Klasse FastaManager
     */
    public static FastaManager getInstance(){
        if(managerInstance == null){
            managerInstance = new FastaManager();
        }
        return managerInstance;
    }

    // Getter and Setter
    public ArrayList<FastaEntryDna> getDnaEntries() {
        return dnaEntries;
    }

    public void setDnaEntries(ArrayList<FastaEntryDna> dnaEntries) {
        this.dnaEntries = dnaEntries;
    }

    public ArrayList<FastaEntryRna> getRnaEntries() {
        return rnaEntries;
    }

    public void setRnaEntries(ArrayList<FastaEntryRna> rnaEntries) {
        this.rnaEntries = rnaEntries;
    }

    public ArrayList<FastaEntryAminoAcid> getAaEntries() {
        return aaEntries;
    }

    public void setAaEntries(ArrayList<FastaEntryAminoAcid> aaEntries) {
        this.aaEntries = aaEntries;
    }

    public ArrayList<FastaEntryAmbiguous> getAmbEntries() {
        return ambEntries;
    }

    public void setAmbEntries(ArrayList<FastaEntryAmbiguous> ambEntries) {
        this.ambEntries = ambEntries;
    }
}
