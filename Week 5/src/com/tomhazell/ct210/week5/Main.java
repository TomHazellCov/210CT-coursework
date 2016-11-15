package com.tomhazell.ct210.week5;

public class Main {

    public static void main(String[] args) {
        int[][] initalMatrix = MatrixUtils.getMatrixInput(3, 3);
        int[][] result = new int[3][3];
        int powerOff = 4;
        result = initalMatrix.clone();
        for (int i = 1; i < powerOff; i++) {
            result = multiplyMatrices(result, initalMatrix);
        }
        MatrixUtils.printMatrix(result);

    }

    /**
     * @param matrix1 a matrix to be added/subtracted mus tbe same size as matrix2
     * @param matrix2 a matrix to be added/subtracted must be same size as matrix1
     * @return the result of the multiplication
     */
    private static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        //check the matrises are the correct sizes.
        if (!(matrix1.length == matrix2[0].length && matrix1[0].length == matrix2.length)) {
            throw new MatrixErrorException();
        }

        int[][] finalMatrix = new int[matrix1.length][matrix2[0].length];
        //for each row down
        for (int i = 0; i < matrix1.length; i++) {
            //for each row accross in the second matrix
            for (int i1 = 0; i1 < matrix2[0].length; i1++) {
                //for each row accross
                int total = 0;
                for (int j = 0; j < matrix1[0].length; j++) {
                    total = total + matrix1[i][j] * matrix2[j][i1];
                }
                finalMatrix[i][i1] = total;
            }
        }
        return finalMatrix;
    }
}
