/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedMansionApp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Crunchify.com How to write JSON object to File in Java?
 */
public class JsonWrite {

    private static FileWriter file;

    public JsonWrite() {
    }

    
    @SuppressWarnings("unchecked")
    public String writeJson(String username, String nome, String vida) {

        // JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
        JSONObject obj_username = new JSONObject();
        obj_username.put(username,vida);
        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter(nome+".txt",true);
            
            file.append(obj_username.toJSONString());
            Log("Successfully Copied JSON Object to File...");
            Log("\nJSON Object: " + obj_username);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return "Sucess write";
    }

    static public void Log(String str) {
        System.out.println("str");
    }

}
