package com.d4viddf.Factory;

import java.sql.Connection;
import java.sql.SQLException;

import com.d4viddf.Connections.BasicConnectionPool;


public class MySQLDAOFactory extends DAOFactory {
    final static String url = "jdbc:mysql:///emp";
final static String user = "root";
final static String password = "1234";
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
