package com.wbasic.apos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.wbasic.apos.appbridge.jsbridge.JSBridge;
import com.wbasic.apos.R;
import com.wbasic.apos.appbridge.NativeModule;
import com.wbasic.apos.manager.ScreenManager;

import org.xwalk.core.XWalkJavascriptResult;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/4/18.
 */

public class RootActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ScreenManager.getScreenManager().pushActivity(this);
    }
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
