package jiayang.com.rxjava.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiayang.com.rxjava.R;
import jiayang.com.rxjava.network.ApiFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by xiangkai on 2016/11/28.
 */
public class RetryWhenFragment extends BaseFragment {
    @Bind(R.id.tokenTv)
    TextView tokenTv;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.requestBt)
    Button requestBt;
    @Bind(R.id.invalidateTokenBt)
    Button invalidateTokenBt;
    private boolean first = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_token_advanced, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.requestBt, R.id.invalidateTokenBt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.requestBt:
                ApiFactory.getStudentApi()
                        .getRetryWhenTest()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .flatMap(new Func1<String, Observable<?>>() {
                            @Override
                            public Observable<?> call(String s) {
                                if (first) {
                                    first = false;
                                    NullPointerException exception = new NullPointerException();
                                    throw exception;
                                }
                                String[] successDatas = {"success"};
                                return Observable.from(successDatas);
                            }
                        })                                                                        //retrywhen抛出异常时重新发起请求
                        .retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {  //func1内部两个参数作用，把错误的observable（第一个参数）处理成正常的observable（第二个参数）
                            //func1实现重试的逻辑
                            @Override
                            public Observable<?> call(Observable<? extends Throwable> observable) {
                                return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                                    @Override
                                    public Observable<?> call(Throwable throwable) {
                                        if (throwable instanceof NullPointerException) {
                                            return ApiFactory.getStudentApi().getSuccess();       //此处返回的observable和retrywhen参数func1 的第二个参数什么关系？？？
                                        }
                                        return null;
                                    }
                                });
                            }
                        })
                        .subscribe(new Subscriber<Object>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(Object student) {
                                Toast.makeText(getActivity(), "数据加载成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
    }
}
