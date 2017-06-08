package com.wbasic.apos.common.pojo;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2017/5/19.
 */

public class Order extends RealmObject {
    @PrimaryKey
    private String ordId;
    private String ordObject;//订单json
    private String comId;
    private RealmList<OrderLine> OrderLines;//订单行
    private RealmList<OrderPay> OrderPays;//订单支付
    private String ordNo;//订单编号
    private String ordType;//订单类型 G实物订单/C卡券订单
    private Date ordDate;//订单日期
    private String ordStat;//订单状态
    private String custId;//客户
    private float amt_org;//原价总额=PRICE*QTY
    private float amt_tot;//订单总额=PRC*QTY
    private float amt_dist;//折扣总额=DIST_ACCT+DIST_COUP+DIST_ADJ
    private float amt_ord;//折后总额=AMT_TOT-AMT_DIST
    private String dlvCd;//提货方式|X现场交易/Z自提/D快递送货
    private String ordDesc;//订单备注
    private float bonus;//应给积分
    private String crateBy;//创建人
    private Date createDate;//创建时间
    private String md;
    private String shopCd;//门店
    private String mediaCd;//媒体

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public String getOrdObject() {
        return ordObject;
    }

    public void setOrdObject(String ordObject) {
        this.ordObject = ordObject;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public RealmList<OrderLine> getOrderLines() {
        return OrderLines;
    }

    public void setOrderLines(RealmList<OrderLine> orderLines) {
        OrderLines = orderLines;
    }

    public RealmList<OrderPay> getOrderPays() {
        return OrderPays;
    }

    public void setOrderPays(RealmList<OrderPay> orderPays) {
        OrderPays = orderPays;
    }

    public String getOrdNo() {
        return ordNo;
    }

    public void setOrdNo(String ordNo) {
        this.ordNo = ordNo;
    }

    public String getOrdType() {
        return ordType;
    }

    public void setOrdType(String ordType) {
        this.ordType = ordType;
    }

    public Date getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(Date ordDate) {
        this.ordDate = ordDate;
    }

    public String getOrdStat() {
        return ordStat;
    }

    public void setOrdStat(String ordStat) {
        this.ordStat = ordStat;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public float getAmt_org() {
        return amt_org;
    }

    public void setAmt_org(float amt_org) {
        this.amt_org = amt_org;
    }

    public float getAmt_tot() {
        return amt_tot;
    }

    public void setAmt_tot(float amt_tot) {
        this.amt_tot = amt_tot;
    }

    public float getAmt_dist() {
        return amt_dist;
    }

    public void setAmt_dist(float amt_dist) {
        this.amt_dist = amt_dist;
    }

    public float getAmt_ord() {
        return amt_ord;
    }

    public void setAmt_ord(float amt_ord) {
        this.amt_ord = amt_ord;
    }

    public String getDlvCd() {
        return dlvCd;
    }

    public void setDlvCd(String dlvCd) {
        this.dlvCd = dlvCd;
    }

    public String getOrdDesc() {
        return ordDesc;
    }

    public void setOrdDesc(String ordDesc) {
        this.ordDesc = ordDesc;
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    public String getCrateBy() {
        return crateBy;
    }

    public void setCrateBy(String crateBy) {
        this.crateBy = crateBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    public String getShopCd() {
        return shopCd;
    }

    public void setShopCd(String shopCd) {
        this.shopCd = shopCd;
    }

    public String getMediaCd() {
        return mediaCd;
    }

    public void setMediaCd(String mediaCd) {
        this.mediaCd = mediaCd;
    }
}
