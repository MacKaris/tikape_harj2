/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.harkkatyo2;

/**
 *
 * @author pkaris
 */
import java.util.*;

import java.sql.*;

 

public class VastausDao implements Dao<Vastaus, Integer> {

 

    private Database database;

 

    public VastausDao(Database database) {

        this.database = database;

    }

 

    @Override

    public Vastaus findOne(Integer key) throws SQLException {

        Connection conn = database.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM vastaus WHERE id = ?");

        stmt.setInt(1, key);

 

        ResultSet rs = stmt.executeQuery();

        boolean hasOne = rs.next();

        if (!hasOne) {

            return null;

        }

 

        Vastaus a = new Vastaus(rs.getInt("id"), rs.getInt("kysymys_id"), rs.getString("teksti"), rs.getBoolean("oikein") );

 

        stmt.close();

        rs.close();

 

        conn.close();

 

        return a;

    }

 

    

    @Override

    public List<Vastaus> findAll() throws SQLException {

        

        List<Vastaus> vastaust = new ArrayList<>();

        

        Connection conn = database.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM vastaus");

        //stmt.setInt(1, key);

 

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) { 

                Vastaus a = new Vastaus(rs.getInt("id"), rs.getInt("kysymys_id"), rs.getString("teksti"), rs.getBoolean("oikein") );

                vastaust.add(a);

        }

 

        stmt.close();

        rs.close();

 

        conn.close();

 

        return vastaust;        

        

    }
    
    public List<Vastaus> findAllRelatingToQuestion(Integer key) throws SQLException {

        

        List<Vastaus> vastaust = new ArrayList<>();

        

        Connection conn = database.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM vastaus WHERE kysymys_id = ?");

        stmt.setInt(1, key);

 

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) { 

                Vastaus a = new Vastaus(rs.getInt("id"), rs.getInt("kysymys_id"), rs.getString("teksti"), rs.getBoolean("oikein") );

                vastaust.add(a);

        }
        if (vastaust.isEmpty()){                   
            Vastaus a = new Vastaus(-1, key, "Kysymykselle ei ole vastausvaihtoehtoja!", false);
            vastaust.add(a);
        }
 

        stmt.close();

        rs.close();

 

        conn.close();

 

        return vastaust;               

    }

 

    @Override

    public Vastaus saveOrUpdate(Vastaus object) throws SQLException {

        // jos asiakkaalla ei ole pääavainta, oletetaan, että vastausta

        // ei ole vielä tallennettu tietokantaan ja tallennetaan vastaus

        

        if (object.getId() == null) {

            return save(object);

        } else {

            // muulloin päivitetään vastaus

            return update(object);

        }

    }

 

    @Override

    public void delete(Integer key) throws SQLException {

        Connection conn = database.getConnection();

        PreparedStatement stmt = conn.prepareStatement("DELETE FROM vastaus WHERE id = ?");

 

        stmt.setInt(1, key);

        stmt.executeUpdate();

 

        stmt.close();

        conn.close();

    }
    
    public void deleteByForeignKey(Integer key) throws SQLException {

        Connection conn = database.getConnection();

        PreparedStatement stmt = conn.prepareStatement("DELETE FROM vastaus WHERE kysymys_id = ?");

 

        stmt.setInt(1, key);

        stmt.executeUpdate();

 

        stmt.close();

        conn.close();

    }

 

    private Vastaus save(Vastaus vastaus) throws SQLException {

 

        Connection conn = database.getConnection();

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Vastaus (kysymys_id, teksti, oikein) VALUES (?,?,?)");

        stmt.setInt(1, vastaus.getKysymys_id());
        stmt.setString(2, vastaus.getTeksti());
        stmt.setBoolean(3, vastaus.getOikein());

 

        stmt.executeUpdate();

        stmt.close();

 

        stmt = conn.prepareStatement("SELECT * FROM vastaus WHERE teksti = ?");

        stmt.setString(1, vastaus.getTeksti());

 

        ResultSet rs = stmt.executeQuery();

        rs.next(); // vain 1 tulos

 

        Vastaus a = new Vastaus(rs.getInt("id"), rs.getInt("kysymys_id"), rs.getString("teksti"), rs.getBoolean("oikein") );

 

        stmt.close();

        rs.close();

 

        conn.close();

 

        return a;

    }

 

    private Vastaus update(Vastaus vastaus) throws SQLException {

 

//        Connection conn = database.getConnection();

//        PreparedStatement stmt = conn.prepareStatement("UPDATE Vastaus SET"

//                + " nimi = ? WHERE id = ?");

//        stmt.setString(1, vastaus.getNimi());

//        stmt.setInt(6, vastaus.getId());

//

//        stmt.executeUpdate();

//

//        stmt.close();

//        conn.close();

//

          return vastaus;

    }

}
