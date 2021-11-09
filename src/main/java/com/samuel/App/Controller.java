package com.samuel.App;

import java.util.List;
import java.util.Scanner;

import com.samuel.Dao.AlumnoDAO;
import com.samuel.Dao.AsignaturaDAO;
import com.samuel.Dao.DepartamentoDAO;
import com.samuel.Dao.ProfesorDAO;
import com.samuel.Factory.DAOFactory;
import com.samuel.vo.Alumno;
import com.samuel.vo.Asignatura;
import com.samuel.vo.Departamento;
import com.samuel.vo.Profesor;

public class Controller {
    static Scanner teclado = new Scanner (System.in);
    static List <Alumno> alumnos;
    static List <Departamento> departamentos;
    static List <Asignatura> asignaturas;
    static List <Profesor> profesores;
    static DAOFactory mySQLFactory;
    static AlumnoDAO alDAO;
    static DepartamentoDAO deptDAO;
    static AsignaturaDAO asgDAO;
    static ProfesorDAO prDAO;
    public static void main(String[] args) {
        try {
            mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            alDAO = mySQLFactory.getAlumnoDAO();
            deptDAO = mySQLFactory.getDepartamentoDAO();
            asgDAO = mySQLFactory.getAsignaturaDAO();
            prDAO = mySQLFactory.getProfesorDAO();
        } catch (Exception e) {
                e.printStackTrace();
        }
        boolean salir = false;
        while (!salir) {
            View v = new View();
            int opcion = v.mostrarMenu();
            switch (opcion) {
                
                case 0: salir = true; break;
                default: System.out.println("Opción no válida"); break;
            }
        }
    }
}