package com.wbasic.apos.drivers;

import android.util.Log;

import com.posin.secondarydisplay.client.SecondaryDisplay;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/10.
 */

public class SecondDisplayService {
    private static final String TAG = "SecondDisplayService";

    /**
     * 打开副屏桌面
     */
    public void openLauncher() {
        Log.d(TAG, "open launcher");
        Map<String, String> map = new HashMap<String, String>();
        map.put("action", "shell");
        map.put("command", "am start -n com.android.launcher3/.Launcher");
        try {
            (new SecondaryDisplay()).execute(map);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开副屏显示程序
     */
    public void openPosinLauncher() {
        Log.d(TAG, "open posin launcher");
        Map<String, String> map = new HashMap<String,String>();
        map.put("action", "shell");
        map.put("command", "am start -n com.posin.secondarydisplay/.MainActivity");

        try {
            (new SecondaryDisplay()).execute(map);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 副屏关机
     */
    public void shutdown() {
        Log.d(TAG, "shutdown secondary display");
        Map<String, String> map = new HashMap<String,String>();
        map.put("action", "power");
        map.put("command", "poweroff");
        try {
            (new SecondaryDisplay()).execute(map);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 顶部区域图片
     * @param logoUrl
     */
    public void setTopLogoImg(String logoUrl){
        setViewProperty("top_image", "source", logoUrl);
    }

    /**
     * 顶部区域文字
     * @param txt
     */
    public void setTopText(String txt){
        setViewProperty("top_text", "text", txt);
    }

    /**
     * 显示左边视频
     * @param videoUrl
     */
    public void setVideoLeft(String videoUrl){
        clearView("left");
        addView("left_video", "left", "video", "source",videoUrl);
    }
    /**
     * 显示左边二维码
     * @param QRCodeUrl
     */
    public void setQRCodeLeft(String QRCodeUrl){
        clearView("left");
        addView("left_qrcode", "left", "qrcode", "content", QRCodeUrl);
    }
    /**
     * 显示左边图片
     * @param imageUrl
     */
    public void setImageLeft(String imageUrl){
        clearView("left");
        addView("left_image", "left", "image", "source",imageUrl);
    }

    /**
     * 添加右边标题
     * @param txt
     */
    public void setTxt1Right(String txt){
        setViewProperty("right_text1", "text",txt);
    }

    /**
     * 添加右边内容
     * @param txt
     */
    public void setTxt2Right(String txt){
        setViewProperty("right_text2", "text", txt);
    }

    /**
     * 添加显示
     * @param name
     * @param parent
     * @param type
     * @param prop
     * @param value
     */
    private void addView(String name, String parent, String type, String prop, String value) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("action", "add");
        map.put("name", name);
        map.put("parent", parent);
        map.put("type", type);
        map.put(prop, value);
        try {
            (new SecondaryDisplay()).send(map);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    /**
     * 清空显示
     * @param name
     */
    private void clearView(String name) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("action", "clear");
        map.put("name", name);
        try {
            (new SecondaryDisplay()).send(map);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除显示
     * @param name
     */
    private void removeView(String name) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("action", "remove");
        map.put("name", name);
        try {
            (new SecondaryDisplay()).send(map);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    /**
     * 设置副屏属性
     * @param name
     * @param prop
     * @param value
     */
    private void setViewProperty(String name, String prop, String value) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("action", "set");
        map.put("name", name);
        map.put(prop, value);
        try {
            (new SecondaryDisplay()).send(map);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
