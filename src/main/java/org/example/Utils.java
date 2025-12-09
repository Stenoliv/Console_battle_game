package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    //input check if a number and in scope of array

    public static int CheckInputNotOutOfBounds(ArrayList<Weapon> inventory) {

        Scanner sysIn = new Scanner(System.in);
        while (true) {
            try {
                int index = Integer.parseInt(sysIn.nextLine());
                if (inventory.size() < index) {
                    System.out.println("Please enter a valid number!");
                } else {
                    return index;
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");

            }
        }
    }


    //overloaded method with one more parameter
    public static int CheckInputNotOutOfBounds(ArrayList<Weapon> inventory, String input) {

        Scanner sysIn = new Scanner(System.in);
        int index = Integer.parseInt(input);
        while (true) {
            try {
                if (inventory.size() < index) {
                    System.out.println("Please enter a valid number!");
                    index = Integer.parseInt(sysIn.nextLine());
                } else {
                    return index;
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
                index = Integer.parseInt(sysIn.nextLine());
            }
        }
    }
}
