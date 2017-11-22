package com.bawei.retrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getNoParams();
    }
    public void getNoParams(){
        final Map<String,String>map=new HashMap<>();
        map.put("mobile","18657874240");
        map.put("password","123456");
        //得到网络请求数据源
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.BASE_PATH).addConverterFactory(GsonConverterFactory.create())
                //支持Rxjava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Apiweixin apiweixin = retrofit.create(Apiweixin.class);
        //得到Observable被观察者      生产时间 得到数据源
        Observable<Beanweixin> observable = apiweixin.getNoParams(map);
        //被观察者订阅观察者  默认在同一个线程
        observable
                //指定io线程做耗时操作
                .subscribeOn(Schedulers.io())
                //指定更新UI在主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Beanweixin>() {
                    @Override
                    public void onCompleted() {//完成

                    }

                    @Override
                    public void onError(Throwable e) {//失败

                    }

                    @Override
                    public void onNext(Beanweixin beanweixin) {//消费事件
                        String msg = beanweixin.getMsg();
                        System.out.println(msg);
                    }

                });

    }
}
