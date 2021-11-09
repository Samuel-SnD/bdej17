package com.samuel.Dao;

import java.sql.Connection;
import java.util.List;

public interface Dao <T> {
    List <T> getAll(Connection conn);
}
