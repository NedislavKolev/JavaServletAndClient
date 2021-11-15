package Clients;

import Config.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Client Side
 * Sending GET request
 */
public class GET {

    public static void main(String[] args) throws IOException {
        GET get = new GET();
        get.getMethod();
    }

    public void getMethod() throws IOException {
        //Set the URI
        URL url = new URL(Config.getInstance().getURL());
        //Open a connection
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        //Set the request method to GET,POST,HEAD,PUT,DELETE
        urlConnection.setRequestMethod(Config.getInstance().getGET());
        urlConnection.setRequestProperty("Content-type", Config.getInstance().getContent_Type());

        //Read and print the response
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null){
            System.out.println(inputLine);
        }

        System.out.println("HTTP Response Code:");
        //HTTP response code
        System.err.println(urlConnection.getResponseCode());
        in.close();
    }
}