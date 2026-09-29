package com.psw.calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculadoraTest {

    private Calculadora calc;

    @BeforeEach
    void setUp() {
        calc = new Calculadora();
    }

    @Test
    @DisplayName("Suma dos numeros positivos")
    void sumar() {
        assertEquals(8.0, calc.sumar(5, 3));
    }

    @Test
    @DisplayName("Suma con numeros negativos")
    void sumarNegativos() {
        assertEquals(-2.0, calc.sumar(-5, 3));
    }

    @Test
    @DisplayName("Resta dos numeros")
    void restar() {
        assertEquals(2.0, calc.restar(5, 3));
    }

    @Test
    @DisplayName("Multiplica dos numeros")
    void multiplicar() {
        assertEquals(15.0, calc.multiplicar(5, 3));
    }

    @Test
    @DisplayName("Multiplicar por cero devuelve cero")
    void multiplicarPorCero() {
        assertEquals(0.0, calc.multiplicar(5, 0));
    }

    @Test
    @DisplayName("Divide dos numeros")
    void dividir() {
        assertEquals(2.5, calc.dividir(5, 2));
    }

    @Test
    @DisplayName("Dividir entre cero lanza ArithmeticException")
    void dividirEntreCero() {
        ArithmeticException ex = assertThrows(ArithmeticException.class, () -> calc.dividir(5, 0));
        assertEquals("No se puede dividir entre cero", ex.getMessage());
    }

    @Test
    @DisplayName("Calcula la potencia")
    void potencia() {
        assertEquals(8.0, calc.potencia(2, 3));
    }

    @Test
    @DisplayName("Calcula la raiz cuadrada")
    void raizCuadrada() {
        assertEquals(4.0, calc.raizCuadrada(16));
    }

    @Test
    @DisplayName("Raiz cuadrada de negativo lanza IllegalArgumentException")
    void raizCuadradaNegativa() {
        assertThrows(IllegalArgumentException.class, () -> calc.raizCuadrada(-4));
    }

    @Test
    @DisplayName("Calcula el porcentaje")
    void porcentaje() {
        assertEquals(20.0, calc.porcentaje(200, 10));
    }
}
