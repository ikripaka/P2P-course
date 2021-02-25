package com.shpp.p2p.cs.ikripaka.assignment15;

import com.shpp.p2p.cs.ikripaka.assignment17.CustomHashMap;

import java.io.*;

/**
 * This class can archive file into (.par) format
 * It works with String byte sequences
 */
class Archiver extends Constants {
    /* Contains filename of file */
    private final String inputFile, filenameForNewFile;

    private double fileSizeBefore, fileSizeAfter;
    /* Contains (key) byte cipher - (symbol count) symbol code */
    private CustomHashMap<Byte, Integer> symbolFrequency = new CustomHashMap<>();
    /* Contains (key) byte cipher - (symbol cipher in String) symbol code */
    private CustomHashMap<Byte, String> ciphersForSymbols = new CustomHashMap<>();

    Archiver(String inputFile, String filenameForNewFile) {
        this.inputFile = inputFile;
        this.filenameForNewFile = filenameForNewFile;
    }

    /**
     * Main method that makes archiving
     *
     * @throws IOException
     */
    void archive() throws Exception {
        findAllSymbols();

        HuffmanTreeBuilder huffmanTreeBuilder = new HuffmanTreeBuilder(symbolFrequency);
        huffmanTreeBuilder.buildTree();
        fillInCiphersForSymbols(huffmanTreeBuilder.getCiphersForSymbols());

        archiveFile();
        printInformation();
    }

    /**
     * Finds all symbols which are in the file
     * Creates symbol frequency
     *
     * @throws IOException
     */
    private void findAllSymbols() throws IOException {
        InputStream reader = new BufferedInputStream(new FileInputStream(inputFile));
        int availableSymbols = reader.available();

        byte oneSymbol;
        for (int i = 0; i < availableSymbols; i++) {
            oneSymbol = (byte) reader.read();
            if (symbolFrequency.containsKey(oneSymbol)) {
                symbolFrequency.put(oneSymbol, (symbolFrequency.get(oneSymbol) + 1));
            } else {
                symbolFrequency.put(oneSymbol, 1);
            }
        }
        reader.close();
    }

    /**
     * Archives file with using bytes that are represented in the
     * string arrays
     *
     * @throws IOException
     */
    private void archiveFile() throws IOException {
        InputStream reader = new BufferedInputStream(new FileInputStream(inputFile));
        OutputStream writer = new BufferedOutputStream(new FileOutputStream(filenameForNewFile));

        int fileSize = (reader.available());
        fileSizeBefore = fileSize;

        writeTable(fileSize, writer);

        int filledBitsInBytes = 0;
        int bytesThatLeft = reader.available();
        int byteCounter = 0;
        byte oneSymbolCode;
        StringBuilder byteSequence = null;
        StringBuilder builder = new StringBuilder();


        while (byteCounter < bytesThatLeft) {
            byteCounter++;
            oneSymbolCode = (byte) reader.read();

            if (bytesThatLeft == byteCounter) {
                if (byteSequence != null && oneSymbolCode == -1 && filledBitsInBytes < BITS_IN_ONE_BYTE
                        && byteSequence.length() == 0) {
                    while (filledBitsInBytes < BITS_IN_ONE_BYTE) {
                        builder.append("0");
                        filledBitsInBytes++;
                    }
                    writer.write((byte) Integer.parseInt(builder.toString(), 2));
                }
                break;
            }

            byteSequence = new StringBuilder(ciphersForSymbols.get(oneSymbolCode));

            while (byteSequence.length() != 0) {
                builder.append(byteSequence.charAt(0));
                byteSequence.deleteCharAt(0);
                filledBitsInBytes++;

                if (filledBitsInBytes == BITS_IN_ONE_BYTE) {
                    filledBitsInBytes = 0;
                    String str = builder.toString();
                    byte lineInByte = (byte) Integer.parseInt(str, 2);
                    writer.write(lineInByte);
                    builder.delete(0, builder.length());
                }
            }
        }

        writer.flush();
        writer.close();

        File getFileSize = new File(filenameForNewFile);
        fileSizeAfter = (int) getFileSize.length();
    }

    /**
     * Forms table that consist of
     * - 4 bytes for length of association table
     * - 8 bytes for number of bytes of input file
     *
     * @param fileLength - file size
     */
    private void writeTable(int fileLength, OutputStream writer) throws IOException {
        writeTableSize(writer);
        writeNumberOfInputBytes(writer, fileLength);
        writeAssociationTable(writer);
    }

    /**
     * Writes association table size
     *
     * @param writer writes table size in file
     * @throws IOException
     */
    private void writeTableSize(OutputStream writer) throws IOException {
        int number;
        number = symbolFrequency.size() * (BITS_TO_CODE_CHARACTER_REPETITION_COUNT + 1);
        for (int counter = 0; counter < 4; counter++) {
            writer.write(number & 255);
            number = number >> 8;
        }
    }

    /**
     * Writes number of input bytes in the table
     *
     * @param writer writes input bytes  in file
     * @param number number that we should write in the table
     * @throws IOException
     */
    private void writeNumberOfInputBytes(OutputStream writer, int number) throws IOException {
        for (int counter = 0; counter < BITS_IN_ONE_BYTE; counter++) {
            writer.write(number & 255);
            number = number >> 8;
        }
    }

    /**
     * Writes association table
     * 1 byte - symbol
     * 4 byte - count
     *
     * @param writer - writes association table in file
     * @throws IOException
     */
    private void writeAssociationTable(OutputStream writer) throws IOException {
        for (byte key : symbolFrequency) {
            writer.write(key);
            for (int j = 4, number = symbolFrequency.get(key); j > 0; j--) {
                writer.write(number & 255);
                number = number >> 8;
            }
        }
    }

    /**
     * Prints file information
     */
    private void printInformation() {
        System.out.println("Input file: " + inputFile);
        System.out.println("Output file: " + filenameForNewFile);
        System.out.println("File size BEFORE: " + fileSizeBefore + " bytes ");
        System.out.println("File size AFTER: " + fileSizeAfter + " bytes ");
        System.out.println("Compression: " + fileSizeBefore / fileSizeAfter);
    }

    /**
     * Fills in reverse byte sequence
     *
     * @param ciphers - ciphers that coded by Huffman code
     */
    private void fillInCiphersForSymbols(CustomHashMap<Byte, String> ciphers) {
        for (byte key : ciphers) {
            ciphersForSymbols.put( key, ciphers.get(key));
        }
    }
}
