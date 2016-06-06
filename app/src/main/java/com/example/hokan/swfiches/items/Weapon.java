package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Ben on 18/04/2016.
 */
public class Weapon extends BattleStuff implements Parcelable, Serializable {

    protected String damage;
    protected int critic;
    protected String range;
    protected Skill skill;


    public Weapon(String name, int weight, int actualMod, int maxMod, String special, String damage, int critic, String range, Skill skill) {
        super(name, weight, actualMod, maxMod, special);
        this.damage = damage;
        this.critic = critic;
        this.range = range;
        this.skill = skill;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public int getCritic() {
        return critic;
    }

    public void setCritic(int critic) {
        this.critic = critic;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
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
        dest.writeString(damage);
        dest.writeInt(critic);
        dest.writeString(range);
        dest.writeParcelable(skill, 0);
    }

    public static final Parcelable.Creator<Weapon> CREATOR
            = new Parcelable.Creator<Weapon>() {
        public Weapon createFromParcel(Parcel in) {
            return new Weapon(in);
        }

        public Weapon[] newArray(int size) {
            return new Weapon[size];
        }
    };

    private Weapon(Parcel in) {
        super(in);
        damage = in.readString();
        critic = in.readInt();
        range = in.readString();
        skill = in.readParcelable(Skill.class.getClassLoader());
    }
}
