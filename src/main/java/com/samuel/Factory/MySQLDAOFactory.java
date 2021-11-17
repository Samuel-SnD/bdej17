package com.samuel.Factory;

import java.io.IOException;
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
import com.samuel.Dao.ImparteDAO;
import com.samuel.Dao.ProfesorDAO;
import com.samuel.Error.Errores;
import com.samuel.vo.Alumno;
import com.samuel.vo.Asignatura;
import com.samuel.vo.Departamento;
import com.samuel.vo.Imparte;
import com.samuel.vo.Profesor;


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
            Errores.muestraErrorSQL(e);
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
    public ImparteDAO getImparteDAO() {
        return new ImparteDAO();
    }

    @Override
    public void volcadoFichero () {
        ArrayList <Alumno> als = new ArrayList <Alumno> ();
        ArrayList <Departamento> depts = new ArrayList <Departamento> ();
        ArrayList <Profesor> profs = new ArrayList <Profesor> ();
        ArrayList <Asignatura> asg = new ArrayList <Asignatura> ();
        ArrayList <Imparte> impn = new ArrayList <Imparte> ();

        AlumnoDAO alDAO = getAlumnoDAO();
        DepartamentoDAO deptDAO = getDepartamentoDAO();
        ProfesorDAO profDAO = getProfesorDAO();
        AsignaturaDAO asgDAO = getAsignaturaDAO();
        ImparteDAO impDAO = getImparteDAO();

        try {
            Path path = Path.of("bdej17\\Insert.txt");
            String contenido = Files.readString(path, StandardCharsets.UTF_8);
            String [] lineas = contenido.split("\n/\n");
            String [] lineasAl = lineas[0].split("\n");
            String [] lineasDept = lineas[1].split("\n");
            String [] lineasProf = lineas[2].split("\n");
            String [] lineasAsg = lineas[3].split("\n");
            String [] lineasImp = lineas[4].split("\n");

            for (int i = 0; i < lineasAl.length; i++) {
                Alumno al = new Alumno();
                String [] alarr = lineasAl[i].split(", ");
                al.setDni(alarr[0]);
                al.setNombre(alarr[1]);
                al.setApellidos(alarr[2]);
                al.setFecha_nacimiento(LocalDate.parse(alarr[3], df));
                als.add(al);
            }
            for (int i = 0; i < lineasDept.length; i++) {
                Departamento dept = new Departamento();
                String [] deptarr = lineasDept[i].split(", ");
                dept.setId(Integer.parseInt(deptarr[0]));
                dept.setNombre(deptarr[1]);
                depts.add(dept);
            }
            for (int i = 0; i < lineasProf.length; i++) {
                Profesor prof = new Profesor();
                String [] profarr = lineasProf[i].split(", ");
                prof.setDni(profarr[0]);
                prof.setNombre(profarr[1]);
                prof.setApellidos(profarr[2]);
                prof.setDepartamento(Integer.parseInt(profarr[3]));
                prof.setFecha_nacimiento(LocalDate.parse(profarr[4], df));
                profs.add(prof);
            }
            for (int i = 0; i < lineasAsg.length; i++) {
                Asignatura asig = new Asignatura();
                String [] asgarr = lineasAsg[i].split(", ");
                asig.setId(Integer.parseInt(asgarr[0]));
                asig.setNombre(asgarr[1]);
                asg.add(asig);
            }
            for (int i = 0; i < lineasImp.length; i++) {
                Imparte imp = new Imparte();
                String [] imparr = lineasImp[i].split(", ");
                imp.setDniProf(imparr[0]);
                imp.setAsig(Integer.parseInt(imparr[1]));
                imp.setDniAl(imparr[2]);
                imp.setCurso(Integer.parseInt(imparr[3]));
                impn.add(imp);
            }

            alDAO.insertarAlumno(getConnection(), als);
            deptDAO.insertarDepartamento(getConnection(), depts);
            profDAO.insertarProfesor(getConnection(), profs);
            asgDAO.insertarAsignatura(getConnection(), asg);
            impDAO.insertarImparte(getConnection(), impn);
        } catch (IOException e) {
            Errores.muestraErrorIO(e);
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }
}
