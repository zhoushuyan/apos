package com.wbasic.apos.service;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View.OnKeyListener;
import android.view.View;
import android.widget.EditText;

import com.wbasic.apos.R;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
/**
 * Created by Administrator on 2017/5/15.
 */

public class KeyboardUtil {
    private Context ctx;
    private KeyboardView keyboardView;
    private Keyboard k1;// 字母键盘
    private Keyboard k2;// 数字键盘
    public boolean isnun = false;// 是否数据键盘
    public boolean isupper = false;// 是否大写
    public EditText ed;
    private KeyboardListener keyboardListener;
    public interface KeyboardListener {
        void onOK();
    }
    /**
     * @param ctx
     * @param parent    包含MyKeyboardView的ViewGroup
     * @param edit
     */
    public KeyboardUtil(Context ctx, View parent, EditText edit) {
        this.ctx = ctx;
        this.ed = edit;
        k1 = new Keyboard(ctx, R.xml.keyboardnumber);
       // k2 = new Keyboard(ctx, R.xml.symbols);
//        keyboardView = (KeyboardView) parent.findViewById(R.id.keyboard_view);
        keyboardView.setKeyboard(k1);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(true);
        keyboardView.setOnKeyboardActionListener(listener);
    }
    private OnKeyboardActionListener listener = new OnKeyboardActionListener() {

        @Override
        public void onPress(int primaryCode) {

        }

        @Override
        public void onRelease(int primaryCode) {

        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {

        }

        @Override
        public void onText(CharSequence text) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };
    public void setKeyboardListener(KeyboardListener keyboardListener) {
        this.keyboardListener = keyboardListener;
    }
}
