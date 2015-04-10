package Models;

import java.util.List;

/**
 * Created by Artsiom on 2/18/15.
 */
public class Question {

    private int QuestionID;
    private int SurveyID;
    private String Title;
    private String Description;
    private List<QuestionAnswer> Answers;

    //setters

    public void setQuestionID(int questionID) {
        this.QuestionID = questionID;
    }

    public void setSurveyID(int surveyID) {
        this.SurveyID = surveyID;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setAnswers(List<QuestionAnswer> answers) {
        this.Answers = answers;
    }

    //getters

    public int getQuestionID() {
        return this.QuestionID;
    }

    public int getSurveyID() {
        return this.SurveyID;
    }

    public String getTitle() {
        return this.Title;
    }

    public String getDescription() {
        return this.Description;
    }

    public List<QuestionAnswer> getAnswers() {
        return this.Answers;
    }
}
