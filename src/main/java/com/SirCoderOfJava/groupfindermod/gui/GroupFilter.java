package com.SirCoderOfJava.groupfindermod.gui;

import com.SirCoderOfJava.groupfindermod.gfserver.GroupInfoParser;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroupFilter {



    private HashMap<FILTER_TYPES, String> textFilters;

    private GroupInfoParser parser;

    public GroupFilter() {
        textFilters = new HashMap<FILTER_TYPES, String>();
        parser = new GroupInfoParser();
    }

    public void addTextFilter(FILTER_TYPES type, String filter) {
        textFilters.put(type, filter);
        //System.out.println("adding a text filter");
    }

    public void clearTextFilters() {
        textFilters.clear();
    }

    public ArrayList<JsonObject> filter(ArrayList<JsonObject> groups) {
        ArrayList<JsonObject> outlist = new ArrayList<JsonObject>();
        for(JsonObject group : groups) {
            boolean passed = true;
            //System.out.println("num of filters: " + textFilters.entrySet().size());
            for(Map.Entry<FILTER_TYPES, String> filter : textFilters.entrySet()) {
                passed = passed && groupPassesTextFilter(filter, group);
            }
            if(passed) {
                outlist.add(group);
            }
        }
        return outlist;
    }

    private boolean groupPassesTextFilter(Map.Entry<FILTER_TYPES, String> filter, JsonObject group) {
        boolean passes = false;
        switch (filter.getKey()) {
            case ANY:
                for(String property : parser.getAllStringProperties(group)) {
                    passes = property.contains(filter.getValue()) || passes;
                }
                break;
            case NAME:
                passes = parser.getName(group).contains(filter.getValue());
                //Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("group [" + parser.getName(group) + "] passed name filter [" + filter.getValue() + "]"));
                break;
            case CATEGORY:
                passes = parser.getCategory(group).contains(filter.getValue());
                break;
            case SUBCATEGORY:
                passes = parser.getSubCategory(group).contains(filter.getValue());
                break;
            case CREATOR:
                passes = parser.getCreator(group).contains(filter.getValue());
                break;
            case MEMBER_NAME:
                ArrayList<String> members = parser.getMembers(group);
                for(String member : members) {
                    passes = member.contains(filter.getValue()) || passes;
                }
        }
        return passes;
    }

    public enum FILTER_TYPES {
        ANY, NAME, CATEGORY, SUBCATEGORY, CREATOR, MEMBER_NAME
    }
}
