package com.wbasic.apos.common.pojo;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2017/5/19.
 * 订单支付表
 */

public class OrderPay extends RealmObject {
    private String payId;
    private String ordId;
    private String ordSeq;
    private String typeCd;//O订单/C储值/R退货退款/D退卡退款
    private String payCd;//支付方式
    private String payStat;//订单状态
    private float payAmt;//支付金额
    private Date payDate;//支付时间
    private String cnfmBy;//确认人
    private String cnfmDate;//确认时间

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public String getOrdSeq() {
        return ordSeq;
    }

    public void setOrdSeq(String ordSeq) {
        this.ordSeq = ordSeq;
    }

    public String getTypeCd() {
        return typeCd;
    }

    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }

    public String getPayCd() {
        return payCd;
    }

    public void setPayCd(String payCd) {
        this.payCd = payCd;
    }

    public String getPayStat() {
        return payStat;
    }

    public void setPayStat(String payStat) {
        this.payStat = payStat;
    }

    public float getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(float payAmt) {
        this.payAmt = payAmt;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getCnfmBy() {
        return cnfmBy;
    }

    public void setCnfmBy(String cnfmBy) {
        this.cnfmBy = cnfmBy;
    }

    public String getCnfmDate() {
        return cnfmDate;
    }

    public void setCnfmDate(String cnfmDate) {
        this.cnfmDate = cnfmDate;
    }
}
