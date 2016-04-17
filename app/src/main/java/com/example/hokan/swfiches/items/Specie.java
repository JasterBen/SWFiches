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
}
