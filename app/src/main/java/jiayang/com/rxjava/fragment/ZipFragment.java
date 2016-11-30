package jiayang.com.rxjava.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiayang.com.rxjava.R;
import jiayang.com.rxjava.adapter.ListAdapter;
import jiayang.com.rxjava.model.ZhuangbiImage;
import jiayang.com.rxjava.network.ApiFactory;
import jiayang.com.rxjava.utils.BeautyAndZhuangBiImageZipFun;
import jiayang.com.rxjava.utils.DateFormatFunc;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xiangkai on 2016/11/28.
 * 两个请求返回的数据，分别取出你所需要的，进行展示
 */
public class ZipFragment extends BaseFragment {
    @Bind(R.id.zipLoadBt)
    Button zipLoadBt;
    @Bind(R.id.gridRv)
    RecyclerView gridRv;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private Observer<? super List<ZhuangbiImage>> observer;
    private List<ZhuangbiImage> datas;
    private ListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_zip, null);
        ButterKnife.bind(this, view);

        datas = new ArrayList<>();
        adapter = new ListAdapter(getActivity(), datas);
        gridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        gridRv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.zipLoadBt)
    public void onClick() {
        subscription = rx.Observable.zip(
                ApiFactory
                        .getZhuangbiApi()
                        .getObservable("装逼"),
                ApiFactory
                        .getBeautyApi()
                        .getBeautyImage(100, 1)
                        .map(new DateFormatFunc()),
                new BeautyAndZhuangBiImageZipFun())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ZhuangbiImage>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        swipeRefreshLayout.setRefreshing(false);
                        Log.e("tag", e.getMessage() + "aaa");
                        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(List<ZhuangbiImage> zhuangbiImages) {
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.setDatas(zhuangbiImages);
                    }
                });
    }
}