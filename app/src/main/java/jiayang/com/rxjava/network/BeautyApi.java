package jiayang.com.rxjava.network;

import jiayang.com.rxjava.model.BeautyResult;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by xiangkai on 2016/11/29.
 */
public interface BeautyApi {
    @GET("data/福利/{number}/{page}")
    Observable<BeautyResult> getBeautyImage(@Path("number") int number, @Path("page") int page);
}
