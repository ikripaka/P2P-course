package com.shpp.p2p.cs.ikripaka.assignment14;

import java.io.*;
import java.util.HashMap;

/**
 * This class can archive file into (.par) format
 * It works with String byte sequences
 */
class Archiver extends Constants {
    // Contains filename of file
    private String inputFile, filenameForArchievedFile;
    private int numberOfBitsToEncryptOneCharacter;
    // Contains (key) byte cipher - (data) symbol code
    private HashMap<Byte, Integer> ciphersForSymbolsInBytes;
    // Contains (key) symbol code - (data)  String cipher
    private HashMap<Byte, String> cipherForSymbolsInString;
    private double fileSizeBefore, fileSizeAfter;

    Archiver(String inputFile, String filenameForArchievedFile) {
        this.inputFile = inputFile;
        this.filenameForArchievedFile = filenameForArchievedFile;
        ciphersForSymbolsInBytes = new HashMap<>();
        cipherForSymbolsInString = new HashMap<>();
    }


    /**
     * Calculates how many symbols we should take to encrypt one character
     */
    private void calculateNumberOfBitsToEncryptOneCharacter() {
        numberOfBitsToEncryptOneCharacter = 1;
        for (int i = 2; i < ciphersForSymbolsInBytes.size(); i *= 2) {
            numberOfBitsToEncryptOneCharacter++;
        }
    }

    /**
     * Finds all symbols which are in the file
     * writes them in the HashMap
     *
     * @param file - string path to the file
     * @throws IOException
     */
    private byte[] findAllSymbols(String file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        BufferedInputStream reader = new BufferedInputStream(input);

        byte[] availableSymbols = new byte[reader.available()];
        reader.read(availableSymbols, 0, reader.available());
        reader.close();

        for (byte availableSymbol : availableSymbols) {
            ciphersForSymbolsInBytes.putIfAbsent(availableSymbol, 0);
        }
        return availableSymbols;
    }

    /**
     * Fills in ByteCode combination (in string) for all letters that encrypt them
     * according to numberOfBitsToEncryptOneCharacter
     */
    private void fillInStringByteCodeCombination() {
        String byteSequence;
        for (Byte key : ciphersForSymbolsInBytes.keySet()) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0, number = ciphersForSymbolsInBytes.get(key); i < numberOfBitsToEncryptOneCharacter; i++) {
                builder.append(number % 2);
                number /= 2;
            }
            byteSequence = builder.reverse().toString();
            cipherForSymbolsInString.put(key, byteSequence);
        }
    }

    /**
     * Fills in byte cipher for all symbols
     */
    private void fillInCipherForSymbols() {
        int i = 0;
        for (Byte key : ciphersForSymbolsInBytes.keySet()) {
            ciphersForSymbolsInBytes.put(key, i);
            i++;
        }
    }

    /**
     * Archives file with using bytes that are represented in the
     * string arrays
     *
     * @throws IOException
     */
    private void archiveFile(byte[] buffer) throws IOException {
        FileOutputStream out = new FileOutputStream(filenameForArchievedFile);
        BufferedOutputStream writer = new BufferedOutputStream(out);

        int fileSize = (buffer.length);
        fileSizeBefore = fileSize;

        byte[] table = formTable(fileSize);
        writer.write(table);

        int filledBitsInBytes = 0;
        StringBuilder byteSequence;
        StringBuilder builder = new StringBuilder();


        for (int i = 0; i < fileSize; i++) {
            byte oneSymbolCode = buffer[i];
            byteSequence = new StringBuilder(cipherForSymbolsInString.get(oneSymbolCode));

            while (byteSequence.length() != 0) {
                builder.append(byteSequence.charAt(0));
                byteSequence.deleteCharAt(0);
                filledBitsInBytes++;

                if (i == buffer.length - 1 && filledBitsInBytes < BITS_IN_ONE_BYTE && byteSequence.length() == 0) {
                    while (filledBitsInBytes < BITS_IN_ONE_BYTE) {
                        builder.append("0");
                        filledBitsInBytes++;
                    }
                }
                if (filledBitsInBytes == BITS_IN_ONE_BYTE) {
                    filledBitsInBytes = 0;
                    String str = builder.toString();
                    byte lineInByte = (byte) Integer.parseInt(str, 2);
                    System.out.println("1 byte:" + lineInByte + " i:" + i);
                    writer.write(lineInByte);
                    builder = new StringBuilder();
                }
            }
        }

        writer.flush();
        writer.close();

        File getFileSize = new File(filenameForArchievedFile);
        fileSizeAfter = (int) getFileSize.length();
    }

    /**
     * Forms table that consist of
     * - 4 bytes for length of association table
     * - 8 bytes for number of bytes of input file
     *
     * @param fileLength - file size
     */
    private byte[] formTable(int fileLength) {
        byte[] buffer = new byte[TABLE_LENGTH + ciphersForSymbolsInBytes.size() * 2];

        writeTableSize(buffer);
        writeNumberOfInputBytes(buffer, fileLength);

        writeAssociationTable(buffer);

        return buffer;
    }

    /**
     * Writes number of input bytes in the table
     *
     * @param buffer - table
     * @param number - number that we should write in the table
     */
    private void writeNumberOfInputBytes(byte[] buffer, int number) {
        for (int counter = TABLE_LENGTH - 1; counter >= 4; counter--) {
            buffer[counter] = (byte) (number & 3);
            number = number >> 2;
        }
    }

    /**
     * Writes association table size
     *
     * @param buffer - table
     */
    private void writeTableSize(byte[] buffer) {
        int number;
        number = ciphersForSymbolsInBytes.size();
        for (int counter = 3; counter >= 0; counter--) {
            buffer[counter] = (byte) (number & 3);
            number = number >> 2;
        }
    }

    /**
     * Writes association table
     *
     * @param buffer - table
     */
    private void writeAssociationTable(byte[] buffer) {
        int i = TABLE_LENGTH;
        for (Byte key : ciphersForSymbolsInBytes.keySet()) {
            buffer[i] = key;
            buffer[i + 1] = ciphersForSymbolsInBytes.get(key).byteValue();
            i += 2;
        }
    }

    /**
     * Prints file size
     */
    private void printFileSize() {
        System.out.println("File size BEFORE: " + fileSizeBefore + " bytes ");
        System.out.println("File size AFTER: " + fileSizeAfter + " bytes ");
        System.out.println("Compression ratio: " + (fileSizeAfter / fileSizeBefore));
    }

    /**
     * Main method that makes archiving
     *
     * @throws IOException
     */
    void archive() throws IOException {
        byte[] allSymbols = findAllSymbols(inputFile);
        fillInCipherForSymbols();
        calculateNumberOfBitsToEncryptOneCharacter();

        fillInStringByteCodeCombination();

        archiveFile(allSymbols);

        printFileSize();
    }
}
