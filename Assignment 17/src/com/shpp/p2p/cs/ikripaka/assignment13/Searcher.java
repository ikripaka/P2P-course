package com.shpp.p2p.cs.ikripaka.assignment13;

import com.shpp.p2p.cs.ikripaka.assignment16.CustomArrayList;
import com.shpp.p2p.cs.ikripaka.assignment16.CustomLinkedList;

import java.util.Objects;

/**
 * This class finds silhouettes
 * It uses method in which we should compare
 * pixel color components (blue, red, green)
 */

class Searcher extends Constants {
    private int silhouettes = 0;
    private int[][] pixels;
    // Saves the array that consist of 0|1|2
    private int[][] imageModel;
    // Contains all silhouettes that program find
    private CustomArrayList<Integer> allSilhouettes;
    private int numOfPixelsInSilhouette = 0;
    // Average value of each background component
    private double backgroundRedComponent = 0;
    private double backgroundBlueComponent = 0;
    private double backgroundGreenComponent = 0;
    // Picture height, width
    private int height, width;

    Searcher(int[][] startPixels, int pictureHeight) {
        pixels = startPixels;
        width = startPixels.length;
        height = pictureHeight;
        imageModel = new int[width][height];
        allSilhouettes = new CustomArrayList<>();

        findFontValue();

        separateBackgroundFromTheSilhouette();
        findSilhouettes();
        wasteGarbage();
    }

    /**
     * Separates the background from the silhouettes and
     * fills imageModel that filled with (0 and 1)
     * 0 - font
     * 1 - silhouette
     */
    private void separateBackgroundFromTheSilhouette() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int blueComponent, redComponent, greenComponent;
                blueComponent = getBlue(pixels[col][row]);
                redComponent = getRed(pixels[col][row]);
                greenComponent = getGreen(pixels[col][row]);
                if (checkColor(blueComponent, backgroundBlueComponent)
                        && checkColor(greenComponent, backgroundGreenComponent)
                        && checkColor(redComponent, backgroundRedComponent)) {
                    imageModel[col][row] = 0;
                } else {
                    imageModel[col][row] = 1;
                }
            }
        }
    }

    /**
     * Checks color component for entry into the gap
     *
     * @param component           - color component
     * @param backgroundComponent - background color component
     * @return if component entry into the gap
     */
    private boolean checkColor(int component, double backgroundComponent) {
        return component >= backgroundComponent - PERMISSIBLE_VALIDATION
                && component <= backgroundComponent + PERMISSIBLE_VALIDATION;
    }

    /**
     * Finds background average components in the picture perimeter
     */
    private void findFontValue() {
        /* Uses for counting average value for background */
        double blueComponentsSum = 0, redComponentsSum = 0, greenComponentsSum = 0;
        for (int col = 0; col < width; col++) {
            blueComponentsSum += getBlue(pixels[col][0]);
            redComponentsSum += getRed(pixels[col][0]);
            greenComponentsSum += getGreen(pixels[col][0]);

            blueComponentsSum += getBlue(pixels[col][height - 1]);
            redComponentsSum += getRed(pixels[col][height - 1]);
            greenComponentsSum += getGreen(pixels[col][height - 1]);
        }
        for (int row = 0; row < height; row++) {
            blueComponentsSum += getBlue(pixels[0][row]);
            redComponentsSum += getRed(pixels[0][row]);
            greenComponentsSum += getGreen(pixels[0][row]);

            blueComponentsSum += getBlue(pixels[width - 1][row]);
            redComponentsSum += getRed(pixels[width - 1][row]);
            greenComponentsSum += getGreen(pixels[width - 1][row]);
        }
        backgroundBlueComponent = blueComponentsSum / (width * 2 + height * 2);
        backgroundRedComponent = redComponentsSum / (width * 2 + height * 2);
        backgroundGreenComponent = greenComponentsSum / (width * 2 + height * 2);
    }

    /**
     * Finds silhouettes and
     * fills allSilhouettes with all silhouettes from the image
     */
    private void findSilhouettes() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (imageModel[col][row] == 1) {
                    widthSearch(row, col);
                    allSilhouettes.add(numOfPixelsInSilhouette);
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
    private void widthSearch(int row, int col) {
        CustomLinkedList<String> queue = new CustomLinkedList<>();
        queue.add(row + "_" + col);
        String[] splittedLine;

        while (queue.size() != 0) {
            splittedLine = Objects.requireNonNull(queue.removeFirst()).split("_");
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
     * Wastes garbage and
     * fills in number of the silhouettes
     */
    private void wasteGarbage() {
        if (allSilhouettes.size() != 0) {
            int maxValueIndex = 0;
            for (int i = 0; i < allSilhouettes.size(); i++) { // finds max silhouettes value
                if (allSilhouettes.get(i) > allSilhouettes.get(maxValueIndex)) {
                    maxValueIndex = i;
                }
            }

            int percentagePart = (int) (allSilhouettes.get(maxValueIndex) * ENTRY_THRESHOLD);

            for (Integer allSilhouette : allSilhouettes) {
                if (allSilhouette > percentagePart) {
                    silhouettes++;
                }
            }
        }
    }

    /**
     * Gets blue pixel value from rgb color
     *
     * @param var1 - rgb color
     * @return - part of the blue color in pixel
     */
    private int getBlue(int var1) {
        return var1 & 0xff;
    }

    /**
     * Gets green pixel value from rgb color
     *
     * @param var1 - rgb color
     * @return - part of the blue color in pixel
     */
    private int getGreen(int var1) {
        return var1 >> 8 & 0xff;
    }

    /**
     * Gets red pixel value from rgb color
     *
     * @param var1 - rgb color
     * @return - part of the blue color in pixel
     */
    private int getRed(int var1) {
        return var1 >> 16 & 0xff;
    }

    int getSilhouettes() {
        return silhouettes;
    }
}
