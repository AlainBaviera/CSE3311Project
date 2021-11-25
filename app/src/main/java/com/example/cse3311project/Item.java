package com.example.cse3311project;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    String itemName, itemCategory, itemUser;
    float itemPrice;

    public Item() {

    }

    protected Item(Parcel in) {
        itemName = in.readString();
        itemCategory = in.readString();
        itemUser = in.readString();
        itemPrice = in.readFloat();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getItemName() {
        return itemName;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public String getItemUser() {
        return itemUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
        dest.writeString(itemCategory);
        dest.writeString(itemUser);
        dest.writeFloat(itemPrice);
    }
}
