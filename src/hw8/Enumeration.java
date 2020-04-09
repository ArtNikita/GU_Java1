package hw8;

/**
 * Класс с названием "Перечисление"
 * Содержит единственную функцию "Отбор"
 * На вход подаются массивы точек и координаты вершин треугольника
 * На выход получаем строку в следующем формате
 * <p>
 * номер 1 точки в массиве точек : номер 2 точки в массиве точек : координата по иксу(x1) пересечения прямой и треугольника : координата по игреку(y1) пересечения прямой и треугольника
 * : координата по иксу(x2) пересечения прямой и треугольника : координата по игреку(y2) пересечения прямой и треугольника : длина отрезка, ограниченного точками пересечения прямой и треугольника
 * <p>
 * Результат соответствует поставленной задаче
 */

public class Enumeration {
    public static String dotsSelection(int[][] dotsArray, int[][] triangleCoordinates) {
        //во всех строковых переменных используется ":" для разделения значений
        String resXY = "";//переменная для записи результата
        double resLength = 0;//переменная для записи результирующей (максимальной) длины отрезка внутри треугольника
        String tempDots;//переменная для записи индексов точек
        String tempXY;//временная переменная для записи координат пересечения прямой и треугольника
        double tempLength;//временная переменная дял записи длины отрезка внутри треугольника
        for (int i = 0; i < dotsArray.length; i++) {//перебираем все возможные варианты сочетаний точек по две
            for (int j = i + 1; j < dotsArray.length; j++) {//перебираем все возможные варианты сочетаний точек по две
                tempDots = "";//обнуляем временную переменную в начале новой итерации
                tempXY = "";//обнуляем временную переменную в начале новой итерации
                if (i == j) {//пропускаем шаг, когда берутся две одинаковые точки
                    continue;
                }
                double[] dots1 = MathFunctions.getIntersectionPoint(triangleCoordinates[0][0], triangleCoordinates[0][1], triangleCoordinates[1][0], triangleCoordinates[1][1], dotsArray[i][0], dotsArray[i][1], dotsArray[j][0], dotsArray[j][1]);//записыаем в двумерные массивы точки пересечения
                double[] dots2 = MathFunctions.getIntersectionPoint(triangleCoordinates[1][0], triangleCoordinates[1][1], triangleCoordinates[2][0], triangleCoordinates[2][1], dotsArray[i][0], dotsArray[i][1], dotsArray[j][0], dotsArray[j][1]);//данной прямой с 1, 2 и 3 сторонами треугольника
                double[] dots3 = MathFunctions.getIntersectionPoint(triangleCoordinates[2][0], triangleCoordinates[2][1], triangleCoordinates[0][0], triangleCoordinates[0][1], dotsArray[i][0], dotsArray[i][1], dotsArray[j][0], dotsArray[j][1]);//(прямыми, содержащими стороны), соответственно
                if (dots1 != null) {//проверка на то, что прямые не параллельны
                    if (MathFunctions.isOnSegment(triangleCoordinates[0][0], triangleCoordinates[0][1], triangleCoordinates[1][0], triangleCoordinates[1][1], dots1[0], dots1[1])) {//проверяем, принадлежит ли точка пересечения прямых стороне треугольника
                        tempDots = Double.toString(i) + ":" + Double.toString(j);//записываем во временную переменную индексы точек - кандидатов
                        tempXY += Double.toString(dots1[0]) + ":" + Double.toString(dots1[1]) + ":";//дозаписываем соответствующие координаты
                    }
                }
                if (dots2 != null) {
                    if (MathFunctions.isOnSegment(triangleCoordinates[1][0], triangleCoordinates[1][1], triangleCoordinates[2][0], triangleCoordinates[2][1], dots2[0], dots2[1])) {
                        tempDots = Double.toString(i) + ":" + Double.toString(j);//можно перезаписать, так как индексы те же
                        tempXY += Double.toString(dots2[0]) + ":" + Double.toString(dots2[1]) + ":";//но здесь дозаписываем, т.к. координаты будут другими
                    }
                }
                if (dots3 != null) {
                    if (MathFunctions.isOnSegment(triangleCoordinates[2][0], triangleCoordinates[2][1], triangleCoordinates[0][0], triangleCoordinates[0][1], dots3[0], dots3[1])) {
                        tempDots = Double.toString(i) + ":" + Double.toString(j);
                        tempXY += Double.toString(dots3[0]) + ":" + Double.toString(dots3[1]) + ":";
                    }
                }
                if (tempXY.length() > 0) {//если точки является кандидатом (то есть выполнились 2 из 3 условий выше)
                    tempLength = MathFunctions.getDistance(Double.parseDouble(tempXY.split(":")[0]), Double.parseDouble(tempXY.split(":")[1]), Double.parseDouble(tempXY.split(":")[2]), Double.parseDouble(tempXY.split(":")[3]));//находим расстояние между точками пересечения
                    if (tempLength >= resLength) {//если расстояние больше того, что записано в максимальное, обновляем данные
                        resXY = tempDots + ":" + tempXY;
                        resLength = tempLength;
                    }
                }
            }
        }
        return resXY + Double.toString(resLength);
    }
}
