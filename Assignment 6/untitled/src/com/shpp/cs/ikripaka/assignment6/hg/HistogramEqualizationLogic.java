package com.shpp.cs.ikripaka.assignment6.hg;

public class HistogramEqualizationLogic {
    private static final int MAX_LUMINANCE = 255;

    /**
     * Given the luminances of the pixels in an image, returns a histogram of the frequencies of
     * those luminances.
     * <p/>
     * You can assume that pixel luminances range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminances The luminances in the picture.
     * @return A histogram of those luminances.
     */
    public static int[] histogramFor(int[][] luminances) { //делает гистограму с того, что получилось
        int[] histogram = new int[256];

        for (int i = 0; i < luminances.length; i++) {
            for (int j = 0; j < luminances[0].length; j++) {
                histogram[luminances[i][j]]++;
            }
        }

        return histogram;
    }

    /**
     * Given a histogram of the luminances in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) { // расширяет
        int previousNumber = 0;

        for (int i = 0; i < 256; i++) {
            histogram[i] = histogram[i] + previousNumber;
            previousNumber = histogram[i];
        }

        return histogram;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) { // возвращает полное количество всех пикселей
        return luminances.length * luminances[0].length;
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminances The luminances of the input image.
     * @return The luminances of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminances) { // делает картинку
        int[] histogram = histogramFor(luminances);
        histogram = cumulativeSumFor(histogram);

        int totalPixels = totalPixelsIn(luminances);

        int newLuminance = 0;

        for (int i = 0; i < luminances.length; i++) {
            for (int j = 0; j < luminances[0].length; j++) {
                newLuminance = (int) (MAX_LUMINANCE * ((histogram[luminances[i][j]] * 1.0) / (totalPixels * 1.0)));
                luminances[i][j] = newLuminance;
            }
        }


        return luminances;
    }
}
