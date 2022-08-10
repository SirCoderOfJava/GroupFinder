package com.SirCoderOfJava.groupfindermod.gfserver;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GFHttpRequestHandler {
    private String playerName;

    private static final String baseURL = "http://ec2-54-196-164-144.compute-1.amazonaws.com:5000";
    public GFHttpRequestHandler(String playerName) {
        this.playerName = playerName;
    }

    public JsonObject getGroups() throws IOException {
        URL url = new URL(baseURL + "/grouplist");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        Gson gson = new Gson();

        String responseString = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                responseString += inputLine;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return gson.fromJson(responseString, JsonObject.class);
    }

}
