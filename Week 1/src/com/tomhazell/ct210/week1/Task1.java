package com.tomhazell.ct210.week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * week 1 advanced task 1
 */
public class Task1 {

    public static final int DAYS_SIMULATED = 30;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //get the user to input the variables
        System.out.println("Enter the number of an alien will lay a day");
        int eggs = scanner.nextInt();

        System.out.println("Enter the number of days it takes an egg  to hatch");
        int hatchDays = scanner.nextInt();

        //use a list where each position represents a day where the value is the number of aliens
        List<Integer> numberOfAliens = new ArrayList<>();
        numberOfAliens.add(1);

        //loop through all 30 days required in the question
        for (int currentDay = 1; currentDay <= DAYS_SIMULATED; currentDay++){
            //checks if its possible for any aliens to have hatched given how long they have been alive
            if((currentDay - hatchDays) > 0){
                //the total number of aliens is calculated by:
                //      aliens alive yesterday + aliens alive hatchDays ago * the number of eggs each alien lays
                numberOfAliens.add(numberOfAliens.get(currentDay - 1) + (eggs * numberOfAliens.get(currentDay - hatchDays)));
            }else{
                numberOfAliens.add(numberOfAliens.get(currentDay - 1));
            }
        }
        //print the solution (the number of aliens on the 30th day)
        for (Integer numberOfAlien : numberOfAliens) {
            System.out.println(numberOfAlien);
        }

    }
}
