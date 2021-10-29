/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import IService.ICibleService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import entities.Cible;

import DB.DB;

/**
 *
 * @author pc-asus
 */
public class CibleServices implements ICibleService {

    static Connection connexion;

    public CibleServices() {
        connexion = DB.getInstance().getConnection();
    }

    @Override
    public void ajouterCible(Cible c) throws SQLException {
        String req = "INSERT INTO `cible`(  `genre`, `ageMax`, `ageMin`, `dateDebutCible`, `dateFinCible`, `prix`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);

            ps.setString(1, c.getGenre());
            ps.setInt(2, c.getAgemax());
            ps.setInt(3, c.getAgemin());
            if (c.getDateDebutCible().compareTo(c.getDateFinCible()) < 0 && c.getDateDebutCible().compareTo(Date.from(Instant.now())) >= 0) {
                ps.setDate(4, (java.sql.Date) (Date) c.getDateDebutCible());
                ps.setDate(5, (java.sql.Date) (Date) c.getDateFinCible());
            }
            int p =AjoutPrix( c);
            ps.setInt(6,p);
            ps.executeUpdate();
            System.out.println("cible ajouté");
        } catch (SQLException ex) {
            System.out.println("Erreur d'ajout" + ex.getMessage());
        }
    }

    @Override
    public int testCible() throws SQLException {
        Statement St;
        St = connexion.createStatement();
        ResultSet rec = St.executeQuery("SELECT COUNT(*) FROM `cible`");
        rec.next();
        int nb = (int) rec.getInt(1);

        return nb;
    }

    @Override
    public int ModifierCible(int c) throws SQLException {
        int n = 0;
        Statement St;
        ResultSet rs;
        String req = "Select * from Cible where 1";
        St = connexion.createStatement();

        rs = (ResultSet) St.executeQuery(req);
        rs.last();
        if (rs.isLast()) {
            Cible c1 = new Cible(
                    rs.getString("genre"),
                    rs.getInt("ageMin"),
                    rs.getInt("ageMax"),
                    rs.getDate("dateDebutCible"),
                    rs.getDate("dateFinCible"),
                    rs.getInt("prix"));

            if (c > c1.getPrix()) {

                n = c - c1.getPrix();
            }

        }
        return n;

    }

    @Override
    public int AjoutPrix(Cible c) {
        int prix = 0;
        int prix_totale = 0;
        int choix = calculateDifference(c.getDateDebutCible(), c.getDateFinCible());
        if (choix <= 31) {
            if ((c.getGenre().equals("Homme")) | (c.getGenre().equals("Famme"))) {
                if (c.getAgemax() - c.getAgemin() < 2) {
                    prix = 100;
                } else if (c.getAgemax() - c.getAgemin() > 2 && c.getAgemax() - c.getAgemin() <= 10) {
                    prix = 200;
                } else if (c.getAgemax() - c.getAgemin() > 10) {
                    prix = 300;

                }
            }
            if (c.getGenre().equals("Tous")) {
                if (c.getAgemax() - c.getAgemin() < 2) {
                    prix = 200;
                } else if (c.getAgemax() - c.getAgemin() > 2 && c.getAgemax() - c.getAgemin() <= 10) {
                    prix = 300;
                } else if (c.getAgemax() - c.getAgemin() > 10) {
                    prix = 400;

                }

            }
            prix_totale = prix + 200;
        } else if (choix >= 62) {
            if ((c.getGenre().equals("Homme")) | (c.getGenre().equals("Famme"))) {
                if (c.getAgemax() - c.getAgemin() <= 5) {
                    prix = 100;
                } else if (c.getAgemax() - c.getAgemin() > 5 && c.getAgemax() - c.getAgemin() <= 15) {
                    prix = 200;
                } else if (c.getAgemax() - c.getAgemin() > 15) {
                    prix = 300;

                }
            }
            if (c.getGenre().equals("Tous")) {
                if (c.getAgemax() - c.getAgemin() <= 5) {
                    prix = 200;
                } else if (c.getAgemax() - c.getAgemin() > 5 && c.getAgemax() - c.getAgemin() <= 15) {
                    prix = 300;
                } else if (c.getAgemax() - c.getAgemin() > 15) {
                    prix = 400;

                }

            }
            prix_totale = prix + 200;
        } else if (choix > 100) {
            if ((c.getGenre().equals("Homme")) | (c.getGenre().equals("Famme"))) {
                if (c.getAgemax() - c.getAgemin() <= 5) {
                    prix = 100;
                } else if (c.getAgemax() - c.getAgemin() > 5 && c.getAgemax() - c.getAgemin() <= 15) {
                    prix = 200;
                } else if (c.getAgemax() - c.getAgemin() > 15) {
                    prix = 300;

                }
            }
            if (c.getGenre().equals("Tous")) {
                if (c.getAgemax() - c.getAgemin() <= 5) {
                    prix = 200;
                } else if (c.getAgemax() - c.getAgemin() > 5 && c.getAgemax() - c.getAgemin() <= 15) {
                    prix = 300;
                } else if (c.getAgemax() - c.getAgemin() > 15) {
                    prix = 400;

                }

            }
            prix_totale = prix + 300;

        }
        return prix_totale;
    }

    public static int calculateDifference(Date a, Date b) {
        int tempDifference = 0;
        int difference = 0;
        Calendar earlier = Calendar.getInstance();
        Calendar later = Calendar.getInstance();

        if (a.compareTo(b) < 0) {
            earlier.setTime(a);
            later.setTime(b);
        } else {
            earlier.setTime(b);
            later.setTime(a);
        }

        while (earlier.get(Calendar.YEAR) != later.get(Calendar.YEAR)) {
            tempDifference = 365 * (later.get(Calendar.YEAR) - earlier.get(Calendar.YEAR));
            difference += tempDifference;

            earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
        }

        if (earlier.get(Calendar.DAY_OF_YEAR) != later.get(Calendar.DAY_OF_YEAR)) {
            tempDifference = later.get(Calendar.DAY_OF_YEAR) - earlier.get(Calendar.DAY_OF_YEAR);
            difference += tempDifference;

            earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
        }

        return difference;
    }

    @Override
    public Cible rechercheCible() throws SQLException {

        Cible c = new Cible();
        String req = "Select 'dateFinCible' from Cible where 1";
        Statement St = connexion.createStatement();
        ResultSet rs = (ResultSet) St.executeQuery(req);

        if (rs.last()) {
            c = new Cible(rs.getDate("dateFinCible"));
        }

        return c;
    }

}
