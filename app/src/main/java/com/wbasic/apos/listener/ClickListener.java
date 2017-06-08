package com.wbasic.apos.listener;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Context;

/**
 * Created by Administrator on 2017/4/14.
 */

public abstract  class ClickListener implements OnClickListener{
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;
    public ClickListener(Context context) {
        this.context = context;
    }
}
