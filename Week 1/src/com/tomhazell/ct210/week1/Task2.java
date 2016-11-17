package com.tomhazell.ct210.week1;

/**
 * This is for advanced task2 addition and subtraction
 */
public class Task2 {

    //advanced task 2
    public static void main(String[] args){
        int[][] matrix1 = new int[2][3];
        int[][] matrix2 = new int[2][3];
        initMatrixes(matrix1, matrix2);

        int[][] solution = addSubtractMatrices(matrix1, matrix2, false);
        MatrixUtils.printMatrix(solution);
    }

    /**
     * This initialises 2 slightly random matrixs of size 2*3
     * @param matrix1 the first matrix to fill up
     * @param matrix2 the second matrix to fill up
     */
    private static void initMatrixes(int[][] matrix1, int[][] matrix2) {
        matrix1[0][0] = 1;
        matrix1[0][1] = 2;
        matrix1[0][2] = 3;
        matrix1[1][0] = 7;
        matrix1[1][1] = 8;
        matrix1[1][2] = 9;

        matrix2[0][0] = 5;
        matrix2[0][1] = 6;
        matrix2[0][2] = 7;
        matrix2[1][0] = 3;
        matrix2[1][1] = 4;
        matrix2[1][2] = 5;
    }

    /**
     * @param matrix1 a matrix to be added/subtracted must be same size as matrix2
     * @param matrix2 a matrix to be added/subtracted must be same size as matrix1
     * @param toAdd boolean if true it will add the matrixes if false it will subtract
     * @return the result of the addition or subtraction
     */
    private static int[][] addSubtractMatrices(int[][] matrix1, int[][] matrix2, boolean toAdd) {
        //check matrices are the same size
        if (!(matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length)){
            throw new MatrixErrorException();
        }
        int[][] finalMatrix = new int[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                finalMatrix[i][j] = toAdd ? matrix1[i][j] + matrix2[i][j] : matrix1[i][j] - matrix2[i][j];
            }
        }
        return finalMatrix;
    }
}
