package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class Career implements Parcelable {

    protected String name;
    protected ArrayList<Skill> carreerSkills;
    protected int careerSkillSize;
    protected ArrayList<Specialization> specializationList;
    protected int specializationListSize;



    //TODO delete this constructor
    public Career(String name) {
        this.name = name;
        this.careerSkillSize = 0;
    }


    public Career(String name, ArrayList<Skill> carreerSkills) {
        this.name = name;
        this.carreerSkills = carreerSkills;
        this.careerSkillSize = carreerSkills.size();
    }

    public String getName() {
        return name;
    }


    public ArrayList<Skill> getCarreerSkills() {
        return carreerSkills;
    }

    public int getCareerSkillSize() {
        return careerSkillSize;
    }

    public ArrayList<Specialization> getSpecializationList() {
        return specializationList;
    }

    public int getSpecializationListSize() {
        return specializationListSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(carreerSkills);
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
        carreerSkills = new ArrayList<>(8);
        in.readTypedList(carreerSkills, Skill.CREATOR);
        specializationList = new ArrayList<>(6);
        in.readTypedList(specializationList, Specialization.CREATOR);
    }


    @Override
    public String toString() {
        return name;
    }

}
