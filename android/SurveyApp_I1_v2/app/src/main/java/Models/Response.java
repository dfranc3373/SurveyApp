package Models;

/**
 * Created by Artsiom on 2/18/15.
 */
public class Response {
    
    private Boolean Success;
    private Object Model;

    //setters

    public void setSuccess(Boolean success) {
        this.Success = success;
    }

    public void setModel(Object model) {
        this.Model = model;
    }

    //getters

    public Boolean getSuccess() {
        return this.Success;
    }

    public Object getModel() {
        return this.Model;
    }
}
