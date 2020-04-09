package hw8;

/**
 * Класс, содержащий все математические функции
 */

public class MathFunctions {

    /**
     * Функция нахождения расстояния между двумя точками
     * На входе - координаты точек
     * На выходе - искомое расстояние
     */
    public static double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));//теорема Пифагора
    }

    /**
     * Функция, проверяющая, лежит ли точка на отрезке
     * На вход подаются координаты коцов отрезков с соответствующими индексами и координата точки (без индекса)
     * На выходе true, если принадлжети отрезку и false, если нет
     */
    public static boolean isOnSegment(double x1, double y1, double x2, double y2, double x, double y) {
        return ((Math.abs((x - x1) * (y2 - y1) - (y - y1) * (x2 - x1)) <= 0.000001) && ((x1 <= x && x <= x2) || (x1 >= x && x >= x2)));
    }//проверяем на равенство нулю векторного произведения соответствующих векторов (для обеспечения коллинеарности)
    //так же проверяем, лежит ли абсцисса точки между абсциссами концов отрезка

    /**
     * Функция поиска точки пересечения двух прямых
     * На вход подаются координаты 4 точек: через точки 1 и 2 проходит первая прямая, через 3 и 4 - вторая
     * На выходе получаем координаты точки пересечения или null, в случае параллельных (в широком смысле) прямых
     */
    public static double[] getIntersectionPoint(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double[] res = new double[2];
        try {
            res[0] = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));//координаты точек пересечения считаем через определители
            res[1] = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));//https://ru.wikipedia.org/wiki/Пересечение_прямых
            if (Double.isInfinite(res[0]) || Double.isInfinite(res[1]) || Double.isNaN(res[0]) || Double.isNaN(res[1])) {//проверка на исключительные случаи, в частности, случай параллельности
                return null;
            } else return res;
        } catch (Exception e) {
        }
        return null;
    }
}
