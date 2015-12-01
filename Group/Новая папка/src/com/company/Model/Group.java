package com.company.Model;

/**
 * Created by Диана on 01.12.2015.
 */
public class Group {

    Teacher teacher;
    Student group[];

    public Group (Teacher teacher, Student[] arr){
        this.teacher = teacher;
        this.group = arr;
    }

    public  Student searchName (String name){
        for (int i = 0; i < group.length; i++){
            if (name == group[i].getName()){
                System.out.print("Student is ");
                return  group [i];
            }
        }
        System.out.print("The student is not here");
        Student eror = new Student(0, 0, "Eror", "Eror", "Eror");
        return eror;
    }

    public void add (Student newStudent){
        Student[] students = new Student[group.length+1];
        for (int i = 0; i < group.length; i++){
            students[i] = group[i];
        }
        students[students.length-1] = newStudent;
        group = students;
    }

    public void deleteStudent (String name, String surname){
        Student[] students = new  Student[group.length-1];
        int n = 0;
        for (int i = 0; i < group.length; i++){
            if ((group[i].getName() == name) && (group[i].getFamily() == surname)){
                n = 1;
            }else {
                students [1-n] = group[n];
            }
        }
        group = students;
    }
}
