package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class Carreer implements Parcelable {

    protected String name;
    //protected ArrayList<Skill> carreerSkills;
    //protected ArrayList<Specialization> specializationList;




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    public static final Parcelable.Creator<Carreer> CREATOR
            = new Parcelable.Creator<Carreer>() {
        public Carreer createFromParcel(Parcel in) {
            return new Carreer(in);
        }

        public Carreer[] newArray(int size) {
            return new Carreer[size];
        }
    };

    private Carreer(Parcel in) {
        name = in.readString();
    }




}
