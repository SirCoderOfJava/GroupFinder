package com.SirCoderOfJava.groupfindermod.gfserver;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class GroupInfoParser {

    public ArrayList<JsonObject> parseGroupList(JsonObject groups) {
        ArrayList<JsonObject> outList = new ArrayList<JsonObject>();
        for(JsonElement element : groups.getAsJsonArray("groups")) {
            if(!element.getAsJsonObject().getAsJsonPrimitive("name").getAsString().equalsIgnoreCase("00000000")) {
                outList.add(element.getAsJsonObject());
            }
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

    public ArrayList<String> getAllStringProperties(JsonObject group) {
        ArrayList<String> outlist = new ArrayList<String>();
        if(getName(group) != null) outlist.add(getName(group));
        if(getMembers(group) != null) outlist.addAll(getMembers(group));
        if(getSubCategory(group) != null) outlist.add(getSubCategory(group));
        if(getCategory(group) != null) outlist.add(getCategory(group));
        if(getCreator(group) != null) outlist.add(getCreator(group));

        return outlist;
    }

}
