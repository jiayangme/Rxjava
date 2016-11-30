package jiayang.com.rxjava.model;

/**
 * Created by xiangkai on 2016/11/30.
 */

public class Course {
    private String china;
    private String math;
    private String english;

    public Course(String china, String math, String english) {
        this.china = china;
        this.math = math;
        this.english = english;
    }

    public String getChina() {
        return china;
    }

    public void setChina(String china) {
        this.china = china;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }
}
