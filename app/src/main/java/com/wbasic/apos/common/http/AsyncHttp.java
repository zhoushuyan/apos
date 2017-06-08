package com.wbasic.apos.common.http;


import com.wbasic.apos.drivers.Drivers;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/1/6.
 */

public class AsyncHttp {
    private static  int retry=0;
    private static OkHttpClient okHttpClient;
    static{
        okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
//                Request request=chain.request();
//                HttpUrl url=request.url();
//                    Log.d("#####url",url.url().toString());
//                    Log.d("#####path",url.url().getPath());
//                    Log.d("#####Query",url.url().getQuery());
                builder.addHeader("Cookie",Drivers.androidId);
                return chain.proceed(builder.build());
            }
        }) .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        try{
                            Response response = chain.proceed(chain.request());
                            return response;
                        }catch (IOException e){
                            if(AsyncHttp.retry<3){
                                try{
                                    Request request=chain.request().newBuilder().build();
                                    return chain.proceed(request);
                                }catch (Exception ex){
                                    ex.printStackTrace();
                                }
                            }
                            AsyncHttp.retry++;
                        }
                        return chain.proceed(chain.request());
                    }
                })
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }
    public  static void  post(String services, HashMap<String, String> paramMap, Callback callback){
        AsyncHttp.retry=0;
//        Log.e("######","===============",null);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Drivers.requestUrl)
                .addConverterFactory(new StringConverterFactory())
                .client(okHttpClient)
                .build();
        IService service = retrofit.create(IService.class);
        paramMap.put("appid","pos");
        paramMap.put("env","demo");
        paramMap.put("sn", Drivers.androidId);
        if("request.wbs".equals(services)){
            Call<ResponseBody> call=service.getCallRequest(services,paramMap);
            call.enqueue(callback);
        }else{
            Call<String> call=service.getCall(services,paramMap);
            call.enqueue(callback);
        }

    }
}
