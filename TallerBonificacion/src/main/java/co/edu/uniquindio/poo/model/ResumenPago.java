package co.edu.uniquindio.poo.model;

public record ResumenPago(String documento, String nombre, String tipoEmpleado,
                          float salarioBruto, float descuentos, float salarioNeto) {

    @Override
    public String toString() {
        return String.format("%-12s | %-20s | %-17s | Bruto: %,12.2f | Desc: %,11.2f | Neto: %,12.2f",
                documento, nombre, tipoEmpleado, salarioBruto, descuentos, salarioNeto);
    }
}
