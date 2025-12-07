package app.utils;

public class MathUtils {
    
    /**
     * Вычисляет факториал числа
     */
    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Факториал определен только для неотрицательных чисел");
        if (n == 0 || n == 1) return 1;
        
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    /**
     * Вычисляет биномиальный коэффициент C(n, k)
     */
    public static long binomialCoefficient(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;
        
        k = Math.min(k, n - k);
        
        long result = 1;
        for (int i = 1; i <= k; i++) {
            result = result * (n - k + i) / i;
        }
        return result;
    }
    
    /**
     * Вычисляет полином Бернштейна
     */
    public static double bernsteinCoefficient(int n, int i, double t) {
        if (i < 0 || i > n) return 0;
        if (t < 0 || t > 1) throw new IllegalArgumentException("Параметр t должен быть в диапазоне [0, 1]");
        
        return binomialCoefficient(n, i) * Math.pow(t, i) * Math.pow(1 - t, n - i);
    }
    
    /**
     * Линейная интерполяция между двумя значениями
     */
    public static double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }
    
    /**
     * Ограничивает значение в заданном диапазоне
     */
    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
    
    /**
     * Вычисляет расстояние между двумя точками
     */
    public static double distance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }
}