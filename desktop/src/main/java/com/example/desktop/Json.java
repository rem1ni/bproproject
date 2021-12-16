package com.example.desktop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.apache.http.Header;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import org.apache.http.client.methods.HttpPost;
public class Json {
    public static String parseUrl(URL url) {
        if (url == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        // открываем соедиение к указанному URL
        // помощью конструкции try-with-resources
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {

            String inputLine;
            // построчно считываем результат в объект StringBuilder
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
                System.out.println(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    // парсим некоторые данные о погоде
    public static ObservableList<User> ShowUsers(String resultJson) {
        ObservableList<User> users= FXCollections.observableArrayList();
        try {
            // конвертируем строку с Json в JSONObject для дальнейшего его парсинга
            JSONParser parser=new JSONParser();
            Object jsonObj=parser.parse(resultJson);
            JSONArray JsonArray = (JSONArray) jsonObj;
            for (int i=0;i<JsonArray.size();i++) {
                JSONObject data=(JSONObject) JsonArray.get(i);
                Object contract = data.get("contract");
                JSONObject contractArray=(JSONObject) contract;
                Long id = (Long) ((JSONObject) JsonArray.get(i)).get("id");
                String username=(String) ((JSONObject) JsonArray.get(i)).get("username");
                Double minutes=(Double) ((JSONObject) JsonArray.get(i)).get("minutes");
                Double account=(Double) ((JSONObject) JsonArray.get(i)).get("account");
                String nametariff= (String) contractArray.get("name");
                Double pay= (Double) contractArray.get("sum");
                User user=new User(id,username,minutes,account,nametariff,pay);
                users.add(user);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return users;
    }
    public static void MinutesUser(Long id,Long minutes) throws IOException {
        // для простоты примера просто хардкодим нужные данные в методе
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("iduser", id);
        jsonObject.put("min", minutes);
        String json = String.valueOf(jsonObject);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8080/bpro/minutes");
        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        System.out.println(json);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        }
    }
    public static void RolesUser(Long id,Integer check1,Integer check2) throws IOException {
        // для простоты примера просто хардкодим нужные данные в методе
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("iduser", id);
        jsonObject.put("check1", check1);
        jsonObject.put("check2", check2);
        String json = String.valueOf(jsonObject);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8080/bpro/role");
        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        System.out.println(json);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        }
    }
    public static void DeleteUser(Long id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("iduser", id);
        String json= String.valueOf(jsonObject);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post=new HttpPost("http://localhost:8080/bpro/delete");
        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
         System.out.println(json);
         try(CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response=httpClient.execute(post)){
             System.out.println(EntityUtils.toString(response.getEntity()));
         }


    }

    // создаем объект URL из указанной в параметре строки
    public static URL createUrl(String link) {
        try {
            return new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
