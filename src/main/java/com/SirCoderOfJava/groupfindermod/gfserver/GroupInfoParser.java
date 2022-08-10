package com.SirCoderOfJava.groupfindermod.gfserver;

import com.google.gson.JsonObject;

public class GroupInfoParser {
    private JsonObject object;

    public GroupInfoParser(JsonObject in) {
        object = in;
    }




    public JsonObject getObject() {
        return object;
    }

    public void setObject(JsonObject newObject) {
        object = newObject;
    }

}
