package com.geeeeeeeek.coolapp.model;

import java.util.List;

/**
 * Created by XiaoQingsong
 * Date: 2020/3/13
 * Time: 3:20 PM
 */
public class Student {
    public String name;
    public List<Course> courses;

    public class Course{
        public String couseName;
        public int score;
    }
}


