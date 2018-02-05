package com.test.models;

public class Shop {
    private String id;
    private String city_id;
    private String name;
    private String code;
    private String  address;
    private String  type;
    private String  address_id;
    private String  longitude;
    private String  latitude;
    private String  contacts;
    private String  status;
    private String  department_id;
    private String  department_type;
    private String  department_number;
    private String  created_at;
    private String  updated_at;
    private String  timetable;

    public Shop(String id, String city_id, String name, String code, String address, String type, String address_id, String longitude, String latitude, String contacts, String status, String department_id, String department_type, String department_number, String created_at, String updated_at, String timetable) {
        this.id = id;
        this.city_id = city_id;
        this.name = name;
        this.code = code;
        this.address = address;
        this.type = type;
        this.address_id = address_id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.contacts = contacts;
        this.status = status;
        this.department_id = department_id;
        this.department_type = department_type;
        this.department_number = department_number;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.timetable = timetable;
    }

    public Shop(){
    }

    public Shop getShop(){
        return this;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_type() {
        return department_type;
    }

    public void setDepartment_type(String department_type) {
        this.department_type = department_type;
    }

    public String getDepartment_number() {
        return department_number;
    }

    public void setDepartment_number(String department_number) {
        this.department_number = department_number;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }
}
