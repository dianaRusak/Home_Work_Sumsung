package com.company.Model;

/**
 * Created by Диана on 01.12.2015.
 */
public class Student {
    public String gender, name, family;
    public int age, IQ;

    public String setName() {
        return name;
    }

    public String setGender() {
        return gender;
    }

    public String setFamily() {
        return family;
    }

    public int setAge() {
        return age;
    }

    public int setIQ() {
        return IQ;
    }

    public int getIQ() {
        return IQ;
    }

    public int getAge() {
        return age;
    }

    public String getFamily() {
        return family;
    }

    public String getName() {
        return name;
    }
    public String getGender() {
        return gender;
    }

    public Student (int age, int IQ, String gender, String name, String family){
        this.age = age;
        this.IQ = IQ;
        this.gender = gender;
        this.name = name;
        this.family = family;
    }
}
