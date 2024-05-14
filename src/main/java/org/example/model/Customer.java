package org.example.model;

public class Customer {
    private int id;
    private String fullName;
    private String address;
    private int age;
    private String phoneNumber;

    public Customer() {
    }

    public Customer(String fullName, String address, int age, String phoneNumber) {
        this.fullName = fullName;
        this.address = address;
        this.age = age;
        this.phoneNumber = phoneNumber;

    }

    public Customer(int id, String fullName, String address, int age, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.age = age;
        this.phoneNumber = phoneNumber;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
