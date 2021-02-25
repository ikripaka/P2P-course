package com.shpp.p2p.cs.ikripaka.assignment12;

import java.util.LinkedList;
import java.util.Objects;

/**
 * This class finds silhouettes
 * It uses method in which we should compare
 * average pixels color value
 */
class Searcher extends Constants {
    private int silhouettes = 0;
    private int[][] pixels;
    // Saves the array that consist of 0|1|2
    private int[][] imageModel;
    private LinkedList<Integer> allSilhouettes;
    private int numOfPixelsInSilhouette = 0;
    private double averageValue;
    private int height, width;

    // true - average font value higher than HALF_OF_THE_MAX_COLOUR_VALUE
    // false - average font value lower than HALF_OF_THE_MAX_COLOUR_VALUE
    private boolean backgroundIndicator = true;

    // CONSTRUCTOR
    Searcher(int[][] startPixels, int pictureHeight, int pictureWidth) {
        pixels = startPixels;
        width = pictureWidth;
        height = pictureHeight;
        allSilhouettes = new LinkedList<>();

        findBackgroundAverageValue();

        // if (font is brighter than silhouette)
        if (averageValue < HALF_OF_THE_MAX_COLOUR_VALUE) {
            backgroundIndicator = false;
        }

        separateTheBackgroundFromTheSilhouette();
        findSilhouettes();
        wasteGarbage();
    }

    /**
     * Separates the background from the silhouettes
     * returns - imageModel thad filled with (0 and 1)
     * 0 - font
     * 1 - silhouette
     */
    private void separateTheBackgroundFromTheSilhouette() {
        imageModel = new int[width][height];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (backgroundIndicator) {
                    if (extractColorValues(pixels[col][row]) < HALF_OF_THE_MAX_COLOUR_VALUE) {
                        imageModel[col][row] = 1;
                    } else if (extractColorValues(pixels[col][row]) >= HALF_OF_THE_MAX_COLOUR_VALUE) {
                        imageModel[col][row] = 0;
                    }
                } else {
                    if (extractColorValues(pixels[col][row]) > HALF_OF_THE_MAX_COLOUR_VALUE) {
                        imageModel[col][row] = 1;
                    } else if (extractColorValues(pixels[col][row]) <= HALF_OF_THE_MAX_COLOUR_VALUE) {
                        imageModel[col][row] = 0;
                    }
                }
            }
        }
    }

    /**
     * Finds average font value
     * returns - fontColour
     */
    private void findBackgroundAverageValue() {
        int numOfPixels = 0;
        int numbersSum = 0;

        for (int col = 0; col < width; col++) {
            numbersSum += extractColorValues(pixels[col][0]);
            numbersSum += extractColorValues(pixels[col][height - 1]);
            numOfPixels += 2;
        }

        for (int row = 0; row < height; row++) {
            numbersSum += extractColorValues(pixels[0][row]);
            numbersSum += extractColorValues(pixels[width - 1][row]);
            numOfPixels += 2;
        }

        averageValue = numbersSum / (numOfPixels * 1.0);
    }

    /**
     * Finds silhouettes
     * returns - allSilhouettes filled with all silhouettes from the image
     */
    private void findSilhouettes() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (imageModel[col][row] == 1) { // if(pixel - silhouette)
                    breadthSearch(row, col);
                    allSilhouettes.add(numOfPixelsInSilhouette);
                    System.out.println(numOfPixelsInSilhouette);
                    numOfPixelsInSilhouette = 0;
                }
            }
        }
    }

    /**
     * Finds all silhouette pixels
     *
     * @param row - row number
     * @param col - col number
     */
    private void breadthSearch(int row, int col) {
        LinkedList<String> queue = new LinkedList<>();
        queue.add(row + "_" + col);
        String[] splittedLine;

        while (queue.size() != 0) {
            splittedLine = Objects.requireNonNull(queue.pollFirst()).split("_");
            row = Integer.parseInt(splittedLine[0]);
            col = Integer.parseInt(splittedLine[1]);


            if (row < height - 1 && imageModel[col][row + 1] == 1) { // bottom pixel \/
                imageModel[col][row + 1] = 2;
                numOfPixelsInSilhouette++;
                queue.addLast((row + 1) + "_" + col);
            }
            if (row >= 1 && imageModel[col][row - 1] == 1) { // upper pixel /\
                imageModel[col][row - 1] = 2;
                numOfPixelsInSilhouette++;
                queue.addLast((row - 1) + "_" + col);
            }
            if (col >= 1 && imageModel[col - 1][row] == 1) { // left pixel <
                imageModel[col - 1][row] = 2;
                numOfPixelsInSilhouette++;
                queue.addLast(row + "_" + (col - 1));
            }
            if (col < width - 1 && imageModel[col + 1][row] == 1) { // right pixel >
                imageModel[col + 1][row] = 2;
                numOfPixelsInSilhouette++;
                queue.addLast(row + "_" + (col + 1));
            }
        }
    }

    /**
     * Wastes garbage
     * returns - number of the silhouettes
     */
    private void wasteGarbage() {
        if (allSilhouettes.size() != 0) {

            int maxValueIndex = 0;
            // finds max recursion value
            for (int i = 0; i < allSilhouettes.size(); i++) {
                if (allSilhouettes.get(i) > allSilhouettes.get(maxValueIndex)) {
                    maxValueIndex = i;
                }
            }

            int percentagePart = (int) (allSilhouettes.get(maxValueIndex) * ENTRY_THRESHOLD);

            // finds silhouettes
            for (Integer allSilhouette : allSilhouettes) {
                if (allSilhouette > percentagePart) {
                    silhouettes++;
                }
            }
        }
    }

    /**
     * Extracts color value from the pixel
     * return - sum of all components (red / green / blue)
     */
    private double extractColorValues(int pixel) {
        int red = getRed(pixel);
        int green = getGreen(pixel);
        int blue = getBlue(pixel);

        return (red + green + blue) / 3.0;
    }

    /**
     * Gets blue pixel value from rgb color
     *
     * @param var1 - rgb color
     * @return - part of the blue color in pixel
     */
    private int getBlue(int var1) {
        return var1 & 0x000000ff;
    }

    /**
     * Gets green pixel value from rgb color
     *
     * @param var1 - rgb color
     * @return - part of the blue color in pixel
     */
    private int getGreen(int var1) {
        return (var1 & 0x0000ff00) >> 8;
    }

    /**
     * Gets red pixel value from rgb color
     *
     * @param var1 - rgb color
     * @return - part of the blue color in pixel
     */
    private int getRed(int var1) {
        return (var1 & 0x00ff0000) >> 16;
    }

    int getSilhouettes() {
        return silhouettes;
    }
}
