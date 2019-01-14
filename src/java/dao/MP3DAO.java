/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import control.MP3Servlet;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
import model.MP3Data;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.ID3v1_1;
import utility.Connectivity;

/**
 *
 * @author Bella
 */
public class MP3DAO {

    public ArrayList<MP3Data> getMP3() {
        ArrayList<MP3Data> mp3list = new ArrayList<MP3Data>();
        Connection c = Connectivity.getConnect();
        String sql = "SELECT id,album, title, artist, year from mp3_tbl group by title";

        MP3Data mp3 = null;
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                mp3 = new MP3Data(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                mp3.setId(rs.getInt(1));
                mp3list.add(mp3);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MP3DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mp3list;
    }

    public MP3Data insertMP3(Part filepart) {
        Connection c = Connectivity.getConnect();
        final String filename = filepart.getSubmittedFileName();
        MP3Data md = null;
        try {
            filepart.write("C:\\Users\\Bella\\Documents\\myuploads\\" + filename);

            File f = new File("C:\\Users\\Bella\\Documents\\myuploads\\" + filename);
            RandomAccessFile raf = new RandomAccessFile(f, "r");
            ID3v1_1 tag = new ID3v1_1(raf);

            String sql = "INSERT INTO mp3_tbl (filename,mp3,album,title,year,artist) VALUES (?,?,?,?,?,?)";
            int i = 0;
            md = new MP3Data(tag.getAlbumTitle(), tag.getSongTitle(), tag.getLeadArtist(), tag.getYear());

            Statement st = c.createStatement();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, filename);
            ps.setBlob(2, filepart.getInputStream());
            ps.setString(3, md.getAlbum());
            ps.setString(4, md.getTitle());
            ps.setString(5, md.getYear());
            ps.setString(6, md.getArtist());
            ps.executeUpdate();
        } catch (TagException ex) {
            Logger.getLogger(MP3Servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MP3DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MP3Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return md;
    }

    public void insertLyricsImg(String lyrics, String img, MP3Data mp3) {
        Connection c = Connectivity.getConnect();
        String sql = "UPDATE mp3_tbl set lyrics = '" + lyrics + "', image = '" + img + "' WHERE title = '" + mp3.getTitle() + "'";
        try {
            Statement st = c.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MP3DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<MP3Data> getLyricsImg() {
        ArrayList<MP3Data> mp3list = new ArrayList<MP3Data>();

        Connection c = Connectivity.getConnect();
        String sql = "select title,artist,lyrics,image from mp3_tbl group by title;";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                MP3Data mp3 = new MP3Data();
                mp3.setTitle(rs.getString(1));
                mp3.setArtist(rs.getString(2));
                mp3.setLyrics(rs.getString(3));
                mp3.setImage(rs.getString(4));
                mp3list.add(mp3);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MP3DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mp3list;
    }

    public void deleteMP3(String artist, String title) {
        Connection c = Connectivity.getConnect();

        String sql = "delete from mp3_tbl where artist = '" + artist + "' and title = '" + title + "'";
        Statement stmt;
        try {
            stmt = c.createStatement();
            int i = stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(MP3DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
