package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class Career implements Parcelable {

    protected String name;
    protected ArrayList<String> careerSkills;
    protected ArrayList<Specialization> specializationList;



    //TODO delete this constructor
    public Career(String name) {
        this.name = name;
        this.careerSkills = new ArrayList<>();
        this.specializationList = new ArrayList<>();
    }


    public Career(String name, ArrayList<String> careerSkills) {
        this.name = name;
        this.careerSkills = careerSkills;
        this.specializationList = new ArrayList<>();

    }

    public Career(String name, ArrayList<String> careerSkills, ArrayList<Specialization> specializationList) {
        this.name = name;
        this.careerSkills = careerSkills;
        this.specializationList = specializationList;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeStringList(careerSkills);
        dest.writeTypedList(specializationList);
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
    }


    @Override
    public String toString() {
        return name;
    }

}
