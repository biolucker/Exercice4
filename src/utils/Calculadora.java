package utils;

public class Calculadora {
    public Calculadora(){}
    public double suma(double a, double b) {
        return a + b;
    }
    public double resta(double a, double b) {
        return a - b;
    }
    public double multiplicar(double a, double b) {
        return a * b;
    }
    public double division(double a, double b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return a / b;
    }
}
