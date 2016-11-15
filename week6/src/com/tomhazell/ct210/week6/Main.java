package com.tomhazell.ct210.week6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

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

        try (BufferedReader br = new BufferedReader(new FileReader("paragraph.csv"))) {
            String line = "";

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");

                root = insertNode(Integer.parseInt(parts[0]), parts[1], root);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        preOrder(root);
        System.out.println();
        findWord("work", root);

    }

    private static void findWord(String word, Node root) {
        System.out.println("At " + root.getWord());

        if (root.getWord().equals(word)) {
            System.out.println("Yes");
        } else {
            if (word.compareTo(root.getWord()) > 0) {//data > root.getWord
                if (root.getRight() == null){
                    System.out.println("No");
                    return;
                }
                System.out.println("Going Right");
                findWord(word, root.getRight());
            } else {
                if (root.getLeft() == null){
                    System.out.println("No");
                    return;
                }
                System.out.println("Going Left");
                findWord(word, root.getLeft());
            }
        }
    }

    private static void preOrder(Node root) {
        System.out.println(root.getWord() + " " + root.getFrequency());
        if (root.getLeft() != null) {
            preOrder(root.getLeft());
        }
        if (root.getRight() != null) {
            preOrder(root.getRight());
        }
    }

    public static Node insertNode(int val, String data, Node root) {
        if (root == null) {
            return new Node(val, data);
        } else {
            if (data.compareTo(root.getWord()) > 0) {
                if (root.getRight() == null) {
                    Node node = new Node(val, data);
                    root.setRight(node);
                    node.setRoot(root);
                } else {
                    return insertNode(val, data, root.getRight());
                }
            } else {
                if (root.getLeft() == null) {
                    Node node = new Node(val, data);
                    root.setLeft(node);
                    node.setRoot(root);
                } else {
                    return insertNode(val, data, root.getLeft());
                }
            }
        }
        Node node = root;
        while (node.getRoot() != null) {
            node = node.getRoot();
        }
        return node;

    }

}

