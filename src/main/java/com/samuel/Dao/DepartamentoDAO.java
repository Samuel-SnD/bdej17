package com.samuel.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.samuel.Error.Errores;
import com.samuel.vo.Departamento;

public class DepartamentoDAO implements Dao <Departamento> {

    static Scanner teclado = new Scanner (System.in);
    
    public Departamento get (int id, Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Departamento where Id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Departamento dp = new Departamento();
                dp.setId (rs.getInt(1));
                dp.setNombre (rs.getString(2));
                return dp;
            }
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
        return new Departamento();
    }

    @Override
    public List <Departamento> getAll (Connection conn) {
        List <Departamento> lista = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM Departamento;");

            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList <Departamento> (totalRows);

            while(rs.next()) {
                Departamento dp = new Departamento();
                dp.setId (rs.getInt(1));
                dp.setNombre (rs.getString(2));
                lista.add(dp);
            }
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
        return lista;
    }

    public void insertarDepartamento (Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO departamento (nombre) VALUES (?);" , ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            System.out.println("¿Cuántos departamentos quieres insertar?");
            int num = Integer.parseInt(teclado.nextLine());

            for (int i = 0; i < num; i++) {
                System.out.println("Inserte nombre del departamento: ");
                ps.setString(1, teclado.nextLine());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public void insertarDepartamento (Connection con, ArrayList <Departamento> depts) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO departamento (id, nombre) VALUES (?, ?);" , ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            for (int i = 0; i < depts.size(); i++) {
                ps.setInt(1, depts.get(i).getId());
                ps.setString(2, depts.get(i).getNombre());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }
    
}