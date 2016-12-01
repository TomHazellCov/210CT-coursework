package com.tomhazell.CT210.Week7;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        //initiate the graph
        Graph graph = new Graph();
        //add nodes
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);

        //add deges
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(4, 3);

        //run is path method, keep ht result as well as printing outputto text file
        boolean result = graph.isPath(1, 4, true);

        graph.isConnected();
        System.out.println(result);
    }


    /**
     * This is a class used to contain a grap and store the related methods.
     */
    static class Graph {
        //use a map to store an adjacency list
        private Map<Integer, List<Integer>> graph = new HashMap<>();

        private List<Integer> nodesBeenTo = new ArrayList<>();//used for the isPath Method
        private BufferedWriter bufferedWriter;//used for printing the isPath to file

        /**
         * @param i the int to add as a node to the graph
         */
        public void addNode(Integer i) {
            graph.put(i, new ArrayList<>());
        }

        /**
         * Adds an edge betwen the 2 nodes
         *
         * @param from the int for one node in the edge
         * @param to   the int for one node in the edge
         */
        public void addEdge(Integer from, Integer to) {
            List<Integer> fromList = graph.get(from);
            if (fromList == null) {
                fromList = new ArrayList<>();
            }
            fromList.add(to);
            graph.put(from, fromList);

            List<Integer> toList = graph.get(to);
            if (toList == null) {
                toList = new ArrayList<>();
            }
            toList.add(from);
            graph.put(to, toList);
        }

        public boolean isConnected() throws IOException {
            Integer startingNode = null;
            //because it is undirected if you can get from one node to everyother node the graph is connected
            for (Integer i : graph.keySet()) {
                //pick the first node as the starting node
                if (startingNode == null) {
                    startingNode = i;
                }

                //see if the current node is connected to the starting node
                if (!isPath(startingNode, i, false)) {
                    //if they are not connected then exit out of the method as we are not in a connected graph
                    System.out.println("No");
                    return false;
                }
            }

            System.out.println("Yes");
            return true;
        }

        /**
         * Navigates teh grtaph and stores the path betwen v and k (if there is one) in path.txt
         * Used to set up the method #recIsPath
         *
         * @param v An int/vertex in the graph
         * @param k An int/vertex in the graph
         * @return a boolean, true if a path is found false otherwise
         */
        public boolean isPath(Integer v, Integer k, boolean printToPath) throws IOException {
            nodesBeenTo.clear();
            FileWriter out = new FileWriter("path.txt", true);
            bufferedWriter = new BufferedWriter(out);
            boolean result = recIsPath(v, k, printToPath);
            bufferedWriter.close();
            out.close();
            return result;
        }

        /**
         * Internal method used to recursively find a path between v and k
         * Base case is where v and k are the same
         *
         * @param v An int/vertex in the graph
         * @param k An int/vertex in the graph
         * @return a boolean, true if a path is found false otherwise
         */
        private boolean recIsPath(Integer v, Integer k, boolean printToPath) throws IOException {
            if (v.equals(k)) {
                if (printToPath) {
                    bufferedWriter.write(k.toString());
                    System.out.println(k.toString());
                    bufferedWriter.write("\n");
                }
                return true;
            }

            nodesBeenTo.add(v);

            List<Integer> integers = graph.get(v);
            for (Integer i : integers) {
                if (!nodesBeenTo.contains(i)) {
                    boolean result = recIsPath(i, k, printToPath);
                    if (result) {
                        if (printToPath) {
                            bufferedWriter.write(v.toString());
                            System.out.println(v.toString());
                            bufferedWriter.write("\n");
                        }
                        return true;
                    }
                }
            }
            return false;
        }

    }
}
