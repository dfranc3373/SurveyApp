package Models;

/**
 * Created by Artsiom on 2/18/15.
 */
public class QuestionAnswer {

    private int QuestionAnswerID;
    private int Question;
    private String Answer;

    //setters

    public void setQuestionAnswerID(int questionAnswerID) {
        this.QuestionAnswerID = questionAnswerID;
    }

    public void setAnswer(String answer) {
        this.Answer = answer;
    }

    public void setQuestion(int q) {
        this.Question = q;
    }

    //getters

    public int getQuestionAnswerID() {
        return this.QuestionAnswerID;
    }

    public String getAnswer() {
        return this.Answer;
    }

    public int getQuestion(int q) {
        return this.Question;
    }

}
