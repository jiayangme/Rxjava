package jiayang.com.rxjava.network;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiangkai on 2016/11/29.
 */

public class ApiFactory {
    private static ZhuangbiApi zhuangbiApi;
    private static BeautyApi beautyApi;
    private static Converter.Factory convertFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory callAdapterFactory = RxJavaCallAdapterFactory.create();
    private static OkHttpClient okHttpClient = new OkHttpClient();

    public static ZhuangbiApi getZhuangbiApi() {
        if (zhuangbiApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://www.zhuangbi.info/")
                    .addConverterFactory(convertFactory)
                    .addCallAdapterFactory(callAdapterFactory)
                    .build();
            zhuangbiApi = retrofit.create(ZhuangbiApi.class);
        }
        return zhuangbiApi;
    }

    public static BeautyApi getBeautyApi() {
        if (beautyApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(convertFactory)
                    .addCallAdapterFactory(callAdapterFactory)
                    .build();
            beautyApi = retrofit.create(BeautyApi.class);
        }
        return beautyApi;
    }
}
