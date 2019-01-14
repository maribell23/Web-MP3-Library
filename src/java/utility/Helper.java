/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import model.MP3Data;
import org.json.JSONObject;

/**
 *
 * @author Bella
 */
public class Helper {

    public static String lyricsService(MP3Data mp3) {
        String title = mp3.getTitle().replaceAll(" ", "%20");
        String artist = mp3.getArtist().replaceAll(" ", "%20");

        URL url;
        String lyrics = null;
        try {
            url = new URL("https://api.lyrics.ovh/v1/" + artist + "/" + title);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                return null;
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String response1 = new String();
                for (String line; (line = br.readLine()) != null; response1 += line);

                JSONObject obj = new JSONObject(response1);
                lyrics = obj.getString("lyrics");

            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lyrics;
    }

    public static String imageService(MP3Data mp3) {
        String img = mp3.getArtist();
        String img_url = "https://album-art-o2s77e5c7ryz.runkit.sh/?search=" + img;
        URL url1;
        String myString = null;
        String img_response = new String();
        try {
            url1 = new URL(img_url);

            HttpURLConnection con1 = (HttpURLConnection) url1.openConnection();
            con1.setRequestMethod("GET");
            con1.setRequestProperty("Accept", "application/json");
            if (con1.getResponseCode() != 200) {
                return null;
            } else {
                BufferedReader br1 = new BufferedReader(new InputStreamReader(con1.getInputStream()));

                for (String line; (line = br1.readLine()) != null; img_response += line);

                myString = img_response.substring(1, img_response.length() - 1);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myString;

    }
}
