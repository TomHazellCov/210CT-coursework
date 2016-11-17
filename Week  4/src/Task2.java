import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * advanced task 2
 */
public class Task2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type n ints sperated by a space");
        String input = scanner.nextLine();
        System.out.println("What position do you want");
        int m = scanner.nextInt();
        List<Integer> initalList = new ArrayList<>();
        for (String s : input.split(" ")) {
            initalList.add(Integer.parseInt(s));
        }

        System.out.print(quick(initalList, m));
    }

    /**
     * @param list the list t to search through
     * @param m    the position in the list to find
     * @return the result when its found
     */
    private static Integer quick(List<Integer> list, int m) {
        Random rand = new Random();

        if (list.size() == 1) {
            return list.get(0);
        } else {
            //check if all the elements in the list are the same, if they are then you have the solution.
            int last = list.get(0);
            boolean allSame = true;

            for (int i = 1; i < list.size(); i++) {
                if (last != list.get(i)) {
                    allSame = false;
                    break;
                }
            }
            if (allSame) {
                return list.get(0);
            }
        }

        //get a pivot by selecting a random element
        int pivot = list.get(rand.nextInt(list.size()));

        List<Integer> smaller = new ArrayList<>();//for ints smaller than or equal
        List<Integer> larger = new ArrayList<>();//for ints larger than

        for (Integer i : list) {
            if (i > pivot) {
                larger.add(i);
            } else {
                smaller.add(i);
            }
        }

        if (m <= smaller.size()) {//use smaller list
            return quick(smaller, m);
        } else {//use larger list
            m = m - smaller.size();
            return quick(larger, m);
        }

    }
}
