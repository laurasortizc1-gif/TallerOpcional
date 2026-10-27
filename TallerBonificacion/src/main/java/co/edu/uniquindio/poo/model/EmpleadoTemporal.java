package co.edu.uniquindio.poo.model;

public class EmpleadoTemporal extends Empleado{

private int diasTrabajados;
private float valorDia;

    public EmpleadoTemporal(String nombre, String documento, int edad, float salarioBase,
                            CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension,
                            int diasTrabajados, float valorDia) {
        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);
        validarNoNegativo(diasTrabajados, "días trabajados");
        validarNoNegativo(valorDia, "valor por día");
        this.diasTrabajados = diasTrabajados;
        this.valorDia = valorDia;
    }

    public float calcularPagoDias() {
        return diasTrabajados * valorDia;
    }

    @Override
    public float calcularSalarioBruto() {
        return (calcularPagoDias() + calcularBonificacionCategoria());
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() +
                "\nDías trabajados: " + diasTrabajados +
                "\nValor día: " + valorDia +
                "\nPago por días: " + calcularPagoDias() +
                "\nSalario bruto: " + calcularSalarioBruto() +
                "\nSalario neto: " + calcularSalarioNeto();
    }
}
