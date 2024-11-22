package com.cf;

import java.util.Random;
import java.util.Scanner;

/**
 * Copyright(C) 2024- com.cf
 * FileName:    Main
 * Author:      cf
 * Date:        2024/11/20 18:58
 * Description:
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        // initial chances
        int chances = 5;
        System.out.println("You have " + chances + " chances to guess the correct number.");

        // Enter a level repeatly until the level is correct
        int level;
        do {
            System.out.println("\nPlease select the difficulty level:");
            System.out.println("1. Easy (10 chances)");
            System.out.println("2. Medium (5 chances)");
            System.out.println("3. Hard (3 chances)");

            System.out.print("\nEnter your choice: ");
             level = scanner.nextInt();
        } while (level != 1 && level != 2 && level != 3);

        String levelName = "";
        switch (level) {
            case 1:
                chances = 10;
                levelName = "Easy";
                break;
            case 2:
                chances = 5;
                levelName = "Medium";
                break;
            case 3:
                chances = 3;
                levelName = "Hard";
                break;
        }

        System.out.println("\nGreat! You have selected the " + levelName + " difficulty level.");
        System.out.println("Let's start the game!");

        Random random = new Random();
        int number = random.nextInt(1, 101);
        System.out.println("====>The number is " + number);
        boolean binggo = false;
        int guess;
        int attempts = 0;
        do {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            attempts++;
            if (guess == number) {
                binggo = true;
                System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                break;
            } else if (guess < number) {
                System.out.println("Incorrect! The number is greater than " + guess + ".");
            } else  {
                System.out.println("Incorrect! The number is less than " + guess + ".");
            }
        } while (attempts < chances);

        if (!binggo) {
            System.out.println("Sorry, you runs out of " + attempts
                    + " changes! The correct number is " + number + ".");
        }
    }
}