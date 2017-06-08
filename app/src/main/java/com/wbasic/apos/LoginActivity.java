package com.wbasic.apos;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.posin.device.CashDrawer;
import com.posin.device.SDK;
import com.wbasic.apos.activity.RootActivity;
import com.wbasic.apos.drivers.Drivers;
import com.wbasic.apos.listener.DelBtnClick;
import com.wbasic.apos.listener.GotoBtnClick;
import com.wbasic.apos.listener.InsertBtnClick;
import com.wbasic.apos.listener.UpdateBtnClick;
import com.wbasic.apos.manager.ScreenManager;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends RootActivity {
    protected Drivers drivers;
    TextView textView=null;
    Button button1=null;
    Button button2=null;
    Button button3=null;
    Button button4=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenManager.getScreenManager().pushActivity(this);
        setContentView(R.layout.activity_login);
        initDB();
        init();
    }

    private void init(){
        Drivers.androidId = Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        // weipos 自带硬件初始化
        drivers = new Drivers(this);
        try {
            SDK.init(this);
        } catch (Throwable e1) {
            e1.printStackTrace();
        }
        try {
            Drivers.mCashDrawer = CashDrawer.newInstance();
        }catch (Throwable e){
            e.printStackTrace();
        }
        textView=(TextView)this.findViewById(R.id.demoview);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button1.setOnClickListener(new InsertBtnClick(this));
        button2.setOnClickListener(new UpdateBtnClick(this));
        button3.setOnClickListener(new DelBtnClick(this));
        button4.setOnClickListener(new GotoBtnClick(this));
    }
    private void initDB(){
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("wbasic.realm")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);
    }
}

