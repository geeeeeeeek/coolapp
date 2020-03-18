package com.geeeeeeeek.coolapp.utils;

import com.geeeeeeeek.coolapp.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoQingsong
 * Date: 2020/3/13
 * Time: 3:20 PM
 */
public class MockDataUtil {

    public static List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        Student student = new Student();
        student.name = "zhangsan";

        List<Student.Course> courses = new ArrayList<>();
        Student.Course course = new Student().new Course();
        course.couseName = "yuwen";
        course.score = 80;
        courses.add(course);
        course = new Student().new Course();
        course.couseName = "shuxue";
        course.score = 70;
        courses.add(course);

        student.courses = courses;

        students.add(student);
        students.add(student);
        students.add(student);

        return students;
    }
}
