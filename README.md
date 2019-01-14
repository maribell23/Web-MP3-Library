# Web MP3 Library

Web App in which an mp3 file can be uploaded, finding song's lyrics through its tags and image of album through web service using the following technologies: Java, Servlets, MYSQL Database, Tomcat, Bootstrap, JS

The first page of this app is Home in which users upload an mp3 file. Using a library, the mp3 tags(eg Title, Artist, Album, Year) are found. Then, using a web service lyrics and the URL's image of the song are found. If song's lyrics exist, the lyrics will be displayed in the next page. If song's lyrics don't exist, a page is displayed with a message that informs the user that "No Lyrics found". 

All mp3s are stored in a database as BLOB. The DB table also consists of: unique ID, Filename, MP3, Album, Title, Artist, Year, Lyrics, Image. 

Navbar, also includes my MP3s page in which is displayed a table with the saved mp3s. There is the option to delete a song from the library.The last page of this app is Lyrics in which is displayed the song with his album image and the lyrics that were found.  
