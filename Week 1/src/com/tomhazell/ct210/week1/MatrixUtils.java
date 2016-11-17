package com.tomhazell.ct210.week1;

import java.util.Scanner;

public class MatrixUtils {

    /**
     * This will print out a matrix
     * @param matrix the matrix to print
     */
    static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + ", ");
            }
            System.out.print("\n");
        }
    }
}
