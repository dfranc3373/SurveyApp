package Models;

/**
 * Created by Artsiom on 2/18/15.
 */
public class UserAnswer {

    private int UserID;
    private int QuestionAnswerID;

    //setters

    public void setUserID(int userID) {
        this.UserID = userID;
    }

    public void setQuestionAnswerID(int questionAnswerID) {
        this.QuestionAnswerID = questionAnswerID;
    }


    //getters

    public int getUserID() {
        return this.UserID;
    }

    public int getQuestionAnswerID() {
        return this.QuestionAnswerID;
    }
}
