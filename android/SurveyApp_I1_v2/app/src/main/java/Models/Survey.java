package Models;

import java.util.Date;
import java.util.List;

/**
 * Created by Artsiom on 2/18/15.
 */
public class Survey {

    private int SurveyID;
    private String Title;
    private String Description;
    private String Category;
    private String CompanyName;
    private Date DateCreated;
    private Coupon Coupon;
    private List<Question> Question;

    //setters

    public void setSurveyID(int surveyID) {
        this.SurveyID = surveyID;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setCategory(String category) {
        this.Category = category;
    }

    public void setCompanyName(String companyName) {
        this.CompanyName = companyName;
    }

    public void setDateCreated(Date dateCreated) {
        this.DateCreated = dateCreated;
    }

    public void setCoupon(Coupon coupon) {
        this.Coupon = coupon;
    }

    public void setQuestion(List<Question> question) {
        this.Question = question;
    }


    //getters

    public int getSurveyID() {
        return this.SurveyID;
    }

    public String getTitle() {
        return this.Title;
    }

    public String getDescription() {
        return this.Description;
    }

    public String getCategory() {
        return this.Category;
    }

    public String getCompanyName() {
        return this.CompanyName;
    }

    public Date getDateCreated() {
        return this.DateCreated;
    }

    public Coupon getCoupon() {
        return this.Coupon;
    }

    public List<Question> getQuestion() {
        return this.Question;
    }

}
