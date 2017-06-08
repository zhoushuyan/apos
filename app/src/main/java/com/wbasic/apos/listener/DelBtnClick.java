package com.wbasic.apos.listener;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wbasic.apos.R;
import com.wbasic.apos.common.pojo.Item;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Administrator on 2017/4/14.
 */

public class DelBtnClick extends ClickListener {
    public DelBtnClick(Context context) {
        super(context);
    }

    @Override
    public void onClick(View v) {
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Item> items=  realm.where(Item.class).findAll();
        final long b=System.currentTimeMillis();
        View parent = (View)v.getParent();
        final TextView textView=(TextView)parent.findViewById(R.id.demoview);
        textView.setText("开始删除");
        try{
            realm.executeTransaction(new Realm.Transaction(){
                @Override
                public void execute(Realm realm) {
                    String text=textView.getText().toString();
                    items.deleteAllFromRealm();
                    text=textView.getText().toString();
                    text=text+"\n"+"del rows:"+items.size();
                    textView.setText(text);
                    items.deleteAllFromRealm();
                    long e=System.currentTimeMillis();
                    text=textView.getText().toString();
                    text=text+"\n"+"del:"+(e-b);
                    textView.setText(text);
                }
            });
        }catch (Exception e){
            Log.e(">>>>>>>>>","del",e);
        }

    }
}
