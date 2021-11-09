package com.samuel.Dao;

import java.sql.Connection;
import java.util.List;

public interface Dao <T> {
    T get (long id);

    List <T> getAll(Connection conn);
}
