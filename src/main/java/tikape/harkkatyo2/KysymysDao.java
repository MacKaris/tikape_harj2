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

 

public class KysymysDao implements Dao<Kysymys, Integer> {

 

    private Database database;

    
    public KysymysDao(Database database) {

        this.database = database;

    }

 

    @Override

    public Kysymys findOne(Integer key) throws SQLException {
        
        VastausDao vastausDao = new VastausDao(database);

        List<Vastaus> vastaust = new ArrayList<>();

        Connection conn = database.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM kysymys WHERE id = ?");

        stmt.setInt(1, key);
        
        
 

        ResultSet rs = stmt.executeQuery();

        boolean hasOne = rs.next();

        if (!hasOne) {

            return null;

        }

        vastaust = vastausDao.findAllRelatingToQuestion(rs.getInt("id"));

        Kysymys a = new Kysymys(rs.getInt("id"),rs.getString("kurssi"), rs.getString("aihe"), rs.getString("teksti"),vastaust);

       

 
        
        stmt.close();

        rs.close();

 

        conn.close();

 

        return a;

    }

 

    // hakee kaikki asiakkaat tietokannasta ja palauttaa ne listalla

    @Override

    public List<Kysymys> findAll() throws SQLException {

        VastausDao vastausDao = new VastausDao(database);

        List<Kysymys> kysymyst = new ArrayList<>();
        List<Vastaus> vastaust = new ArrayList<>();

        

        Connection conn = database.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM kysymys");

        //stmt.setInt(1, key);

 

        ResultSet rs = stmt.executeQuery();
        
//        Vastaus b = new Vastaus(1,1,"suck on my chocolate salty balls", true);
//        vastaust.add(b);

        while (rs.next()) {
            
//                PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM Vastaus WHERE kysymys_id = ?");
//               stmt2.setInt(1, rs.getInt("id"));
//              ResultSet rs2 = stmt2.executeQuery();
//                
//                while (rs2.next()) {
//                    Vastaus c = new Vastaus(rs2.getInt("id"),rs2.getInt("kysymys_id"), rs2.getString("teksti"), rs2.getBoolean("oikein"));
//                    vastaust.add(b);
//                }

                vastaust = vastausDao.findAllRelatingToQuestion(rs.getInt("id"));
                Kysymys a = new Kysymys(rs.getInt("id"),rs.getString("kurssi"), rs.getString("aihe"), rs.getString("teksti"),vastaust);

                kysymyst.add(a);
                
//                stmt2.close();
//                rs2.close();

        }

 

        stmt.close();
        

        rs.close();
        

 

        conn.close();

 

        return kysymyst;        

        

    }

 

    @Override

    public Kysymys saveOrUpdate(Kysymys object) throws SQLException {

        // jos asiakkaalla ei ole pääavainta, oletetaan, että kysymysta

        // ei ole vielä tallennettu tietokantaan ja tallennetaan kysymys

        //return save(object);

        if (object.getId() == null) {

            return save(object);

        } else {

            // muulloin päivitetään kysymys

            return update(object);

        }

    }

 

    @Override

    public void delete(Integer key) throws SQLException {

        Connection conn = database.getConnection();
        
        VastausDao vastausDao = new VastausDao(database);

        PreparedStatement stmt = conn.prepareStatement("DELETE FROM kysymys WHERE id = ?");

        stmt.setInt(1, key);

        stmt.executeUpdate();
        
        vastausDao.deleteByForeignKey(key);

 

        stmt.close();

        conn.close();

    }

 

    private Kysymys save(Kysymys kysymys) throws SQLException {

        VastausDao vastausDao = new VastausDao(database);

        
        List<Vastaus> vastaust = new ArrayList<>();

        Connection conn = database.getConnection();

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO kysymys (kurssi, aihe, teksti) VALUES (?,?,?)");

        stmt.setString(1, kysymys.getKurssi());
        stmt.setString(2, kysymys.getAihe());
        stmt.setString(3, kysymys.getTeksti());
 

        stmt.executeUpdate();

        stmt.close();

 

        stmt = conn.prepareStatement("SELECT * FROM kysymys WHERE teksti = ?");

        stmt.setString(1, kysymys.getTeksti());

 

        ResultSet rs = stmt.executeQuery();

        rs.next(); // vain 1 tulos

        
        vastaust = vastausDao.findAllRelatingToQuestion(rs.getInt("id"));
        Kysymys a = new Kysymys(rs.getInt("id"),rs.getString("kurssi"), rs.getString("aihe"), rs.getString("teksti"),vastaust);

 

        stmt.close();

        rs.close();

 

        conn.close();

 
        //return null;
        return a;

    }

 

    private Kysymys update(Kysymys kysymys) throws SQLException {

 

//        Connection conn = database.getConnection();

//        PreparedStatement stmt = conn.prepareStatement("UPDATE Kysymys SET"

//                + " nimi = ? WHERE id = ?");

//        stmt.setString(1, kysymys.getNimi());

//        stmt.setInt(6, kysymys.getId());

//

//        stmt.executeUpdate();

//

//        stmt.close();

//        conn.close();

//

          return kysymys;

    }

}