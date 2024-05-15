package com.bloodunity.models;

public class UserModel {
    String Name, Phone, City, BloodType, profile_image, UserID, Location;

    public UserModel() {
        // default constructor...
    }

    public UserModel(String Name, String Phone, String City, String profile_image, String BloodType, String UserID, String Location) {
        this.Name = Name;
        this.Phone = Phone;
        this.City = City;
        this.profile_image = profile_image;
        this.BloodType = BloodType;
        this.UserID = UserID;
        this.Location = Location;
    }


    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getBloodType() {
        return BloodType;
    }

    public void setBloodType(String bloodType) {
        BloodType = bloodType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
}
