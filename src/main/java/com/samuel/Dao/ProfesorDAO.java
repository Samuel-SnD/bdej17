package com.samuel.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.samuel.Error.Errores;
import com.samuel.vo.Profesor;

public class ProfesorDAO implements Dao <Profesor> {

    static Scanner teclado = new Scanner (System.in);
    
    public Profesor get (String dni, Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Profesor where Dni = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Profesor pr = new Profesor();
                pr.setDni (rs.getString(1));
                pr.setDepartamento(rs.getInt(2));
                pr.setNombre (rs.getString(3));
                pr.setApellidos (rs.getString(4));
                pr.setFecha_nacimiento (LocalDate.parse(rs.getString(5)));
                return pr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Profesor();
    }

    @Override
    public List <Profesor> getAll (Connection conn) {
        List <Profesor> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM Profesor;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList <Profesor> (totalRows);
            while(rs.next()) {
                Profesor pr = new Profesor();
                pr.setDni (rs.getString(1));
                pr.setDepartamento(rs.getInt(2));
                pr.setNombre (rs.getString(3));
                pr.setApellidos (rs.getString(4));
                pr.setFecha_nacimiento (LocalDate.parse(rs.getString(5)));
                lista.add(pr);
            }
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
        return lista;
    }

    public List <Profesor> getByDept (int dept, Connection con) {
        List <Profesor> lista = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT dni, nombre, apellidos, fecha_nacimiento from profesor where departamento = ?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, dept);
            ResultSet rs = ps.executeQuery();
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList <Profesor> (totalRows);
            while(rs.next()) {
                Profesor pr = new Profesor();
                pr.setDni (rs.getString(1));
                pr.setNombre (rs.getString(2));
                pr.setApellidos (rs.getString(3));
                pr.setFecha_nacimiento (LocalDate.parse(rs.getString(4)));
                lista.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void insertarProfesor (Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO alumno (dni, nombre, apellidos, departamento, fecha_nacimiento) VALUES (?, ?, ?, ?, ?);" , ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("¿Cuántos profesores quieres insertar?");
            int num = Integer.parseInt(teclado.nextLine());
            for (int i = 0; i < num; i++) {
                System.out.println("Inserte dni profesor: ");
                ps.setString(1, teclado.nextLine());
                System.out.println("Inserte nombre profesor: ");
                ps.setString(2, teclado.nextLine());
                System.out.println("Inserte apellidos profesor: ");
                ps.setString(3, teclado.nextLine());
                System.out.println("Inserte número departamento: ");
                ps.setInt(4, Integer.parseInt(teclado.nextLine()));
                System.out.println("Inserte día nacimiento alumno: ");
                int dia = Integer.parseInt(teclado.nextLine());
                System.out.println("Inserte mes nacimiento alumno: ");
                int mes = Integer.parseInt(teclado.nextLine());
                System.out.println("Inserte año nacimiento alumno: ");
                int año = Integer.parseInt(teclado.nextLine());
                LocalDate fecha = LocalDate.of(año, mes, dia);
                ps.setDate(5, Date.valueOf(fecha));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
