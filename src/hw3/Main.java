package hw3;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        guessTheNumber();
        guessTheWord();
    }

    //Guess the number game
    private static void guessTheNumber() {
        Random r = new Random();
        int attempts = 3, currentRound = 1, guess, answer = r.nextInt(10);
        boolean gameState = true, restart = false;
        Scanner in = new Scanner(System.in);
        while (gameState) {
            if (currentRound == 1) {
                System.out.printf("\nGuess the number between 0 and 9. You have %d attempts.\n\n", attempts);
            }
            System.out.println("\tRound " + currentRound);
            System.out.print("Enter your value: ");
            try {
                guess = in.nextInt();
            } catch (Exception e) {
                System.out.println("You should enter integer number!!!\n");
                in.next();
                continue;
            }
            if (guess == answer) {
                gameState = false;
                System.out.println("Congratulations! You won this game :D\nWant to play one more?[1-yes/0-no]");
                if (in.next().equals("1")) {
                    restart = true;
                } else {
                    System.out.println("Goodbye!");
                    currentRound = -2;
                }
            } else if (guess < answer) {
                System.out.println("\nYour number is less than the guessed.\n");
            } else {
                System.out.println("\nYour number is more than the guessed.\n");
            }
            currentRound++;
            if (currentRound > attempts && !restart) {
                gameState = false;
                System.out.println("\nYou have exhausted all attempts.\nThe answer is " + answer + "\n\nWant to play one more?[1-yes/0-no]");
                if (in.next().equals("1")) {
                    restart = true;
                } else {
                    System.out.println("Goodbye!");
                }
            }
            if (restart) {
                answer = r.nextInt(10);
                attempts = 3;
                currentRound = 1;
                restart = false;
                gameState = true;
            }
        }
    }

    //Guess the word game
    private static void guessTheWord(){
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leek", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        Random r = new Random();
        Scanner in = new Scanner(System.in);
        int answerIndex = r.nextInt(words.length);
        boolean gameState = true;
        String answer = words[answerIndex];
        StringBuilder resultToShow = new StringBuilder();
        System.out.print("\nI think about some food. Can you guess it?\n\nGuess: ");
        while (gameState) {
            String guess = in.next().toLowerCase();
            if (guess.equals(answer)) {
                System.out.println("Good job, you guessed it!");
                gameState = false;
            } else {
                for (int i = 0; i < 15; i++) {
                    if (i < answer.length() && i < guess.length()) {
                        if (answer.charAt(i) == guess.charAt(i)) {
                            resultToShow.append(answer.charAt(i));
                        } else {
                            resultToShow.append('#');
                        }
                    } else {
                        resultToShow.append('#');
                    }
                }
                System.out.println("\nYou guessed the following letters correctly:\n" + resultToShow);
                resultToShow = new StringBuilder();
                System.out.print("Try again: ");
            }
        }
    }
}