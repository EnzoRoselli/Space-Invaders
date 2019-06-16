package Controller;

/**
 *
 * @author InvadersTeam
 */
public class ScoreAndUserCatcher {
    private static int score;
    private static String gmail;

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        ScoreAndUserCatcher.score = score;
    }

    public static String getGmail() {
        return gmail;
    }

    public static void setGmail(String gmail) {
        ScoreAndUserCatcher.gmail = gmail;
    }
}
