package com.aprendec.model;

public class Nomina {

    private String dni_empleado;
    private int sueldo;

    // Constructor con parámetros
    public Nomina(String dni_empleado, int sueldo) {
        super();
        this.dni_empleado = dni_empleado;
        this.sueldo = sueldo;
    }

    // Constructor vacío
    public Nomina() {
        // TODO Auto-generated constructor stub
    }

    // Getters y Setters
    public String getDni_empleado() {
        return dni_empleado;
    }

    public void setDni_empleado(String dni_empleado) {
        this.dni_empleado = dni_empleado;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    // Método toString
    @Override
    public String toString() {
        return "Nomina [dni_empleado=" + dni_empleado + ", sueldo=" + sueldo + "]";
    }
}
