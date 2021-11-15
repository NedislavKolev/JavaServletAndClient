package Config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private final String PUT;
    private final String GET;
    private final String Content_Type;
    private final String file_path;
    private static Config instance = null;
    private final String URL;
    private final String client_file;

    FileInputStream FIS;
    {
        try {
            FIS = new FileInputStream("D:\\Manik\\NedkoServlet\\src\\main\\java\\Config\\config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Properties properties;

    //Loading the config.properties file
    public Config() throws IOException {
        properties = new Properties();
        properties.load(FIS);
        FIS.close();
        PUT = properties.getProperty("PUT");
        GET = properties.getProperty("GET");
        Content_Type = properties.getProperty("Content-Type");
        file_path = properties.getProperty("file_path");
        URL = properties.getProperty("URL");
        client_file = properties.getProperty("client_file");
    }
    //Using Singleton method
    public static Config getInstance() throws IOException {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String getPUT() {
        return PUT;
    }

    public String getGET() {
        return GET;
    }

    public String getContent_Type() {
        return Content_Type;
    }

    public String getFile_path() {
        return file_path;
    }

    public  String getURL(){return URL;}

    public String getClient_file(){return client_file;}
}
