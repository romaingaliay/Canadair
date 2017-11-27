package util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static util.Temps.getDateEpoch;

public class Score {

    public static int CalculScore() {
        //à completer
        return 0;
    }

    private static int CherchePetitScore() {
        int idPetitScore = -1;
        int minScore = 1000000000;

        try {
            String bestScores = new String(Files.readAllBytes(Paths.get("res" + File.separator + "fileGame" + File.separator + "score.json")));
            JSONObject bestScoreJson = new JSONObject(bestScores);

            for (int i = 0; i < bestScoreJson.getJSONObject("Best Score").getInt("nb"); i++)
                if (bestScoreJson.getJSONObject("Best Score").getJSONArray("list").getJSONObject(i).getInt("score") < minScore) {
                    minScore = bestScoreJson.getJSONObject("Best Score").getJSONArray("list").getJSONObject(i).getInt("score");
                    idPetitScore = i;
            }
        }
        catch (JSONException | IOException e) {
            System.out.println("Erreur, impossible de charger le fichier score.json.");
        }

        return idPetitScore;
    }

    private static boolean isBestScore(int score) {
        boolean isBestScore = false;

        try {
            String bestScores = new String(Files.readAllBytes(Paths.get("res" + File.separator + "fileGame" + File.separator + "score.json")));
            JSONObject bestScoreJson = new JSONObject(bestScores);

            for (int i = 0; i < bestScoreJson.getJSONObject("Best Score").getInt("nb"); i++)
                if (bestScoreJson.getJSONObject("Best Score").getJSONArray("list").getJSONObject(i).getInt("score") < score)
                    isBestScore = true;

        }
        catch (JSONException | IOException e) {
            System.out.println("Erreur, impossible de charger le fichier score.json.");
        }

        return isBestScore;
    }

    public static void SaveScore(int score, String pseudo) {
        try {
            String bestScores = new String(Files.readAllBytes(Paths.get("res" + File.separator + "fileGame" + File.separator + "score.json")));
            JSONObject bestScoreJson = new JSONObject(bestScores);
            String adresseBestScore = System.getProperty("user.dir") + File.separator + "res" + File.separator + "fileGame" + File.separator + "score.json";
            FileWriter file;

            if (isBestScore(score)) {
                bestScoreJson.getJSONObject("Best Score").getJSONArray("list").remove(CherchePetitScore());
                bestScoreJson.getJSONObject("Best Score").getJSONArray("list").put(new JSONObject().put("score", score).put("pseudo", pseudo).put("date", getDateEpoch()));

                file = new FileWriter(adresseBestScore);
                file.write(bestScoreJson.toString());
                file.flush();
                file.close();
            }
        }
        catch (JSONException | IOException e) {
            System.out.println("Erreur, impossible de charger le fichier score.json.");
        }
    }
}
