package com.samuel.App;

import java.util.Scanner;

public class View {
    
    static Scanner teclado = new Scanner (System.in);
    
    public int mostrarMenu () {
        System.out.println("1) Crear tablas");
        System.out.println("2) Insertar alumno");
        System.out.println("3) Insertar asignatura");
        System.out.println("4) Insertar departamento");
        System.out.println("5) Insertar profesor");
        System.out.println("6) Insertar imparte");
        System.out.println("7) Ver todos los alumnos");
        System.out.println("8) Ver todas las asignaturas");
        System.out.println("9) Ver todos los departamentos");
        System.out.println("10) Ver todos los profesores");
        System.out.println("11) Ver imparte");
        System.out.println("12) Buscar alumno por dni");
        System.out.println("13) Buscar asignatura por id");
        System.out.println("14) Buscar departamento por id");
        System.out.println("15) Buscar profesor por dni");
        System.out.println("16) Buscar en imparte por curso");
        System.out.println("17) Buscar alumnos de asignatura");
        System.out.println("18) Buscar asignaturas de profesor");
        System.out.println("19) Buscar profesores de departamento");
        System.out.println("20) Salir");
        int opc = Integer.parseInt(teclado.nextLine());
        return opc;
    }
    
    public boolean showMessage(String message) {
        System.out.println(message);
        return true;
    }
}