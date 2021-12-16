package com.example.desktop;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http {

    static String query = "http://localhost:8080/bpro/admin";

    static HttpURLConnection connection = null;



    public static void GetShowUsers() throws IOException, ParseException {
        connection = (HttpURLConnection) new URL(query).openConnection();
        connection.setRequestMethod("GET");
        connection.setUseCaches(false);
        connection.setConnectTimeout(250);
        connection.setReadTimeout(250);
        connection.connect();
        if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while (true) {
                try {
                    if (!((line = in.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sb.append(line);
                System.out.println(sb.toString());
            }
        }
    }
}
