package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class Career implements Parcelable, Serializable {

    @SerializedName("career_name")
    protected String name;
    @SerializedName("career_skill")
    protected ArrayList<String> careerSkills;
    @SerializedName("specialization_list")
    protected ArrayList<Specialization> specializationList;
    @SerializedName("need_force")
    protected boolean needForce;




    public Career(String name, ArrayList<String> careerSkills, ArrayList<Specialization> specializationList, boolean force) {
        this.name = name;
        this.careerSkills = careerSkills;
        this.specializationList = specializationList;
        this.needForce = force;
    }

    public String getName() {
        return name;
    }


    public ArrayList<String> getCareerSkills() {
        return careerSkills;
    }


    public ArrayList<Specialization> getSpecializationList() {
        return specializationList;
    }


    public boolean isNeedForce() {
        return needForce;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeStringList(careerSkills);
        dest.writeTypedList(specializationList);
        dest.writeByte((byte) (needForce ? 1 : 0));
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
        careerSkills = new ArrayList<>(8);
        in.readStringList(careerSkills);
        specializationList = new ArrayList<>(6);
        in.readTypedList(specializationList, Specialization.CREATOR);
        needForce = in.readByte() != 0;
    }


    @Override
    public String toString() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        Career c = (Career) o;
        return this.getName().equals(c.getName());
    }
}
