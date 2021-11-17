package com.samuel.Factory;

import java.sql.Connection;
import java.sql.SQLException;

import com.samuel.Dao.AlumnoDAO;
import com.samuel.Dao.AsignaturaDAO;
import com.samuel.Dao.DepartamentoDAO;
import com.samuel.Dao.ImparteDAO;
import com.samuel.Dao.ProfesorDAO;

public abstract class DAOFactory {
    public static final int MYSQL = 1;

    public abstract Connection getConnection() throws SQLException;
    public abstract AlumnoDAO getAlumnoDAO();
    public abstract DepartamentoDAO getDepartamentoDAO();
    public abstract AsignaturaDAO getAsignaturaDAO();
    public abstract ProfesorDAO getProfesorDAO();
    public abstract ImparteDAO getImparteDAO();
    public abstract void volcadoFichero();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
        case MYSQL:
            return new MySQLDAOFactory();
        default:
            return null;
        }
    }

    public boolean releaseConnection(Connection connection) {
        return false;
    }

    public int getSize() {
        return 0;
    }

    public void shutdown() throws Exception {
    }
}