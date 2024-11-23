package com.cf;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
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
        // Track the fewest number of attempts
        // it took to guess the number under
        // a specific difficulty level
        HashMap<String, Integer> record = new HashMap<>();
        record.put("Easy", 0);
        record.put("Medium", 0);
        record.put("Hard", 0);
        while (true) {
            int chances = getChances(scanner);
            playGame(scanner, chances, record);
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

        // Enter a level repeatedly until the level is correct
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
     * @param record  Store the minimum number of attempts
     *                it took for the user to guess the correct number
     *                at different levels.
     */
    private static void playGame(Scanner scanner, int chances, HashMap<String, Integer> record) {
        Random random = new Random();
        int number = random.nextInt(1, 101);
//        System.out.println("====>The number is " + number);
        boolean bingo = false;
        int guess;
        int attempts = 0;
        Instant start = Instant.now();
        do {
            System.out.print("\nEnter your guess: ");
            guess = scanner.nextInt();
            attempts++;
            if (guess == number) {
                bingo = true;
                Instant end = Instant.now();
                Duration costTime = Duration.between(start, end);
                System.out.println("Congratulations! You guessed the correct number in "
                        + attempts  + (attempts == 1 ? " attempt" : " attempts")
                        + ", costs time: " + (costTime.toMillis() / 1000) +"s");

                String levelName = "";
                Integer minAttempts = 0;
                if (chances == 10) {
                    levelName = "Easy";
                    minAttempts = record.getOrDefault("Easy", 0);
                } else if (chances == 5){
                    levelName = "Medium";
                    minAttempts = record.getOrDefault("Medium", 0);
                } else if (chances == 3) {
                    levelName = "Hard";
                    minAttempts = record.getOrDefault("Hard", 0);
                }

                //  Fist time guess the number.
                if (minAttempts == 0) {
                    record.put(levelName, attempts);
                }
                // Get a new fewest number of attempts
                if (minAttempts != 0 && attempts < minAttempts) {
                    record.put(levelName, attempts);
                }

                System.out.println("Your fewest number of attempts to guess the number " +
                        "under a " + levelName + " level is " + record.get(levelName));

                break;
            } else if (guess < number) {
                System.out.println("Incorrect! The number is greater than " + guess + ".");
            } else  {
                System.out.println("Incorrect! The number is less than " + guess + ".");
            }
            hint(number, guess, attempts, chances);
        } while (attempts < chances);

        if (!bingo) {
            System.out.println("Sorry, you runs out of " + attempts
                    + " changes! The correct number is " + number + ".");
        }
    }

    /**
     * Provides clues to the user at different levels.
     *  Easy (chances == 10) and attempts == 5
     *  Medium (chances == 5) and attempts == 3
     *  Hard (chances == 3) and attempts == 2
     * @param number   the correct number
     * @param guess    user guess
     * @param attempts the number of attempts so far
     * @param chances  the number of chances
     */
    private static void hint(int number, int guess, int attempts, int chances) {
        if ((chances == 10 && attempts == 5)
                || (chances == 5 && attempts == 3)
                || (chances == 3 && attempts == 2)) {
            int diff = Math.abs(guess -number);
            if (diff > 20) {
                System.out.println("The difference between your guess and the correct number is large.");
            } else if (diff > 10) {
                System.out.println("The difference between your guess and the correct number " +
                        "is greater than 10 but less than or equal to 20.");
            } else {
                System.out.println("Your guess is very close.");
            }
        }
    }
}