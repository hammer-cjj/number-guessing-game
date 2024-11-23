package com.cf;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

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
        while (true) {
            int chances = getChances(scanner);
            playGame(scanner, chances);
            String yesOrNot;
            do {
                System.out.print("Are you want to play again? (Y/N): ");
                yesOrNot = scanner.next();
            } while (!yesOrNot.equalsIgnoreCase("y")
                    && !yesOrNot.equalsIgnoreCase("n"));
            // Quit when the user enter N or n
            if (yesOrNot.equalsIgnoreCase("n")) {
                break;
            }

        }
    }

    /**
     * Get chances from user input
     * @param scanner Scanner
     * @return The chances of guessing
     */
    private static int getChances(Scanner scanner) {
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
        return chances;
    }

    /**
     * Play game with user
     * @param scanner Scanner
     * @param chances Guessing chances
     */
    private static void playGame(Scanner scanner, int chances) {
        Random random = new Random();
        int number = random.nextInt(1, 101);
        System.out.println("====>The number is " + number);
        boolean binggo = false;
        int guess;
        int attempts = 0;
        Instant start = Instant.now();
        do {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            attempts++;
            if (guess == number) {
                binggo = true;
                Instant end = Instant.now();
                Duration costTime = Duration.between(start, end);
                System.out.println("Congratulations! You guessed the correct number in "
                        + attempts  + (attempts == 1 ? " attempt" : " attempts")
                        + ", costs time: " + (costTime.toMillis() / 1000) +"s");
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