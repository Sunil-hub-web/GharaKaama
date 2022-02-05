package com.example.modelclass;

public class BookingDetails_ModelClass {

    String order_id,order_status,book_id,service_catagory_1,service_catagory_2,ServiceTypeDesc,description,
            date,time,address1,locality,landmark,state,contact_no;

    public BookingDetails_ModelClass(String order_id, String order_status, String book_id,
                                     String service_catagory_1, String service_catagory_2,
                                     String serviceTypeDesc, String description, String date,
                                     String time, String address1, String locality, String landmark,
                                     String state, String contact_no) {
        this.order_id = order_id;
        this.order_status = order_status;
        this.book_id = book_id;
        this.service_catagory_1 = service_catagory_1;
        this.service_catagory_2 = service_catagory_2;
        ServiceTypeDesc = serviceTypeDesc;
        this.description = description;
        this.date = date;
        this.time = time;
        this.address1 = address1;
        this.locality = locality;
        this.landmark = landmark;
        this.state = state;
        this.contact_no = contact_no;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getService_catagory_1() {
        return service_catagory_1;
    }

    public void setService_catagory_1(String service_catagory_1) {
        this.service_catagory_1 = service_catagory_1;
    }

    public String getService_catagory_2() {
        return service_catagory_2;
    }

    public void setService_catagory_2(String service_catagory_2) {
        this.service_catagory_2 = service_catagory_2;
    }

    public String getServiceTypeDesc() {
        return ServiceTypeDesc;
    }

    public void setServiceTypeDesc(String serviceTypeDesc) {
        ServiceTypeDesc = serviceTypeDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }
}
