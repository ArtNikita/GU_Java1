package hw8;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 * Класс рисования
 */

public class Drawing extends JPanel {
    public void paintComponent(Graphics g) {
        this.setBackground(Color.WHITE);
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(2.0f));//устанавливаем толщину линии
        g2.setColor(Color.BLACK);//устанавливаем цвет кисти
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));

        dskDrawing(g2);//рисование с.к.

        //Начало рисования сгенерированных точек (цвет синий)
        g2.setColor(Color.blue);
        for (int i = 0; i < Main.dotsArray.length; i++) {
            g2.fillOval((Generation.fieldSize + Main.dotsArray[i][0]) * 10, (Generation.fieldSize - Main.dotsArray[i][1]) * 10, 7, 7);
        }
        //Конец рисования сгенерированных точек

        //Начало рисования треугольника
        g2.setColor(Color.GREEN);
        g2.drawLine((Generation.fieldSize + Main.triangleCoordinates[0][0]) * 10 + 2, (Generation.fieldSize - Main.triangleCoordinates[0][1]) * 10 + 2, (Generation.fieldSize + Main.triangleCoordinates[1][0]) * 10 + 2, (Generation.fieldSize - Main.triangleCoordinates[1][1]) * 10 + 2);
        g2.drawLine((Generation.fieldSize + Main.triangleCoordinates[1][0]) * 10 + 2, (Generation.fieldSize - Main.triangleCoordinates[1][1]) * 10 + 2, (Generation.fieldSize + Main.triangleCoordinates[2][0]) * 10 + 2, (Generation.fieldSize - Main.triangleCoordinates[2][1]) * 10 + 2);
        g2.drawLine((Generation.fieldSize + Main.triangleCoordinates[2][0]) * 10 + 2, (Generation.fieldSize - Main.triangleCoordinates[2][1]) * 10 + 2, (Generation.fieldSize + Main.triangleCoordinates[0][0]) * 10 + 2, (Generation.fieldSize - Main.triangleCoordinates[0][1]) * 10 + 2);
        //Конец рисования треугольника

        if (Main.res.equals("0.0")) {
            System.out.println("Ни одна из прямых не пересекает треугольник :(");
        } else {
            //x1, y1, x2, y2 - нужные точки из массива сгенерированных точек dotsArray
            double x1 = Main.dotsArray[(int) Double.parseDouble(Main.res.split(":")[0])][0];
            double y1 = Main.dotsArray[(int) Double.parseDouble(Main.res.split(":")[0])][1];
            double x2 = Main.dotsArray[(int) Double.parseDouble(Main.res.split(":")[1])][0];
            double y2 = Main.dotsArray[(int) Double.parseDouble(Main.res.split(":")[1])][1];
            double k = (y2 - y1) / (x2 - x1);//коэффициент прямой, построенной на данных точках
            double b = y1 - k * x1;//свободный член
            g2.setColor(Color.yellow);
            double fieldSize = Generation.fieldSize;//записываем для удобства в отдельную переменную
            g2.draw(new Line2D.Double(fieldSize * 10 - fieldSize * 10 + 2, fieldSize * 10 - (k * (-fieldSize) + b) * 10 + 2, fieldSize * 10 + fieldSize * 10 + 2, fieldSize * 10 - (k * (fieldSize) + b) * 10 + 2));// рисуем прямую(желтую), проходящую через данные две точки (y = kx + b)
            g2.setColor(Color.red);
            g2.draw(new Line2D.Double(fieldSize * 10 + Double.parseDouble(Main.res.split(":")[2]) * 10 + 2, fieldSize * 10 - Double.parseDouble(Main.res.split(":")[3]) * 10 + 2, fieldSize * 10 + Double.parseDouble(Main.res.split(":")[4]) * 10 + 2, fieldSize * 10 - Double.parseDouble(Main.res.split(":")[5]) * 10 + 2));//рисуем красный отрезок
            g2.draw(new Ellipse2D.Double((fieldSize + x1) * 10 - 2, (fieldSize - y1) * 10 - 2, 10, 10));//обводим точки в кружок
            g2.draw(new Ellipse2D.Double((fieldSize + x2) * 10 - 2, (fieldSize - y2) * 10 - 2, 10, 10));//обводим точки в кружок
        }
    }

    public static void dskDrawing(Graphics2D g2) {
        //Начало рисования оси X
        g2.drawLine(Generation.fieldSize * 10, Generation.fieldSize * 20, Generation.fieldSize * 10, 0);//ось OY
        g2.drawLine(Generation.fieldSize * 10, 0, Generation.fieldSize * 10 - 5, 15);//левая часть стрелки OY
        g2.drawLine(Generation.fieldSize * 10, 0, Generation.fieldSize * 10 + 5, 15);//правая часть стрелки OY
        g2.drawString("X", Generation.fieldSize * 20 - 20, Generation.fieldSize * 10 + 30);//подпись оси
        //Конец рисования оси X

        //Начало рисования оси Y
        g2.drawLine(0, Generation.fieldSize * 10, Generation.fieldSize * 20, Generation.fieldSize * 10);//ось OX
        g2.drawLine(Generation.fieldSize * 20, Generation.fieldSize * 10, Generation.fieldSize * 20 - 15, Generation.fieldSize * 10 - 5);//верхняя часть стрелки OX
        g2.drawLine(Generation.fieldSize * 20, Generation.fieldSize * 10, Generation.fieldSize * 20 - 15, Generation.fieldSize * 10 + 5);//нижняя часть стрелки OX
        g2.drawString("Y", Generation.fieldSize * 10 - 25, 25);//подпись оси
        //Конец рисования оси Y

        //Начало рисования разметки оси X
        g2.setColor(Color.black);
        for (int i = 5 * 10; i < Generation.fieldSize * 20; i += 5 * 10) {
            if (i == Generation.fieldSize * 10) { //итерация, когда должен рисоваться ноль
                g2.drawString("0", Generation.fieldSize * 10 - 20, Generation.fieldSize * 10 + 25);//рисуем ноль
                continue;
            }
            g2.drawLine(i, Generation.fieldSize * 10 - 5, i, Generation.fieldSize * 10 + 5);//рисуем черточки
            g2.drawString(Integer.toString(-Generation.fieldSize + i / 10), i - 10, Generation.fieldSize * 10 + 25);//рисуем числа
        }
        //Конец рисования разметки оси X

        //Начало рисования разметки оси Y
        for (int i = 5 * 10; i < Generation.fieldSize * 20; i += 5 * 10) {
            if (i == Generation.fieldSize * 10) { //итерация, когда должен рисоваться ноль
                continue;
            }
            g2.drawLine(Generation.fieldSize * 10 - 5, i, Generation.fieldSize * 10 + 5, i);//рисуем черточки
            g2.drawString(Integer.toString(Generation.fieldSize - i / 10), Generation.fieldSize * 10 - 40, i + 10);//рисуем числа
        }
        //Конец рисования разметки оси Y
    }
}
