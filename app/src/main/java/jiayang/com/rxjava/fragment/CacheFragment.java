package jiayang.com.rxjava.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiayang.com.rxjava.R;

/**
 * Created by xiangkai on 2016/11/28.
 */
public class CacheFragment extends Fragment {
    @Bind(R.id.loadingTimeTv)
    AppCompatTextView loadingTimeTv;
    @Bind(R.id.loadBt)
    AppCompatButton loadBt;
    @Bind(R.id.cacheRv)
    RecyclerView cacheRv;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.clearMemoryCacheBt)
    AppCompatButton clearMemoryCacheBt;
    @Bind(R.id.clearMemoryAndDiskCacheBt)
    AppCompatButton clearMemoryAndDiskCacheBt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_cache, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.loadBt, R.id.clearMemoryCacheBt, R.id.clearMemoryAndDiskCacheBt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loadBt:
                break;
            case R.id.clearMemoryCacheBt:
                break;
            case R.id.clearMemoryAndDiskCacheBt:
                break;
        }
    }
}
