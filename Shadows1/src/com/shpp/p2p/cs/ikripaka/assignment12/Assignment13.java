package com.shpp.p2p.cs.ikripaka.assignment12;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.regex.Matcher;

/**
 * -------- SILHOUETTES --------
 * This class can count how many shadows in the picture.
 * Program can work with jpg/png files.
 * Built on the breadth first search
 */

public class Assignment13 extends Constants {

    public static void main(String[] args) {
        try {
            String pathToTheFile = checkLine(args);

            BufferedImage bufferedImage;
            bufferedImage = ImageIO.read(new File(pathToTheFile));

            System.out.println("Input image: " + pathToTheFile);
            Searcher searcher = new Searcher(getPixelsArray(bufferedImage,
                    bufferedImage.getHeight(), bufferedImage.getWidth()), bufferedImage.getHeight(), bufferedImage.getWidth());
            System.out.println(searcher.getSilhouettes() + " - silhouette(s) in the image");

        } catch (Exception e) {
            System.err.println("Something went wrong");
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Fills the variable (pixels) with color values
     *
     * @param bufferedImage - buffered image image
     * @param height        - height of the image
     * @param width         - width of the image
     * @return - variable that filled with color values
     */
    private static int[][] getPixelsArray(BufferedImage bufferedImage, int height, int width) {
        int[][] pixels = new int[width][height];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                pixels[col][row] = bufferedImage.getRGB(col, row);
            }
        }
        return pixels;
    }


    /**
     * Check line for correctness
     * Returns path to the file
     *
     * @param arg - path to the file
     * @return - path ti the file
     * @throws Exception - Invalid name / Invalid path to the file
     */
    private static String checkLine(String[] arg) throws Exception {
        Constants constants = new Constants();
        if (arg.length != 0) {
            Matcher matcher = constants.PICTURE_FORMAT.matcher(arg[0]);
            if (!matcher.matches()) {
                throw new Exception("Invalid name");
            }
            File file = new File(arg[0]);
            if (!file.exists() || file.isDirectory()) {
                throw new Exception("Invalid path to the file");
            }
        } else {
            return "assets/image1.jpg";
        }
        return arg[0];
    }
}
