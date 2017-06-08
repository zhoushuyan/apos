package com.wbasic.apos.drivers;

import android.os.RemoteException;

import com.thyb.weightservice.IWeight;
import com.thyb.weightservice.Weight;

import java.text.DecimalFormat;

/**
 * 总量服务
 * Created by zhoushuyan on 2016-09-18.
 */
public class WeightService {
    static DecimalFormat df0 = new DecimalFormat("#0");
    static DecimalFormat df1 = new DecimalFormat("#0.0");
    static DecimalFormat df2 = new DecimalFormat("#0.00");
    static DecimalFormat df3 = new DecimalFormat("#0.000");
    public static IWeight iWeight;
    /**
     * 去皮操作
     */
    public static void callsetTare() {
        try {
            iWeight.setTare();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     *  置零操作
     */
    public static void callsetZero() {
        try {
            //play();
            iWeight.setZero();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得总量
     * @return
     */
    public static String getWeight(){
        String netweight="0";
        try {
            Weight weight = new Weight();
            weight=iWeight.getWeight();
            int pnumber=weight.getPointnumber();
            switch (pnumber) {
                case 0:
                    netweight=df0.format(weight.getNetWeight());
                    break;
                case 1:
                    netweight=(df1.format(weight.getNetWeight()/10f));
                    break;
                case 2:
                    netweight=(df2.format(weight.getNetWeight()/100f));
                    break;
                case 3:
                    netweight=(df3.format(weight.getNetWeight()/1000f));
                    break;
                default:
                    netweight=(df3.format(weight.getNetWeight()/1000f));
                    break;
            }
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return netweight;
    }
}
