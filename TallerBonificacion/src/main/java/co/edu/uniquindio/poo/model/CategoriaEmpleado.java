package co.edu.uniquindio.poo.model;

public enum CategoriaEmpleado {

    JUNIOR(5),
    SEMI_SENIOR(10),
    SENIOR(15);

    private final float porcentajeBonificacion;

    CategoriaEmpleado(float porcentajeBonificacion) {

        this.porcentajeBonificacion = porcentajeBonificacion;
    }

    public float getPorcentajeBonificacion() {

        return porcentajeBonificacion;
    }
}
