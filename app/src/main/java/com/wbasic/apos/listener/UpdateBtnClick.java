package com.wbasic.apos.listener;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wbasic.apos.R;
import com.wbasic.apos.common.pojo.Item;

import io.realm.Realm;

/**
 * Created by Administrator on 2017/4/14.
 */

public class UpdateBtnClick extends ClickListener {
    public UpdateBtnClick(Context context) {
        super(context);
    }
    @Override
    public void onClick(View v) {
        Realm realm = Realm.getDefaultInstance();
        final long b=System.currentTimeMillis();
        View parent = (View)v.getParent();
        final TextView textView=(TextView)parent.findViewById(R.id.demoview);
        textView.setText("开始更新");
        try{
            realm.executeTransaction(new Realm.Transaction(){
                @Override
                public void execute(Realm realm) {
                    for(int i=0;i<50000;i++) {
                        String id=""+i;
                        Item item = realm.where(Item.class).equalTo("itemId", id).findFirst();
                        item.setGoodId(""+i);
                        item.setPrc(9);
                        item.setPrice(10);
                        item.setSaleYn("Y");
                        item.setItemFn("商品100g商品100g商品100g商品100g商品100g"+i);
                        item.setItemName("商品100g商品100g商品100g商品100g商品100g"+i);
                    }
                    long e=System.currentTimeMillis();
                    String text=textView.getText().toString();
                    text=text+"\n"+"update:"+(e-b);
                    textView.setText(text);
                }
            });
        }catch (Exception e){
            Log.e(">>>>>>>>>","update",e);
        }

    }
}
