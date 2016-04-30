package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Utilisateur on 03/02/2016.
 */
public class Skill implements Parcelable {

    @SerializedName("name")
    protected String name;
    protected int level;
    protected int bonus;
    protected int malus;

    /**
     * characteristic
     * 'b' Brawn
     * 'a' Agility
     * 'i' Intelligence
     * 'c' Cunning
     * 'w' Willpower
     * 'p' Presence
     **/
    @SerializedName("characteristic")
    protected char characteristic;
    protected boolean isCareer;


    public Skill(String name, char characteristic) {
        this.name = name;
        this.characteristic = characteristic;
        this.level = 0;
    }

    public Skill(String name, int level, char characteristic, boolean isCareer) {
        this.name = name;
        this.level = level;
        this.characteristic = characteristic;
        this.isCareer = isCareer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int lvl) {
        if (lvl > 5)
            lvl = 5;
        this.level = lvl;
    }

    public char getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(char characteristic) {
        this.characteristic = characteristic;
    }

    public boolean isCareer() {
        return isCareer;
    }

    public void setIsCareer(boolean isCareer) {
        this.isCareer = isCareer;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        if (bonus > 4)
            bonus = 4;
        this.bonus = bonus;
    }

    public int getMalus() {
        return malus;
    }

    public void setMalus(int malus) {
        if (malus > 4)
            malus = 4;
        this.malus = malus;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(level);
        dest.writeInt(bonus);
        dest.writeInt(malus);
        dest.writeInt(characteristic);
        dest.writeByte((byte) (isCareer ? 1 : 0));
    }


    public static final Parcelable.Creator<Skill> CREATOR
            = new Parcelable.Creator<Skill>() {
        public Skill createFromParcel(Parcel in) {
            return new Skill(in);
        }

        public Skill[] newArray(int size) {
            return new Skill[size];
        }
    };

    private Skill(Parcel in) {
        name = in.readString();
        level = in.readInt();
        bonus = in.readInt();
        malus = in.readInt();
        characteristic = (char) in.readInt();
        isCareer = in.readByte() != 0;
    }
}
