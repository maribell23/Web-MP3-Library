/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Bella
 */
public class Connectivity {

    public static Connection getConnect() {
        Connection connection = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/MP3DB");
            connection = ds.getConnection();

        } catch (NamingException ex) {
            System.out.println("error1");
        } catch (SQLException ex) {
            System.out.println("error2");
        }
        return connection;

    }
}
