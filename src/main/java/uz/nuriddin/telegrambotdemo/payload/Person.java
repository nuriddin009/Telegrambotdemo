package uz.nuriddin.telegrambotdemo.payload;

import lombok.Data;

@Data
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String birthDate;
    private String image;
    private String bloodGroup;
    private int height;
    private double weight;
    private String eyeColor;
    private Hair hair;
    private String domain;
    private String ip;
    private Address address;
    private String macAddress;
    private String university;
    private Bank bank;
    private Company company;
    private String ein;
    private String ssn;
    private String userAgent;


    @Data
    static class Hair {
        private String color;
        private String type;
    }

    @Data
    static class Coordinates {
        private double lat;
        private double lng;
    }

    @Data
    static class Address {
        private String address;
        private String city;
        private Coordinates coordinates;
        private String postalCode;
        private String state;
    }

    @Data
    static class Bank {
        private String cardExpire;
        private String cardNumber;
        private String cardType;
        private String currency;
        private String iban;
    }

    @Data
    static class Company {
        private Address address;
        private String department;
        private String name;
        private String title;
    }
}
