package com.samuel.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.samuel.Error.Errores;
import com.samuel.vo.Asignatura;

public class AsignaturaDAO implements Dao <Asignatura> {

    public Asignatura get (int id, Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Asignatura where Id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Asignatura as = new Asignatura();
                as.setId (rs.getInt(1));
                as.setNombre (rs.getString(2));
                return as;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Asignatura();
    }

    @Override
    public List <Asignatura> getAll (Connection conn) {
        List <Asignatura> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM Asignatura;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList <Asignatura> (totalRows);
            while(rs.next()) {
                Asignatura as = new Asignatura();
                as.setId (rs.getInt(1));
                as.setNombre (rs.getString(2));
                lista.add(as);
            }
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
        return lista;
    }
    
    public Asignatura getByProfesor (String dni, Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT id, a.nombre from asignatura a inner join imparte i on i.asignatura = a.id inner join profesor p on i.profesor = p.dni where p.dni = ? group by a.id;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Asignatura as = new Asignatura();
                as.setId (rs.getInt(1));
                as.setNombre (rs.getString(2));
                return as;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Asignatura();
    }

}
