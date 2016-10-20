package com.tomhazell.ct210.week2;

public class Main {

    public static void main(String[] args) {
        task1(1, 20, 3, 5);
    }

    /**
     * This takes the variables from 2 linear functions m1*n + k1 and m2*n array<int, 10> a+ k1. And will return the int n that
     * the relative runtime of the 2 algorithms switches.
     * @param m1 where m1*n + k1 is the first equation
     * @param k1 where m1*n + k1 is the first equation
     * @param m2 where m2*n + k2 is the second equation
     * @param k2 where m2*n + k2 is the second equation
     * @return the input value at which one overtakes the other in relative run time.
     */
    static int task1(int m1, int k1, int m2, int k2) {
        //work which is initially faster
        boolean isAFaster = m1 + k1 > m2 + k2;
        int count = 1;
        while (true) {
            boolean result = (m1 * count) + k1 > (m2 * count) + k2;
            if (result != isAFaster) {
                break;
            }
            count += 1;
        }
        System.out.println("Initially " + (isAFaster ? "A" : "B") + " was faster");
        System.out.println("But it was over taken at n=" + count);
        return count;
    }
}
