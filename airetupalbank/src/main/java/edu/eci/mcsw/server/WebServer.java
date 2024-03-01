package edu.eci.mcsw.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import edu.eci.mcsw.persistence.DbConnection;

public class WebServer {
    private static final int PORT = 35000;

    /**
     * Defautl Constructor
     */
    public WebServer() {

    }

    /**
     * Method that start the web server
     * 
     * @throws IOException throws IOException if something fails
     */
    public static void startServer() throws IOException {

        DbConnection.sqlExecutor();
        System.out.println("correct search into db: "+ DbConnection.userAuth("proof", "proof"));
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            boolean readingFirst = true;
            String petition = "";

            while ((inputLine = in.readLine()) != null) {

                if (readingFirst) {
                    petition = inputLine.split(" ")[1];
                    readingFirst = false;
                }
                if (!in.ready()) {
                    break;
                }
            }

            System.out.println("Peticion: " + petition);

            outputLine = getPetitionPage(petition, clientSocket.getOutputStream());
            
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();

        }

        serverSocket.close();

    }

    /**
     * method that returns the login page
     * 
     * @return the login page
     */
    private static String getPetitionPage(String pagePetition, OutputStream op) {
        return getOKHeader() + getMimeType(pagePetition) + "\r\n"
                + "\r\n"
                + getStaticFile(pagePetition, op);
    }

    /**
     * Method that identify the MIME type of the files to return to the client
     * 
     * @param filePetition path of the petition
     * @return a String with the MIME type of the file
     */
    private static String getMimeType(String filePetition) {
        if (filePetition.endsWith(".html") || filePetition.endsWith("/")) {
            return "text/html";
        } else if (filePetition.endsWith(".css")) {
            return "text/css";
        } else if (filePetition.endsWith(".scss")) {
            return "text/x-scss"; // o "text/scss"
        } else if (filePetition.endsWith(".js")) {
            return "application/javascript";
        } else if (filePetition.endsWith(".jpg")) {
            return "image/jpg";
        } else {
            return "text/plain";
        }
    }

    /**
     * returns the static file related with the request
     * 
     * @return string with all information insite the file
     */
    private static String getStaticFile(String file, OutputStream ops) {
        Path path = (file.equals("/"))
                ? Paths.get(getStaticFilesDirectory() + "/login.html")
                : Paths.get(getStaticFilesDirectory() + file);

        try {
            Charset charset = Charset.forName("UTF-8");
            StringBuilder outputLine = new StringBuilder();
            byte[] bytes;

            if (file.endsWith(".jpg")) {
                bytes = getAnImage(file);
                String response = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: image/jpeg\r\n" +
                        "Content-Length: " + bytes.length + "\r\n" +
                        "\r\n";
                ops.write(response.getBytes());
                ops.write(bytes);
            } else {
                try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        outputLine.append(line).append("\n");
                    }
                }
            }

            return outputLine.toString();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return getNotFoundHeader();
        }
    }

    /**
     * Method that return the bytes of an image
     * 
     * @param filePetition the route of the file to return to the browser
     * @return an array of bytes
     */
    private static byte[] getAnImage(String filePetition) {

        Path image = Paths.get(getStaticFilesDirectory() + filePetition);

        try {
            return Files.readAllBytes(image);
        } catch (Exception e) {
            System.out.println("can't send the image: " + e.getMessage());
            ;
        }
        return null;
    }

    /**
     * method that returns the Ok header when a petition was succesful
     * 
     * @return A string which content is the Ok header
     */
    private static String getOKHeader() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: ";
    }

    /**
     * Method that returns the Not Found header
     * 
     * @return a String with not found header
     */
    private static String getNotFoundHeader() {
        return "HTTP/1.1 404 NOT FOUND\r\n"
                + "Content-Type: ";
    }

    public static String getStaticFilesDirectory() {
        return "target/classes/public/static";
    }
}
