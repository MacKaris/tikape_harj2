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
public class Vastaus {

    private Integer id;
    private Integer kysymys_id;
    private String teksti;
    private boolean oikein;

    public Vastaus(Integer id, Integer kysymys_id, String teksti, boolean oikein) {
        this.id = id;
        this.kysymys_id = kysymys_id;
        this.teksti = teksti;
        this.oikein = oikein;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKysymys_id() {
        return kysymys_id;
    }

    public void setKysymys_id(Integer kysymys_id) {
        this.kysymys_id = kysymys_id;
    }
    
    public String getTeksti() {
        return teksti;
    }

    public void setTeksti(String teksti) {
        this.teksti = teksti;
    }
    
    public boolean getOikein() {
        return oikein;
    }

    public void setOikein(boolean oikein) {
        this.oikein = oikein;
    }

}