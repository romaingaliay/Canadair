package com.knadr.util;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Find {

    public static int findKeyArray(JSONArray settingsJson, String key) {
        try {
            for (int i = 0; i < settingsJson.length(); i++)
                if (settingsJson.get(i).getClass().getSimpleName().equals("JSONObject"))
                    if (settingsJson.getJSONObject(i).names().get(0).equals(key))
                        return i;

            return -1;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int findPositionSkins(ArrayList<String> list, String prefix) {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).startsWith(prefix))
                return i;

        return -1;
    }

    public static int findIndexRes(ArrayList<Resolution> list, String resolution) {
        for (int i=0; i < list.size(); i++)
            if (list.get(i).toString().equals(resolution))
                return i;

        return -1;
    }

    public static int findIndexLang(ArrayList<Language> list, String language) {
        for (int i=0; i < list.size(); i++)
            if (list.get(i).toString().equals(language))
                return i;

        return -1;
    }
}
