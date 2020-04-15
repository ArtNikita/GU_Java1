package hw8;

import java.util.Random;

/**
 * Класс - генератор массива точек
 */
public class Generation {
    static int fieldSize = 50; //длина полуоси ДСК (можно изменять)

    /**
     * Генерирование точек
     * Функция generateDots
     * возвращает двумерный массив (вложенные массивы имеют размерность 2, содержат координаты точек: x и y)
     * На вход принимает k - количество генерируемых точек
     */
    public static int[][] generateDots(int k) {
        int[][] dotsArray = new int[k][2]; //создание массива нужного размера (длина массива = кол-во точек)
        Random rand = new Random();

        //начало заполнения массива случайными значениями
        int i = 0;//переменная для итерирования внутри цикла while
        while (i < dotsArray.length) {
            int x = rand.nextInt((fieldSize * 2) + 1) - fieldSize;//генерируем случайное число от -fieldSize до +fieldSize
            int y = rand.nextInt((fieldSize * 2) + 1) - fieldSize;
            dotsArray[i][0] = x;
            dotsArray[i][1] = y;
            int temp = i;
            //проверка на повторение (наличие данной точки в массиве)
            for (int j = 0; j < temp; j++) {
                if (dotsArray[j][0] == x && dotsArray[j][1] == y) {
                    i--; //в случае присутствия такой же точки ранее, декрементируем переменную i,
                    //чтобы в следующей итерации перезаписать её же
                }
            }
            i++;
        }
        //конец заполнения массива случайными значениями
        return dotsArray;
    }
}
