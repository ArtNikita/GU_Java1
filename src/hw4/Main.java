package hw4;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final char EMPTY = '_';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        int fieldSize;
        int winLineLength = 3;
        System.out.print("Welcome to the XO Game.\nEnter size of the field: ");
        while (true) {
            try {
                fieldSize = in.nextInt();
                break;
            } catch (Exception e) {
                in.next();
                System.out.print("\nIncorrect input.\nEnter size of the field again: ");
            }
        }
        if (fieldSize <= 3) {
            fieldSize = 3;
        } else if (fieldSize == 4) {

        } else if (fieldSize == 5) {
            winLineLength = 4;
        } else if (fieldSize <= 9) {
            winLineLength = 5;
        } else {
            fieldSize = 9;
            winLineLength = 5;
        }
        System.out.println("-------------------\nField Size: " + fieldSize);
        System.out.println("Win line length: " + winLineLength);
        System.out.println("You play with " + DOT_X + ".\n-------------------\n\nEnter the number of the row and the column\nyou want to go (e.t. 1 2).\n");
        startGame(fieldSize, winLineLength);
    }

    private static boolean ifWin(char[][] fieldArray, char symbolToCheck, int winLineLength) {
        for (int i = 0; i < fieldArray.length; i++) {
            for (int j = 0; j < fieldArray[i].length; j++) {
                if (fieldArray[i][j] == symbolToCheck) {
                    //check right
                    if (j <= fieldArray[i].length - winLineLength) {
                        int tempSum = 1;
                        for (int t = 1; t < winLineLength; t++) {
                            if (fieldArray[i][j + t] == symbolToCheck) {
                                tempSum++;
                            }
                        }
                        if (tempSum == winLineLength) return true;
                    }
                    //check down
                    if (i <= fieldArray.length - winLineLength) {
                        int tempSum = 1;
                        for (int t = 1; t < winLineLength; t++) {
                            if (fieldArray[i + t][j] == symbolToCheck) {
                                tempSum++;
                            }
                        }
                        if (tempSum == winLineLength) return true;
                    }
                    //check rightDown
                    if ((j <= fieldArray[i].length - winLineLength) && (i <= fieldArray.length - winLineLength)) {
                        int tempSum = 1;
                        for (int t = 1; t < winLineLength; t++) {
                            if (fieldArray[i + t][j + t] == symbolToCheck) {
                                tempSum++;
                            }
                        }
                        if (tempSum == winLineLength) return true;
                    }
                    //check rightUp
                    if ((j <= fieldArray[i].length - winLineLength) && (i >= winLineLength - 1)) {
                        int tempSum = 1;
                        for (int t = 1; t < winLineLength; t++) {
                            if (fieldArray[i - t][j + t] == symbolToCheck) {
                                tempSum++;
                            }
                        }
                        if (tempSum == winLineLength) return true;
                    }
                }
            }
        }
        return false;
    }

    private static void printField(char[][] fieldArray) {
        System.out.print("   ");
        for (int i = 0; i < fieldArray.length; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < fieldArray.length; i++) {
            System.out.print(i + 1 + " |");
            for (int j = 0; j < fieldArray[0].length; j++) {
                System.out.print(fieldArray[i][j] + "|");
            }
            System.out.println();
        }
    }

    private static boolean isValid(char[][] fieldArray, int x, int y) {
        return x >= 0 && y >= 0 && x < fieldArray.length && y < fieldArray.length && fieldArray[x][y] == EMPTY;
    }

    private static boolean fieldIsFull(char[][] fieldArray) {
        for (char[] chars : fieldArray) {
            for (char aChar : chars) {
                if (aChar == EMPTY) return false;
            }
        }
        return true;
    }

    private static void userTurn(char[][] fieldArray) {
        System.out.println("\nYour turn.");
        int x, y;
        do {
            System.out.print("\nCoords to go: ");
            x = in.nextInt() - 1;
            y = in.nextInt() - 1;
        } while (!isValid(fieldArray, x, y));
        fieldArray[x][y] = DOT_X;
    }

    private static void pcTurn(char[][] fieldArray, int winLineLength) {
        System.out.println("\nAI turn.");
        //If AI wins
        for (int i = 0; i < fieldArray.length; i++) {
            for (int j = 0; j < fieldArray[i].length; j++) {
                if (fieldArray[i][j] == EMPTY) {
                    fieldArray[i][j] = DOT_O;
                    if (ifWin(fieldArray, DOT_O, winLineLength)) {
                        System.out.println("\nCoords: " + (i + 1) + " " + (j + 1));
                        return;
                    }
                    fieldArray[i][j] = EMPTY;
                }
            }
        }
        //If human wins
        for (int i = 0; i < fieldArray.length; i++) {
            for (int j = 0; j < fieldArray[i].length; j++) {
                if (fieldArray[i][j] == EMPTY) {
                    fieldArray[i][j] = DOT_X;
                    if (ifWin(fieldArray, DOT_X, winLineLength)) {
                        fieldArray[i][j] = DOT_O;
                        System.out.println("\nCoords: " + (i + 1) + " " + (j + 1));
                        return;
                    }
                    fieldArray[i][j] = EMPTY;
                }
            }
        }

        //if human has line with spaces
        for (int i = 0; i < fieldArray.length; i++) {
            for (int j = 0; j < fieldArray[i].length; j++) {
                if (fieldArray[i][j] == DOT_X) {
                    //DOWN
                    if ((i + 2) < fieldArray.length && fieldArray[i + 2][j] == DOT_X && fieldArray[i + 1][j] == EMPTY) {
                        fieldArray[i + 1][j] = DOT_O;
                        System.out.println("\nCoords: " + (i + 2) + " " + (j + 1));
                        return;
                    }
//                    if((i-2) >= 0 && fieldArray[i-2][j] == DOT_X && fieldArray[i-1][j] == EMPTY){
//                        fieldArray[i-1][j] = DOT_O;
//                        System.out.println("\nCoords: " + (i) + " " + (j+1));
//                        return;
//                    }
                    //RIGHT
                    if ((j + 2) < fieldArray.length && fieldArray[i][j + 2] == DOT_X && fieldArray[i][j + 1] == EMPTY) {
                        fieldArray[i][j + 1] = DOT_O;
                        System.out.println("\nCoords: " + (i + 1) + " " + (j + 2));
                        return;
                    }
//                    if((j-2) >= 0 && fieldArray[i][j-2] == DOT_X && fieldArray[i][j-1] == EMPTY){
//                        fieldArray[i][j-1] = DOT_O;
//                        System.out.println("\nCoords: " + (i+1) + " " + (j));
//                        return;
//                    }
                    //RIGHT DOWN
                    if ((j + 2) < fieldArray.length && (i + 2) < fieldArray.length && fieldArray[i + 2][j + 2] == DOT_X && fieldArray[i + 1][j + 1] == EMPTY) {
                        fieldArray[i + 1][j + 1] = DOT_O;
                        System.out.println("\nCoords: " + (i + 2) + " " + (j + 2));
                        return;
                    }
                    //RIGHT UP
                    if ((j + 2) < fieldArray.length && (i - 2) >= 0 && fieldArray[i - 2][j + 2] == DOT_X && fieldArray[i - 1][j + 1] == EMPTY) {
                        fieldArray[i - 1][j + 1] = DOT_O;
                        System.out.println("\nCoords: " + (i) + " " + (j + 2));
                        return;
                    }
                }
            }
        }
        //if human has line
        for (int i = 0; i < fieldArray.length; i++) {
            for (int j = 0; j < fieldArray[i].length; j++) {
                if (fieldArray[i][j] == DOT_X) {
                    //DOWN
                    if ((i + 1) < fieldArray.length && fieldArray[i + 1][j] == DOT_X) {
                        if ((i + 2) < fieldArray.length && fieldArray[i + 2][j] == EMPTY) {
                            fieldArray[i + 2][j] = DOT_O;
                            System.out.println("\nCoords: " + (i + 3) + " " + (j + 1));
                            return;
                        }
                        if ((i - 1) >= 0 && fieldArray[i - 1][j] == EMPTY) {
                            fieldArray[i - 1][j] = DOT_O;
                            System.out.println("\nCoords: " + (i) + " " + (j + 1));
                            return;
                        }
                    }
                    //RIGHT
                    if ((j + 1) < fieldArray.length && fieldArray[i][j + 1] == DOT_X) {
                        if ((j + 2) < fieldArray.length && fieldArray[i][j + 2] == EMPTY) {
                            fieldArray[i][j + 2] = DOT_O;
                            System.out.println("\nCoords: " + (i + 1) + " " + (j + 3));
                            return;
                        }
                        if ((j - 1) >= 0 && fieldArray[i][j - 1] == EMPTY) {
                            fieldArray[i][j - 1] = DOT_O;
                            System.out.println("\nCoords: " + (i + 1) + " " + (j));
                            return;
                        }
                    }
                    //RIGHT DOWN
                    if ((i + 1) < fieldArray.length && (j + 1) < fieldArray.length && fieldArray[i + 1][j + 1] == DOT_X) {
                        if ((i + 2) < fieldArray.length && (j + 2) < fieldArray.length && fieldArray[i + 2][j + 2] == EMPTY) {
                            fieldArray[i + 2][j + 2] = DOT_O;
                            System.out.println("\nCoords: " + (i + 3) + " " + (j + 3));
                            return;
                        }
                        if ((i - 1) >= 0 && (j - 1) >= 0 && fieldArray[i - 1][j - 1] == EMPTY) {
                            fieldArray[i - 1][j - 1] = DOT_O;
                            System.out.println("\nCoords: " + (i) + " " + (j));
                            return;
                        }
                    }
                    //RIGHT UP
                    if ((i - 1) >= 0 && (j + 1) < fieldArray.length && fieldArray[i - 1][j + 1] == DOT_X) {
                        if ((i - 2) >= 0 && (j + 2) < fieldArray.length && fieldArray[i - 2][j + 2] == EMPTY) {
                            fieldArray[i - 2][j + 2] = DOT_O;
                            System.out.println("\nCoords: " + (i - 1) + " " + (j + 3));
                            return;
                        }
                        if ((i + 1) <= fieldArray.length && (j - 1) >= 0 && fieldArray[i + 1][j - 1] == EMPTY) {
                            fieldArray[i + 1][j - 1] = DOT_O;
                            System.out.println("\nCoords: " + (i + 2) + " " + (j));
                            return;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < fieldArray.length; i++) {
            for (int j = 0; j < fieldArray[i].length; j++) {
                if (fieldArray[i][j] == DOT_X) {
                    //DOWN
                    if ((i + 1) < fieldArray.length && fieldArray[i + 1][j] == EMPTY) {
                        fieldArray[i + 1][j] = DOT_O;
                        System.out.println("\nCoords: " + (i + 2) + " " + (j + 1));
                        return;
                    }
                    //RIGHT
                    if ((j + 1) < fieldArray.length && fieldArray[i][j + 1] == EMPTY) {
                        fieldArray[i][j + 1] = DOT_O;
                        System.out.println("\nCoords: " + (i + 1) + " " + (j + 2));
                        return;
                    }
                    //RIGHT DOWN
                    if ((i + 1) < fieldArray.length && (j + 1) < fieldArray.length && fieldArray[i + 1][j + 1] == EMPTY) {
                        fieldArray[i + 1][j + 1] = DOT_O;
                        System.out.println("\nCoords: " + (i + 2) + " " + (j + 2));
                        return;
                    }
                    //RIGHT UP
                    if ((i - 1) >= 0 && (j + 1) < fieldArray.length && fieldArray[i - 1][j + 1] == EMPTY) {
                        fieldArray[i - 1][j + 1] = DOT_O;
                        System.out.println("\nCoords: " + (i) + " " + (j + 2));
                        return;
                    }
                }
            }
        }
    }

    private static void startGame(int fieldSize, int winLineLength) {
        char[][] fieldArray = new char[fieldSize][fieldSize];
        boolean gameState = true;
        for (char[] chars : fieldArray) {
            Arrays.fill(chars, EMPTY);
        }
        printField(fieldArray);
        while (gameState) {
            userTurn(fieldArray);
            printField(fieldArray);
            if (ifWin(fieldArray, DOT_X, winLineLength)) {
                System.out.println("Congratulations! You win!");
                gameState = false;
                continue;
            } else if (fieldIsFull(fieldArray)) {
                System.out.println("Draw!");
                gameState = false;
                continue;
            }
            pcTurn(fieldArray, winLineLength);
            printField(fieldArray);
            if (ifWin(fieldArray, DOT_O, winLineLength)) {
                System.out.println("AI won!");
                gameState = false;
            } else if (fieldIsFull(fieldArray)) {
                System.out.println("Draw!");
                gameState = false;
            }
        }
    }
}
