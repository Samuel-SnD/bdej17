package com.samuel.Error;
import java.io.IOException;
import java.sql.SQLException;

public class Errores {
    public static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL ERROR Mensaje: " + e.getMessage());
        System.err.println("SQL ERROR Estado: " + e.getSQLState());
        System.err.println("SQL ERROR Código específico: " + e.getErrorCode());
    }

    public static void muestraErrorIO (IOException e) {
        System.err.println("IO ERROR Mensaje: " + e.getMessage());
        System.err.println("IO ERROR Causa: " + e.getCause());
        System.err.println("IO ERROR Clase: " + e.getClass());
    }

    public static void muestraErrorGenerico (Exception e) {
        System.err.println("ERROR Mensaje: " + e.getMessage());
        System.err.println("ERROR Causa: " + e.getCause());
        System.err.println("ERROR Clase: " + e.getClass());
    }
}
