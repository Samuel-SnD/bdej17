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
    
}
