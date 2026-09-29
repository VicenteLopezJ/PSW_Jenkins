package com.psw.calculadora;

/**
 * Calculadora basica usada como proyecto base para practicar
 * pruebas JUnit, Jenkins y notificaciones a Slack.
 */
public class Calculadora {

    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir entre cero");
        }
        return a / b;
    }

    public double potencia(double base, int exponente) {
        return Math.pow(base, exponente);
    }

    public double raizCuadrada(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("No existe raiz cuadrada real de un numero negativo");
        }
        return Math.sqrt(a);
    }

    public double porcentaje(double valor, double porcentaje) {
        return valor * porcentaje / 100;
    }
}
