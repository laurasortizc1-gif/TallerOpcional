package co.edu.uniquindio.poo.model;

public class EmpleadoVentas extends Empleado {

    private float totalVentas;
    private float porcentajeComision;

    public EmpleadoVentas(String nombre, String documento, int edad, float salarioBase,
                          CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension,
                          float totalVentas, float porcentajeComision) {
        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);
        validarNoNegativo(totalVentas, "total de ventas");
        validarPorcentaje(porcentajeComision, "porcentaje de comisión");
        this.totalVentas = totalVentas;
        this.porcentajeComision = porcentajeComision;
    }

    public float calcularComision() {
        return totalVentas * porcentajeComision / 100;
    }

    @Override
    public float calcularSalarioBruto() {
        return salarioBase + calcularBonificacionCategoria() + calcularComision();
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() +
                "\nTotal ventas: " + totalVentas +
                "\nPorcentaje comisión: " + porcentajeComision + "%" +
                "\nComisión: " + calcularComision() +
                "\nSalario bruto: " + calcularSalarioBruto() +
                "\nSalario neto: " + calcularSalarioNeto();
    }
}


