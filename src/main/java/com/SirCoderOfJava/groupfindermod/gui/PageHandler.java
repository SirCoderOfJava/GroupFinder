package com.SirCoderOfJava.groupfindermod.gui;

import com.SirCoderOfJava.groupfindermod.gfserver.GFHttpRequestHandler;
import com.SirCoderOfJava.groupfindermod.gfserver.GroupInfoParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;

import static com.SirCoderOfJava.groupfindermod.util.ChatMessageColorizer.replaceCodes;

public class PageHandler {

    public static final int MAX_GROUPS_PER_PAGE = 9;
    ArrayList<JsonObject> groups;
    GroupInfoParser parser;
    GFHttpRequestHandler handler;

    ArrayList<ArrayList<JsonObject>> pages;

    GroupFilter groupFilter;

    public static final String NO_GROUPS_JSON = "{\"name\": \"No Groups Found\"}";

    public PageHandler() {
        groups = new ArrayList<JsonObject>();
        pages = new ArrayList<ArrayList<JsonObject>>();
        parser = new GroupInfoParser();
        handler = new GFHttpRequestHandler(Minecraft.getMinecraft().thePlayer.getName());
        groupFilter = new GroupFilter();

        update();
    }

    public void update() {
        //Update groups with call to server
        //groupFilter.addTextFilter(GroupFilter.FILTER_TYPES.ANY, "testplayer");

        groups.clear();
        try {
            groups = parser.parseGroupList(handler.getGroups());
        } catch (Exception e) {
            e.printStackTrace();
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("houston we have a problem"));
        }

        groups = groupFilter.filter(groups);

        if(groups.size() == 0) {
            Gson gson = new Gson();
            groups = new ArrayList<JsonObject>();
            groups.add(gson.fromJson(NO_GROUPS_JSON, JsonObject.class));
        }

        //update pages

        pages.clear();
        int currentGroupListReaderPos = 0;
        int numPages = getNumberOfPages();
        for (int i = 0; i < numPages; i++) {
            ArrayList<JsonObject> page = new ArrayList<JsonObject>();
            for (int j = 0; (j < MAX_GROUPS_PER_PAGE && currentGroupListReaderPos < groups.size()); j++) {
                page.add(groups.get(currentGroupListReaderPos));
                currentGroupListReaderPos++;
            }
            pages.add(page);
        }

    }

    public List<String> getDisplayLinesFromGroup(JsonObject group) {
        List<String> lines = new ArrayList<String>();

        if(parser.getName(group).equalsIgnoreCase("No Groups Found")) {
            lines.add(replaceCodes("&cNo Groups Found"));
            return lines;
        }

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

    public String getLongestLineInGroupLines(List<String> lines) {
        String out = "";
        for (String line : lines) {
            if (line.length() > out.length()) out = line;
        }
        return out;
    }

    public void addTextFilter(GroupFilter.FILTER_TYPES filterType, String value) {
        groupFilter.addTextFilter(filterType, value);
    }

    public int getNumberOfPages() {
        return (int) Math.ceil((float) groups.size() / (float) MAX_GROUPS_PER_PAGE);
    }


}
