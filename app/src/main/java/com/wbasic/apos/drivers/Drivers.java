package com.wbasic.apos.drivers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.posin.device.CashDrawer;
import com.posin.device.CustomerDisplay;
import com.wbasic.apos.drivers.card.MifareCard;

import org.json.JSONArray;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/6/8.
 */

public class Drivers {
    String TAG="Drivers";
    public static String androidId;//加载设备号
    public static String requestUrl;//加载请求地址
    public static CashDrawer mCashDrawer = null;
    public static byte[] bankLogo=null;//统一加载银行Logo
    /**
     * 获得设备SN
     * @return
     */
    public String  getAndroidId(){
        return Drivers.androidId;
    }
    public Drivers(Context context){
        try {
            logoBank(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 打印文字
     * @param data
     */
    public void printer(String data){
        PrinterThread t=new PrinterThread("PT",data);
        t.start();

        // print.printer(data);
    }

    /**
     * 打开钱箱
     */
    public  void openBox(){
//        PrinterThread t=new PrinterThread("O");
//        t.start();
        // print.openBox();
        try {
            if(Drivers.mCashDrawer==null){
                Drivers.mCashDrawer= CashDrawer.newInstance();
            }
            Drivers.mCashDrawer.kickOutPin2(100);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 切纸
     */
    public void cutPaper(){
        PrinterThread t=new PrinterThread("C");
        t.start();
        //print.cutPaper();
    }
    public void bankPrint(HashMap dataMap){
        PrinterThread t=new PrinterThread("BANK",dataMap);
        t.start();
    }
    /**
     * 打印一维码
     * @param text  内容
     * @param width
     * @param height
     */
    public void createBRcode(String text,int width,int height){

        //print.createBRCodeIMG(bitmap);
        ImgPrinter imgPrinter=new ImgPrinter();
        imgPrinter.setWidth(width);
        imgPrinter.setHeight(height);
        imgPrinter.setText(text);
        PrinterThread t=new PrinterThread("PB",imgPrinter);
        t.start();

    }
    /**
     * 打印二维码
     * @param text  内容
     * @param width
     * @param height
     */
    public void createQRcode(String text,int width,int height){
        ImgPrinter imgPrinter=new ImgPrinter();
        imgPrinter.setWidth(width);
        imgPrinter.setHeight(height);
        imgPrinter.setText(text);
        PrinterThread t=new PrinterThread("PQ",imgPrinter);
        t.start();

    }

    /**
     * 去皮
     */
    public void callsetTare(){
        WeightService.callsetTare();
    }

    /**
     * 置零
     */
    public void callsetZero(){
        WeightService.callsetZero();
    }

    /**
     * 获得总量
     */
    public String getWeight(){
        return WeightService.getWeight();
    }

    /**
     *获得蓝牙设备列表
     */
    public JSONArray bluetoothDeviceList(){
        Bluetooth blue=new Bluetooth();
        return blue.bluetoothDeviceList();
    }



    /**
     *蜂鸣
     */
    public void beep(){
        MifareCard.beep();
    }

    /**
     * 读卡
     * @return
     */
    public String readDataByAddress(int address){
        int blockNo=0;//块
        MifareCard.reset();
        MifareCard.getCardID();
        MifareCard.loadKey(address);
        String no=MifareCard.readData(address, blockNo);
        return no.trim();
    }

    /**
     * 写卡
     * @param data
     * @return
     */
    public boolean writeDataByAddress(int address, String data){
        int blockNo=0;//块
        MifareCard.reset();
        MifareCard.getCardID();
        MifareCard.loadKey(address);
        MifareCard.writeData(address,blockNo,data.trim());
        return true;
    }
    /**
     * 复位副屏
     */
    public void resetDisplay(){
        CustomerDisplay dsp = null;
        try {
            dsp = CustomerDisplay.newInstance();
            dsp.reset();
        } catch (Throwable e) {
            e.printStackTrace();
            Log.d(TAG,"",e);
        } finally {
            // 用完要记得关掉
            if(dsp != null)
                dsp.close();
        }
    }

    /**
     * 打开副屏桌面
     */
    public void openLauncher() {
        SecondDisplayService service=new SecondDisplayService();
        service.openLauncher();
    }
    /**
     * 打开副屏显示程序
     */
    public void openPosinLauncher() {
        SecondDisplayService service=new SecondDisplayService();
        service.openPosinLauncher();
    }
    /**
     * 副屏关机
     */
    public void shutdown() {
        SecondDisplayService service=new SecondDisplayService();
        service.shutdown();
    }
    /**
     * 顶部区域图片
     * @param logoUrl
     */
    public void setTopLogoImg(String logoUrl){
        SecondDisplayService service=new SecondDisplayService();
        service.setTopLogoImg(logoUrl);
    }

    /**
     * 顶部区域文字
     * @param txt
     */
    public void setTopText(String txt){
        SecondDisplayService service=new SecondDisplayService();
        service.setTopText(txt);
    }
    /**
     * 显示左边视频
     * @param videoUrl
     */
    public void setVideoLeft(String videoUrl){
        SecondDisplayService service=new SecondDisplayService();
        service.setVideoLeft(videoUrl);
    }
    /**
     * 显示左边二维码
     * @param QRCodeUrl
     */
    public void setQRCodeLeft(String QRCodeUrl){
        SecondDisplayService service=new SecondDisplayService();
        service.setQRCodeLeft(QRCodeUrl);
    }
    /**
     * 显示左边图片
     * @param imageUrl
     */
    public void setImageLeft(String imageUrl){
        SecondDisplayService service=new SecondDisplayService();
        service.setImageLeft(imageUrl);
    }
    /**
     * 添加右边标题
     * @param txt
     */
    public void setTxt1Right(String txt){
        SecondDisplayService service=new SecondDisplayService();
        service.setTxt1Right(txt);
    }

    /**
     * 添加右边内容
     * @param txt
     */
    public void setTxt2Right(String txt){
        SecondDisplayService service=new SecondDisplayService();
        service.setTxt2Right(txt);
    }
    /**
     * 加载银行Logo
     * @param context
     * @return
     * @throws IOException
     */
    public  void   logoBank(Context context) throws IOException {
        Bitmap nbm = BitmapFactory.decodeStream(context.getAssets().open("logo.bmp"));
        int width=nbm.getWidth();
        int length=nbm.getHeight();
        byte[] bitbuf = new byte[width / 8];
        byte[] imgbuf = new byte[width / 8 * length + 8];
        int s = 0;
        // 打印光栅位图的指令
        imgbuf[0] = 29;// 十六进制0x1D
        imgbuf[1] = 118;// 十六进制0x76
        imgbuf[2] = 48;// 30
        imgbuf[3] = 0;// 位图模式 0,1,2,3
        // 表示水平方向位图字节数（xL+xH × 256）
        imgbuf[4] = (byte) (width / 8);
        imgbuf[5] = 0;
        // 表示垂直方向位图点数（ yL+ yH × 256）
        imgbuf[6] = (byte) (length % 256);//
        imgbuf[7] = (byte) (length / 256);
        s = 7;
        for (int i = 0; i < length; i++) {// 循环位图的高度
            for (int k = 0; k < width / 8; k++) {// 循环位图的宽度
                int c0 = nbm.getPixel(k * 8 + 0, i);// 返回指定坐标的颜色
                int p0;
                if (c0 == -1)// 判断颜色是不是白色
                    p0 = 0;// 0,不打印该点
                else {
                    p0 = 1;// 1,打印该点
                }
                int c1 = nbm.getPixel(k * 8 + 1, i);
                int p1;
                if (c1 == -1)
                    p1 = 0;
                else {
                    p1 = 1;
                }
                int c2 = nbm.getPixel(k * 8 + 2, i);
                int p2;
                if (c2 == -1)
                    p2 = 0;
                else {
                    p2 = 1;
                }
                int c3 = nbm.getPixel(k * 8 + 3, i);
                int p3;
                if (c3 == -1)
                    p3 = 0;
                else {
                    p3 = 1;
                }
                int c4 = nbm.getPixel(k * 8 + 4, i);
                int p4;
                if (c4 == -1)
                    p4 = 0;
                else {
                    p4 = 1;
                }
                int c5 = nbm.getPixel(k * 8 + 5, i);
                int p5;
                if (c5 == -1)
                    p5 = 0;
                else {
                    p5 = 1;
                }
                int c6 = nbm.getPixel(k * 8 + 6, i);
                int p6;
                if (c6 == -1)
                    p6 = 0;
                else {
                    p6 = 1;
                }
                int c7 = nbm.getPixel(k * 8 + 7, i);
                int p7;
                if (c7 == -1)
                    p7 = 0;
                else {
                    p7 = 1;
                }
                int value = p0 * 128 + p1 * 64 + p2 * 32 + p3 * 16 + p4 * 8
                        + p5 * 4 + p6 * 2 + p7;
                bitbuf[k] = (byte) value;
            }
            for (int t = 0; t < width / 8; t++) {
                s++;
                imgbuf[s] = bitbuf[t];
            }
        }
        Drivers.bankLogo=imgbuf;
    }
}
