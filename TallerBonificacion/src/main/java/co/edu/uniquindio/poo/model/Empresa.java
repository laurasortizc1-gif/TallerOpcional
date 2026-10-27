package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

    private final String nombre;
    private final ArrayList<Empleado> empleados = new ArrayList<>();

    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }


    public void agregarEmpleado(Empleado empleado) {
        if (buscarPorDocumento(empleado.getDocumento()) != null)
            throw new IllegalArgumentException("Ya existe un empleado con documento " + empleado.getDocumento());
        empleados.add(empleado);
    }

    public void mostrarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        for (Empleado e : empleados) {
            System.out.println("-----------------------------");
            System.out.println(e.mostrarInformacion()); // polimorfismo
        }
        System.out.println("-----------------------------");
    }

    public Empleado buscarPorDocumento(String documento) {
        for (Empleado e : empleados) {
            if (e.getDocumento().equals(documento)) return e;
        }
        return null;
    }

    public Empleado empleadoQueMasGana() {
        Empleado mayor = null;
        for (Empleado e : empleados) {
            if (mayor == null || e.calcularSalarioNeto() > mayor.calcularSalarioNeto()) mayor = e;
        }
        return mayor;
    }

    public float calcularNominaTotal() {
        float total = 0;
        for (Empleado e : empleados) total += e.calcularSalarioNeto();
        return total;
    }

    public List<ResumenPago> generarResumenesPago() {
        List<ResumenPago> resumenes = new ArrayList<>();
        for (Empleado e : empleados) resumenes.add(e.generarResumenPago());
        return resumenes;
    }

    public void mostrarResumenesPago() {
        List<ResumenPago> resumenes = generarResumenesPago();
        if (resumenes.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        for (ResumenPago r : resumenes) System.out.println(r);
    }
}

