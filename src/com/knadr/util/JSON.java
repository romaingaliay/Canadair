package com.knadr.util;

import org.json.JSONArray;
import org.json.JSONException;

public class JSON {

    public static int chercheKeyJSONArray(JSONArray json, String key) {
        try {
            for (int i=0; i < json.length(); i++)
                if (json.get(i).getClass().getSimpleName().equals("JSONObject"))
                    if (json.getJSONObject(i).names().toString().contains(key))
                        return i;

            return -1;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int chercheSkin(JSONArray skin, String skinName) {
        try {
            for (int i=0; i < skin.length(); i++) {
                if (skin.getJSONObject(i).getString("name").equals(skinName))
                    return i;
            }

            return -1;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
