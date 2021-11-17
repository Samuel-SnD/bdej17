package com.samuel.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.samuel.Error.Errores;
import com.samuel.vo.Imparte;

public class ImparteDAO implements Dao <Imparte>{

    @Override
    public List <Imparte> getAll(Connection conn) {
        List <Imparte> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM Imparte;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList <Imparte> (totalRows);
            while(rs.next()) {
                Imparte imp = new Imparte();
                imp.setDniProf(rs.getString(0));
                imp.setAsig(rs.getInt(1));
                imp.setDniAl(rs.getString(2));
                imp.setCurso(rs.getInt(3));
                lista.add(imp);
            }
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
        return lista;
    }

    public void insertarImparte (Connection con, ArrayList <Imparte> imps) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO imparte (Profesor, Asignatura, Alumno, Curso) VALUES (?, ?, ?, ?);" , ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            for (int i = 0; i < imps.size(); i++) {
                ps.setString(1, imps.get(i).getDniProf());
                ps.setInt(2, imps.get(i).getAsig());
                ps.setString(3, imps.get(i).getDniAl());
                ps.setInt(4, imps.get(i).getCurso());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }
    
}
