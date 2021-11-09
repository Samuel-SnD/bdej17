package com.samuel.Dao;

import java.sql.Connection;
import java.util.List;
import vo.Alumno;

public class AlumnoDAO implements Dao <Alumno> {

    @Override
    public Alumno get (long id) {
        return null;
    }

    @Override
    public List <Alumno> getAll (Connection conn) {
        return null;
    }
    
}
