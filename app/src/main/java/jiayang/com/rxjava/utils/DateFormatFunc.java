package jiayang.com.rxjava.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jiayang.com.rxjava.model.BeautyImage;
import jiayang.com.rxjava.model.BeautyResult;
import jiayang.com.rxjava.model.ZhuangbiImage;
import rx.functions.Func1;

/**
 * Created by xiangkai on 2016/11/29.
 */
public class DateFormatFunc implements Func1<BeautyResult, List<ZhuangbiImage>> { //泛型表示把前者转换成后者

    @Override
    public List<ZhuangbiImage> call(BeautyResult result) {
        List<BeautyImage> images = result.getResults();
        List<ZhuangbiImage> beautyImages = new ArrayList<>();
        ZhuangbiImage zhuangbiImage;
        for (int i = 0; i < images.size(); i++) {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");  //月份，小时，毫秒 大写
            SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
            Date date;
            String strDate;
            try {
                date = inputFormat.parse(images.get(i).getPublishedAt());
                strDate = outputFormat.format(date);
            } catch (ParseException e) {
                strDate = "unknown";
            }
            zhuangbiImage = new ZhuangbiImage();
            zhuangbiImage.setImage_url(images.get(i).getUrl());
            zhuangbiImage.setDescription(strDate);
            beautyImages.add(zhuangbiImage);
        }
        return beautyImages;
    }
}
