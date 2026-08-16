package com.bonginkosi.employeeservice.event;

public class EmployeeCreatedEvent {

    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String mobileNumbers;
    private String role;
    private String employmentType;
    private String department;


    public EmployeeCreatedEvent(){}

    public EmployeeCreatedEvent(Integer id, String name, String surname, Integer age, String email, String mobileNumbers, String role, String employmentType, String department ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.mobileNumbers = mobileNumbers;
        this.role = role;
        this.employmentType = employmentType;
        this.department = department;

    }


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

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumbers() {
        return mobileNumbers;
    }

    public void setMobileNumbers(String mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getEmploymentType() {
        return employmentType;
    }
    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

//    public Integer getEmployeeId() {
//        return employeeId;
//    }
//
//    public void setEmployeeId(Integer id) {
//        this.employeeId = employeeId;
//    }

}
