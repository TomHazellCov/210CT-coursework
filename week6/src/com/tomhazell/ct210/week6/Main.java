package com.tomhazell.ct210.week6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    /**
     * class used for storing data about each element in a tree
     */
    static class Node {
        private String word;
        private int frequency;
        private Node top;
        private Node left;
        private Node right;
        private Node root;

        public Node(int frequency, String word) {
            setWord(word);
            setFrequency(frequency);
        }


        public Node getRoot() {
            return root;
        }

        public void setRoot(Node root) {
            this.root = root;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public Node getTop() {
            return top;
        }

        public void setTop(Node top) {
            this.top = top;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Node root = null;

        //read in all elements from the csv file, for each add it to the tree in the appropriate position using the
        // insertNode function
        try (BufferedReader br = new BufferedReader(new FileReader("paragraph.csv"))) {
            String line = "";

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                root = insertNode(Integer.parseInt(parts[0]), parts[1], root);
            }

        } catch (IOException e) {
            System.out.println(e.toString());
            return;
        }

        System.out.println("Pre Order Printing");
        preOrder(root);
        System.out.println("Searching for work");
        findWord("work", root);

    }

    /**
     * This searches the binary search tree to find a specific work and prints out its path getting there
     * and "yes" if it finds it or "no" if it dose not
     *
     * @param word
     * @param root
     */
    private static void findWord(String word, Node root) {
        System.out.println("At " + root.getWord());

        if (root.getWord().equals(word)) {
            System.out.println("Yes");
        } else {
            if (word.compareTo(root.getWord()) > 0) {//data > root.getWord
                if (root.getRight() == null) {
                    System.out.println("No");
                    return;
                }
                System.out.println("Going Right");
                findWord(word, root.getRight());
            } else {
                if (root.getLeft() == null) {
                    System.out.println("No");
                    return;
                }
                System.out.println("Going Left");
                findWord(word, root.getLeft());
            }
        }
    }

    /**
     * This prints out all nodes in the tree where the parameter root is the root of the tree to print
     *
     * @param root teh root of the tree.
     */
    private static void preOrder(Node root) {
        System.out.println(root.getWord() + " " + root.getFrequency());
        if (root.getLeft() != null) {
            preOrder(root.getLeft());
        }
        if (root.getRight() != null) {
            preOrder(root.getRight());
        }
    }

    /**
     * @param val  the frequency of the word
     * @param data the word that is stored in each node and that the nodes are sorted by
     * @param root the current root node (can be null)
     * @return the root node after the addition operation
     */
    private static Node insertNode(int val, String data, Node root) {
        //if the root is null then just create the root and return it
        if (root == null) {
            return new Node(val, data);
        } else {
            //see what side of the tree this new node need to be on (compared to the current root node)
            if (data.compareTo(root.getWord()) > 0) {
                //if that side of the tree is empty then create the new node, else recurse deeper
                if (root.getRight() == null) {
                    Node node = new Node(val, data);
                    root.setRight(node);
                    node.setRoot(root);
                } else {
                    return insertNode(val, data, root.getRight());
                }
            } else {
                //if that side of the tree is empty then create the new node, else recurse deeper
                if (root.getLeft() == null) {
                    Node node = new Node(val, data);
                    root.setLeft(node);
                    node.setRoot(root);
                } else {
                    return insertNode(val, data, root.getLeft());
                }
            }
        }
        //this is to ensure that we return the root node and not another node. This is used because in java all
        // parameters are parsed by value not by reference
        Node node = root;
        while (node.getRoot() != null) {
            node = node.getRoot();
        }
        return node;

    }

}

