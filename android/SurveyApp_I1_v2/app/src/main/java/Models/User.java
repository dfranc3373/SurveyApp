package Models;

/**
 * Created by Artsiom on 2/18/15.
 */
public class User {

    private int UserID;
    private String Email;
    private boolean FB;
    private int Gender;
    private int Age_Range;
    private String Token;

    //setters

    public void setUserID(int userID) {
        this.UserID = userID;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setFB(boolean FB) {
        this.FB = FB;
    }

    public void setGender(int gender) {
        this.Gender = gender;
    }

    public void setAge_Range(int age_Range) {
        this.Age_Range = age_Range;
    }

    public void setToken(String token) {
        this.Token = token;
    }


    //getters

    public int getUserID() {
        return this.UserID;
    }

    public String getEmail() {
        return this.Email;
    }

    public boolean isFB() {
        return this.FB;
    }

    public int getGender() {
        return this.Gender;
    }

    public int getAge_Range() {
        return this.Age_Range;
    }

    public String getToken() {
        return this.Token;
    }
}
