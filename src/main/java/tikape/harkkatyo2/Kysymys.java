/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.harkkatyo2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pkaris
 */
public class Kysymys {

    private Integer id;
    private String kurssi;
    private String aihe;
    private String teksti;
    private List<Vastaus> vastaukset = new ArrayList<>();

    public Kysymys(Integer id, String kurssi, String aihe, String teksti, List<Vastaus> vastaukset) {
        this.id = id;
        this.kurssi = kurssi;
        this.aihe = aihe;
        this.teksti = teksti;
        this.vastaukset = vastaukset;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKurssi() {
        return kurssi;
    }

    public void setKurssi(String kurssi) {
        this.kurssi = kurssi;
    }
    
    public String getAihe() {
        return aihe;
    }

    public void setAihe(String aihe) {
        this.aihe = aihe;
    }
    
    public String getTeksti() {
        return teksti;
    }

    public void setTeksti(String teksti) {
        this.teksti = teksti;
    }
    
    public List<Vastaus> getVastaukset() {
        return vastaukset;
    }

    public void setVastaukset(List<Vastaus> vastaukset) {
        this.vastaukset = vastaukset;
    }

}