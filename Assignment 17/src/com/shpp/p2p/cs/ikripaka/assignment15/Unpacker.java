package com.shpp.p2p.cs.ikripaka.assignment15;

/**
 * This class unpacking archived file
 * It works with String byte sequences
 */

import com.shpp.p2p.cs.ikripaka.assignment17.CustomHashMap;

import java.io.*;

class Unpacker extends Constants {
    // Contains filenames
    private final String filenameForNewFile, inputFile;

    // Contains (key) byte cipher - (symbol count) symbol code
    private CustomHashMap<Byte, Integer> symbolFrequency;
    // Contains (key) String cipher - (symbol byte code) symbol code
    private CustomHashMap<String, Integer> ciphersForSymbols;
    private int originalFileSize, associationTableSize;
    private double fileSizeBefore, fileSizeAfter;

    Unpacker(String inputFile, String filenameForNewFile) {
        this.inputFile = inputFile;
        this.filenameForNewFile = filenameForNewFile;
        ciphersForSymbols = new CustomHashMap<>();
        symbolFrequency = new CustomHashMap<>();
    }

    /**
     * Main method that unzip file
     *
     * @throws IOException
     */
    void unzip() throws Exception {
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream(inputFile));
        fileSizeBefore = reader.available();

        readTable(reader);
        HuffmanTreeBuilder huffmanTreeBuilder = new HuffmanTreeBuilder(symbolFrequency);
        huffmanTreeBuilder.buildTree();

        fillInCipherForSymbols(huffmanTreeBuilder.getCiphersForSymbols());
        unzipFile(reader);

        printFileSize();
    }

    /**
     * Prints information about file size
     */
    private void printFileSize() {
        System.out.println("Input file: " + inputFile);
        System.out.println("Output file: " + filenameForNewFile);
        System.out.println("File size BEFORE: " + fileSizeBefore + " bytes ");
        System.out.println("File size AFTER: " + fileSizeAfter + " bytes ");
    }

    /**
     * This function unpacks file with using bytes that are represented in the
     * string arrays
     *
     * @param reader - reads file
     * @throws IOException
     */
    private void unzipFile(BufferedInputStream reader) throws IOException {
        OutputStream writer = new BufferedOutputStream(new FileOutputStream(filenameForNewFile));

        StringBuilder builder = new StringBuilder();
        StringBuilder oneByteCode;
        int byteCounter = 0;
        int oneByte;

        while (byteCounter < originalFileSize) {
            oneByte = reader.read();

            oneByteCode = extractByteCode(oneByte);

            while (oneByteCode.length() != 0) {
                while (!ciphersForSymbols.containsKey(builder.toString()) && oneByteCode.length() != 0) {
                    builder.append(oneByteCode.charAt(0));
                    oneByteCode.deleteCharAt(0);
                }
                if (ciphersForSymbols.containsKey(builder.toString())) {
                    writer.write(ciphersForSymbols.get(builder.toString()));
                    byteCounter++;

                    builder.delete(0, builder.length());

                    if (byteCounter == originalFileSize) break;
                }
            }
        }
        writer.flush();
        writer.close();

        File getFileSize = new File(filenameForNewFile);
        fileSizeAfter = getFileSize.length();
    }

    /**
     * Determines byte code combination in string
     *
     * @param number - the number which must be translated into a binary system of the number
     * @return - byte code (in string)
     */
    private StringBuilder extractByteCode(int number) {
        StringBuilder builder = new StringBuilder(Integer.toBinaryString(number));
        while (builder.length() < BITS_IN_ONE_BYTE) {
            builder.insert(0, "0");
        }
        return builder;
    }

    /**
     * Fills in string byte code to the HashMap
     *
     * @param ciphers - ciphers for symbols which coded by Huffman code
     */
    private void fillInCipherForSymbols(CustomHashMap<Byte, String> ciphers) {
        for (byte key : ciphers) {
            ciphersForSymbols.put(ciphers.get(key), (int) (byte) key);
        }
    }

    /**
     * Reads table
     * - 4 bytes for length of association table
     * - 8 bytes for number of bytes of input file
     *
     * @param reader - reads file
     * @throws IOException
     */
    private void readTable(BufferedInputStream reader) throws IOException {
        associationTableSize = readTableSize(reader);
        originalFileSize = readFileSize(reader);
        readAssociationTable(reader);
    }

    /**
     * Reads association table
     * - 1 byte - symbol byte code
     * - 4 bytes - symbol count
     *
     * @throws IOException
     */
    private void readAssociationTable(BufferedInputStream reader) throws IOException {
        for (int i = TABLE_LENGTH; i < associationTableSize + TABLE_LENGTH; i += 5) {
            int symbolCode = reader.read();
            int number = readNumber(reader, BITS_TO_CODE_CHARACTER_REPETITION_COUNT);
            symbolFrequency.put((byte) symbolCode, number);
        }
    }

    /**
     * Reads number of input bytes
     *
     * @param reader - reads file
     * @return number
     * @throws IOException
     */
    private int readFileSize(BufferedInputStream reader) throws IOException {
        return readNumber(reader, BITS_TO_CODE_ORIGINAL_FILE_SIZE);
    }

    /**
     * Reads table size
     *
     * @param reader - reads file
     * @return table size
     * @throws IOException
     */
    private int readTableSize(BufferedInputStream reader) throws IOException {
        return readNumber(reader, BITS_TO_CODE_ASSOCIATION_TABLE_SIZE);
    }

    /**
     * Reads number in file
     *
     * @param reader  - reads file
     * @param counter - number length in bytes
     * @return - number
     * @throws IOException
     */
    private int readNumber(BufferedInputStream reader, int counter) throws IOException {
        int number;
        StringBuilder builder = new StringBuilder();
        StringBuilder str;
        for (int i = 0; i < counter; i++) {
            int oneNumber = reader.read();
            str = new StringBuilder(Integer.toBinaryString(oneNumber));
            while (str.length() < BITS_IN_ONE_BYTE) {
                str.insert(0, "0");
            }
            builder.append(str.reverse());
        }
        number = Integer.parseInt(builder.reverse().toString(), 2);
        return number;
    }
}
