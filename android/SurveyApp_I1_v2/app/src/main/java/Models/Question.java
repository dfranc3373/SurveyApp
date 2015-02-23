package Models;

import java.util.List;

/**
 * Created by Artsiom on 2/18/15.
 */
public class Question {

    int QuestionID;
    int SurveyID;
    String Title;
    String Description;
    List<QuestionAnswer> Answers;

}
