//This is the class that is tied to the database
//


package com.bonginkosi.userservice.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_numbers")
    private  String mobileNumbers;


    // Constructors
    public User() {}

    public User(String name, String role,String email, String mobileNumbers) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.mobileNumbers = mobileNumbers;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumbers() {
        return mobileNumbers;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNumbers(String mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }

}
