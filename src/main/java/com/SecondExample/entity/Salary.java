package com.SecondExample.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double basicSalary;

    private Double bonus;

    private Double deductions;

    private Double netSalary;

    private Long userId;

    // Getters and Setters
    //For id
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    //For basicSalary
    public Double getBasicSalary() {
        return basicSalary;
    }
    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
    }


   //For bonus
    public Double getBonus() {
        return bonus;
    }
    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    //For deductions
    public Double getDeductions() {
        return deductions;
    }
    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }

    //For netSalary
    public Double getNetSalary() {
        return netSalary;
    }
    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    //For userId
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
