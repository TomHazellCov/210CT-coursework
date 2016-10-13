package com.tomhazell.ct210.week1;

/**
 * This is for advanced task2 addition and subtraction
 */
public class Task2Part2 {

    //advanced task 2
    public static void main(String[] args){
        int[][] matrix1 = new int[2][3];
        int[][] matrix2 = new int[3][2];
        initMatrixes(matrix1, matrix2);

        int[][] solution = multiplyMatrices(matrix1, matrix2);
        MatrixUtils.printMatrix(solution);
    }

    private static void initMatrixes(int[][] matrix1, int[][] matrix2) {
        matrix1[0][0] = 1;
        matrix1[0][1] = 2;
        matrix1[0][2] = 3;
        matrix1[1][0] = 4;
        matrix1[1][1] = 5;
        matrix1[1][2] = 6;

        matrix2[0][0] = 7;
        matrix2[1][0] = 9;
        matrix2[2][0] = 11;
        matrix2[0][1] = 8;
        matrix2[1][1] = 10;
        matrix2[2][1] = 12;
    }

    /**
     * @param matrix1 a matrix to be added/subtracted mus tbe same size as matrix2
     * @param matrix2 a matrix to be added/subtracted must be same size as matrix1
     * @return the result of the multiplication
     */
    private static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        //check the matrises are the correct sizes.
        if (!(matrix1.length == matrix2[0].length && matrix1[0].length == matrix2.length)){
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
