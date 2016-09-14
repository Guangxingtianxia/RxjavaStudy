// (c)2016 Flipboard Inc, All Rights Reserved.

package com.rengwuxian.rxjavasamples.util;

import com.rengwuxian.rxjavasamples.model.GankBeauty;
import com.rengwuxian.rxjavasamples.model.GankBeautyResult;
import com.rengwuxian.rxjavasamples.model.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.functions.Func1;

/**
 * GankBeautyResultToItemsMapper 类 实现 Fun1<T,R > 接口，实现转换
 * 面向接口编程
 */
public class GankBeautyResultToItemsMapper implements Func1<GankBeautyResult, List<Item>>
{
    // 构造私有化
    private GankBeautyResultToItemsMapper()
    {
    }

    // 静态实例 实例
    private static GankBeautyResultToItemsMapper INSTANCE = new GankBeautyResultToItemsMapper();

    // 对外暴露提供本类实例的方法
    public static GankBeautyResultToItemsMapper getInstance()
    {
        return INSTANCE;
    }

    /**
     * 父类call()方法
     *
     * @param gankBeautyResult
     * @return
     */
    @Override
    public List<Item> call(GankBeautyResult gankBeautyResult)
    {
        List<GankBeauty> gankBeauties = gankBeautyResult.beauties;
        List<Item> items = new ArrayList<>(gankBeauties.size());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        // list 集合遍历----for--each循环，值得学习
        for (GankBeauty gankBeauty : gankBeauties)
        {
            // 此对象，有两个属性；1：图片的描述；2：图片的描述
            Item item = new Item();
            try
            {
                // 格式化时间
                Date date = inputFormat.parse(gankBeauty.createdAt);
                item.description = outputFormat.format(date);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
                item.description = "unknown date";
            }
            item.imageUrl = gankBeauty.url;
            items.add(item);
        }
        return items;
    }
}
