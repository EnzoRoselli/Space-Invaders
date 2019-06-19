package Controller;
public class OnlyBestPlayerUser {
    private Integer score;
    private String nickName;

    public OnlyBestPlayerUser() {
        score = 0;
        nickName = "";
    }

    public OnlyBestPlayerUser(Integer score, String nickName) {
        this.score = score;
        this.nickName = nickName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }       
}
