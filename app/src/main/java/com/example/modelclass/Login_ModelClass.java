package com.example.modelclass;

public class Login_ModelClass {

    String userid,mobileNo,emailId,userName;

    public Login_ModelClass(String userid, String mobileNo, String emailId, String userName) {
        this.userid = userid;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.userName = userName;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
