package co.edu.uniquindio.poo.model;

public class EmpleadoPlanta extends Empleado{

    private String cargo;
    private int horasExtra;
    private float valorHoraExtra;
    private float auxilioTransporte;

    public EmpleadoPlanta(String nombre, String documento, int edad, float salarioBase,
                          CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension,
                          String cargo, int horasExtra, float valorHoraExtra, float auxilioTransporte) {
        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);
        validarNoNegativo(horasExtra, "horas extra");
        validarNoNegativo(valorHoraExtra, "valor hora extra");
        validarNoNegativo(auxilioTransporte, "auxilio de transporte");
        this.cargo = cargo;
        this.horasExtra = horasExtra;
        this.valorHoraExtra = valorHoraExtra;
        this.auxilioTransporte = auxilioTransporte;
    }

    @Override
    public float calcularSalarioBruto() {
        return salarioBase + calcularBonificacionCategoria()
                + horasExtra * valorHoraExtra + auxilioTransporte;
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() +
                "\nCargo: " + cargo +
                "\nHoras extra: " + horasExtra +
                "\nValor hora extra: " + valorHoraExtra +
                "\nAuxilio transporte: " + auxilioTransporte +
                "\nSalario bruto: " + calcularSalarioBruto() +
                "\nSalario neto: " + calcularSalarioNeto();
    }
}
