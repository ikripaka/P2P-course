package com.shpp.cs.ikripaka.assignment6.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];

        // Combines all sounds in the column
        for(int row = 0; row < toneMatrix.length; row++){
            if(toneMatrix[row][column]){
                for(int i = 0; i < samples[0].length; i++){
                    result[i] = result[i] + samples[row][i];
                }
            }
        }

        // Finds max value
        double maxValue = 0;
        for(int i = 0; i < result.length; i++) {
            if (Math.abs(result[i]) > Math.abs(maxValue)) {
                maxValue = result[i];
            }
        }

        // Normalize array if max value > 1
        if( Math.abs(maxValue) > 1.0){
            for(int i = 0; i < samples[0].length; i++){
                result[i] = result[i] / maxValue;
            }
        }

        return result;
    }
}
