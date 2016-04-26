package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ben on 18/04/2016.
 */
public class Armor extends BattleStuff implements Parcelable {

    protected int soak;
    protected int contactDef;
    protected int rangeDef;


    public Armor()
    {
        super("", 0, 0, 0, "");
        this.soak = 0;
        this.contactDef = 0;
        this.rangeDef = 0;
    }

    public Armor(String name, int weight, int actualMod, int maxMod, String special, int soak, int contactDef, int rangeDef) {
        super(name, weight, actualMod, maxMod, special);
        this.soak = soak;
        this.contactDef = contactDef;
        this.rangeDef = rangeDef;
    }


    public int getSoak() {
        return soak;
    }

    public void setSoak(int soak) {
        this.soak = soak;
    }

    public int getContactDef() {
        return contactDef;
    }

    public void setContactDef(int contactDef) {
        this.contactDef = contactDef;
    }

    public int getRangeDef() {
        return rangeDef;
    }

    public void setRangeDef(int rangeDef) {
        this.rangeDef = rangeDef;
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
        dest.writeInt(soak);
        dest.writeInt(contactDef);
        dest.writeInt(rangeDef);
    }

    public static final Parcelable.Creator<Armor> CREATOR
            = new Parcelable.Creator<Armor>() {
        public Armor createFromParcel(Parcel in) {
            return new Armor(in);
        }

        public Armor[] newArray(int size) {
            return new Armor[size];
        }
    };

    private Armor(Parcel in) {
        super(in);
        soak = in.readInt();
        contactDef = in.readInt();
        rangeDef = in.readInt();
    }
}
