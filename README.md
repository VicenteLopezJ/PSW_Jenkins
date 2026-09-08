# 📦 SPW_JACOCO — Refactorización con Cobertura JaCoCo

> **Curso:** Programación Orientada a Objetos  
> **Sesión:** S06 — Jacoco y Refactorización  
> **Proyecto:** `reto-refactorizacion`  

---

## 👥 Integrantes del Equipo

| N° | Nombre completo |
|----|----------------|
| 1 | Carlos Joel Tomi Chanta |
| 2 | Fabrizio Leonardo Ramirez Sanchez |
| 3 | Dayron Paul Casas Sánchez |
| 4 | Aixa Magali Gonzales del Valle Aburto |
| 5 | Junior Jeanpier Vicente Lopez |


---

## 📋 Descripción

Proyecto Maven en Java que implementa un servicio de gestión de pedidos (`PedidoService`) con cálculo de precios, descuentos y validaciones. El objetivo es aplicar **refactorización de código** guiada por el reporte de cobertura de **JaCoCo**, identificando zonas no probadas y mejorando la calidad del software.

---

## 🗂️ Estructura del Proyecto

```
SPW_JACOCO/
├── src/
│   ├── main/
│   │   └── java/pe/edu/vallegrande/
│   │       └── PedidoService.java        # Clase de producción
│   └── test/
│       └── java/pe/edu/vallegrande/
│           └── PedidoServiceTest.java    # Tests unitarios
├── target/
│   └── site/jacoco/
│       ├── index.html                    # Reporte JaCoCo visual
│       └── jacoco.csv                    # Métricas en CSV
├── pom.xml
└── README.md
```

---

## ⚙️ Tecnologías Utilizadas

| Tecnología | Versión | Uso |
|-----------|---------|-----|
| Java | 17 | Lenguaje principal |
| Maven | 3.x | Gestión de dependencias y build |
| JUnit Jupiter | 5.10.2 | Framework de testing |
| JaCoCo | 0.8.12 | Cobertura de código |

---

## 🔍 Situación Inicial — Problemas Hallados

Al ejecutar `mvn clean test` por primera vez, JaCoCo reveló los siguientes problemas:

| # | Problema | Severidad |
|---|----------|-----------|
| 1 | `calcularTotal` retorna `0` silenciosamente si cantidad ≤ 0 | 🔴 Alta |
| 2 | No se valida precio negativo | 🔴 Alta |
| 3 | Descuento por volumen (≥ 10 unidades) no está probado | 🔴 Alta |
| 4 | Combinación de ambos descuentos no está probada | 🔴 Alta |
| 5 | `obtenerEstado` solo cubre el caso `"MEDIANO"` | 🟡 Media |
| 6 | `validarPedido` no prueba `null`, vacío ni cantidad inválida | 🟡 Media |

### Reporte JaCoCo Inicial

| Métrica | Perdidas | Cubiertas | % Cobertura |
|---------|---------|-----------|-------------|
| Instrucciones | 15 | 70 | **82%** |
| Ramas | 10 | 12 | **55%** |
| Líneas | 5 | 18 | **78%** |
| Complejidad | 10 | 7 | **41%** |
| Métodos | 0 | 6 | **100%** |

---

## 🔧 Refactorización Aplicada

### 1. `PedidoService.java` — Validaciones de Entrada

**Antes ❌**
```java
public double calcularTotal(double precio, int cantidad, boolean clienteFrecuente) {
    if (cantidad <= 0) {
        return 0; // falla silenciosa
    }
    ...
}
```

**Después ✅**
```java
public double calcularTotal(double precio, int cantidad, boolean clienteFrecuente) {
    if (precio < 0) {
        throw new IllegalArgumentException("El precio no puede ser negativo: " + precio);
    }
    if (cantidad <= 0) {
        throw new IllegalArgumentException("La cantidad debe ser mayor a cero: " + cantidad);
    }
    ...
}
```

### 2. `PedidoServiceTest.java` — Nuevos Tests

| Test | Tipo | Qué verifica |
|------|------|-------------|
| `debeCalcularTotal` | ✅ Existía | Precio normal sin descuento → `200.0` |
| `debeAplicarDescuentoClienteFrecuente` | ✅ Existía | Cliente frecuente → `180.0` |
| `debeObtenerEstadoMediano` | ✅ Existía | Total 200 → `"MEDIANO"` |
| `debeValidarPedidoCorrecto` | ✅ Existía | Pedido válido → `true` |
| `debeLanzarExcepcionSiCantidadEsCero` | 🆕 Nuevo | Cantidad 0 → excepción |
| `debeLanzarExcepcionSiPrecioEsNegativo` | 🆕 Nuevo | Precio -10 → excepción |
| `debeAplicarDescuentoPorVolumen` | 🆕 Nuevo | 10 uds. → `950.0` |
| `debeAplicarAmbosDescuentos` | 🆕 Nuevo | Frecuente + volumen → `855.0` |
| `debeObtenerEstadoError` | 🆕 Nuevo | Total 0 → `"ERROR"` |
| `debeObtenerEstadoPequeno` | 🆕 Nuevo | Total 50 → `"PEQUEÑO"` |
| `debeObtenerEstadoGrande` | 🆕 Nuevo | Total 600 → `"GRANDE"` |
| `debeRechazarPedidoConProductoNulo` | 🆕 Nuevo | `null` → `false` |
| `debeRechazarPedidoConProductoVacio` | 🆕 Nuevo | `""` → `false` |
| `debeRechazarPedidoConCantidadInvalida` | 🆕 Nuevo | Cantidad 0 → `false` |

---

## 📊 Cuadro Comparativo: Antes vs Después

| Métrica | ❌ Antes | ✅ Después | Mejora |
|---------|---------|-----------|--------|
| Tests totales | 4 | 14 | +10 tests |
| Instrucciones cubiertas | 82% | 100% | +18% |
| Ramas cubiertas | 55% | 100% | **+45%** |
| Líneas cubiertas | 78% | 100% | +22% |
| Complejidad cubierta | 41% | 100% | **+59%** |
| Métodos cubiertos | 100% | 100% | — |
| Validaciones de entrada | ❌ 0 | ✅ 2 | — |
| Casos borde probados | 0 | 5 | +5 |

---

## ▶️ Cómo Ejecutar

```bash
# Compilar y ejecutar tests + generar reporte JaCoCo
mvn clean test
```

El reporte HTML se genera en:
```
target/site/jacoco/index.html
```

---

## ✅ Resultado Final

```
Tests run: 14, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

---

## 📝 Conclusión

La refactorización guiada por JaCoCo permitió pasar de una cobertura parcial del **55% en ramas** a un **100%** en todas las métricas. Se identificaron y corregieron fallos silenciosos en la lógica de negocio, y se añadieron validaciones explícitas con `IllegalArgumentException` para entradas inválidas.

> JaCoCo no solo mide cobertura, sino que **guía la calidad** del software al revelar exactamente qué caminos del código nunca fueron ejecutados por los tests.