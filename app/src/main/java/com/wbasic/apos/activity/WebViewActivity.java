package com.wbasic.apos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.ValueCallback;

import com.wbasic.apos.R;
import com.wbasic.apos.appbridge.NativeModule;
import com.wbasic.apos.appbridge.jsbridge.JSBridge;
import com.wbasic.apos.manager.ScreenManager;

import org.xwalk.core.XWalkJavascriptResult;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;

import java.util.regex.Pattern;
/**
 * Created by Administrator on 2017/4/19.
 */

public class WebViewActivity extends AppCompatActivity {
    public XWalkView webView;
    public void setWebView(XWalkView webView){
        this.webView=webView;
    }
    public XWalkView getWebView(){
        return this.webView;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);
        ScreenManager.getScreenManager().pushActivity(this);
        // JSBridge配置
        JSBridge.getConfig().setProtocol("App")
                .setLoadReadyFuncName("onAppReady")
                .registerModule(NativeModule.class);
        webView = (XWalkView) findViewById(R.id.webView);
    }
    protected void loadWebView(final Activity activity, String url){
        webView.setUIClient(new XWalkUIClient(webView){
            Pattern pass = Pattern.compile("#.*$");
            @Override
            public void onPageLoadStarted(XWalkView view, String url) {
                super.onPageLoadStarted(view, url);
                Log.d("webview", "onPageStart is run: " + url);
            }

            @Override
            public void onPageLoadStopped(XWalkView view, String url, LoadStatus status) {
                super.onPageLoadStopped(view, url, status);
                boolean urlPass = pass.matcher(url).find();
                Log.d("webview", "onPageFinish is run: " + url);
                JSBridge.injectJs(webView);
                for(int i=0;i<1000;i++){
                    runJsCode("test","测试123abc"+i);
                }
            }

            @Override
            public boolean onJsPrompt(XWalkView view, String url, String message, String defaultValue, XWalkJavascriptResult result) {
                result.confirmWithResult(JSBridge.callJsPrompt(activity, view, message));
                Log.d("webview", "onJsPrompt: " + url);
                return true;
            }
        });
        if (url != null){
            webView.loadUrl(url);
        };
    }
    private void runJsCode(String jsEvent,String data) {
        String jsfunction=jsEvent+"(\""+data+"\")";
        System.out.println("=====>>>>>>>"+jsfunction);
        webView.evaluateJavascript(jsfunction, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Log.d("======", "onReceiveValue value=" + value);
            }});
    }
//    /**
//     * 设置 JsBridge 全局暴露的对象名
//     *
//     * @param name 暴露的对象名
//     * @return 对象自身, 链式调用
//     */
//    protected WebViewActivity setProtocol(String name) {
//        JSBridge.getConfig().setProtocol(name);
//        return this;
//    }
//    /**
//     * 注册 JsBridge 新模块
//     *
//     * @param onJsReady 初始化完成后调用的js函数
//     * @return 对象自身, 链式调用
//     */
//    protected WebViewActivity setLoadReadyFuncName(String onJsReady) {
//        JSBridge.getConfig().setLoadReadyFuncName(onJsReady);
//        return this;
//    }
//    /**
//     * 注册 JsBridge 新模块
//     *
//     * @param instance 要注册的模块 class
//     * @return 对象自身, 链式调用
//     */
//    protected WebViewActivity registerModule(Class instance) {
//        JSBridge.getConfig().registerModule(instance);
//        return this;
//    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        ScreenManager.getScreenManager().popActivity(this);
    }
    /**
     * <退出系统>
     * <绑定退出按钮>
     * @param v
     * @see [类、类#方法、类#成员]
     */
    public void exitSystem(View v){
        ScreenManager.getScreenManager().popAllActivityExceptMain(getClass());
        finish();
    }
}
