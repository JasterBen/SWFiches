package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class SWCharacter extends CharacSuperClass implements Parcelable {

    protected String name;
    protected Specie specie;
    protected int forceRating;
    protected Career career;
    protected Specialization mainSpecialization;
    protected ArrayList<Specialization> secondarySpecializations;
    //protected ArrayList<Skill> skills;



    public SWCharacter(String name) {
        this.name = name;
        this.brawn = -1;
        this.agility = -1;
        this.intellect = -1;
        this.cunning = -1;
        this.willpower = -1;
        this.presence = -1;
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
        this.brawn = specie.getBrawn();
        this.agility = specie.getAgility();
        this.intellect = specie.getIntellect();
        this.cunning = specie.getCunning();
        this.willpower = specie.getWillpower();
        this.presence = specie.getPresence();
        this.wound = specie.getWound() + brawn;
        this.strain = specie.getStrain() + willpower;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public Specialization getMainSpecialization() {
        return mainSpecialization;
    }

    public void setMainSpecialization(Specialization mainSpecialization) {
        this.mainSpecialization = mainSpecialization;
    }

    public ArrayList<Specialization> getSecondarySpecializations() {
        return secondarySpecializations;
    }

    public void setSecondarySpecializations(ArrayList<Specialization> secondarySpecializations) {
        this.secondarySpecializations = secondarySpecializations;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(specie, 0);
        dest.writeInt(brawn);
        dest.writeInt(agility);
        dest.writeInt(intellect);
        dest.writeInt(cunning);
        dest.writeInt(willpower);
        dest.writeInt(presence);
        dest.writeInt(wound);
        dest.writeInt(strain);
        dest.writeInt(forceRating);
        dest.writeParcelable(career, 1);
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
        brawn = in.readInt();
        agility = in.readInt();
        intellect = in.readInt();
        cunning = in.readInt();
        willpower= in.readInt();
        presence= in.readInt();
        wound= in.readInt();
        strain= in.readInt();
        forceRating = in.readInt();
        career = in.readParcelable(Career.class.getClassLoader());
    }
}
