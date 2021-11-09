package com.samuel.Dao;

import java.sql.Connection;
import java.util.List;
import com.samuel.vo.Profesor;

public class ProfesorDAO implements Dao <Profesor> {

    @Override
    public Profesor get (long id) {
        return null;
    }

    @Override
    public List <Profesor> getAll (Connection conn) {
        return null;
    }
    
}
