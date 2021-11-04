package com.d4viddf.Factory;

import java.sql.Connection;

public abstract class DAOFactory {
    public static final int MYSQL = 1;
    public abstract Connection getConnection() throws Exception;

    public static DAOFactory getDAOFactory(int whichFactory) {

        switch (whichFactory) {
        case MYSQL:
            return new MySQLDAOFactory();
        default:
            return null;
        }
    }

    public boolean releaseConnection(Connection connection) {
        // TODO Auto-generated method stub
        return false;
    }

    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    public void shutdown() throws Exception {
        // TODO Auto-generated method stub
    }
}