package jiayang.com.rxjava.network;

import java.util.ArrayList;
import java.util.List;

import jiayang.com.rxjava.model.Course;
import jiayang.com.rxjava.model.Student;
import rx.Observable;

/**
 * Created by xiangkai on 2016/11/30.
 */
public class StudentApi {
    public static Observable<Student> getStudent() {
        List<Student> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Course> courses = new ArrayList<>();
            Course course = new Course("语文" + i, "数学" + i, "英语" + i);
            Course course1 = new Course("语文" + i + 1, "数学" + 1 + i, "英语" + i + 1);
            courses.add(course);
            courses.add(course1);

            Student student = new Student("jiayang" + i, courses);
            datas.add(student);
        }
        return Observable.from(datas); //from 方法的返回值是item，不是整个list
    }

    public static Observable<String> getRetryWhenTest() {
        String[] datas = {"英语"};
        return Observable.from(datas);
    }

    public static Observable<String> getSuccess() {
        String[] datas = {"语文", "数学"};
        return Observable.from(datas);
    }
}
