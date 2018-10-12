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
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {
    
    
    public static void main(String[] args) throws Exception {
        
        File tiedosto = new File("db", "database.db");

        Database database = new Database("jdbc:sqlite:" + tiedosto.getAbsolutePath());
        
        KysymysDao kysymysDao = new KysymysDao(database);
        VastausDao vastausDao = new VastausDao(database);
        
                
        Spark.get("/teuvo/:id", (req, res) -> {
        HashMap map = new HashMap<>();
        
        String id = req.params("id");
        map.put("kysymykset", kysymysDao.findOne(Integer.parseInt(id))); 
        return new ModelAndView(map, "kysymys");

        }, new ThymeleafTemplateEngine());  
              
        Spark.get("/", (req, res) -> {
        HashMap map = new HashMap<>();
        map.put("kysymykset", kysymysDao.findAll()); 
        return new ModelAndView(map, "index");

        }, new ThymeleafTemplateEngine());
        
        Spark.post("/kysymysAdder", (req, res) -> { 
            
            if (req.queryParams("kysymysteksti").trim()!=null && !req.queryParams("kysymysteksti").trim().isEmpty()){
                kysymysDao.saveOrUpdate(new Kysymys(null, req.queryParams("kurssi"), req.queryParams("aihe"), req.queryParams("kysymysteksti").trim(),null));
            }
            res.redirect("/");
            return "";
        });
        
        Spark.post("/vastausAdder/:id", (req, res) -> {
            
            String id = req.params("id");
            System.out.println(id);
            if (req.queryParams("vastausteksti").trim()!=null && !req.queryParams("vastausteksti").trim().isEmpty()){
                vastausDao.saveOrUpdate(new Vastaus(null, Integer.parseInt(req.queryParams("kysymys_id")), req.queryParams("vastausteksti").trim(), Boolean.parseBoolean(req.queryParams("oikein"))));
            }
            
            res.redirect("/teuvo/"+id);
            return "";
        });
        
        Spark.post("/vastausRemover/:id", (req, res) -> {
            String id = req.params("id");
            
            if (Integer.parseInt(id)>0){
                vastausDao.delete(Integer.parseInt(id));
            }
                       
            res.redirect("/teuvo/"+req.queryParams("kysid"));
            return "";
        });
        
        Spark.post("/kysymysRemover/:id", (req, res) -> {
            String id = req.params("id");
            kysymysDao.delete(Integer.parseInt(id));
            
            res.redirect("/");
            return "";
        });
        
    }
}