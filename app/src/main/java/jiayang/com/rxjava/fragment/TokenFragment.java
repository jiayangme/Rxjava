package jiayang.com.rxjava.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiayang.com.rxjava.R;
import jiayang.com.rxjava.model.Course;
import jiayang.com.rxjava.model.Student;
import jiayang.com.rxjava.model.ZhuangbiImage;
import jiayang.com.rxjava.network.ApiFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by xiangkai on 2016/11/28.
 */
public class TokenFragment extends BaseFragment {
    @Bind(R.id.tokenTv)
    TextView tokenTv;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.requestBt)
    Button requestBt;
    private List<ZhuangbiImage> datas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_token, null);
        ButterKnife.bind(this, view);
        datas = new ArrayList<>();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.requestBt)
    public void onClick() {
        subscription = ApiFactory.getStudentApi()
                .getStudent()
                .map(new Func1<Student, List<Course>>() {
                    @Override
                    public List<Course> call(Student student) {
                        return student.getCourses();
                    }
                })
//                .flatMap(new Func1<Student, Observable<Course>>() {
//                    @Override
//                    public Observable<Course> call(Student student) {
//
//                        return Observable.from(student.getCourses());
//                    }
//                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Course>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
                    }

                    //此处体现map与flatmap区别，使用flatmap可以直接返回course对象，不用再在onnext中做逻辑处理
                    //因为flatmap可以消解双重list
                    @Override
                    public void onNext(List<Course> courses) {
                        for (int i = 0; i < courses.size(); i++) {
                            tokenTv.setText(courses.get(i).getChina());
                            Log.e("tag", courses.get(i).getChina());
                        }
                    }
                });
    }
}
