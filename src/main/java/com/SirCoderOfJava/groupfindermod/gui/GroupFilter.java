package com.SirCoderOfJava.groupfindermod.gui;

import com.SirCoderOfJava.groupfindermod.gfserver.GroupInfoParser;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class handles filtering out groups based on a set of filter rules
 */
public class GroupFilter {
    /**
     * This object stores text filter rules. Keys are types of filters from {@link FILTER_TYPES} and values are Strings to filter by.
     */
    private HashMap<FILTER_TYPES, String> textFilters;

    private GroupInfoParser parser;

    public GroupFilter() {
        textFilters = new HashMap<FILTER_TYPES, String>();
        parser = new GroupInfoParser();
    }

    public void addTextFilter(FILTER_TYPES type, String filter) {
        textFilters.put(type, filter);
    }

    public void clearTextFilters() {
        textFilters.clear();
    }

    /**
     * This method takes a list of groups and filters it based on the stored filter rules
     * @param groups unfiltered {@link ArrayList} of {@link JsonObject} groups
     * @return filtered {@link ArrayList} of {@link JsonObject} groups
     */
    public ArrayList<JsonObject> filter(ArrayList<JsonObject> groups) {
        //output list
        ArrayList<JsonObject> outlist = new ArrayList<JsonObject>();

        //loop through every group
        for(JsonObject group : groups) {
            //flag for if the group passed the test
            boolean passed = true;

            //loop through all the filters
            for(Map.Entry<FILTER_TYPES, String> filter : textFilters.entrySet()) {
                //if previous tests have passed and the current filter passes, continue to pass
                passed = passed && groupPassesTextFilter(filter, group);
            }

            //if all tests passed, add the group to the output list
            if(passed) {
                outlist.add(group);
            }
        }
        return outlist;
    }

    /**
     * Determines if a group passes a specific filter
     * @param filter a key-value pair that represents a filter
     * @param group the group to check
     * @return boolean flag for whether the group passed the filter
     */
    private boolean groupPassesTextFilter(Map.Entry<FILTER_TYPES, String> filter, JsonObject group) {
        //flag for if the group passes the test
        boolean passes = false;
        switch (filter.getKey()) {
            case ANY:
                //check every property in the group
                for(String property : parser.getAllStringProperties(group)) {
                    //if any other property passed the filter or if the current one does, the group passes
                    passes = property.contains(filter.getValue()) || passes;
                }
                break;
            case NAME:
                //check the name property
                passes = parser.getName(group).contains(filter.getValue());
                break;
            case CATEGORY:
                //check the category property
                passes = parser.getCategory(group).contains(filter.getValue());
                break;
            case SUBCATEGORY:
                //check the subcategory property
                passes = parser.getSubCategory(group).contains(filter.getValue());
                break;
            case CREATOR:
                //check the creator property
                passes = parser.getCreator(group).contains(filter.getValue());
                break;
            case MEMBER_NAME:
                //check all the members with the same strategy as the ANY rule
                ArrayList<String> members = parser.getMembers(group);
                for(String member : members) {
                    passes = member.contains(filter.getValue()) || passes;
                }
        }
        return passes;
    }

    /**
     * All the filter types
     */
    public enum FILTER_TYPES {
        ANY, NAME, CATEGORY, SUBCATEGORY, CREATOR, MEMBER_NAME
    }
}
