package com.SirCoderOfJava.groupfindermod.gfserver;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class GroupInfoParser {

    public ArrayList<JsonObject> parseGroupList(JsonObject groups) {
        ArrayList<JsonObject> outList = new ArrayList<JsonObject>();
        for(JsonElement element : groups.getAsJsonArray("groups")) {
            outList.add(element.getAsJsonObject());
        }
        return outList;
    }

    public String getCreator(JsonObject group) {
        if(!group.has("creator")) return null;
        return group.getAsJsonPrimitive("creator").getAsString();
    }

    public String getCategory(JsonObject group) {
        if(!group.has("category")) return null;
        return group.getAsJsonPrimitive("category").getAsString();
    }

    public String getSubCategory(JsonObject group) {
        if(!group.has("subcategory")) return null;
        return group.getAsJsonPrimitive("subcategory").getAsString();
    }

    public ArrayList<String> getMembers(JsonObject group) {
        if(!group.has("members")) return null;
        ArrayList<String> outList = new ArrayList<String>();
        for(JsonElement element : group.getAsJsonArray("members")) {
            outList.add(element.getAsString());
        }
        return outList;
    }

    public String getName(JsonObject group) {
        if(!group.has("name")) return null;
        return group.getAsJsonPrimitive("name").getAsString();
    }

}
