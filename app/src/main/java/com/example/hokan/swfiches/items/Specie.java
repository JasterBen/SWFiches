package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class Specie implements Parcelable {

    protected String name;
    protected int brawn;
    protected int agility;
    protected int intellect;
    protected int cunning;
    protected int willpower;
    protected int presence;
    protected int wound;
    protected int strain;
    protected int startingxp;
    protected boolean canHaveForce;

    public Specie(String name, int brawn, int agility, int intellect, int cunning, int willpower,
                  int presence, int wound, int strain, int startingxp, boolean canHaveForce) {
        this.name = name;
        this.brawn = brawn;
        this.agility = agility;
        this.intellect = intellect;
        this.cunning = cunning;
        this.willpower = willpower;
        this.presence = presence;
        this.wound = wound;
        this.strain = strain;
        this.startingxp = startingxp;
        this.canHaveForce = canHaveForce;
    }


    //region getter setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public int getStartingxp() {
        return startingxp;
    }

    public void setStartingxp(int startingxp) {
        this.startingxp = startingxp;
    }

    public boolean isCanHaveForce() {
        return canHaveForce;
    }

    public void setCanHaveForce(boolean canHaveForce) {
        this.canHaveForce = canHaveForce;
    }


    //endregion



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(brawn);
        dest.writeInt(agility);
        dest.writeInt(intellect);
        dest.writeInt(cunning);
        dest.writeInt(willpower);
        dest.writeInt(presence);
        dest.writeInt(wound);
        dest.writeInt(strain);
        dest.writeInt(startingxp);
        dest.writeByte((byte) (canHaveForce ? 1 : 0));
    }

    public static final Parcelable.Creator<Specie> CREATOR
            = new Parcelable.Creator<Specie>() {
        public Specie createFromParcel(Parcel in) {
            return new Specie(in);
        }

        public Specie[] newArray(int size) {
            return new Specie[size];
        }
    };

    private Specie(Parcel in) {
        name = in.readString();
        brawn = in.readInt();
        agility = in.readInt();
        intellect = in.readInt();
        cunning = in.readInt();
        willpower= in.readInt();
        presence= in.readInt();
        wound= in.readInt();
        strain= in.readInt();
        startingxp= in.readInt();
        canHaveForce = in.readByte() != 0;
    }


    @Override
    public String toString() {
        return name;
    }
}
