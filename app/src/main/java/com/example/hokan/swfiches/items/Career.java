package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class Career implements Parcelable {

    protected String name;
    //protected ArrayList<Skill> carreerSkills;
    //protected ArrayList<Specialization> specializationList;


    public Career(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    public static final Parcelable.Creator<Career> CREATOR
            = new Parcelable.Creator<Career>() {
        public Career createFromParcel(Parcel in) {
            return new Career(in);
        }

        public Career[] newArray(int size) {
            return new Career[size];
        }
    };

    private Career(Parcel in) {
        name = in.readString();
    }



    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name;
    }
}
