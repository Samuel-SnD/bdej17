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
import com.samuel.vo.Profesor;

public class ProfesorDAO implements Dao <Profesor> {

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

    public Profesor getByDept (int dept, Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT dni, nombre, apellidos, fecha_nacimiento from profesor where departamento = ?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, dept);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Profesor pr = new Profesor();
                pr.setDni (rs.getString(1));
                pr.setNombre (rs.getString(2));
                pr.setApellidos (rs.getString(3));
                pr.setFecha_nacimiento (LocalDate.parse(rs.getString(4)));
                return pr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Profesor();
    }
    
}
