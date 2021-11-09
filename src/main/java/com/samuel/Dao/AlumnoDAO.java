package com.samuel.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.samuel.Error.Errores;
import com.samuel.vo.Alumno;

public class AlumnoDAO implements Dao <Alumno> {

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
                al.setFecha_nacimiento (LocalDate.parse(rs.getString(5)));
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
                al.setFecha_nacimiento (LocalDate.parse(rs.getString(5)));
                lista.add(al);
            }
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
        return lista;
    }
    
    public Alumno getByAsignatura (int id, Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT dni, a.nombre, apellidos, fecha_nacimiento from alumno a inner join imparte i on a.dni = i.alumno inner join asignatura asg on i.asignatura = asg.id where asg.id = ? group by a.dni;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Alumno al = new Alumno();
                al.setDni (rs.getString(1));
                al.setNombre (rs.getString(2));
                al.setApellidos (rs.getString(3));
                al.setFecha_nacimiento (LocalDate.parse(rs.getString(5)));
                return al;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Alumno();
    }
}
