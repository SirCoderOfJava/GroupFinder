package com.SirCoderOfJava.groupfindermod.gfserver;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class GroupInfoParser {

    /**
     * This method parses out the groups from the {@code /grouplist} server endpoint into an {@link ArrayList} containing all groups the endpoint returned.
     * @param groups {@link JsonObject} containing raw JSON data from the {@code /grouplist} server endpoint
     * @return {@link ArrayList<JsonObject>} containing all the groups
     */
    public ArrayList<JsonObject> parseGroupList(JsonObject groups) {
        //blank arraylist to store the output
        ArrayList<JsonObject> outList = new ArrayList<JsonObject>();

        //the endpoint returns a JsonObject with a "groups" property paired to an array with all the groups
        for(JsonElement element : groups.getAsJsonArray("groups")) {
            //There is always a group with a specific name saved in the server to prevent issues - this if statement filters it out
            if(!element.getAsJsonObject().getAsJsonPrimitive("name").getAsString().equalsIgnoreCase("00000000")) {
                outList.add(element.getAsJsonObject());
            }
        }
        return outList;
    }

    //The following methods all operate on a group object from the array returned by parseGroupList
    //They all extract certain properties from the group object
    //If the group doesn't have that property, they return null

    /**
     * @param group {@link JsonObject} representing the group
     * @return the {@link String} associated with the {@code creator} property of the group
     */
    public String getCreator(JsonObject group) {
        if(!group.has("creator")) return null;
        return group.getAsJsonPrimitive("creator").getAsString();
    }

    /**
     * @param group {@link JsonObject} representing the group
     * @return the {@link String} associated with the {@code category} property of the group
     */
    public String getCategory(JsonObject group) {
        if(!group.has("category")) return null;
        return group.getAsJsonPrimitive("category").getAsString();
    }

    /**
     * @param group {@link JsonObject} representing the group
     * @return the {@link String} associated with the {@code subcategory} property of the group
     */
    public String getSubCategory(JsonObject group) {
        if(!group.has("subcategory")) return null;
        return group.getAsJsonPrimitive("subcategory").getAsString();
    }

    /**
     * @param group {@link JsonObject} representing the group
     * @return an {@link ArrayList<String>} containing all the members associated with the {@code members} property of the group
     */
    public ArrayList<String> getMembers(JsonObject group) {
        if(!group.has("members")) return null;
        ArrayList<String> outList = new ArrayList<String>();
        for(JsonElement element : group.getAsJsonArray("members")) {
            outList.add(element.getAsString());
        }
        return outList;
    }

    /**
     * @param group {@link JsonObject} representing the group
     * @return the {@link String} associated with the {@code name} property of the group
     */
    public String getName(JsonObject group) {
        if(!group.has("name")) return null;
        return group.getAsJsonPrimitive("name").getAsString();
    }

    /**
     * This method is used by the ANY filter. It returns all the properties so that they can be easily indexed by a filter.
     * @param group {@link JsonObject} representing the group
     * @return an {@link ArrayList} containing all the possible properties associated with the group
     */
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
