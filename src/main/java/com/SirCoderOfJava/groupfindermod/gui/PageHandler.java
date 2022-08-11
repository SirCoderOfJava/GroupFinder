package com.SirCoderOfJava.groupfindermod.gui;

import com.SirCoderOfJava.groupfindermod.gfserver.GFHttpRequestHandler;
import com.SirCoderOfJava.groupfindermod.gfserver.GroupInfoParser;

import static com.SirCoderOfJava.groupfindermod.util.ChatMessageColorizer.replaceCodes;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;

public class PageHandler {

    ArrayList<JsonObject> groups;
    GroupInfoParser parser;
    GFHttpRequestHandler handler;

    ArrayList<ArrayList<JsonObject>> pages;

    public PageHandler() {
        groups = new ArrayList<JsonObject>();
        pages = new ArrayList<ArrayList<JsonObject>>();
        parser = new GroupInfoParser();
        handler = new GFHttpRequestHandler(Minecraft.getMinecraft().thePlayer.getName());
        update();
    }

    public void update() {
        //Update groups with call to server
        groups.clear();
        try {
            groups = parser.parseGroupList(handler.getGroups());
        } catch (Exception e) {
            e.printStackTrace();
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("houston we have a problem"));
        }

        if(groups == null) {
            groups = new ArrayList<JsonObject>();
        }

        //update pages

        pages.clear();
        int currentGroupListReaderPos = 0;
        int numPages = getNumberOfPages();
        for (int i = 0; i < numPages; i++) {
            ArrayList<JsonObject> page = new ArrayList<JsonObject>();
            for (int j = 0; (j < 9 && currentGroupListReaderPos < groups.size()); j++) {
                page.add(groups.get(currentGroupListReaderPos));
                currentGroupListReaderPos++;
            }
            pages.add(page);
        }

    }

    public List<String> getDisplayLinesFromGroup(JsonObject group) {
        List<String> lines = new ArrayList<String>();

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

    public int getNumberOfPages() {
        return (int) Math.ceil((float) groups.size() / 9F);
    }


}