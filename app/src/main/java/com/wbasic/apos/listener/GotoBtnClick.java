package com.wbasic.apos.listener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wbasic.apos.LoginActivity;
import com.wbasic.apos.MainActivity;

/**
 * Created by Administrator on 2017/4/19.
 */

public class GotoBtnClick extends ClickListener{
    public GotoBtnClick(Context context) {
        super(context);
    }
    @Override
    public void onClick(View v) {
        View parent = (View)v.getParent();
        Intent intent = new Intent();
        //制定intent要启动的类
        intent.setClass(this.getContext(),MainActivity.class);
//        Bundle mBundle = new Bundle();
//        mBundle.putString("data1", "数据1");
//        mBundle.putString("data2", "数据2");
//        intent.putExtras(mBundle);
        //启动一个新的Activity
        this.getContext().startActivity(intent);
        //关闭当前的
        ((LoginActivity)this.getContext()).finish();
    }
}
