package com.wbasic.apos.common.pojo;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2017/5/19.
 * 订单行表
 */

public class OrderLine extends RealmObject {
    private String lineId;//订单行ID
    private float price;//商品原价
    private float prc;//折后价
    private float qty;//商品数量
    private float amt_org;//原价总额=PRICE*QTY
    private float amt_tot;//订单总额=PRC*QTY
    private float amt_dist;//折扣总额=DIST_ACCT+DIST_COUP+DIST_ADJ
    private float amt_ord;//折后总额=AMT_TOT-AMT_DIST
    private String goodId;
    private String itemId;
    private float cost;//成本
    private Date ordDate;
    private String shopCd;//门店
    private String mediaCd;//媒体
    private float dist_coup;//优惠折扣
    private float emp_x;//销售X
    private float emp_y;//销售Y
    private float emp_z;//销售Z

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrc() {
        return prc;
    }

    public void setPrc(float prc) {
        this.prc = prc;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
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

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Date getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(Date ordDate) {
        this.ordDate = ordDate;
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

    public float getDist_coup() {
        return dist_coup;
    }

    public void setDist_coup(float dist_coup) {
        this.dist_coup = dist_coup;
    }

    public float getEmp_x() {
        return emp_x;
    }

    public void setEmp_x(float emp_x) {
        this.emp_x = emp_x;
    }

    public float getEmp_y() {
        return emp_y;
    }

    public void setEmp_y(float emp_y) {
        this.emp_y = emp_y;
    }

    public float getEmp_z() {
        return emp_z;
    }

    public void setEmp_z(float emp_z) {
        this.emp_z = emp_z;
    }
}
