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
import com.samuel.vo.Alumno;

public class AlumnoDAO implements Dao <Alumno> {

    static Scanner teclado = new Scanner (System.in);

    public Alumno get (String Dni, Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Alumno where Dni = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, Dni);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Alumno al = new Alumno();
                al.setDni (rs.getString(1));
                al.setNombre (rs.getString(2));
                al.setApellidos (rs.getString(3));
                al.setFecha_nacimiento (LocalDate.parse(rs.getString(4)));
                return al;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Alumno();
    }

    @Override
    public List <Alumno> getAll (Connection conn) {
        List <Alumno> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM Alumno;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList <Alumno> (totalRows);
            while(rs.next()) {
                Alumno al = new Alumno();
                al.setDni (rs.getString(1));
                al.setNombre (rs.getString(2));
                al.setApellidos (rs.getString(3));
                al.setFecha_nacimiento (LocalDate.parse(rs.getString(4)));
                lista.add(al);
            }
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
        return lista;
    }
    
    public List <Alumno> getByAsignatura (int id, Connection con) {
        List <Alumno> lista = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT dni, a.nombre, apellidos, fecha_nacimiento from alumno a inner join imparte i on a.dni = i.alumno inner join asignatura asg on i.asignatura = asg.id where asg.id = ? group by a.dni;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList <Alumno> (totalRows);
            while(rs.next()) {
                Alumno al = new Alumno();
                al.setDni (rs.getString(1));
                al.setNombre (rs.getString(2));
                al.setApellidos (rs.getString(3));
                al.setFecha_nacimiento (LocalDate.parse(rs.getString(4)));
                lista.add(al);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void insertarAlumno (Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO alumno (dni, nombre, apellidos, fecha_nacimiento) VALUES (?, ?, ?, ?);" , ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("¿Cuántos alumnos quieres insertar?");
            int num = Integer.parseInt(teclado.nextLine());
            for (int i = 0; i < num; i++) {
                System.out.println("Inserte dni alumno: ");
                ps.setString(1, teclado.nextLine());
                System.out.println("Inserte nombre alumno: ");
                ps.setString(2, teclado.nextLine());
                System.out.println("Inserte apellidos alumno: ");
                ps.setString(3, teclado.nextLine());
                System.out.println("Inserte día nacimiento alumno: ");
                int dia = Integer.parseInt(teclado.nextLine());
                System.out.println("Inserte mes nacimiento alumno: ");
                int mes = Integer.parseInt(teclado.nextLine());
                System.out.println("Inserte año nacimiento alumno: ");
                int año = Integer.parseInt(teclado.nextLine());
                LocalDate fecha = LocalDate.of(año, mes, dia);
                ps.setDate(4, Date.valueOf(fecha));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertarAlumno (Connection con, ArrayList <Alumno> als) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO alumno (dni, nombre, apellidos, fecha_nacimiento) VALUES (?, ?, ?, ?);" , ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            for (int i = 0; i < als.size(); i++) {
                ps.setString(1, teclado.nextLine());
                ps.setString(2, teclado.nextLine());
                ps.setString(3, teclado.nextLine());
                ps.setString(4, teclado.nextLine());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
