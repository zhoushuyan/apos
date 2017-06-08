package com.wbasic.apos.common.http;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/3/3.
 */

public interface IService {
    @FormUrlEncoded
    @POST("{services}")
    Call<String> getCall (@Path("services") String services, @FieldMap Map<String, String> queryMap);
    @FormUrlEncoded
    @POST("{services}")
    Call<ResponseBody> getCallRequest (@Path("services") String services, @FieldMap Map<String, String> queryMap);
}
