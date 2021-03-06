package Models;

/**
 * Created by Artsiom on 2/18/15.
 */
public class User {

    private int UserID;
    private String Name;
    private String Password;
    private String Email;
    private boolean FB;
    private String Gender;
    private String Age_Range;
    private String Token;

    //setters

    public void setUserID(int userID) {
        this.UserID = userID;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setFB(boolean FB) {
        this.FB = FB;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }

    public void setAge_Range(String age_Range) {
        this.Age_Range = age_Range;
    }

    public void setToken(String token) {
        this.Token = token;
    }


    //getters

    public int getUserID() {
        return this.UserID;
    }

    public String getName() {
        return this.Name;
    }

    public String getPassword() {
        return this.Password;
    }

    public String getEmail() {
        return this.Email;
    }

    public boolean isFB() {
        return this.FB;
    }

    public String getGender() {
        return this.Gender;
    }

    public String getAge_Range() {
        return this.Age_Range;
    }

    public String getToken() {
        return this.Token;
    }
}
