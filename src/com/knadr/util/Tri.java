package com.knadr.util;

import java.util.ArrayList;

public class Tri {
    public static void TriScore(ArrayList<String> pseudo, ArrayList<Integer> bestScores) {
        int compteur=1;
        String pseudot;
        int  scoret;
        while (compteur>0){
            compteur=0;
            for(int i=0;i<9;i++){
                if(bestScores.get(i)<bestScores.get(i+1)){
                    compteur++;

                    pseudot=pseudo.get(i);
                    pseudo.set(i,pseudo.get(i+1));
                    pseudo.set(i+1,pseudot);

                    scoret=bestScores.get(i);
                    bestScores.set(i,bestScores.get(i+1));
                    bestScores.set(i+1,scoret);
                }
        }

        }
    }
}
