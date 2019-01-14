<%-- 
    Document   : mymp3
    Created on : 28 Οκτ 2018, 8:41:57 μμ
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

    <body class="text-center">

        <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
            <header class="masthead mb-auto">
                <div class="inner">
                    <nav class="nav nav-pills justify-content-between">
                        <a class="nav-link " href="home.jsp">Home</a>
                        <a class="nav-link active" href="MyMP3Servlet">My MP3s</a>
                        <a class="nav-link" href="MyMP3LyricsServlet">Lyrics</a>
                        <input class="nav-link" type="search" placeholder="Search" aria-label="Search">
                        <button class="nav-link btn btn-outline-primary my-2 my-sm-0" type="submit">Search</button>

                    </nav>
                </div>
            </header>

            <main role="main" class="inner cover">
                <h1 class="cover-heading">My MP3 List.</h1>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Album</th>
                            <th scope="col">Title</th>
                            <th scope="col">Artist</th>
                            <th scope="col">Year</th>
                            <th scope="col">Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% ArrayList<MP3Data> array = (ArrayList<MP3Data>) (request.getAttribute("mymp3s"));
                            for (MP3Data mp3a : array) {
                        %>
                        <tr>
                            <th scope="row"><%= mp3a.getId()%></th>
                            <td><%= mp3a.getAlbum()%></td>
                            <td><%= mp3a.getTitle()%></td>
                            <td><%= mp3a.getArtist()%></td>
                            <td><%= mp3a.getYear()%></td>
                            <td><a href="MP3Delete?artist=<%= mp3a.getArtist()%>&title=<%= mp3a.getTitle()%>">Delete</a></td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>

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
