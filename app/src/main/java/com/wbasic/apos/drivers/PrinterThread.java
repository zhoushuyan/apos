package com.wbasic.apos.drivers;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhoushuyan on 2016-09-25.
 */

public class PrinterThread extends Thread {
    private static Lock lock = new ReentrantLock();
    PrinterService printer=new PrinterService();
    private String commond;
    private String datas;
    private ImgPrinter imgPrinter;
    private HashMap dataMap;
    public PrinterThread(String commond){
        this.commond=commond;
    }
    public PrinterThread(String commond, String datas){
        this.commond=commond;
        this.datas=datas;
    }
    public PrinterThread(String commond, ImgPrinter imgPrinter){
        this.commond=commond;
        this.imgPrinter=imgPrinter;
    }
    public PrinterThread(String commond, HashMap dataMap){
        this.commond=commond;
        this.dataMap=dataMap;
    }
    public synchronized void run() {
        lock.lock();
        try{
                //切纸
                if("C".equals(commond)){
                    printer.cutPaper();
                }
                //开钱箱
                if("O".equals(commond)){
                    printer.openBox();
                }
                //打印文字
                if("PT".equals(commond)){
                    if(datas!=null)
                        printer.printer(datas);
                }
                //打印二维码
                if("PQ".equals(commond)){
                    int width=imgPrinter.getWidth();
                    int height=imgPrinter.getHeight();
                    String text=imgPrinter.getText();
                    Bitmap bitmap = printer.getQRCodeByte(text, width, height);
                    if(bitmap!=null)
                        printer.createQRCodeIMG(bitmap);
                }
                //打印一维码
                if("PB".equals(commond)){
                    int width=imgPrinter.getWidth();
                    int height=imgPrinter.getHeight();
                    String text=imgPrinter.getText();
                    Bitmap bitmap = printer.getBRCodeByte(text, width, height);
                    if(bitmap!=null)
                        printer.createBRCodeIMG(bitmap);
                }
                if("BANK".equals(commond)){
                    if(dataMap!=null){
                        printer.printerBank(dataMap);
                    }
                }
            }catch(Exception e){

            }finally {
                lock.unlock();
            }

    }
}
