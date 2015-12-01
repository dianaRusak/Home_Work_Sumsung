package com.company.Model;

/**
 * Created by Диана on 01.12.2015.
 */
public class Teacher {

    //Учитель должен иметь поля: имя, фамилия, возраст, пол, квалификация
    public String gender, name, family;
    public int age, skill;

    public int getAge() {
        return age;
    }

    public int getSkill() {
        return skill;
    }

    public String getFamily() {
        return family;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public Teacher(int age, int skill, String gender, String family, String name) {
        this.age = age;
        this.skill = skill;
        this.gender = gender;
        this.family = family;
        this.name = name;
    }
}