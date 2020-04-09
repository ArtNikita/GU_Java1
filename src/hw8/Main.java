package hw8;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Main {
    static int dotsAmount = -1;//количество точек (можно изменять)
    static int[][] dotsArray;//генерирование массива точек
    static int[][] triangleCoordinates;//генерирование координат вершин треугольника
    static String res;//строка с ответом, расшифровка описана в классе Enumeration

    public static void main(String[] args) {
        Start start = new Start();
        start.setVisible(true);
    }

    public static void run() {
        System.out.println("Массив точек: " + Arrays.deepToString(dotsArray));
        System.out.println("Вершины треугольника: " + Arrays.deepToString(triangleCoordinates));
        try {//на случай, если ни одна из прямых не пересекает треугольник
            System.out.println("Длина отрезка: " + res.split(":")[6]);
        } catch (Exception e) {
        }

        /**
         * Блок рисования
         */
        JFrame frame = new JFrame("Декартова с.к.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Drawing drawing = new Drawing();
        drawing.setBackground(Color.WHITE);
        frame.add(drawing);
        frame.setSize(Generation.fieldSize * 20 + 60, Generation.fieldSize * 20 + 60);
        frame.setVisible(true);
    }

    public static void generateDots() {

    }
}
//сгенерированные точки нарисованы синим цветом
//треугольник - зеленым
//отрезок внутри треугольника - красным
//искомые точки обведены в кружок
//в консоль выводятся точки, вершины треугольника и длина искомого отрезка