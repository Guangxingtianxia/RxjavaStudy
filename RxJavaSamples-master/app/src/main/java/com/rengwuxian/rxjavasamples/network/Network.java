// (c)2016 Flipboard Inc, All Rights Reserved.

package com.rengwuxian.rxjavasamples.network;

import com.rengwuxian.rxjavasamples.network.api.FakeApi;
import com.rengwuxian.rxjavasamples.network.api.GankApi;
import com.rengwuxian.rxjavasamples.network.api.ZhuangbiApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络类
 */
public class Network
{
    private static ZhuangbiApi zhuangbiApi;
    private static GankApi gankApi;
    private static FakeApi fakeApi;
    // okhttpClient
    private static OkHttpClient okHttpClient = new OkHttpClient();
    // gson转化工厂
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    // rxjava 适配器工厂
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    private OkHttpClient.Builder mBuilder;



    /**
     * 装逼 api
     * with｛@link ZhuangbiApi @ ZhuangbiApi｝.
     *
     * @return
     */
    public static ZhuangbiApi getZhuangbiApi()
    {
        // 判断非空
        if (zhuangbiApi == null)
        {
            // 新建retrofit
            Retrofit retrofit = new Retrofit.Builder().client(okHttpClient).baseUrl("http://zhuangbi.info/").addConverterFactory(gsonConverterFactory).addCallAdapterFactory(rxJavaCallAdapterFactory).build();
            zhuangbiApi = retrofit.create(ZhuangbiApi.class);
        }
        return zhuangbiApi;
    }

    public static GankApi getGankApi()
    {
        if (gankApi == null)
        {
            Retrofit retrofit = new Retrofit.Builder().client(okHttpClient).baseUrl("http://gank" + ".io/api/").
                    addConverterFactory(gsonConverterFactory).addCallAdapterFactory(rxJavaCallAdapterFactory).build();
            gankApi = retrofit.create(GankApi.class);

        }
        return gankApi;
    }

    public static FakeApi getFakeApi()
    {
        if (fakeApi == null)
        {
            fakeApi = new FakeApi();
        }
        return fakeApi;
    }
}

