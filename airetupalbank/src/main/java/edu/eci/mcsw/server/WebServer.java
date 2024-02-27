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

            System.out.println("Peticion: "+petition);

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
     * @return the login page
     */
    private static String getPetitionPage(String pagePetition, OutputStream op) {
        return getOKHeader() + getMimeType(pagePetition) +"\r\n"
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
        return (filePetition.endsWith(".html") || filePetition.endsWith("/")) ? "text/html"
                : ((filePetition.endsWith(".css")) ? "text/css"
                        : (filePetition.endsWith(".js")) ? "application/javascript"
                                : (filePetition.endsWith(".jpg")) ? "image/jpg" : "text/plain");
    }


    /**
     * this mehtod returns the static file related with the request
     * 
     * @param filePetition path of the file
     * @param op           OutputStream to return an image if is necessary
     * @return A string with all information insite the file
     */
    private static String getStaticFile(String filePetition, OutputStream op) {
        Path file = (filePetition.equals("/")) ? Paths.get("target/classes/public/static/Login.html")
                : (Paths.get("target/classes/public/static" + filePetition));

        // System.out.println(filePetition);
        Charset charset = Charset.forName("ISO_8859_1");
        StringBuilder outputLine = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;

            while ((line = reader.readLine()) != null) {
                if (filePetition.contains(".jpg")) {
                    byte[] imageBytes = getAnImage(filePetition);
                    String response = getOKHeader() + getMimeType(filePetition) + "\r\n" +
                            "Content-Length: " + imageBytes.length + "\r\n" +
                            "\r\n";
                    op.write(response.getBytes());
                    op.write(imageBytes);
                }
                outputLine.append(line).append("\n");

            }
        } catch (Exception e) {
            System.err.format(e.getMessage(), e);
            // e.printStackTrace();
        }

        return outputLine.toString();
    }


    /**
     * Method that return the bytes of an image
     * 
     * @param filePetition the route of the file to return to the browser
     * @return an array of bytes
     */
    private static byte[] getAnImage(String filePetition) {

        Path image = Paths.get("target/classes/public/static" + filePetition);

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
}
