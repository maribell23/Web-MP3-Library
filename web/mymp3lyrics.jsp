<%-- 
    Document   : mymp3lyrics
    Created on : 30 Οκτ 2018, 2:40:21 μμ
    Author     : Bella
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.MP3Data"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../../../favicon.ico">

        <title>MP3 Library</title>

        <!-- Bootstrap core CSS -->
        <link href="https://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="cover.css" rel="stylesheet">
        <script type="text/javascript" language="javascript">
            function checkfile(sender) {
                var validExts = ".mp3";
                var fileExt = sender.value;
                fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
                if (validExts !== fileExt) {
                    alert("Invalid file selected, valid files are of " +
                            validExts + " types.");
                    sender.value = "";
                    return false;
                } else
                    return true;
            }

        </script>

    </head>

    <body class="text-left">

        <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
            <header class="masthead mb-auto">
                <div class="inner">
                    <nav class="nav nav-pills justify-content-between">
                        <a class=" nav-link" href="home.jsp" >Home</a>
                        <a class="nav-link" href="MyMP3Servlet"  >My MP3s</a>
                        <a class="nav-link active" href="MyMP3LyricsServlet" >Lyrics</a>
                        <input class="nav-link" type="search" placeholder="Search" aria-label="Search">
                        <button class="nav-link btn btn-outline-primary my-2 my-sm-0" type="submit">Search</button>

                    </nav>
                </div>
            </header>

            <main role="main" class="inner cover">
                <h1 class="cover-heading">My MP3 Lyrics</h1>
                <div class="container">

                    <div>
                        <% ArrayList<MP3Data> array = (ArrayList<MP3Data>) (request.getAttribute("mylyrics"));
                            for (MP3Data mp3a : array) {
                        %> 

                        <div class="row">
                            <div class="col-md-3">

                                <img class=" img-fluid rounded " src="<%= mp3a.getImage()%> ">

                            </div>
                            <div class="col-md-9">
                                <div>
                                    <h3><%= mp3a.getArtist() + "\t" + mp3a.getTitle()%></h3>
                                </div>
                                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#lyrics" aria-expanded="false" aria-controls="collapseExample" >
                                    View Lyrics
                                </button> 
                                <div class="collapse" id="lyrics" >
                                    <div class="card card-body text-primary">
                                        <%= mp3a.getLyrics()%>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <%  }%>

                        </main>

                        <footer class="mastfoot mt-auto">
                            <div class="inner">
                                <p>&copy; MB 2018-2019.</p>
                            </div>
                        </footer>
                    </div>


                    <!-- Bootstrap core JavaScript
                      ================================================== -->
                    <!-- Placed at the end of the document so the pages load faster -->
                    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                    crossorigin="anonymous"></script>
                    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
                    <script src="https://getbootstrap.com/docs/4.1/assets/js/vendor/popper.min.js"></script>
                    <script src="https://getbootstrap.com/docs/4.1/dist/js/bootstrap.min.js"></script>
                    </body>

                    </html>