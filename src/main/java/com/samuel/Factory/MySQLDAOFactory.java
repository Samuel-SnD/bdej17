package com.samuel.Factory;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.samuel.Connections.BasicConnectionPool;
import com.samuel.Dao.AlumnoDAO;
import com.samuel.Dao.AsignaturaDAO;
import com.samuel.Dao.DepartamentoDAO;
import com.samuel.Dao.ProfesorDAO;
import com.samuel.vo.Alumno;
import com.samuel.vo.Asignatura;


public class MySQLDAOFactory extends DAOFactory {
    final static String url = "jdbc:mysql:///Centro_Estudios";
    final static String user = "admin";
    final static String password = "abc123.";
    static BasicConnectionPool bcp;
    static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public MySQLDAOFactory() {
        try {
            bcp = BasicConnectionPool.create(url, user, password);
        } catch (SQLException e) {
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

    @Override
    public void volcadoFichero () {
        ArrayList <Alumno> als = new ArrayList <Alumno> ();
        ArrayList <Asignatura> asg = new ArrayList <Asignatura> ();

        try {
            Path path = Path.of("bdej17\\Insert.txt");
            String contenido = Files.readString(path, StandardCharsets.UTF_8);
            String [] lineas = contenido.split("\n/");
            String [] lineasAl = lineas[0].split("\n");
            for (int i = 0; i < lineasAl.length; i++) {
                Alumno al = new Alumno();
                String [] alarr = lineasAl[i].split(", ");
                al.setDni(alarr[0]);
                al.setNombre(alarr[1]);
                al.setApellidos(alarr[2]);
                al.setFecha_nacimiento(LocalDate.parse(alarr[3], df));
                als.add(al);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
