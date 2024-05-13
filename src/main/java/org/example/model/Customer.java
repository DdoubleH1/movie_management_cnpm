package org.example.model;

public class Customer {
    private int id;
    private String fullName;
    private String address;
    private int age;
    private String phoneNumber;
    private int memberPoint;

    public Customer() {
    }

    public Customer(String fullName, String address, int age, String phoneNumber, int memberPoint) {
        this.fullName = fullName;
        this.address = address;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.memberPoint = memberPoint;
    }

    public Customer(int id, String fullName, String address, int age, String phoneNumber, int memberPoint) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.memberPoint = memberPoint;
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

    public int getMemberPoint() {
        return memberPoint;
    }

    public void setMemberPoint(int memberPoint) {
        this.memberPoint = memberPoint;
    }
}
