package Clients;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PUT {

    public static void main(String[] args) throws IOException {

        postMethod();
    }

    public static void postMethod() throws IOException {
        URL  url = new URL("http://localhost:8090/servlet");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("PUT");
        urlConnection.setDoOutput(true);

        //Set request property content-type(as we choose what type we will use)
        urlConnection.setRequestProperty("Content-Type","application/xml");
        urlConnection.setRequestProperty("Accept","application/xml");

        OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream());

        FileInputStream fis = new FileInputStream("D:\\Java\\JavaServletAndClient\\src\\main\\java\\Files\\clientfile.xml");

        // reads a byte at a time, if it reached end of the file, returns -1
        int content;
        while ((content = fis.read()) != -1) {
            osw.write(content);
            osw.flush();
        }
        System.out.println("Successfully sent");
        System.out.println("HTTP Response Code:");
        System.err.println(urlConnection.getResponseCode());
        //close the connection
        osw.close();




    }
}

