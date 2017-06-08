package com.wbasic.apos.drivers;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.posin.device.Printer;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 打印方法
 * Created by zhoushuyan on 2016-09-18.
 */
public class PrinterService {
    private String getVal(Object obj){
        if(obj==null){
            return "";
        }else{
            return String.valueOf(obj);
        }
    }
    public  void printerBank(HashMap map){
        try {
            String MerNo=getVal(map.get("MerNo"));//商户号
            String operator=getVal(map.get("operator"));//操作员
            String TerNo=getVal(map.get("TerNo"));//终端号
            String ReqSn=getVal(map.get("ReqSn"));//流水号
            String batchNO=getVal(map.get("batchNO"));//批次号
            String bankNO=getVal(map.get("bankNO"));//主账号
            String amtStr=getVal(map.get("amt"));//消费金额
            String consum=getVal(map.get("consum"));//消费类型
            String acqBank=getVal(map.get("acqBank"));//发卡行
            String issuerBank=getVal(map.get("issuerBank"));//收单行
            String validDate=getVal(map.get("validDate"));//卡有效
            String cardType=getVal(map.get("cardType"));//卡类型
            byte[] sendImg=Drivers.bankLogo;
            byte[] start = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x1B, 0x40, 0x1B, 0x33, 0x00 };
            byte[] end = { 0x1d, 0x4c, 0x1f, 0x00 };
            final byte[] CMD_CUT = {0x1D, 0x56, 0x01};
            final byte[] CMD_INIT = { 0x1B, 0x40};
            String heard="兰州银行股份有限公司 \n" ;
            String heard1= "  商户存根                 MERCHANT COPY\n" ;
            String heard2= "  持卡人存根             CARDHOLDER COPY\n" ;
            String body="  ---------------------------------------\n" +
                    "  商户号："+MerNo+"\n" +
                    "  终端号："+TerNo+"\n" +
                    "  操作员："+operator+"\n" +
                    "  收单行："+acqBank+"\n" +
                    "  发卡行："+issuerBank+"\n" +
                    "  发类型："+cardType+"\n" ;
            String bankNo="  卡号："+bankNO+"\n" ;
            String body1= "  交易类型："+consum+"\n" +
                    "  有效期："+validDate+"\n" +
                    "  批次号："+batchNO+"\n" +
                    "  凭证号："+ReqSn+"\n" +
                    "  授权码：\n" +
                    "  日期/时间："+getDateTimeString()+"\n" ;
            String amt="  金额  RMB："+amtStr+"\n" ;
            String body2="  备注/PEFERENCE\n" +
                    "  ---------------------------------------\n" +
                    "  持卡人签名：\n\n\n\n" +
                    "  ---------------------------------------\n" +
                    "  本人确认以上交易，同意将其计入本卡账户\n" +
                    "  服务热线：96799\n" +
                    "  PAX-S90-32007201\n\n";
            String body3="  备注/PEFERENCE\n" +
                    "  ---------------------------------------\n" +
                    "  本人确认以上交易，同意将其计入本卡账户\n" +
                    "  服务热线：96799\n" +
                    "  PAX-S90-32007201\n\n";
            byte[] heardData = heard.getBytes("GBK");
            byte[] heard1Data = heard1.getBytes("GBK");
            byte[] heard2Data = heard2.getBytes("GBK");
            byte[] bodyData = body.getBytes("GBK");
            byte[] bankNoData=bankNo.getBytes("GBK");
            byte[] body1Data = body1.getBytes("GBK");
            byte[] amtData = amt.getBytes("GBK");
            byte[] body2Data = body2.getBytes("GBK");
            byte[] body3Data = body3.getBytes("GBK");
            //打印
            List<byte[]> list=new ArrayList();
            ////////商户存根
            list.add(CMD_INIT);
            list.add(start);
            list.add(center());
            list.add(sendImg);
            list.add(end);
            list.add(CMD_INIT);
            list.add(center());
            list.add(fontBold());
            list.add(heardData);
            list.add(left());
            list.add(fontStandard());
            list.add(heard1Data);
            list.add(bodyData);
            list.add(fontBold());
            list.add(bankNoData);
            list.add(fontStandard());
            list.add(body1Data);
            list.add(fontBold());
            list.add(amtData);
            list.add(fontStandard());
            list.add(body2Data);
            list.add("\n\n\n\n".getBytes());
            list.add(CMD_CUT);
            //////////////////////持卡人存根
            list.add(CMD_INIT);
            list.add(start);
            list.add(center());
            list.add(sendImg);
            list.add(end);
            list.add(CMD_INIT);
            list.add(center());
            list.add(fontBold());
            list.add(heardData);
            list.add(left());
            list.add(fontStandard());
            list.add(heard2Data);
            list.add(bodyData);
            list.add(fontBold());
            list.add(bankNoData);
            list.add(fontStandard());
            list.add(body1Data);
            list.add(fontBold());
            list.add(amtData);
            list.add(fontStandard());
            list.add(body3Data);
            list.add("\n\n\n\n".getBytes());
            list.add(CMD_CUT);
            this.write(list);
        }catch (Throwable e) {
            e.getMessage();
        }
    }
    /**
     * 打印控件
     * @param datas
     */
    public void printer(String datas){
        try{
            byte[] data = datas.getBytes("GBK");
            printer(data);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 写打印命令  final byte[] CMD_INIT = { 0x1B, 0x40};
     * @param bytesList
     */
    private synchronized  void write(List<byte[]> bytesList){
        Printer p = null;
        try {
            p = Printer.newInstance();
            OutputStream os = p.getOutputStream();
            for(byte[] bytes:bytesList){
                os.write(bytes);
            }
        }catch (Throwable e) {
            e.getMessage();
        } finally {
            if(p != null) {
                p.close();
            }
        }
    }
    /**
     * 打开钱箱
     */
    public void openBox(){
        try {
            List<byte[]> list=new ArrayList();
            final byte[] CMD_INIT = { 0x1B, 0x40};
            byte[] data = new byte[] { 0x1B, 0x70, 0x7 };
            list.add(CMD_INIT);
            list.add(data);
            this.write(list);
        }catch (Throwable e) {
            e.getMessage();
        }
    }
    /**
     * 打印数据
     * @param data
     */
    private void printer(byte[] data){
        try {
            //打印
            List<byte[]> list=new ArrayList();
            final byte[] CMD_INIT = { 0x1B, 0x40};
            list.add(CMD_INIT);
            list.add(center());
            list.add(data);
            this.write(list);
        }catch (Throwable e) {
            e.getMessage();
        }
        //cutPaper();
    }
    /**
     * 切纸
     */
    public void cutPaper(){
        try {
            //打印
            List<byte[]> list=new ArrayList();
            final byte[] CMD_INIT = { 0x1B, 0x40};
            final byte[] CMD_CUT = {0x1D, 0x56, 0x01};
            list.add(CMD_INIT);
            list.add("\n\n\n\n".getBytes());
            list.add(CMD_CUT);
            this.write(list);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    /**
     * 生成二维码
     * @param url
     * @param widths
     * @param heights
     * @return
     */
    public Bitmap getQRCodeByte(String url, int widths, int heights) {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, widths, heights);
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (matrix.get(x, y)) {
                        pixels[y * width + x] = 0xff000000;
                    }else {
                        pixels[y * width + x] = 0xffffffff;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 生成一维码
     * @param url
     * @param widths
     * @param heights
     * @return
     */
    public Bitmap getBRCodeByte(String url, int widths, int heights) {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(url, BarcodeFormat.CODE_128, widths, heights);
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (matrix.get(x, y)) {
                        pixels[y * width + x] = 0xff000000;
                    }else {
                        pixels[y * width + x] = 0xffffffff;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 打印出二维码
     * @param bitmap
     */
    public void createQRCodeIMG(Bitmap bitmap) {
        //peripheral.printCenter();
        try {
            List<byte[]> list=new ArrayList();
            byte[] start = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x1B, 0x40, 0x1B, 0x33, 0x00 };
            byte[] draw2PxPoint = draw2PxPoint(bitmap,1000);
            byte[] end = { 0x1d, 0x4c, 0x1f, 0x00 };
            list.add(start);
            list.add(this.center());
            list.add(draw2PxPoint);
            list.add(end);
            this.write(list);
        }catch (Throwable e) {
            e.printStackTrace();
        }
    }
    /**
     * 打印出一维码
     * @param bitmap
     */
    public void createBRCodeIMG(Bitmap bitmap) {
        //peripheral.printCenter();
        try {
            List<byte[]> list=new ArrayList();
            byte[] start = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x1B, 0x40, 0x1B, 0x33, 0x00 };
            byte[] draw2PxPoint = draw2PxPoint(bitmap,5000);
            byte[] end = { 0x1d, 0x4c, 0x1f, 0x00 };
            list.add(start);
            list.add(this.center());
            list.add(draw2PxPoint);
            list.add(end);
            this.write(list);


        }catch (Throwable e) {
            e.printStackTrace();
        }
    }
    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
    public static byte[] draw2PxPoint(Bitmap bmp, int addsize) {
        int size = bmp.getWidth() * bmp.getHeight() / 8 + addsize;
        byte[] data = new byte[size];
        int k = 0;
        //设置行距为0的指令
        data[k++] = 0x1B;
        data[k++] = 0x33;
        data[k++] = 0x00;
        // 逐行打印
        for (int j = 0; j < bmp.getHeight() / 24f; j++) {
            //打印图片的指令
            data[k++] = 0x1B;
            data[k++] = 0x2A;
            data[k++] = 33;
            data[k++] = (byte) (bmp.getWidth() % 256); //nL
            data[k++] = (byte) (bmp.getWidth() / 256); //nH
            //对于每一行，逐列打印
            for (int i = 0; i < bmp.getWidth(); i++) {
                //每一列24个像素点，分为3个字节存储
                for (int m = 0; m < 3; m++) {
                    //每个字节表示8个像素点，0表示白色，1表示黑色
                    for (int n = 0; n < 8; n++) {
                        byte b = px2Byte(i, j * 24 + m * 8 + n, bmp);
                        data[k] += data[k] + b;
                    }
                    k++;
                }
            }
            data[k++] = 10;//换行
        }
        return data;
    }

    public static byte px2Byte(int x, int y, Bitmap bit) {
        if (x < bit.getWidth() && y < bit.getHeight()) {
            byte b;
            int pixel = bit.getPixel(x, y);
            int red = (pixel & 0x00ff0000) >> 16; // 取高两位
            int green = (pixel & 0x0000ff00) >> 8; // 取中两位
            int blue = pixel & 0x000000ff; // 取低两位
            int gray = RGB2Gray(red, green, blue);
            if (gray < 128) {
                b = 1;
            } else {
                b = 0;
            }
            return b;
        }
        return 0;
    }

    /**
     * 图片灰度的转化
     */
    private static int RGB2Gray(int r, int g, int b) {
        int gray = (int) (0.29900 * r + 0.58700 * g + 0.11400 * b);  //灰度转化公式
        return gray;
    }
    public static final byte[][] byteCommands = {
            { 0x1b, 0x40 },// 复位打印机
            { 0x1b, 0x4d, 0x00 },// 标准ASCII字体
            { 0x1b, 0x4d, 0x01 },// 压缩ASCII字体
            { 0x1d, 0x21, 0x00 },// 字体不放大
            { 0x1d, 0x21, 0x02 },// 宽高加倍
            { 0x1d, 0x21, 0x11 },// 宽高加倍
            { 0x1b, 0x45, 0x00 },// 取消加粗模式
            { 0x1b, 0x45, 0x01 },// 选择加粗模式
            { 0x1b, 0x7b, 0x00 },// 取消倒置打印
            { 0x1b, 0x7b, 0x01 },// 选择倒置打印
            { 0x1d, 0x42, 0x00 },// 取消黑白反显
            { 0x1d, 0x42, 0x01 },// 选择黑白反显
            { 0x1b, 0x56, 0x00 },// 取消顺时针旋转90°
            { 0x1b, 0x56, 0x01 },// 选择顺时针旋转90°
            { 0x1b, 0x61, 0x30 },// 左对齐
            { 0x1b, 0x61, 0x31 },// 居中对齐
            { 0x1b, 0x61, 0x32 }// 右对齐
    };

    public byte[] reset() {
        return byteCommands[0];
    }

    public byte[] left() {
        return byteCommands[14];
    }

    public byte[] right() {
        return byteCommands[16];
    }

    public byte[] center() {
        return byteCommands[15];
    }
    public byte[] fontBold() {
        return byteCommands[7];
    }
    public byte[] fontStandard() { return byteCommands[6]; }
    public static final String simple  = "yyyy/MM/dd HH:mm:ss";
    public static String getDateTimeString() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(simple, Locale.CHINA);
        String str = sdf.format(new Date());
        return str;
    }
}
