package pe.edu.vallegrande;

public class PedidoService {

    private static final double DESCUENTO_CLIENTE_FRECUENTE = 0.90;
    private static final double DESCUENTO_VOLUMEN = 0.95;
    private static final int CANTIDAD_MINIMA_DESCUENTO_VOLUMEN = 10;

    public double calcularTotal(double precio, int cantidad, boolean clienteFrecuente) {
        if (cantidad <= 0) {
            return 0;
        }

        double subtotal = calcularSubtotal(precio, cantidad);
        double totalConDescuentos = aplicarDescuentos(subtotal, cantidad, clienteFrecuente);

        return totalConDescuentos;
    }

    private double calcularSubtotal(double precio, int cantidad) {
        return precio * cantidad;
    }

    private double aplicarDescuentos(double subtotal, int cantidad, boolean clienteFrecuente) {
        double total = subtotal;

        if (clienteFrecuente) {
            total = total * DESCUENTO_CLIENTE_FRECUENTE;
        }

        if (cantidad >= CANTIDAD_MINIMA_DESCUENTO_VOLUMEN) {
            total = total * DESCUENTO_VOLUMEN;
        }

        return total;
    }

    public String obtenerEstado(double total) {
        if (total <= 0) {
            return "ERROR";
        }
        if (total < 100) {
            return "PEQUEÑO";
        }
        if (total < 500) {
            return "MEDIANO";
        }
        return "GRANDE";
    }

    public boolean validarPedido(String producto, int cantidad) {
        boolean productoValido = producto != null && !producto.isEmpty();
        boolean cantidadValida = cantidad > 0;

        return productoValido && cantidadValida;
    }
}
