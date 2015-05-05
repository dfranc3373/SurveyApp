package Models;

/**
 * Created by Artsiom on 2/18/15.
 */
public class Coupon {

    private int CouponID;
    private String Title;
    private String Description;
    private String CouponCode;
    private String CouponImage;
    private String CompanyName;
    private Boolean DeleteOnLoad;

    //setters

    public void setCouponID(int couponID){
        this.CouponID = couponID;
    }

    public void setTitle(String title){
        this.Title = title;
    }

    public void setDescription(String description){
        this.Description = description;
    }

    public void setCouponCode(String couponCode){
        this.CouponCode = couponCode;
    }

    public void setCouponImage(String couponImage) {
        this.CouponImage = couponImage;
    }

    public void setCompanyName(String companyName) {
        this.CompanyName = companyName;
    }

    public void setDeleteOnLoad(Boolean deleteOnLoad) {
        this.DeleteOnLoad = deleteOnLoad;
    }

    //getters

    public int getCouponID() {
        return this.CouponID;
    }

    public String getTitle() {
        return this.Title;
    }

    public String getDescription() {
        return this.Description;
    }

    public String getCouponCode() {
        return this.CouponCode;
    }

    public String getCouponImage() {
        return this.CouponImage;
    }

    public String getCompanyName() {
        return this.CompanyName;
    }

    public Boolean getDeleteOnLoad() {
        return this.DeleteOnLoad;
    }
}
