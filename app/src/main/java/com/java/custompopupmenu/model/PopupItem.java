package com.java.custompopupmenu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopupItem {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("itemIcon")
    @Expose
    private int itemIcon;

    public PopupItem(String id, String itemName, int itemIcon) {
        this.id = id;
        this.itemName = itemName;
        this.itemIcon = itemIcon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(int itemIcon) {
        this.itemIcon = itemIcon;
    }
}
