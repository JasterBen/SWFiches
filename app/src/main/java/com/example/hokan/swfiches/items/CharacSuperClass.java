package com.example.hokan.swfiches.items;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Utilisateur on 19/04/2016.
 */
public abstract class CharacSuperClass {

    @SerializedName("name")
    protected String name;
    @SerializedName("brawn")
    protected int brawn;
    @SerializedName("agility")
    protected int agility;
    @SerializedName("intellect")
    protected int intellect;
    @SerializedName("cunning")
    protected int cunning;
    @SerializedName("willpower")
    protected int willpower;
    @SerializedName("presence")
    protected int presence;
    @SerializedName("wound")
    protected int wound;
    @SerializedName("strain")
    protected int strain;


    public int getBrawn() {
        return brawn;
    }

    public void setBrawn(int brawn) {
        this.brawn = brawn;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntellect() {
        return intellect;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public int getCunning() {
        return cunning;
    }

    public void setCunning(int cunning) {
        this.cunning = cunning;
    }

    public int getWillpower() {
        return willpower;
    }

    public void setWillpower(int willpower) {
        this.willpower = willpower;
    }

    public int getPresence() {
        return presence;
    }

    public void setPresence(int presence) {
        this.presence = presence;
    }

    public int getWound() {
        return wound;
    }

    public void setWound(int wound) {
        this.wound = wound;
    }

    public int getStrain() {
        return strain;
    }

    public void setStrain(int strain) {
        this.strain = strain;
    }

}
