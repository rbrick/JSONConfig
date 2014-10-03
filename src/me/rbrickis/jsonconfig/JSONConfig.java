package me.rbrickis.jsonconfig;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONConfig implements Serializable {
    
    // The parsed objects
    private Map<String, Object> parsedObjects = new HashMap<>();
    
    File f;
    
    public JSONConfig() { }
    
     public JSONConfig(File f) {
         this.f = f;
         
         if(!f.exists()) {
             try {
                 f.createNewFile();
             } catch(IOException ex) {
                 ex.printStackTrace();
             }
         } else {
             try {
                 loadJSONConfig(f);
             } catch(Exception ex) {
                 
             }
         }
     }
     
    public JSONConfig loadJSONConfig(File f) throws FileNotJSONException {
        if(!f.exists()) {
            throw new NullPointerException("Could not find file " + f.getName() + " in " + f.getPath());
        } else if(!f.getName().endsWith(".json")) {                 
           throw new FileNotJSONException("Unsupported file extension \"" + f.getName().split("\\.")[1] + "\".");
        } else {
            JSONParser parser = new JSONParser();
            try {
                // Parses the JSON
                JSONObject parsed = (JSONObject) parser.parse(new FileReader(f));
                
                Set<Object> keys = parsed.keySet();
               
                
                for(Object key : keys) {
                    parsedObjects.put((String) key, parsed.get(key));
                }
                   
                
                
            } catch (ParseException | IOException ex) {
                Logger.getLogger(JSONConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        return this; 
    }
     
     
   public <T> T get(String name) throws NullPointerException, ClassCastException,IOException {
        return (T) parsedObjects.get(name);
       
   }
   
   public void set(String key, Object obj) {
       parsedObjects.put(key, obj);
   }
     
   public void save() {
       JSONObject JSON_OBJECT = new JSONObject();
       
       for(Entry<String, Object> entry : parsedObjects.entrySet()) {
           String key = entry.getKey();
           Object obj = entry.getValue();
           
           if(obj instanceof List || obj.getClass().isAssignableFrom(List.class)) {
               // Infer generic types cause sw333333333333333333333333333g amirite?
               List<?> list = (List<?>) obj;
               JSONArray array = new JSONArray();
               
               for(Object objs : list) {
                  // Y0L0?
                 if(objs instanceof String) {
                     array.add((String)objs);
                 } 
                 if(objs instanceof Integer) {
                     array.add((Integer)objs);
                 }
                 if(objs instanceof Double) {
                     array.add((Double)objs);
                 }
                 if(objs instanceof Float) {
                     array.add((Float) objs);
                 }
                 if(objs instanceof Short) {
                     array.add((Short) objs);
                 }
                 if(objs instanceof ItemStack) {
                     // TODO: Serialize ItemStack
                     continue;
                 }
                 if(objs instanceof Entity) {
                     // TODO: Serialize Entity
                     continue;
                 }
                 
               }
               JSON_OBJECT.put(key,array);
                  
           }
           if(obj instanceof Integer) {
               JSON_OBJECT.put(key,(Integer) obj);
           }
           
           if(obj instanceof Double) {
               JSON_OBJECT.put(key, (Double) obj);
           }
           
           if(obj instanceof String) {
               JSON_OBJECT.put(key, (String) obj);
           }
       }
     try {
      FileWriter writer = new FileWriter(f);
      
      JSON_OBJECT.writeJSONString(writer);
      
      writer.flush();
      
      writer.close();
      
     } catch(Exception ex) {
         ex.printStackTrace();
     }
   }
}

