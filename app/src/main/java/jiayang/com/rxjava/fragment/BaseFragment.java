package jiayang.com.rxjava.fragment;

import rx.Subscription;

/**
 * Created by xiangkai on 2016/11/28.
 */
public class BaseFragment extends android.app.Fragment {
    protected Subscription subscription;

    @Override
    public void onDestroy() {
        super.onDestroy();
        //如果用户没有发起请求，则 subscription 为 null
        //如果数据已经返回，isUnsubscribed()为 false
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
