package com.tomhazell.ct210.week3;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the answer for Q1 a
 */
public class Main {

    //used to store the answer
    static List<Integer> results = new ArrayList<>();

    public static void main(String[] args) {
        //add some mock cubes
        Cube c = new Cube(0, 5);
        Cube c1 = new Cube(1, 5);
        Cube c2 = new Cube(0, 6);
        List<Cube> cubes = new ArrayList<>();
        cubes.add(c);
        cubes.add(c1);
        cubes.add(c2);
        //call function
        getLargestTower(cubes, new ArrayList<>());

        //print result
        int largest = 0;
        for (Integer result : results) {
            if (result > largest) {
                largest = result;
            }
        }
        System.out.println(largest);
    }

    /**
     * This uses a bruit force like method, by generating every possible order of ayny number of the cubes the calling
     * verifyResult to see if its the largest results.
     * @param cubesLeft the cubes that are not currently in the tower
     * @param cubesUsed the cubes currently in the tower
     */
    public static void getLargestTower(List<Cube> cubesLeft, List<Cube> cubesUsed) {
        for (Cube cude : cubesLeft) {
            if (cubesUsed.size() == 0 || cubesUsed.get(cubesUsed.size() - 1).getColour() != cude.getColour()) {

                List<Cube> newCubes = new ArrayList<>(cubesLeft);
                List<Cube> newUsedCubes = new ArrayList<>(cubesUsed);
                newCubes.remove(cude);
                newUsedCubes.add(cude);

                getLargestTower(newCubes, newUsedCubes);
            }
        }
        verifyResult(cubesUsed);

    }

    /**
     * This checks if the current value of resuts is larger or smaller than the one parsed in to the method.
     * @param cubesUsed The resuts to check if its larger
     */
    private static void verifyResult(List<Cube> cubesUsed) {
        int height = 0;
        for (Cube cube : cubesUsed) {
            height += cube.getEdgeLength();
        }
        results.add(height);
    }

    /**
     * This is the object used to store info about cubes
     * It stores both a colour and edge height as ints.
     */
    private static class Cube {
        private int colour;
        private int edgeLength;

        public Cube(int colour, int edgeLength) {
            this.colour = colour;
            this.edgeLength = edgeLength;
        }

        public Cube() {
        }

        public int getColour() {
            return colour;
        }

        public void setColour(int colour) {
            this.colour = colour;
        }

        public int getEdgeLength() {
            return edgeLength;
        }

        public void setEdgeLength(int edgeLength) {
            this.edgeLength = edgeLength;
        }
    }
}
