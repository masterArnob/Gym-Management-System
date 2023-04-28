/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym3;

/**
 *
 * @author ASUS
 */
public class coachData {
      private String coachid;
    private String name;
    private String address;
    private String gender;
    private String phone;
    private String status;
    private String picture;

    public coachData(String coachid, String name, String address, String gender, String phone, String status, String picture) {
        this.coachid = coachid;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.phone = phone;
        this.status = status;
        this.picture = picture;
    }

    public String getCoachid() {
        return coachid;
    }

    public void setCoachid(String coachid) {
        this.coachid = coachid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    
}
