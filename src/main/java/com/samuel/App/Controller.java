package com.samuel.App;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import com.samuel.Dao.AlumnoDAO;
import com.samuel.Dao.AsignaturaDAO;
import com.samuel.Dao.DepartamentoDAO;
import com.samuel.Dao.ProfesorDAO;
import com.samuel.Error.Errores;
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
        mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        alDAO = mySQLFactory.getAlumnoDAO();
        deptDAO = mySQLFactory.getDepartamentoDAO();
        asgDAO = mySQLFactory.getAsignaturaDAO();
        prDAO = mySQLFactory.getProfesorDAO();
        boolean salir = false;
        while (!salir) {
            View v = new View();
            int opcion = v.mostrarMenu();
            switch (opcion) {
                case 1: crearTablas(); break;
                case 2: insertarAlumno(); break;
                case 3: insertarAsignatura(); break;
                case 4: insertarDepartamento();; break;
                case 5: insertarProfesor(); break;
                case 6: insertarImparte(); break;
                case 7: verAlumnos(); break;
                case 8: verAsignaturas(); break;
                case 9: verDepartamentos(); break;
                case 10: verProfesores(); break;
                case 11: verAlumno(); break;
                case 12: verAsignatura(); break;
                case 13: verDepartamento(); break;
                case 14: verProfesor(); break;
                case 15: verAlumnoAsignatura(); break;
                case 16: verAsignaturaProfesor(); break;
                case 17: verProfesorDepartamento(); break;
                case 18: eliminarTablas(); break;
                case 19: rellenarTablas(); break;
                case 20: borrarTablas(); break;
                case 0: salir = true; break;
                default: System.out.println("Opción no válida"); break;
            }
        }
    }

    public static void verAlumnos () {
        try {
            alumnos = alDAO.getAll(mySQLFactory.getConnection());
            for (int i = 0; i < alumnos.size(); i++) {
                System.out.println(alumnos.get(i).toString());
            }
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void verAsignaturas () {
        try {
            asignaturas = asgDAO.getAll(mySQLFactory.getConnection());
            for (int i = 0; i < asignaturas.size(); i++) {
                System.out.println(asignaturas.get(i).toString());
            }
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void verDepartamentos () {
        try {
            departamentos = deptDAO.getAll(mySQLFactory.getConnection());
            for (int i = 0; i < departamentos.size(); i++) {
                System.out.println(departamentos.get(i).toString());
            }
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void verProfesores () {
        try {
            profesores = prDAO.getAll(mySQLFactory.getConnection());
            for (int i = 0; i < profesores.size(); i++) {
                System.out.println(profesores.get(i).toString());
            }
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void verAlumno() {
        System.out.println("Introduce el dni del alumno: ");
        String dni = teclado.nextLine();
        try {
            Alumno al = alDAO.get(dni, mySQLFactory.getConnection());
            System.out.println(al.toString());
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void verAsignatura() {
        System.out.println("Introduce el id de la asignatura: ");
        int id = Integer.parseInt(teclado.nextLine());
        try {
            Asignatura asg = asgDAO.get(id, mySQLFactory.getConnection());
            System.out.println(asg.toString());
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void verDepartamento() {
        System.out.println("Introduce el id del departamento: ");
        int id = Integer.parseInt(teclado.nextLine());
        try {
            Departamento dept = deptDAO.get(id, mySQLFactory.getConnection());
            System.out.println(dept.toString());
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void verProfesor() {
        System.out.println("Introduce el dni del profesor: ");
        String dni = teclado.nextLine();
        try {
            Profesor pr = prDAO.get(dni, mySQLFactory.getConnection());
            System.out.println(pr.toString());
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void verAlumnoAsignatura () {
        System.out.println("Introduce el id de la asignatura: ");
        int id = Integer.parseInt(teclado.nextLine());
        try {
            alumnos = alDAO.getByAsignatura(id, mySQLFactory.getConnection());
            for (int i = 0; i < alumnos.size(); i++) {
                System.out.println(alumnos.get(i).toString());
            }
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void verAsignaturaProfesor () {
        System.out.println("Introduce el dni del profesor: ");
        String dni = teclado.nextLine();
        try {
            asignaturas = asgDAO.getByProfesor(dni, mySQLFactory.getConnection());
            for (int i = 0; i < asignaturas.size(); i++) {
                System.out.println(asignaturas.get(i).toString());
            }
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void verProfesorDepartamento () {
        System.out.println("Introduce el id del departamento: ");
        int id = Integer.parseInt(teclado.nextLine());
        try {
            profesores = prDAO.getByDept(id, mySQLFactory.getConnection());
            for (int i = 0; i < profesores.size(); i++) {
                System.out.println(profesores.get(i).toString());
            }
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void eliminarTablas() {
        try {
            Statement s = mySQLFactory.getConnection().createStatement();
            s.execute("Drop table imparte;");
            s.execute("Drop table profesor;");
            s.execute("Drop table departamento;");
            s.execute("Drop table asignatura;");
            s.execute("Drop table alumno;");
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void borrarTablas() {
        try {
            Statement s = mySQLFactory.getConnection().createStatement();
            s.executeUpdate("Delete from imparte;");
            s.executeUpdate("Delete from profesor;");
            s.executeUpdate("Delete from departamento;");
            s.executeUpdate("Delete from asignatura;");
            s.executeUpdate("Delete from alumno;");
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void crearTablas () {
        try {
            Statement s = mySQLFactory.getConnection().createStatement();
            s.execute("CREATE TABLE Alumno(Dni CHAR(9) NOT NULL PRIMARY KEY, Nombre VARCHAR(25) NOT NULL, Apellidos VARCHAR(25) NOT NULL, Fecha_nacimiento DATE NOT NULL);");
            s.execute("CREATE TABLE Asignatura(Id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, Nombre VARCHAR(25) NOT NULL);");
            s.execute("CREATE TABLE Departamento(Id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, Nombre VARCHAR(25) NOT NULL);");
            s.execute("CREATE TABLE Profesor(Dni CHAR(9) NOT NULL PRIMARY KEY, Departamento INT UNSIGNED NOT NULL, Nombre VARCHAR(25) NOT NULL, Apellidos VARCHAR(25) NOT NULL, Fecha_nacimiento DATE NOT NULL);");
            s.execute("CREATE TABLE Imparte(Profesor CHAR(9) NOT NULL, Asignatura INT UNSIGNED NOT NULL, Alumno CHAR(9) NOT NULL, Curso CHAR(7) NOT NULL);");
            s.execute("ALTER TABLE Imparte ADD PRIMARY KEY (Profesor, Asignatura, Alumno, Curso);");
            s.execute("ALTER TABLE Profesor ADD CONSTRAINT profesor_departamento_foreign FOREIGN KEY (Departamento) REFERENCES Departamento(Id);");
            s.execute("ALTER TABLE Imparte ADD CONSTRAINT FOREIGN KEY imparte_profesor_foreign (Profesor) REFERENCES Profesor(Dni);");
            s.execute("ALTER TABLE Imparte ADD CONSTRAINT FOREIGN KEY imparte_asignatura_foreign (Asignatura) REFERENCES Asignatura(Id);");
            s.execute("ALTER TABLE Imparte ADD CONSTRAINT FOREIGN KEY imparte_alumno_foreign (Alumno) REFERENCES Alumno(Dni);");
        } catch (SQLException e) {
            System.out.println("No puedes crear las tablas si ya están creadas, primero utiliza la función de borrar tablas");
        }
    }

    public static void insertarImparte () {
        try {
            PreparedStatement ps = mySQLFactory.getConnection().prepareStatement("INSERT INTO asignatura (profesor, asignatura, alumno, curso) VALUES (?, ?, ?, ?);" , ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("¿Cuántas filas quieres insertar?");
            int num = Integer.parseInt(teclado.nextLine());
            for (int i = 0; i < num; i++) {
                System.out.println("Inserte dni del profesor: ");
                ps.setString(1, teclado.nextLine());
                System.out.println("Inserte id de la asignatura: ");
                ps.setInt(2, Integer.parseInt(teclado.nextLine()));
                System.out.println("Inserte dni del alumno: ");
                ps.setString(3, teclado.nextLine());
                System.out.println("Inserte curso: ");
                ps.setInt(4, Integer.parseInt(teclado.nextLine()));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void insertarAlumno () {
        try {
            alDAO.insertarAlumno(mySQLFactory.getConnection());
            alumnos = alDAO.getAll(mySQLFactory.getConnection());
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void insertarAsignatura () {
        try {
            asgDAO.insertarAsignatura(mySQLFactory.getConnection());
            asignaturas = asgDAO.getAll(mySQLFactory.getConnection());
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void insertarDepartamento () {
        try {
            deptDAO.insertarDepartamento(mySQLFactory.getConnection());
            departamentos = deptDAO.getAll(mySQLFactory.getConnection());
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void insertarProfesor () {
        try {
            prDAO.insertarProfesor(mySQLFactory.getConnection());
            profesores = prDAO.getAll(mySQLFactory.getConnection());
        } catch (SQLException e) {
            Errores.muestraErrorSQL(e);
        }
    }

    public static void rellenarTablas () {
        mySQLFactory.volcadoFichero();
    }
}