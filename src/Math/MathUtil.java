package Math;

public class MathUtil {
    public static boolean fuzzyEquals(double a, double b, double tolerance) {
        return Math.abs(a-b) < tolerance;
    }
}
