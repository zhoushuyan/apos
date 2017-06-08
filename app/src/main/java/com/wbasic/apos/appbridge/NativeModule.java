package com.wbasic.apos.appbridge;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.ValueCallback;
import android.widget.Toast;

import com.wbasic.apos.MainActivity;
import com.wbasic.apos.R;
import com.wbasic.apos.appbridge.jsbridge.JSCallback;
import com.wbasic.apos.appbridge.jsbridge.JsModule;

import org.xwalk.core.XWalkView;


/**
 * Created by Administrator on 2017/4/18.
 */

public class NativeModule implements JsModule {
    @Override
    public String getModuleName() {
        return "native";
    }
    public static String getVersion(MainActivity activity,String param) {
        return activity.getString(R.string.version);
    }
    public static void toast(MainActivity activity, String text) {
        Toast toast=Toast.makeText(activity, "原生提示消息:"+text, Toast.LENGTH_SHORT);
        toast.show();
    }
    public static void getData(MainActivity activity, String jsEvent) {
        XWalkView webView= activity.getWebView();
        String data="我是函数名回调的";
        String jsfunction=jsEvent+"(\""+data+"\")";
        webView.evaluateJavascript(jsfunction, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Log.d("======", "onReceiveValue value=" + value);
            }});
    }
    public static void getDataCallback(MainActivity activity, String option,final JSCallback callback) {
        String params="{" +
                "\"rcode\": 1000," +
                "\"trans_det\":\"消费成功\"," +
                "\"trans_id\":\"1111111\"" +
                ",\"trans_code\":\"22222\"" +
                "}";
        callback.apply(params);
    }
    public static void getDataAsyncCallback(MainActivity activity, String option,final JSCallback callback) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPreExecute() {
                Log.i("task", "loading...");
            }
            @Override
            protected String doInBackground(Void... params) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String rtnparams="{" +
                        "\"rcode\": 1000," +
                        "\"rtip\":\"消费成功\""+
                        "}";
                return rtnparams;
            }

            @Override
            protected void onPostExecute(String params) {
                callback.apply(params);
            }
        }.execute();
    }
}
