package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class Specie extends CharacSuperClass implements Parcelable {

    @SerializedName("startingxp")
    protected int startingxp;
    @SerializedName("can_have_force")
    protected boolean canHaveForce;
    @SerializedName("special")
    protected String special;


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

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
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
        dest.writeString(special);
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
        special = in.readString();
    }


    @Override
    public String toString() {
        return name;
    }
}
