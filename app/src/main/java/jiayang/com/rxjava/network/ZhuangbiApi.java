package jiayang.com.rxjava.network;

import java.util.List;

import jiayang.com.rxjava.model.ZhuangbiImage;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xiangkai on 2016/11/29.
 */

public interface ZhuangbiApi {
    @GET("search")
//    指定泛型，以在数据返回后方便解析
    Observable<List<ZhuangbiImage>> getObservable(@Query("q") String type);
}
