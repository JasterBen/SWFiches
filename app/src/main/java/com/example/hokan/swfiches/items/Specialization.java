package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class Specialization implements Parcelable, Serializable {

    @SerializedName("spe_name")
    protected String name;
    @SerializedName("spe_list_name")
    protected String listName;
    @SerializedName("specialization_skill")
    protected ArrayList<String> specializationrSkills;


    public Specialization() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListName() {
        return listName;
    }

    public ArrayList<String> getSpecializationrSkills() {
        return specializationrSkills;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(listName);
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
        listName = in.readString();
        specializationrSkills = new ArrayList<>(4);
        in.readStringList(specializationrSkills);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
