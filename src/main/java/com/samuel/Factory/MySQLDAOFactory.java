package com.samuel.Factory;

import java.sql.Connection;
import java.sql.SQLException;

import com.samuel.Connections.BasicConnectionPool;
import com.samuel.Dao.AlumnoDAO;
import com.samuel.Dao.AsignaturaDAO;
import com.samuel.Dao.DepartamentoDAO;
import com.samuel.Dao.ProfesorDAO;


public class MySQLDAOFactory extends DAOFactory {
    final static String url = "jdbc:mysql:///Centro_Estudios";
final static String user = "admin";
final static String password = "abc123.";
    static BasicConnectionPool bcp;

    public MySQLDAOFactory() {
        try {
            bcp = BasicConnectionPool.create(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return bcp.getConnection();
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        return bcp.releaseConnection(connection);
    }

    @Override
    public int getSize() {
        return bcp.getSize();
    }

    // add getUser, getURL....
    @Override
    public void shutdown() throws SQLException {
        bcp.shutdown();
    }

    @Override
    public AlumnoDAO getAlumnoDAO() {
        return new AlumnoDAO();
    }

    @Override
    public DepartamentoDAO getDepartamentoDAO() {
        return new DepartamentoDAO();
    }

    @Override
    public AsignaturaDAO getAsignaturaDAO() {
        return new AsignaturaDAO();
    }

    @Override
    public ProfesorDAO getProfesorDAO() {
        return new ProfesorDAO();
    }
    
}
