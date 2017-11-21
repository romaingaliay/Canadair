package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Temps {

    public static long getDateEpoch() { return System.currentTimeMillis(); }

    public static String convertEpochToDate(long epoch) {
        String date;

        Calendar cal1 = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        cal1.setTimeInMillis(epoch);
        date = dateFormat.format(cal1.getTime());

        return date;
    }

    public static class Chrono {
        private long tempsDepart = 0;
        private long tempsFin = 0;
        private long pauseDepart = 0;
        private long pauseFin = 0;
        private long duree = 0;

        public void start() {
            tempsDepart = System.currentTimeMillis();
            tempsFin = 0;
            pauseDepart = 0;
            pauseFin = 0;
            duree = 0;
        }

        public void pause() {
            if(tempsDepart == 0) return;
            pauseDepart = System.currentTimeMillis();
        }

        public void resume() {
            if(tempsDepart == 0) return;
            if(pauseDepart == 0) return;
            pauseFin = System.currentTimeMillis();
            tempsDepart += pauseFin - pauseDepart;
            tempsFin = 0;
            pauseDepart = 0;
            pauseFin = 0;
            duree = 0;
        }

        public void stop() {
            if(tempsDepart == 0) return;
            tempsFin = System.currentTimeMillis();
            duree = (tempsFin - tempsDepart) - (pauseFin - pauseDepart);
            tempsDepart = 0;
            tempsFin = 0;
            pauseDepart = 0;
            pauseFin = 0;
        }

        public String getDureeTxt() { return timeToHMS(duree); }

        String timeToHMS(long epoch) {
            String res = "";
            int h = (int) (epoch / 3600);
            int m = (int) ((epoch % 3600) / 60);
            int s = (int) (epoch % 60);

            if(h>0) res += h + " h ";
            if(m>0) res += m + " min ";
            if(s>0) res += s + " s";
            if(h<=0 && m<=0 && s<=0) res = "0 s";

            return res;
        }
    }
}
