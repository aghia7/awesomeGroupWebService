package data;

import data.interfaces.DBConnection;

import javax.ws.rs.ServerErrorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection implements DBConnection {
    @Override
    public Connection getConnection() {
        try {
            String connStr = "jdbc:postgresql://localhost:5432/awesomeGroupDB";
            Connection conn = DriverManager.getConnection(connStr, "postgres", "0000");
            return conn;
        } catch (SQLException ex) {
            throw new ServerErrorException("Cannot connect to DB", 500);
        }
    }
}
