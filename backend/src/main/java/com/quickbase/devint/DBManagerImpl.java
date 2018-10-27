package com.quickbase.devint;

import java.sql.*;
import static com.quickbase.devint.DevIntConstants.*;

/**
 * This DBManager implementation provides a connection to the database containing population data.
 * Created by ckeswani on 9/16/15.
 */
public class DBManagerImpl implements DBManager {
    Connection c = null;
    //Return's the object of connection
    public Connection getConnection() {
        try {
            Class.forName(CONNECTION_CLASS);
            this.c = DriverManager.getConnection(CONNECTION_URL);
            System.out.println(DB_CONN_SUCCESS_MSG);

        } catch (ClassNotFoundException cnf) {
            System.out.println(DB_CONN_DRIVERCLASS_ERR);
        } catch (SQLException sqle) {
            System.out.println(DB_CONN_ERR + sqle.getStackTrace());
        }
        return this.c;
    }

    @Override
    //Closes the current instance of connection.
    public void closeConnection() {
        try {
            this.c.close();
            System.out.println(DB_CLOSE_CONN_MSG);
        }
        catch (SQLException e){
            System.out.println(DB_CLOSE_CONN_ERR + e.getStackTrace());
        }

    }

}
