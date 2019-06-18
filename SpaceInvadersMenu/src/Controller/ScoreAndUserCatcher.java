package Controller;

/**
 *
 * @author InvadersTeam
 */
public class ScoreAndUserCatcher {
    private static int score=0;
    private static String gmail="";

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        ScoreAndUserCatcher.score = score;
    }

    public static String getGmailAndNick() {
        return gmail;
    }

    public static void setGmail(String gmail) {
        ScoreAndUserCatcher.gmail = gmail;
    }
}
