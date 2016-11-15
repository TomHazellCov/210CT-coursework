package com.tomhazell.ct210.week5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by tom on 10/11/16.
 */
public class Task2 {

    public static void main(String[] args) {
        List<Element> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Type The nth child on one line, no the next all the names seperated by a comma and on the final one who to start on");
        int nth = Integer.parseInt(scanner.nextLine());
        String names = scanner.nextLine();
        String starting = scanner.nextLine();

        //generate linked list
        String[] namesSplit = names.split(",");
        Element head = null;
        for (String name : namesSplit) {
            if (head != null) {
                Element el = new Element(name);
                head.setNext(el);
                list.add(el);
                head = el;
            }else {
                Element el = new Element(name);
                list.add(el);
                head = el;
            }
        }
        head.setNext(list.get(0));

        //search for starting element
        Element search = list.get(0);
        while (!search.getName().equals(starting)){
            search = search.getNext();
        }

        Element perv = null;
        //now lets go. We want every nth element and to remove it
        while (!search.getNext().equals(search)){
            //find the nth element
            for (int i = 0; i < nth - 1; i++) {
                perv = search;
                search = search.getNext();
            }
            //now remove the element
            perv.setNext(search.getNext());
        }

        System.out.println("Winner: " + search.getName());

    }

    /**
     * A simple class used in hte linked list to store the next element and the name of the current element
     */
    static class Element {
        private String name;
        private Element next;

        public Element(String name) {
            this.name = name;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Element getNext() {
            return next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Element element = (Element) o;

            return name.equals(element.name);

        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }
}
