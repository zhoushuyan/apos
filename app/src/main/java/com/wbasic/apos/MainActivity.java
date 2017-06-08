package com.wbasic.apos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.wbasic.apos.activity.RootActivity;
import com.wbasic.apos.activity.WebViewActivity;
import com.wbasic.apos.listener.DelBtnClick;
import com.wbasic.apos.listener.InsertBtnClick;
import com.wbasic.apos.listener.UpdateBtnClick;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends WebViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle bundle = getIntent().getExtras();
//        String data1 = bundle.getString( "data1" );
//        String data2 = bundle.getString( "data2" );
//        Log.d("======",">>>>>>>>>>>>>=="+data1);
//        Log.d("======",">>>>>>>>>>>>>=="+data2);
        this.loadWebView(this,"file:///android_asset/index.html");
    }

}
