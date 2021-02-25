package com.shpp.p2p.cs.ikripaka.assignment14;

/**
 * This class unpacking archived file in (.uar) format
 */

import java.io.*;
import java.util.HashMap;

class Unpacker extends Constants {
    // Contains filename of file
    private String inputFile, filenameForUnarchievedFile;
    private int numberOfBitsToEncryptOneCharacter;
    // Contains (key) byte cipher - (data) symbol code
    private HashMap<Byte, Integer> ciphersForSymbolsInByte;
    // Contains (key) String cipher - (data) symbol code
    private HashMap<String, Integer> ciphersForSymbolInString;
    private int bytesNumber, associationTableSize;
    private double fileSizeBefore, fileSizeAfter;

    Unpacker(String inputFile, String filenameForUnarchievedFile) {
        this.inputFile = inputFile;
        this.filenameForUnarchievedFile = filenameForUnarchievedFile;
        ciphersForSymbolInString = new HashMap<>();
        ciphersForSymbolsInByte = new HashMap<>();
    }

    /**
     * Prints information about file size
     */
    private void printFileSize() {
        System.out.println("File size BEFORE: " + fileSizeBefore + " bytes ");
        System.out.println("File size AFTER: " + fileSizeAfter + " bytes ");
    }

    /**
     * This function unpacks file
     *
     * @param buffer - array with all symbols from the file
     * @throws IOException
     */
    private void unzipFile(byte[] buffer) throws IOException {
        FileOutputStream out = new FileOutputStream(filenameForUnarchievedFile);
        BufferedOutputStream writer = new BufferedOutputStream(out);

        int bytesInFile = buffer.length;

        fileSizeBefore = bytesInFile;

        StringBuilder builder = new StringBuilder();
        StringBuilder oneByteCode;
        int byteCounter = 0;

        for (int i = TABLE_LENGTH + associationTableSize * 2; i < bytesInFile; i++) {
            oneByteCode = extractByteCode(buffer[i] < 0 ? (256 - Math.abs(buffer[i])) : buffer[i]);

            while (oneByteCode.length() != 0) {
                if (builder.length() == numberOfBitsToEncryptOneCharacter) {
                    builder = builder.delete(0, builder.length());
                }
                while (builder.length() < numberOfBitsToEncryptOneCharacter && oneByteCode.length() != 0) {
                    builder.append(oneByteCode.charAt(0));
                    oneByteCode.deleteCharAt(0);
                }
                if (builder.length() == numberOfBitsToEncryptOneCharacter) {
                    writer.write(ciphersForSymbolInString.get(builder.toString()));
                    byteCounter++;

                    if (byteCounter == bytesNumber) break;
                }
            }
        }
        writer.flush();
        writer.close();

        File getFileSize = new File(filenameForUnarchievedFile);
        fileSizeAfter = getFileSize.length();
    }

    /**
     * Determines byte code combination in string
     *
     * @param number - the number which must be translated into a binary system of the number
     * @return - byte code (in string)
     */
    private StringBuilder extractByteCode(int number) {
        StringBuilder builder = new StringBuilder();
        while (number != 0) {
            builder.append(number % 2);
            number /= 2;
        }
        while (builder.length() < BITS_IN_ONE_BYTE) {
            builder.append("0", 0, 1);
        }
        return builder.reverse();
    }

    /**
     * Fills in string cipher for symbols
     */
    private void fillInStringByteCodeCombination() {
        String byteSequence;
        for (Byte key : ciphersForSymbolsInByte.keySet()) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0, number = key < 0 ? (256 - Math.abs(key)) : key; i < numberOfBitsToEncryptOneCharacter; i++) {
                builder.append(number % 2);
                number /= 2;
            }
            byteSequence = builder.reverse().toString();
            ciphersForSymbolInString.put(byteSequence, ciphersForSymbolsInByte.get(key));
        }
    }

    /**
     * Calculates how many symbols wo should take to encrypt one character
     */
    private void calculateNumberOfBitsToEncryptOneCharacter() {
        numberOfBitsToEncryptOneCharacter = 1;
        for (int i = 2; i < ciphersForSymbolsInByte.size(); i *= 2) {
            numberOfBitsToEncryptOneCharacter++;
        }
    }

    /**
     * Reads table
     * @param file - path to the file
     *             - 4 bytes for length of association table
     *             - 8 bytes for number of bytes of input file
     * @throws IOException
     */
    private byte[] readTable(String file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        BufferedInputStream reader = new BufferedInputStream(inputStream);

        byte[] allSymbolsFromFile = new byte[reader.available()];
        reader.read(allSymbolsFromFile, 0, reader.available());
        reader.close();

        StringBuilder str = new StringBuilder();

        associationTableSize = readTableSize(allSymbolsFromFile);

        bytesNumber = readNumberOfInputBytes(allSymbolsFromFile);


        readAssociationTable(allSymbolsFromFile);
        return allSymbolsFromFile;
    }

    /**
     * Reads association table
     *
     * @param allSymbols - array with all symbols from the file
     * @throws IOException
     */
    private void readAssociationTable(byte[] allSymbols) {
        for (int i = 12; i < associationTableSize * 2 + TABLE_LENGTH; i += 2) {
            int symbolCode = allSymbols[i];
            int symbolCipher = allSymbols[i + 1];
            ciphersForSymbolsInByte.put((byte) symbolCipher, symbolCode);
        }
    }

    /**
     * Reads number of input bytes
     *
     * @param allSymbols - byte array which contain all symbols from the file
     * @return - string with number of input bytes
     * @throws IOException
     */
    private int readNumberOfInputBytes(byte[] allSymbols) {
        int number = 0;
        for (int i = BITS_TO_CODE_ASSOCIATION_TABLE_SIZE;
             i < BITS_TO_CODE_ORIGINAL_FILE_SIZE + BITS_TO_CODE_ASSOCIATION_TABLE_SIZE; i++) {
            number = number | allSymbols[i];
            if (i != (BITS_TO_CODE_ORIGINAL_FILE_SIZE + BITS_TO_CODE_ASSOCIATION_TABLE_SIZE) - 1) {
                number = number << 2;
            }
        }
        return number;
    }

    /**
     * Reads table size
     *
     * @param allSymbols - byte array which contain
     * @throws IOException
     */
    private int readTableSize(byte[] allSymbols) {
        int number = 0;
        for (int i = 0; i < BITS_TO_CODE_ASSOCIATION_TABLE_SIZE; i++) {
            number = (number | allSymbols[i]);
            if (i != BITS_TO_CODE_ASSOCIATION_TABLE_SIZE - 1) number = number << 2;
        }
        return number;
    }

    /**
     * Main method that unzip file
     *
     * @throws IOException
     */
    void unzip() throws IOException {
        byte[] buffer = readTable(inputFile);
        calculateNumberOfBitsToEncryptOneCharacter();
        fillInStringByteCodeCombination();

        unzipFile(buffer);

        printFileSize();
    }

}
