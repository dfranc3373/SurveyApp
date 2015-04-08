package Models;

import java.util.Date;
import java.util.List;

/**
 * Created by Artsiom on 2/18/15.
 */
public class Survey {

    int SurveyID;
    String Title;
    String Description;
    String Category;
    String CompanyName;
    Date DateCreated;
    Coupon Coupon;
    Question Question;
    List<QuestionAnswer> Answers;
}
