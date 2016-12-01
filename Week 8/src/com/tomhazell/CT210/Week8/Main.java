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
        //Initiate the graph object
        Graph graph = new Graph();

        //add all the nodes
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);

        //add all the edges
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 4, 4);
        graph.addEdge(2, 5, 2);
        graph.addEdge(2, 4, 4);
        graph.addEdge(1, 5, 3);
        graph.addEdge(4, 5, 4);
        graph.addEdge(3, 5, 4);
        graph.addEdge(3, 6, 5);
        graph.addEdge(5, 6, 7);

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
         * Adds an edge between the 2 nodes
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
            //this is hte map we will store the new graph in
            Map<Integer, List<Edge>> newGraph = new HashMap<>();

            //add all edges to a list
            List<Edge> edges = new ArrayList<>();
            for (Map.Entry<Integer, List<Edge>> entry : graph.entrySet()) {
                for (Edge edge : entry.getValue()) {
                    edge.setFrom(entry.getKey());
                    if (!edges.contains(edge)) {
                        edges.add(edge);
                    }
                }
            }

            //sort the list of edges in reverse order by weight see Edge.compareTo
            Collections.sort(edges);

            while (edges.size() > 0) {//we would idealy use a isConnected() method here but that would be considerably less efficient
                //get and  remove the longest edge
                Edge edge = edges.get(0);
                edges.remove(0);

                //if the graph already contains a path betwen the 2 nodes specifyed in the edge (meaning we would create a cycle by adding the new edge)
                //then dont run the next code block
                if ((!newGraph.containsKey(edge.getFrom()) || !newGraph.containsKey(edge.getTo())) || !isPath(edge.getTo(), edge.getFrom(), newGraph)) {
                    //add the edge to the new graph
                    List<Edge> fromList = newGraph.get(edge.getFrom()) != null ? newGraph.get(edge.getFrom()) : new ArrayList<>();
                    List<Edge> toList = newGraph.get(edge.getTo()) != null ? newGraph.get(edge.getTo()) : new ArrayList<>();

                    fromList.add(new Edge(edge.getWeight(), edge.getTo()));
                    toList.add(new Edge(edge.getWeight(), edge.getFrom()));

                    newGraph.put(edge.getFrom(), fromList);
                    newGraph.put(edge.getTo(), toList);
                }
            }

            System.out.println("Printing pre and post order of max cost spanning tree");
            printSpanningTree(newGraph);
        }

        class Edge implements Comparable {
            private Integer weight;
            private Integer to;
            private Integer from;//only used for the spanning tree to store all edges

            public Edge(Integer weight, Integer to) {
                this.weight = weight;
                this.to = to;
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


            /**
             * re writen the equals method because i want to and from to be interchangeable
             */
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

            /**
             * sort by weight in reverse order,
             * used by Collections.sort(List<Edge>);
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
