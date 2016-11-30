package jiayang.com.rxjava.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import jiayang.com.rxjava.utils.DateFormatFunc;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by xiangkai on 2016/11/28.
 */
public class MapFragment extends BaseFragment {
    @Bind(R.id.pageTv)
    TextView pageTv;
    @Bind(R.id.previousPageBt)
    AppCompatButton previousPageBt;
    @Bind(R.id.nextPageBt)
    AppCompatButton nextPageBt;
    @Bind(R.id.gridRv)
    RecyclerView gridRv;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private int page = 0;
    private int number = 10;
    private ListAdapter adapter;
    private List<ZhuangbiImage> zhuangbiImages;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_map, null);
        ButterKnife.bind(this, view);

        pageTv.setText("第 0 页");
        zhuangbiImages = new ArrayList<>();
        adapter = new ListAdapter(getActivity(), zhuangbiImages);
        gridRv.setAdapter(adapter);
        gridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.previousPageBt, R.id.nextPageBt})
    public void onClick(View view) {
        subscription.unsubscribe();
        switch (view.getId()) {
            case R.id.previousPageBt:
                swipeRefreshLayout.setRefreshing(true);
                loadDatas();
                page--;
                break;
            case R.id.nextPageBt:
                swipeRefreshLayout.setRefreshing(true);
                loadDatas();
                page++;
                break;
        }
    }

    private void loadDatas() {
        if (page <= 0) {
            previousPageBt.setEnabled(false);
        } else {
            previousPageBt.setEnabled(true);
        }
        subscription = ApiFactory.getBeautyApi()
                .getBeautyImage(number, page)
                .map(new DateFormatFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ZhuangbiImage>>() {
                    @Override
                    public void call(List<ZhuangbiImage> zhuangbiImages) {
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.setDatas(zhuangbiImages);
                        pageTv.setText("第" + page + "页");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
