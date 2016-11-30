package jiayang.com.rxjava.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import jiayang.com.rxjava.R;
import jiayang.com.rxjava.adapter.ListAdapter;
import jiayang.com.rxjava.model.ZhuangbiImage;
import jiayang.com.rxjava.network.ApiFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xiangkai on 2016/11/28.
 */
public class ElementaryFragment extends BaseFragment {
    @Bind(R.id.searchRb1)
    AppCompatRadioButton searchRb1;
    @Bind(R.id.searchRb2)
    AppCompatRadioButton searchRb2;
    @Bind(R.id.searchRb3)
    AppCompatRadioButton searchRb3;
    @Bind(R.id.searchRb4)
    AppCompatRadioButton searchRb4;
    @Bind(R.id.gridRv)
    RecyclerView gridRv;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private ListAdapter adapter;
    private List<ZhuangbiImage> datas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_element, null);
        ButterKnife.bind(this, view);

        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.GRAY);
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

    @OnCheckedChanged({R.id.searchRb1, R.id.searchRb2, R.id.searchRb3, R.id.searchRb4})
    void onChecked(CompoundButton searchRb, boolean isChecked) {
        // isChecked 参数表示：标记此次被点击的 view ，赋值 true ，并且，撤销之前点击的 view ，赋值 false
        //因此，isChecked 会有两次结果
        if (isChecked == true) {
            swipeRefreshLayout.setRefreshing(true);
            subscription = ApiFactory.getZhuangbiApi().getObservable(searchRb.getText().toString())
                    .subscribeOn(Schedulers.io())               //当使用多个 subscribeon ，只有第一个起效，其他被截断
                    .observeOn(AndroidSchedulers.mainThread())  //observeon 指定的是当前 observable 所对应的直接下级 subscriber
                    .subscribe(new Subscriber<List<ZhuangbiImage>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            swipeRefreshLayout.setRefreshing(false);
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
}