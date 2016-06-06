package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Ben on 18/04/2016.
 */
public class BattleStuff implements Parcelable, Serializable {

    protected String name;
    protected int weight;
    protected int actualMod;
    protected int maxMod;
    protected String special;

    public BattleStuff(String name, int weight, int actualMod, int maxMod, String special) {
        this.name = name;
        this.weight = weight;
        this.actualMod = actualMod;
        this.maxMod = maxMod;
        this.special = special;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getActualMod() {
        return actualMod;
    }

    public void setActualMod(int actualMod) {
        this.actualMod = actualMod;
    }

    public int getMaxMod() {
        return maxMod;
    }

    public void setMaxMod(int maxMod) {
        this.maxMod = maxMod;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(weight);
        dest.writeInt(actualMod);
        dest.writeInt(maxMod);
        dest.writeString(special);
    }

    public static final Parcelable.Creator<BattleStuff> CREATOR
            = new Parcelable.Creator<BattleStuff>() {
        public BattleStuff createFromParcel(Parcel in) {
            return new BattleStuff(in);
        }

        public BattleStuff[] newArray(int size) {
            return new BattleStuff[size];
        }
    };

    protected BattleStuff(Parcel in) {
        name = in.readString();
        weight = in.readInt();
        actualMod = in.readInt();
        maxMod = in.readInt();
        special = in.readString();
    }
}
