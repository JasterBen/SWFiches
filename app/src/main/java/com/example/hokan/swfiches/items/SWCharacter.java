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
    protected int soak;
    protected int actualWound;
    protected int actualStrain;
    protected int contactDefense;
    protected int rangeDefense;
    protected int weight;
    protected int actualWeight;
    protected int actualXp;
    protected int totalXp;
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
        this.wound = -1;
        this.strain = -1;
        this.soak = -1;
        this.actualWound = 0;
        this.actualStrain = 0;
        this.forceRating = 0;
        this.contactDefense = 0;
        this.rangeDefense = 0;
        this.weight = -1;
        this.actualWeight = 0;
        this.actualXp = 0;
        this.totalXp = -1;
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
        this.soak = specie.getBrawn();
        this.weight = 5 + specie.getBrawn();
        this.totalXp = specie.getStartingxp();
    }


    //region getter setter


    @Override
    public void setBrawn(int brawn) {
        super.setBrawn(brawn);
        soak = brawn;
        weight = 5 + brawn;
        wound = specie.getWound() + brawn;
    }

    @Override
    public void setWillpower(int willpower) {
        super.setWillpower(willpower);
        strain = specie.getStrain() + willpower;
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

    public int getForceRating() {
        return forceRating;
    }

    public void setForceRating(int forceRating) {
        if (specie.isCanHaveForce())
            this.forceRating = forceRating;
    }

    public int getSoak() {
        return soak;
    }

    public void setSoak(int soak) {
        this.soak = soak;
    }

    public int getActualWound() {
        return actualWound;
    }

    public void setActualWound(int actualWound) {
        this.actualWound = actualWound;
    }

    public int getActualStrain() {
        return actualStrain;
    }

    public void setActualStrain(int actualStrain) {
        this.actualStrain = actualStrain;
    }

    public int getContactDefense() {
        return contactDefense;
    }

    public void setContactDefense(int contactDefense) {
        this.contactDefense = contactDefense;
    }

    public int getRangeDefense() {
        return rangeDefense;
    }

    public void setRangeDefense(int rangeDefense) {
        this.rangeDefense = rangeDefense;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(int actualWeight) {
        this.actualWeight = actualWeight;
    }

    public int getTotalXp() {
        return totalXp;
    }

    public void setTotalXp(int totalXp) {
        this.totalXp = totalXp;
    }

    public int getActualXp() {
        return actualXp;
    }

    public void setActualXp(int actualXp) {
        this.actualXp = actualXp;
    }

    //endregion

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
        dest.writeInt(soak);
        dest.writeInt(actualWound);
        dest.writeInt(actualStrain);
        dest.writeInt(contactDefense);
        dest.writeInt(rangeDefense);
        dest.writeInt(weight);
        dest.writeInt(actualWeight);
        dest.writeInt(actualXp);
        dest.writeInt(totalXp);
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
        soak = in.readInt();
        actualWound = in.readInt();
        actualStrain = in.readInt();
        contactDefense = in.readInt();
        rangeDefense = in.readInt();
        weight = in.readInt();
        actualWeight = in.readInt();
        actualXp = in.readInt();
        totalXp = in.readInt();
        career = in.readParcelable(Career.class.getClassLoader());
    }
}
