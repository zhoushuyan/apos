package com.wbasic.apos.common.pojo;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2017/6/8.
 * 商品分类
 */

public class GmCate extends RealmObject {
    @PrimaryKey
    private String cateId;
    private String comId;//商户ID
    private String cateCd;//商品类别
    private String cateCdPt;//父分类
    private String cateFn;//类别名称
    private String cateName;//分类名称
    private String ordNo;
    private String imgUrl;
    private RealmList<Item> items;

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getCateCd() {
        return cateCd;
    }

    public void setCateCd(String cateCd) {
        this.cateCd = cateCd;
    }

    public String getCateCdPt() {
        return cateCdPt;
    }

    public void setCateCdPt(String cateCdPt) {
        this.cateCdPt = cateCdPt;
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

    public String getOrdNo() {
        return ordNo;
    }

    public void setOrdNo(String ordNo) {
        this.ordNo = ordNo;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public RealmList<Item> getItems() {
        return items;
    }

    public void setItems(RealmList<Item> items) {
        this.items = items;
    }
}
