package com.SirCoderOfJava.groupfindermod.gui;

import com.SirCoderOfJava.groupfindermod.gfserver.GFHttpRequestHandler;
import com.SirCoderOfJava.groupfindermod.gfserver.GroupInfoParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.SirCoderOfJava.groupfindermod.util.ChatMessageColorizer.replaceCodes;

public class PageHandler {

    public static final int MAX_GROUPS_PER_PAGE = 9;
    ArrayList<JsonObject> groups;
    GroupInfoParser parser;
    GFHttpRequestHandler handler;

    /**
     * Each page is represented as an {@link ArrayList} of {@link JsonObject}s. This object stores an {@link ArrayList} of these.
     */
    ArrayList<ArrayList<JsonObject>> pages;

    GroupFilter groupFilter;

    private static final String NO_GROUPS_JSON = "{\"name\": \"No Groups Found\"}";

    public PageHandler() {
        groups = new ArrayList<JsonObject>();
        pages = new ArrayList<ArrayList<JsonObject>>();
        parser = new GroupInfoParser();
        handler = new GFHttpRequestHandler(Minecraft.getMinecraft().thePlayer.getName());
        groupFilter = new GroupFilter();

        update();
    }

    /**
     * Method to update all the pages and group data
     */
    public void update() {
        //Update groups with call to server
        try {
            groups = parser.parseGroupList(handler.getGroups());
        } catch (Exception e) {
            e.printStackTrace();
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("houston we have a problem"));
        }

        //apply filter rules
        groups = groupFilter.filter(groups);

        //if there are no groups in the list after filtering, replace it with a special group that tells the user that there are no groups
        if(groups.size() == 0) {
            Gson gson = new Gson();
            groups = new ArrayList<JsonObject>();
            groups.add(gson.fromJson(NO_GROUPS_JSON, JsonObject.class));
        }

        //update pages

        //reset the lsit of pages
        pages.clear();

        //initialize iterator to pass through all the groups
        Iterator<JsonObject> groupIterator = groups.iterator();

        //Use a method to determine how many pages there should be
        int numPages = getNumberOfPages();
        for (int i = 0; i < numPages; i++) {
            //For every page there is supposed to be, create a new ArrayList to represent a page
            ArrayList<JsonObject> page = new ArrayList<JsonObject>();
            //Add groups to the page until either the maximum groups per page has been reached or there are no more groups to add
            for (int j = 0; (j < MAX_GROUPS_PER_PAGE && groupIterator.hasNext()); j++) {
                page.add(groupIterator.next());
            }
            //After a page has been populated, add it to the array of pages
            pages.add(page);
        }

    }

    /**
     * Method to extract information from a group and convert it into display lines
     * @param group {@link JsonObject} containing the data for a group
     * @return {@link List} of {@link String}s to display for the group
     */
    public List<String> getDisplayLinesFromGroup(JsonObject group) {
        //Initialize empty output list
        List<String> lines = new ArrayList<String>();

        //If the indicator for no groups existing is found, return a list that displays a "No Groups Found" preview
        if(parser.getName(group).equalsIgnoreCase("No Groups Found")) {
            lines.add(replaceCodes("&cNo Groups Found"));
            return lines;
        }

        //get all the properties of the group and add then to the display lines
        String name = replaceCodes("Group Name: &c") + parser.getName(group);
        String creator = replaceCodes("Group Leader: &c") + parser.getCreator(group);
        String category = replaceCodes("Category: &c") + parser.getCategory(group);
        String subcategory = replaceCodes("Subcategory: &c") + parser.getSubCategory(group);
        ArrayList<String> members = parser.getMembers(group);
        lines.add(name);
        lines.add(creator);
        lines.add(category);
        lines.add(subcategory);
        if (members != null) {
            lines.add(replaceCodes("Members: &c" + members.size()));
            for (String member : members) {
                lines.add(replaceCodes(" - &c ") + member);
            }
        }
        return lines;
    }

    public ArrayList<ArrayList<JsonObject>> getPages() {
        return pages;
    }

    /**
     * Utility function to get the longest {@link String} in a {@link List} of {@link String}s
     * @param lines {@link List} of {@link String}s to pull the longest from
     * @return the longest {@link String} from the lines
     */
    public String getLongestLineInGroupLines(List<String> lines) {
        String out = "";
        for (String line : lines) {
            if (line.length() > out.length()) out = line;
        }
        return out;
    }

    /**
     * Adds a text filter to the {@code groupFilter} object
     * @param filterType one of {@link com.SirCoderOfJava.groupfindermod.gui.GroupFilter.FILTER_TYPES}
     * @param value the {@link String} to filter by
     */
    public void addTextFilter(GroupFilter.FILTER_TYPES filterType, String value) {
        groupFilter.addTextFilter(filterType, value);
    }

    /**
     * @return the number of pages needed to hold all the groups based on the size of the {@code groups} object
     */
    public int getNumberOfPages() {
        return (int) Math.ceil((float) groups.size() / (float) MAX_GROUPS_PER_PAGE);
    }


}
