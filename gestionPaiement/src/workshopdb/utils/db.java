package workshopdb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Aminous
 */
public class db {

    final String url = "jdbc:mysql://localhost/9arini";
    final String login = "root";
    final String password = "";
    Connection connexion;
    static db instance;

    private db() {
        try {
            connexion = DriverManager.getConnection(url, login, password);
            System.out.println("La connexion à la base de données est réussie.");
        } catch (SQLException ex) {
            System.out.println("La connexion à la base de données est échouée. " + ex.getMessage());
        }
    }

    public static db getInstance() {
        if (instance == null) {
            instance = new db();
        }
        return instance;
    }

    public Connection getConnection() {
        return connexion;
    }
}
