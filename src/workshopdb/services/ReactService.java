/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshopdb.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import workshodb.entities.Article;
import workshodb.entities.React;
import workshopdb.utils.MyDB;

/**
 *
 * @author yahia
 */
public class ReactService {
    Connection connexion;

    public ReactService() {
        connexion = MyDB.getInstance().getConnection();
    }
    
    public void ajouterforum(React r) throws SQLException {
        String req = " INSERT INTO `react` "
                + "(`id`,`reactType`) "
                + "VALUES ('"
                + r.getId() + "', '"
                + r.getReacType()
                + "')";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
}
