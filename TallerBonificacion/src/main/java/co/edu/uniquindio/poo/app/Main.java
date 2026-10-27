package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.*;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Empresa empresa = new Empresa("Mi Empresa");
        int opcion;

        do {
            System.out.println("\n===== bienvenido al sistema=====");
            System.out.println("1. Agregar empleado de planta");
            System.out.println("2. Agregar empleado de ventas");
            System.out.println("3. Agregar empleado temporal");
            System.out.println("4. Mostrar todos los empleados");
            System.out.println("5. Buscar empleado por documento");
            System.out.println("6. Mostrar empleado con mayor salario neto");
            System.out.println("7. Mostrar nómina total");
            System.out.println("8. Mostrar resumen de pagos");
            System.out.println("9. Salir");
            opcion = leerInt("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 -> {
                        DatosBase d = leerDatosBase();
                        String cargo = leerTexto("Cargo: ");
                        int horas = leerInt("Horas extra: ");
                        float valorHora = leerFloat("Valor hora extra: ");
                        float auxilio = leerFloat("Auxilio de transporte: ");
                        empresa.agregarEmpleado(new EmpleadoPlanta(d.nombre, d.documento, d.edad, d.salarioBase,
                                d.categoria, d.salud, d.pension, cargo, horas, valorHora, auxilio));
                        System.out.println("Empleado de planta agregado.");
                    }
                    case 2 -> {
                        DatosBase d = leerDatosBase();
                        float ventas = leerFloat("Total ventas: ");
                        float comision = leerFloat("Porcentaje comisión (0-100): ");
                        empresa.agregarEmpleado(new EmpleadoVentas(d.nombre, d.documento, d.edad, d.salarioBase,
                                d.categoria, d.salud, d.pension, ventas, comision));
                        System.out.println("Empleado de ventas agregado.");
                    }
                    case 3 -> {
                        DatosBase d = leerDatosBase();
                        int dias = leerInt("Días trabajados: ");
                        float valorDia = leerFloat("Valor por día: ");
                        empresa.agregarEmpleado(new EmpleadoTemporal(d.nombre, d.documento, d.edad, d.salarioBase,
                                d.categoria, d.salud, d.pension, dias, valorDia));
                        System.out.println("Empleado temporal agregado.");
                    }
                    case 4 -> empresa.mostrarEmpleados();
                    case 5 -> {
                        Empleado e = empresa.buscarPorDocumento(leerTexto("Documento a buscar: "));
                        System.out.println(e == null ? "Empleado no encontrado." : e.mostrarInformacion());
                    }
                    case 6 -> {
                        Empleado e = empresa.empleadoQueMasGana();
                        if (e == null) System.out.println("No hay empleados registrados.");
                        else {
                            System.out.println("Empleado con mayor salario neto:");
                            System.out.println(e.mostrarInformacion());
                        }
                    }
                    case 7 -> System.out.printf("Nómina total: %,.2f%n", empresa.calcularNominaTotal());
                    case 8 -> empresa.mostrarResumenesPago();
                    case 9 -> System.out.println("¡Hasta luego!");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } while (opcion != 9);
    }

    // Datos comunes a todos los empleados
    private record DatosBase(String nombre, String documento, int edad, float salarioBase,
                             CategoriaEmpleado categoria, float salud, float pension) { }

    private static DatosBase leerDatosBase() {
        String nombre = leerTexto("Nombre: ");
        String documento = leerTexto("Documento: ");
        int edad = leerInt("Edad: ");
        float salario = leerFloat("Salario base: ");
        CategoriaEmpleado categoria = leerCategoria();
        float salud = leerFloat("Descuento salud % (0-100): ");
        float pension = leerFloat("Descuento pensión % (0-100): ");
        return new DatosBase(nombre, documento, edad, salario, categoria, salud, pension);
    }

    private static CategoriaEmpleado leerCategoria() {
        while (true) {
            int op = leerInt("Categoría (1. JUNIOR, 2. SEMI_SENIOR, 3. SENIOR): ");
            switch (op) {
                case 1: return CategoriaEmpleado.JUNIOR;
                case 2: return CategoriaEmpleado.SEMI_SENIOR;
                case 3: return CategoriaEmpleado.SENIOR;
                default: System.out.println("Categoría no válida.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static int leerInt(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero.");
            }
        }
    }

    private static float leerFloat(String mensaje) {
        while (true) {
            try {
                return Float.parseFloat(leerTexto(mensaje).replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }
}
