package com.bawei.retrofit2;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by 猥琐的熊猫 on 2017/11/22.
 */

public interface Apiweixin {
   //https://www.zhaoapi.cn/user/reg
    @POST("user/reg")
    Observable<Beanweixin> getNoParams(@QueryMap Map<String ,String>map);
}
