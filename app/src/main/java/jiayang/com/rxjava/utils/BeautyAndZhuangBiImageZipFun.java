package jiayang.com.rxjava.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import jiayang.com.rxjava.model.ZhuangbiImage;
import rx.functions.Func2;

/**
 * Created by xiangkai on 2016/11/30.
 */

public class BeautyAndZhuangBiImageZipFun implements Func2<List<ZhuangbiImage>, List<ZhuangbiImage>, List<ZhuangbiImage>> {
    @Override
    public List<ZhuangbiImage> call(List<ZhuangbiImage> zhuangbiImages, List<ZhuangbiImage> beautyImages) {
        List<ZhuangbiImage> results = new ArrayList<>();
        Log.e("tag", zhuangbiImages.size() + "a" + beautyImages.size());
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                results.add(beautyImages.get(i));
            } else {
                results.add(zhuangbiImages.get(i));
            }
        }
        return results;
    }
}
