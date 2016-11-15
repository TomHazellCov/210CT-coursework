package com.tomhazell.ct210.week5;

import java.util.Scanner;

/**
 * Taken From week 1
 * Created by tom on 11/10/16.
 */
public class MatrixUtils {

    /**
     * This will print out a matrix
     *
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

    static int[][] getMatrixInput(int x, int y) {
        int[][] input = new int[y][x];
        System.out.println("Type a matrix of ints " + x + " by " + y + " Seperated by a space");
        Scanner scanner = new Scanner(System.in);
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                input[j][i] = scanner.nextInt();

            }
        }

        return input;
    }
}
