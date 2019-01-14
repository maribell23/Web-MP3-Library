/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.MP3DAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.MP3Data;
import utility.Helper;

/**
 *
 * @author Bella
 */
@WebServlet(name = "MP3Servlet", urlPatterns = {"/MP3Servlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 20, maxRequestSize = 1024 * 1024 * 20 * 20)
public class MP3Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        final Part filepart = request.getPart("myfile");
        MP3Data mp3 = new MP3Data();
        MP3DAO mp3dao = new MP3DAO();
        mp3 = mp3dao.insertMP3(filepart);

        String img_response = new String();
        String myString = null;
        String lyrics = Helper.lyricsService(mp3);

        if (lyrics == null) {
            request.setAttribute("message", "No lyrics found");
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        } else {
            myString = Helper.imageService(mp3);
            System.out.println(mp3.getTitle());
            String db_lyrics = lyrics.replaceAll("'", " ");
            mp3dao.insertLyricsImg(db_lyrics, myString, mp3);
            request.setAttribute("image", myString);
            request.setAttribute("lyrics", lyrics);
            request.setAttribute("mymp3", mp3);
            RequestDispatcher rd = request.getRequestDispatcher("lyrics.jsp");
            rd.forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
