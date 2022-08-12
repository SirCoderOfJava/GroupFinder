package com.SirCoderOfJava.groupfindermod.gfserver;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GFHttpRequestHandler {

    private static final String baseURL = "http://ec2-54-196-164-144.compute-1.amazonaws.com:5000";
    private String playerName;

    public GFHttpRequestHandler(String playerName) {
        this.playerName = playerName;
    }

    //Known issue: this crashes the game when the server is down
    public JsonObject getGroups() throws IOException {

        Gson gson = new Gson();

        URL url = new URL(baseURL + "/grouplist");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");


        StringBuilder responseString = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                responseString.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("[GroupFinder] IOException while reading from /grouplist endpoint"));
        }

        return gson.fromJson(responseString.toString(), JsonObject.class);

    }

}
