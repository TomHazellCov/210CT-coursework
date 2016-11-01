package com.tomhazell.ct210.week3;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Integer> results = new ArrayList<>();//TODO output format just heoght or full tower??

    public static void main(String[] args) {
        Cube c = new Cube(0, 5);
        Cube c1 = new Cube(1, 5);
        Cube c2 = new Cube(0, 6);
        List<Cube> cubes = new ArrayList<>();
        cubes.add(c);
        cubes.add(c1);
        cubes.add(c2);
        rec(cubes, new ArrayList<>());

        int largest = 0;
        for (Integer result : results) {
            if (result > largest) {
                largest = result;
            }
        }
        System.out.println(largest);
    }

    public static void rec(List<Cube> cubesLeft, List<Cube> cubesUsed) {
        for (Cube cude : cubesLeft) {
            if (cubesUsed.size() == 0 || cubesUsed.get(cubesUsed.size() - 1).getColour() != cude.getColour()) {

                List<Cube> newCubes = new ArrayList<>(cubesLeft);
                List<Cube> newUsedCubes = new ArrayList<>(cubesUsed);
                newCubes.remove(cude);
                newUsedCubes.add(cude);

                rec(newCubes, newUsedCubes);
            }


        }
        addResult(cubesUsed);

    }

    private static void addResult(List<Cube> cubesUsed) {
        int height = 0;
        for (Cube cube : cubesUsed) {
            height += cube.getEdgeLength();
        }
        results.add(height);
    }

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
