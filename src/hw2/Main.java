package hw2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /*
        1. Задать целочисленный массив, состоящий из элементов 0 и 1.
        Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
        С помощью цикла и условия заменить 0 на 1, 1 на 0;
        */
        int[] a = new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < a.length; i++) {
            a[i] = (a[i] == 0) ? 1 : 0;
        }

        /*
        2. Задать пустой целочисленный массив размером 8.
        С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
        */
        int[] b = new int[8];
        for (int i = 0; i < b.length; i++) {
            b[i] = 3 * i;
        }

        /*
        3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
        пройти по нему циклом, и числа меньшие 6 умножить на 2;
        */
        int[] c = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < c.length; i++) {
            if (c[i] < 6) c[i] *= 2;
        }

        /*
        4. Создать квадратный двумерный целочисленный массив
        (количество строк и столбцов одинаковое), и с помощью цикла(-ов)
        заполнить его диагональные элементы единицами;
         */
        int[][] d = new int[7][7];
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                if ((i == j) || (i == d[i].length - j - 1)) {//первое условие - для главной диагонали, второе - для побочной
                    d[i][j] = 1;
                }
            }
        }

        /*
        5. ** Задать одномерный массив и найти в нем
        минимальный и максимальный элементы (без помощи интернета);
         */
        int[] e = new int[]{4, 3, 5, 6, 21, 7, 34, 7};
        int min = e[0], max = e[0];
        for (int i = 1; i < e.length; i++) {
            min = (e[i] < min) ? e[i] : min;
            max = (e[i] > max) ? e[i] : max;
        }
    }

    /*
    6. ** Написать метод, в который передается не пустой одномерный
    целочисленный массив, метод должен вернуть true, если в массиве есть
    место, в котором сумма левой и правой части массива равны. Примеры:
    checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
    граница показана символами ||, эти символы в массив не входят.
     */
    private static boolean checkBalance(int[] array) {
        int leftIndex = 0, rightIndex = array.length - 1, leftSum = 0, rightSum = 0;
        while (leftIndex <= rightIndex) {
            if (leftSum < rightSum) {
                leftSum += array[leftIndex];
                leftIndex++;
            } else {
                rightSum += array[rightIndex];
                rightIndex--;
            }
        }
        return leftSum == rightSum;
    }

    /*
    7. **** Написать метод, которому на вход подается одномерный массив
    и число n (может быть положительным, или отрицательным), при этом метод
    должен сместить все элементымассива на n позиций. Для усложнения задачи
    нельзя пользоваться вспомогательными массивами.
    */
    private static int[] shiftArray(int[] array, int n) {
        if (array.length == 0) {
            return array;
        }
        if (n > 0) {//shift right
            for (int j = 0; j < n % array.length; j++) {
                int last = array[array.length - 1];
                for (int i = array.length - 1; i > 0; i--) {
                    array[i] = array[i - 1];
                }
                array[0] = last;
            }
        } else if (n < 0) {//shift left
            for (int j = 0; j < ((-1) * n) % array.length; j++) {
                int first = array[0];
                for (int i = 0; i < array.length - 1; i++) {
                    array[i] = array[i + 1];
                }
                array[array.length - 1] = first;
            }
        }
        return array;
    }

    //Придумал, как сделать красиво задачу 7.
    private static int[] shiftArrayPro(int[] array, int n) {
        if (array.length == 0) {
            return array;
        }
        int currentIndex = 0, nextIndex;
        int temp = array[currentIndex], temp1;
        for (int i = 0; i < array.length; i++) {
            nextIndex = ((currentIndex + n) % array.length + array.length) % array.length;
            temp1 = array[nextIndex];
            array[nextIndex] = temp;
            temp = temp1;
            currentIndex = nextIndex;
        }
        return array;
    }

    /*
    8. ***** (Задача с вебинара).
    Написать метод, возвращающий 3 по величине элемент.
    По условию на вход подается массив длиной хотя бы 3.
     */
    private static int print3Min(int[] array) {
        int[] window = new int[]{array[0], array[1], array[2]};
        sortArrayOf3(window);
        for (int i = 3; i < array.length; i++) {
            if (array[i] <= window[2]) {
                window[2] = array[i];
                sortArrayOf3(window);
            }
        }
        return window[2];
    }

    //сортировка массива из 3 элементов (для print3Min)
    private static void sortArrayOf3(int[] window) {
        int temp;
        if (window[0] > window[1]) {
            temp = window[0];
            window[0] = window[1];
            window[1] = temp;
        }
        if (window[1] > window[2]) {
            temp = window[1];
            window[1] = window[2];
            window[2] = temp;
        }
        if (window[0] > window[1]) {
            temp = window[0];
            window[0] = window[1];
            window[1] = temp;
        }
    }
}