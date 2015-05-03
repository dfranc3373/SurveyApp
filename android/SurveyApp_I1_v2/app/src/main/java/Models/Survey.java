package Models;

import java.util.List;

/**
 * Created by Artsiom on 2/18/15.
 */
public class Survey {

    private int SurveyID;
    private String Name;
    private int AgeRange;
    private int GenderType;
    private String Description;
    private String Category;
    private String Company;
    private long DateCreated;
    private Coupon Coupon;
    private List<Question> Questions;

    //setters

    public void setSurveyID(int surveyID) {
        this.SurveyID = surveyID;
    }

    public void setAgeRange(int ageRange) {
        AgeRange = ageRange;
    }

    public void setGenderType(int genderType) {
        GenderType = genderType;
    }

    public void setTitle(String title) {
        this.Name = title;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setCategory(String category) {
        this.Category = category;
    }

    public void setCompanyName(String companyName) {
        this.Company = companyName;
    }

    public void setDateCreated(long dateCreated) {
        this.DateCreated = dateCreated;
    }

    public void setCoupon(Coupon coupon) {
        this.Coupon = coupon;
    }

    public void setQuestion(List<Question> question) {
        this.Questions = question;
    }


    //getters

    public int getSurveyID() {
        return this.SurveyID;
    }

    public String getTitle() {
        return this.Name;
    }

    public String getDescription() {
        return this.Description;
    }

    public String getCategory() {
        return this.Category;
    }

    public String getCompanyName() {
        return this.Company;
    }

    public long getDateCreated() {
        return this.DateCreated;
    }

    public Coupon getCoupon() {
        return this.Coupon;
    }

    public List<Question> getQuestion() {
        return this.Questions;
    }

    public int getGenderType() {
        return GenderType;
    }
    public int getAgeRange() {
        return AgeRange;
    }

}
