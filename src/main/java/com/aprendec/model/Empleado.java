package com.aprendec.model;

public class Empleado {

    private String dni;
    private String nombre;
    private String sexo;
    private int categoria;
    private int anyosTrabajados;

    // Constructor con parámetros
    public Empleado(String dni, String nombre, String sexo, int categoria, int anyosTrabajados) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.categoria = categoria;
        this.anyosTrabajados = anyosTrabajados;
    }

    // Constructor vacío
    public Empleado() {
        // TODO Auto-generated constructor stub
    }

    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getAnyosTrabajados() {
        return anyosTrabajados;
    }

    public void setAnyosTrabajados(int anyosTrabajados) {
        this.anyosTrabajados = anyosTrabajados;
    }

    // Método toString
    @Override
    public String toString() {
        return "Empleado [dni=" + dni + ", nombre=" + nombre + ", sexo=" + sexo + ", categoria=" + categoria
                + ", anyosTrabajados=" + anyosTrabajados + "]";
    }
}
