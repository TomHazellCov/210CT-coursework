package com.tomhazell.CT210.Week8;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * Most of this is taken from week 7
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(3);

        graph.addEdge(1, 2, 9);
        graph.addEdge(2, 3, 9);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 4, 1);

        graph.maxSpanningTree();
    }


    /**
     * Mostly from week 8
     * This is a class used to contain a grap and store the related methods.
     */
    static class Graph {
        //use a map to store an adjacency list
        Map<Integer, List<Edge>> graph = new HashMap<>();

        List<Integer> nodesBeenTo = new ArrayList<>();//used for the isPath Method

        /**
         * From week 8
         *
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
        public void addEdge(Integer from, Integer to, Integer weight) {
            List<Edge> fromList = graph.get(from);
            if (fromList == null) {
                fromList = new ArrayList<>();
            }
            fromList.add(new Edge(weight, to));
            graph.put(from, fromList);

            List<Edge> toList = graph.get(to);
            if (toList == null) {
                toList = new ArrayList<>();
            }
            toList.add(new Edge(weight, from));
            graph.put(to, toList);
        }

//        /**
//         * from week 8
//         */
//        public boolean isConnected() throws IOException {
//            Integer startingNode = null;
//            //because it is undirected if you can get from one node to everyother node the graph is connected
//            for (Integer i : graph.keySet()) {
//                //pick the first node as the starting node
//                if (startingNode == null) {
//                    startingNode = i;
//                }
//
//                //see if the current node is connected to the starting node
//                if (!isPath(startingNode, i, graph)) {
//                    //if they are not connected then exit out of the method as we are not in a connected graph
//                    System.out.println("No");
//                    return false;
//                }
//            }
//
//            System.out.println("Yes");
//            return true;
//        }

        /**
         * Mostly from week 8
         * <p>
         * Navigates teh grtaph and stores the path betwen v and k (if there is one) in path.txt
         * Used to set up the method #recIsPath
         *
         * @param v An int/vertex in the graph
         * @param k An int/vertex in the graph
         * @return a boolean, true if a path is found false otherwise
         */
        public boolean isPath(Integer v, Integer k, Map<Integer, List<Edge>> graph) throws IOException {
            nodesBeenTo.clear();

            boolean result = recIsPath(v, k, graph);
            return result;
        }

        /**
         * Mostly from week 8
         * <p>
         * Internal method used to recursively find a path between v and k
         * Base case is where v and k are the same
         *
         * @param v An int/vertex in the graph
         * @param k An int/vertex in the graph
         * @return a boolean, true if a path is found false otherwise
         */
        private boolean recIsPath(Integer v, Integer k, Map<Integer, List<Edge>> graph) {
            if (v.equals(k)) {
                return true;
            }

            nodesBeenTo.add(v);

            List<Edge> edges = graph.get(v);
            for (Edge i : edges) {
                if (!nodesBeenTo.contains(i.getTo())) {
                    boolean result = recIsPath(i.getTo(), k, graph);
                    if (result) {
                        return true;
                    }
                }
            }
            return false;
        }

        private void printSpanningTree(Map<Integer, List<Edge>> graph){
            nodesBeenTo.clear();

            recPrintSpanningTree(graph.keySet().iterator().next(), graph);
        }

        private void recPrintSpanningTree(Integer v, Map<Integer, List<Edge>> graph) {
            nodesBeenTo.add(v);

            //preorder print
            System.out.println("Pre: " + v.toString());

            List<Edge> edges = graph.get(v);
            for (Edge i : edges) {
                if (!nodesBeenTo.contains(i.getTo())) {
                     recPrintSpanningTree(i.getTo(), graph);
                }
            }

            //post order print
            System.out.println("Post: " + v.toString());
        }

        public void maxSpanningTree() throws IOException {
            Map<Integer, List<Edge>> newGraph = new HashMap<>();

            //add all graps to a list
            List<Edge> edges = new ArrayList<>();
            for (Map.Entry<Integer, List<Edge>> entry : graph.entrySet()) {
                for (Edge edge : entry.getValue()) {
                    edge.from = entry.getKey();
                    if (!edges.contains(edge)) {
                        edges.add(edge);
                    }
                }
            }

            Collections.sort(edges);

            while (edges.size() > 0) {
                //get and  remove the longest edge
                Edge edge = edges.get(0);
                edges.remove(0);

                if ((!newGraph.containsKey(edge.from) || !newGraph.containsKey(edge.to)) || !isPath(edge.getTo(), edge.getFrom(), newGraph)) {
                    //we will not create a cycle by inputing this edge
                    List<Edge> fromList = newGraph.get(edge.getFrom()) != null ? newGraph.get(edge.getFrom()) : new ArrayList<>();
                    List<Edge> toList = newGraph.get(edge.getTo()) != null ? newGraph.get(edge.getTo()) : new ArrayList<>();

                    fromList.add(new Edge(edge.weight, edge.to));
                    toList.add(new Edge(edge.weight, edge.from));

                    newGraph.put(edge.getFrom(), fromList);
                    newGraph.put(edge.getTo(), toList);
                }
            }

            System.out.println("Printing pre and post order of max cost spaning tree");
            printSpanningTree(newGraph);


            // Now do pre and post order traversal of the tree
        }

        private void printGraph(Map<Integer, List<Edge>> graph) {

        }

        class Edge implements Comparable {
            private Integer weight;
            private Integer to;
            private Integer from;//only used for hte spanning tree

            public Edge(Integer weight, Integer to) {
                this.weight = weight;
                this.to = to;
            }

            public Edge(Integer weight, Integer to, Integer from) {
                this.weight = weight;
                this.to = to;
                this.from = from;
            }

            public Edge() {
            }

            public Integer getFrom() {
                return from;
            }

            public void setFrom(Integer from) {
                this.from = from;
            }

            public Integer getTo() {
                return to;
            }

            public void setTo(Integer to) {
                this.to = to;
            }

            public Integer getWeight() {
                return weight;
            }

            public void setWeight(Integer weight) {
                this.weight = weight;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Edge edge = (Edge) o;

                if (weight.equals(edge.weight)) {
                    if (from == null && edge.from == null) {
                        if (to.equals(edge.to)) {
                            return true;
                        }
                    } else if (from == null || edge.from == null) {
                        return false;
                    } else if ((to.equals(edge.to) && from.equals(edge.from)) || (to.equals(edge.from) && from.equals(edge.to))) {
                        return true;
                    }
                }
                return false;

            }

            @Override
            public int hashCode() {
                int result = weight != null ? weight.hashCode() : 0;
                result = 31 * result + (to != null ? to.hashCode() : 0);
                return result;
            }

            /**
             * sort by weight in reverse order
             */
            @Override
            public int compareTo(Object o) {
                if (o instanceof Edge) {
                    return weight.compareTo(((Edge) o).weight) * -1;
                }
                return -99;
            }
        }

    }
}
