package com.wbasic.apos.common.pojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2017/4/14.
 */

public class Item extends RealmObject {
    @PrimaryKey
    private String itemId;//品相ID
    private String comId;//商户ID
    private String shopCd;//店铺ID
    private String itemName;//品名称
    private String itemNo;//品编码
    private String saleYn;//是否可以销售
    private String itemFn;//全名称
    private String imgUrl;//图片地址
    private String goodId;//商品ID
    private String goodNo;//商品编号
    private String statusCd;//审核状态
    private float cost;//商品成本
    private float price;//会员价格
    private float prc;//会员价格
    private float stortQty;//库存
    private String saleType;//销售类型，是否可以无库存销售
    private String unitQty;//基本商品数量
    private String unitCd;//单位Cd
    private String unitName;//单位名称
    private String barCode;//商品条码
    private String sarCode;//速记码
    private String o2oGoodDlvNo;//o2o提货编号
    private String flagWeight;//是否称重商品
    private String goodTip;//商品摘要
    private String goodDesc;//商品描述
    private String md;//商品经理
    private String mark;//备注
    private String cateCd;//商品类别
    private String cateFn;//类别名称
    private String cateName;//分类名称
    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getShopCd() {
        return shopCd;
    }

    public void setShopCd(String shopCd) {
        this.shopCd = shopCd;
    }

    public String getGoodNo() {
        return goodNo;
    }

    public void setGoodNo(String goodNo) {
        this.goodNo = goodNo;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getStortQty() {
        return stortQty;
    }

    public void setStortQty(float stortQty) {
        this.stortQty = stortQty;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public String getUnitQty() {
        return unitQty;
    }

    public void setUnitQty(String unitQty) {
        this.unitQty = unitQty;
    }

    public String getUnitCd() {
        return unitCd;
    }

    public void setUnitCd(String unitCd) {
        this.unitCd = unitCd;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getSarCode() {
        return sarCode;
    }

    public void setSarCode(String sarCode) {
        this.sarCode = sarCode;
    }

    public String getO2oGoodDlvNo() {
        return o2oGoodDlvNo;
    }

    public void setO2oGoodDlvNo(String o2oGoodDlvNo) {
        this.o2oGoodDlvNo = o2oGoodDlvNo;
    }

    public String getFlagWeight() {
        return flagWeight;
    }

    public void setFlagWeight(String flagWeight) {
        this.flagWeight = flagWeight;
    }

    public String getGoodTip() {
        return goodTip;
    }

    public void setGoodTip(String goodTip) {
        this.goodTip = goodTip;
    }

    public String getGoodDesc() {
        return goodDesc;
    }

    public void setGoodDesc(String goodDesc) {
        this.goodDesc = goodDesc;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getCateCd() {
        return cateCd;
    }

    public void setCateCd(String cateCd) {
        this.cateCd = cateCd;
    }

    public String getCateFn() {
        return cateFn;
    }

    public void setCateFn(String cateFn) {
        this.cateFn = cateFn;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getSaleYn() {
        return saleYn;
    }

    public void setSaleYn(String saleYn) {
        this.saleYn = saleYn;
    }

    public String getItemFn() {
        return itemFn;
    }

    public void setItemFn(String itemFn) {
        this.itemFn = itemFn;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
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
}