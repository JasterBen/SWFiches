package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class Specialization implements Parcelable {

    protected String name;
    protected ArrayList<String> specializationrSkills;


    public Specialization(String name, ArrayList<String> specializationrSkills) {
        this.name = name;
        this.specializationrSkills = specializationrSkills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSpecializationrSkills() {
        return specializationrSkills;
    }

    public void setSpecializationrSkills(ArrayList<String> specializationrSkills) {
        this.specializationrSkills = specializationrSkills;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeStringList(specializationrSkills);
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
        in.readStringList(specializationrSkills);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
