package com.knadr.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SkinSelected {

    public static String skinCanadairSelected() {
        try {
            String adresseSettings = new String(Files.readAllBytes(Paths.get("res" + File.separator + "fileGame" + File.separator + "settings.json")));
            JSONObject skinsJson = new JSONObject(adresseSettings).getJSONObject("skin");
            StringBuilder url = new StringBuilder();
            int skinSelected = -1;

            for (int i=0; i < skinsJson.getJSONArray("canadair").length(); i++)
                if (skinsJson.getJSONArray("canadair").getJSONObject(i).getBoolean("selected"))
                    skinSelected = i;

            for (int i=0; i < skinsJson.getJSONArray("canadair").getJSONObject(skinSelected).getJSONArray("url").length(); i++) {
                if (i != skinsJson.getJSONArray("canadair").getJSONObject(skinSelected).getJSONArray("url").length() - 1)
                    url.append(skinsJson.getJSONArray("canadair").getJSONObject(skinSelected).getJSONArray("url").getString(i)).append(File.separator);
                else
                    url.append(skinsJson.getJSONArray("canadair").getJSONObject(skinSelected).getJSONArray("url").getString(i));
            }

            return url.toString();
        }
        catch (JSONException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String skinMapMapSelected() {
        return null;
    }

    public static String skinMapBackgroundSelected() {
        return null;
    }

    public static String skinMapBackground2Selected() {
        return null;
    }

    public static String skinMapBackground3Selected() {
        return null;
    }
}
