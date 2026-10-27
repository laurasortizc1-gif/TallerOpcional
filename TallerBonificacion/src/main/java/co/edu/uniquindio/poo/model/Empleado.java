package co.edu.uniquindio.poo.model;

public abstract class Empleado {

    private String nombre;
    private String documento;
    private int edad;
    protected float salarioBase;
    protected CategoriaEmpleado categoria;
    protected float descuentoSalud;
    protected float descuentoPension;


    //set get

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(float salarioBase) {
        this.salarioBase = salarioBase;
    }

    public CategoriaEmpleado getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEmpleado categoria) {
        this.categoria = categoria;
    }

    public float getDescuentoSalud() {
        return descuentoSalud;
    }

    public void setDescuentoSalud(float descuentoSalud) {
        this.descuentoSalud = descuentoSalud;
    }

    public float getDescuentoPension() {
        return descuentoPension;
    }

    public void setDescuentoPension(float descuentoPension) {
        this.descuentoPension = descuentoPension;
    }

    //constructor y validador
    public Empleado(String nombre,String documento,int edad,float salarioBase, CategoriaEmpleado categoria,
                    float descuentoSalud,float descuentopension) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (documento == null || documento.isBlank())
            throw new IllegalArgumentException("El documento no puede estar vacío");
        if (edad < 0)
            throw new IllegalArgumentException("La edad no puede ser negativa");
        if (salarioBase < 0)
            throw new IllegalArgumentException("El salario base no puede ser negativo");
        if (categoria == null)
            throw new IllegalArgumentException("La categoría es obligatoria");
        validarPorcentaje(descuentoSalud, "descuento de salud");
        validarPorcentaje(descuentoPension, "descuento de pensión");

        this.nombre = nombre;
        this.documento= documento;
        this.edad=edad;
        this.salarioBase=salarioBase;
        this.descuentoSalud=descuentoSalud;
        this.descuentoPension=descuentoPension;
    }

    protected static void validarPorcentaje(float valor, String campo) {
        if (valor < 0 || valor > 100)
            throw new IllegalArgumentException("El " + campo + " debe estar entre 0 y 100");
    }

    protected static void validarNoNegativo(float valor, String campo) {
        if (valor < 0)
            throw new IllegalArgumentException("El valor " + campo + " no puede ser negativo");
    }
     //metodo abstracto
    public abstract float calcularSalarioBruto();

    //metodo 1
    public float calcularBonificacionCategoria() {
        return salarioBase * categoria.getPorcentajeBonificacion() / 100;
    }

    //metodo 2
    public float calcularDescuentos() {
        return calcularSalarioBruto() * (descuentoSalud + descuentoPension) / 100;
    }

    //metodo 3
    public float calcularSalarioNeto() {
        return calcularSalarioBruto() - calcularDescuentos();
    }

    //metodo 4

    public String mostrarInformacion() {
        return "Tipo: " + getClass().getSimpleName() +
                "\nNombre: " + nombre +
                "\nDocumento: " + documento +
                "\nEdad: " + edad +
                "\nCategoría: " + categoria +
                "\nSalario base: " + salarioBase +
                "\nBonificación categoría: " + calcularBonificacionCategoria() +
                "\nDescuento salud: " + descuentoSalud + "%" +
                "\nDescuento pensión: " + descuentoPension + "%";
    }

    // metodo record
    public ResumenPago generarResumenPago() {
        return new ResumenPago(documento, nombre, getClass().getSimpleName(),
                calcularSalarioBruto(), calcularDescuentos(), calcularSalarioNeto());
    }
}
