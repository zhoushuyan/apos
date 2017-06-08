package com.wbasic.apos.drivers.card;

import com.posin.device.Mifare;
import com.posin.device.Mifare.Device;

/**
 * Created by zhoushuyan on 2016-10-15.
 */

public class MifareCard {
    public static Mifare mDev = null;
   // public static InputMethodManager mImm;

    /**
     * 打开设备
     * @return
     */
    public static boolean open() {
        boolean b=false;
        try{
            if(MifareCard.mDev ==null){
                // 如未打开则打开设备
                MifareCard.mDev = Mifare.newInstance(Device.RF400U);
                MifareCard.mDev.open();
            }
            if(MifareCard.mDev!=null) {
                b = true;
            }
        }catch(Throwable e){
            b=false;
        }
        return b;
    }

    /**
     * 关闭设备
     */
    public static void close(){
        try{
            if(MifareCard.mDev !=null){
                MifareCard.mDev.close();
                MifareCard.mDev = null;
            }
        }catch(Throwable e){
            e.printStackTrace();
        }
    }

    /**
     * 蜂鸣
     */
   public static void beep() {
        if(MifareCard.mDev == null) {
            if(!open()){
                return;
            }
        }
        try {
            MifareCard.mDev.beep(1);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    /**
     * 复位
     */
    public static void reset() {
        if(MifareCard.mDev == null) {
            if(!open()){
                return;
            }
        }
        try {
            MifareCard.mDev.reset();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 读卡序列号
     */
    public static void  getCardID(){
        if(MifareCard.mDev == null) {
            if(!open()){
                return;
            }
        }
        try {
            // 获取 卡的序列号
            final byte[] id = MifareCard.mDev.getCardID();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 校验密码
     * @param sectorNo 扇区
     */
    public static void loadKey(int sectorNo){
        try{
            String strKey="FF FF FF FF FF FF";
            // 开始校验密码
            // 将 16进制字符串 转换为 byte[], 例如 "FF FF" -> {0xFF, 0xFF}
            final byte[] key = Utils.bytesFromHex(strKey, Mifare.KEY_SIZE);
            MifareCard.mDev.loadSecKey(Mifare.KeyType.KeyA, sectorNo, key);
        }catch (Throwable e){
            e.printStackTrace();
        }

    }
    /**
     * 寻卡
     */
    public static boolean detectCard() {
        if(MifareCard.mDev == null) {
            if(!open()){
                return false;
            }
        }
        try {
            // 进行 寻卡 操作，如果找不到卡片，则抛出异常
            MifareCard.mDev.detectCard();
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 读取扇区信息  2扇区 0块
     * sectorNo : 扇区号 0
     * blockNo : 块号 0
     */
    public static String readData(int sectorNo, int blockNo){
        try{
            if(MifareCard.mDev == null) {
                if(!open()){
                    return "";
                }
            }
            final byte[] data = MifareCard.mDev.readBlock(sectorNo, blockNo);
            //return Utils.bytesToHex(data);
            return  new String(data, "UTF-8");
        }catch (Throwable e){
            e.printStackTrace();
        }
        return  "";
    }
    /**
     * 读取扇区信息  2扇区 0块
     * sectorNo : 扇区号
     * blockNo : 块号
     * strValue : 块数据 字符串
     */
    public static boolean writeData(int sectorNo, int blockNo, String strValue){
        try{
            if(MifareCard.mDev == null) {
                if(!open()){
                    return false;
                }
            }
            //转换16进制字符串
            byte[] data=strValue.getBytes("UTF-8");
            strValue=Utils.bytesToHex(data);
            ///
            byte[] value = Utils.bytesFromHex(strValue, Mifare.S50_BLOCK_SIZE);
            // 将value写入指定块
            MifareCard.mDev.writeBlock(sectorNo, blockNo, value);
        }catch (Throwable e){
            e.printStackTrace();
            return false;
        }
        return  true;
    }

}
