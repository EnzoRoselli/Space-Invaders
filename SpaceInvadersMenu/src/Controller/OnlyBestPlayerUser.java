package Controller;

/**
 * Class used to assign values for the current best player of the game.
 * @author InvadersTeam
 * @since March 2019
 */
public class OnlyBestPlayerUser {
    private Integer score;
    private String nickName;

    /**
     * assign: score = 0.<p>
     * nickname" ".
     */
    public OnlyBestPlayerUser() {
        score = 0;
        nickName = "";
    }

    /**
     *
     * @param score Best player score.
     * @param nickName Best player nickname.
     */
    public OnlyBestPlayerUser(Integer score, String nickName) {
        this.score = score;
        this.nickName = nickName;
    }

    /**
     *
     * @return
     */
    public Integer getScore() {
        return score;
    }

    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     *
     * @return
     */
    public String getNickName() {
        return nickName;
    }

    /**
     *
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }       
}
