package hw8;
/**
 * Класс создает меню выбора ввода
 * 1)случайная генерация точек
 * 2)ввод вручную
 * 3)через файл
 * <p>
 * 59 строчка содержит строку - путь хранения папки с файлами, содержащими координаты
 * <p>
 * В меню: в первой строке указывается номер варианта ввода, во второй - параметры
 * для 1 - кол-во точек
 * для 2 - координаты точек
 * для 3 - имя файла без расширения (то есть, не "file.txt", а "file")
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Start extends JFrame {
    private JButton button = new JButton("Готово!");
    private JTextField input1 = new JTextField("", 5);
    private JTextField input2 = new JTextField("", 5);
    private JLabel label1 = new JLabel("1 - случайная генерация точек (количесвто точек)");
    private JLabel label2 = new JLabel("2 - ввод вручную (координаты вершин треугольника, затем сами точки в виде \"x1 y1 x2 y2 x3 y3...\")");
    private JLabel label3 = new JLabel("3 - через файл (имя файла (например \"coords\"), запись координат аналогична варианту 2)");

    public Start() {
        super("Первое окно - вариант ввода данных, второе - параметры для ввода (указаны в скобках)");
        this.setBounds(10, 10, 700, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(6, 1, 2, 2));
        container.add(label1);
        container.add(label2);
        container.add(label3);
        container.add(input1);
        container.add(input2);
        button.addActionListener(new ButtonEventListener());
        container.add(button);
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch (Integer.parseInt(input1.getText())) {
                case 1://случайный ввод
                    try {
                        Main.dotsAmount = Integer.parseInt(input2.getText());
                        Main.dotsArray = Generation.generateDots(Main.dotsAmount);
                        Main.triangleCoordinates = Generation.generateDots(3);
                        Main.res = Enumeration.dotsSelection(Main.dotsArray, Main.triangleCoordinates);
                    } catch (Exception e1) {
                    }
                    break;
                case 2://ввод вручную
                    try {
                        String[] coords = input2.getText().split(" ");
                        filling(coords);
                    } catch (Exception e2) {
                    }
                    break;
                case 3://ввод из файла
                    try {
                        String path = "C:\\JavaProjects\\";//место, где лежит текстовый документ с координатами
                        path += input2.getText() + ".txt";
                        String fileText = readLineByLineJava8(path);
                        fileText = fileText.substring(0, fileText.length() - 1);//удаляем перенос строки

                        String[] coords = fileText.split(" ");
                        filling(coords);
                    } catch (Exception e4) {
                    }
                    break;
            }
            if (Main.dotsAmount == -1) {//на случай, если был выполнен некорректный ввод
                System.out.println("Что-то введено неверно, выполнены построения для 1 варианта с параметром \"5\"");
                Main.dotsAmount = 5;
                Main.dotsArray = Generation.generateDots(Main.dotsAmount);
                Main.triangleCoordinates = Generation.generateDots(3);
                Main.res = Enumeration.dotsSelection(Main.dotsArray, Main.triangleCoordinates);
            }
            Main.run();
        }
    }

    public static void filling(String[] coords) {//заполнение массивов с координатами для 2 и 4 случаев
        Main.triangleCoordinates = new int[3][2];
        for (int i = 0; i < 6; i++) {//заполняем массив с вершинами треугольника
            Main.triangleCoordinates[i / 2][i % 2] = Integer.parseInt(coords[i]);
        }
        Main.dotsArray = new int[coords.length / 2 - 3][2];
        for (int i = 6; i < coords.length; i++) {//заполняем массив с точками
            Main.dotsArray[i / 2 - 3][i % 2] = Integer.parseInt(coords[i]);
        }
        Main.dotsAmount = coords.length / 2 - 3;
        Main.res = Enumeration.dotsSelection(Main.dotsArray, Main.triangleCoordinates);
    }

    private static String readLineByLineJava8(String filePath)//функция для чтения файла
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
