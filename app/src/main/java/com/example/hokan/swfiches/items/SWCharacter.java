package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class SWCharacter implements Parcelable {

    protected String name;
    protected Specie specie;
    protected int forceRating;
    protected Carreer carreer;
    //protected ArrayList<Skill> skills;



    public SWCharacter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public Carreer getCarreer() {
        return carreer;
    }

    public void setCarreer(Carreer carreer) {
        this.carreer = carreer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(specie, 0);
        dest.writeInt(forceRating);
        dest.writeParcelable(carreer, 1);
    }

    public static final Parcelable.Creator<SWCharacter> CREATOR
            = new Parcelable.Creator<SWCharacter>() {
        public SWCharacter createFromParcel(Parcel in) {
            return new SWCharacter(in);
        }

        public SWCharacter[] newArray(int size) {
            return new SWCharacter[size];
        }
    };

    private SWCharacter(Parcel in) {
        name = in.readString();
        specie = in.readParcelable(Specie.class.getClassLoader());
        forceRating = in.readInt();
        carreer = in.readParcelable(Carreer.class.getClassLoader());
    }
}
