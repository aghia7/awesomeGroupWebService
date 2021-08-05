package data.interfaces;

import java.sql.Connection;

public interface DBConnection {
    Connection getConnection();
}
