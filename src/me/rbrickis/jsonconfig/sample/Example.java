package me.rbrickis.jsonconfig.sample;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.rbrickis.jsonconfig.JSONConfig;

public class Example {

    public static void main(String[] args) {
        File f = new File("./example.json");
        
        // Creates a new JSONConfig
        JSONConfig config = new JSONConfig(f);
        
        config.set("example", "this is a example!");
        
        config.save();
        try {
         String string = config.get("name");
         System.out.println(string);
        } catch (NullPointerException | ClassCastException | IOException ex) {
            Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
