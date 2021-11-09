package com.samuel.vo;

import java.time.LocalDate;

public class Profesor {
    
    private String dni;
    private int departamento;
    private String nombre;
    private String apellidos;
    private LocalDate fecha_nacimiento;
    
    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFecha_nacimiento() {
        return this.fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }


    public Profesor() {
    }

    public Profesor(String dni, int departamento, String nombre, String apellidos, LocalDate fecha_nacimiento) {
        this.dni = dni;
        this.departamento = departamento;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    @Override
    public String toString() {
        return "{" +
            " dni='" + getDni() + "'" +
            ", departamento='" + getDepartamento() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", apellidos='" + getApellidos() + "'" +
            ", fecha_nacimiento='" + getFecha_nacimiento() + "'" +
            "}";
    }

}
