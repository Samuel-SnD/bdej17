package com.samuel.Factory;

import java.sql.Connection;
import java.sql.SQLException;

import com.samuel.Connections.BasicConnectionPool;


public class MySQLDAOFactory extends DAOFactory {
    final static String url = "jdbc:mysql:///Centro_Estudios";
final static String user = "admin";
final static String password = "abc123.";
    static BasicConnectionPool bcp;

    public MySQLDAOFactory() {
        try {
            bcp = BasicConnectionPool.create(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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

    
}
