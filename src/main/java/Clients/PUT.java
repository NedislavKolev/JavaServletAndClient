package Clients;

import Config.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Client side
 * Sendin PUT request
 */
public class PUT {

    public static void main(String[] args) throws IOException {
        PUT put = new PUT();
        put.postMethod();
    }

    /**
     * Sending PUT request
     * @throws IOException
     */
    public void postMethod() throws IOException {
        //Set the URL and open the connection
        URL  url = new URL(Config.getInstance().getURL());
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("PUT");
        urlConnection.setDoOutput(true);

        //Set request property content-type(as we choose what type we will use)
        urlConnection.setRequestProperty("Content-Type","application/xml");
        urlConnection.setRequestProperty("Accept","application/xml");

        OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream());

        FileInputStream fis = new FileInputStream(Config.getInstance().getClient_file());

        // reads a byte at a time, if it reached end of the file, returns -1
        int content;
        while ((content = fis.read()) != -1) {
            osw.write(content);
            osw.flush();
        }
        System.out.println("Successfully sent");
        System.out.println("HTTP Response Code:");
        //HTTP response code
        System.err.println(urlConnection.getResponseCode());
        //close the connection
        osw.close();
    }
}

