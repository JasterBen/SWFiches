package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class Specialization implements Parcelable {

    protected String name;
    protected ArrayList<Skill> specializationrSkills;
    protected Career career;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(specializationrSkills);
        dest.writeParcelable(career, 0);
    }

    public static final Parcelable.Creator<Specialization> CREATOR
            = new Parcelable.Creator<Specialization>() {
        public Specialization createFromParcel(Parcel in) {
            return new Specialization(in);
        }

        public Specialization[] newArray(int size) {
            return new Specialization[size];
        }
    };

    private Specialization(Parcel in) {
        name = in.readString();
        specializationrSkills = new ArrayList<>(4);
        in.readTypedList(specializationrSkills, Skill.CREATOR);
        career = in.readParcelable(Career.class.getClassLoader());
    }
}
