package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.hokan.swfiches.SWFichesApplication;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class SWCharacter extends CharacSuperClass implements Parcelable {

    protected Specie specie;
    protected int forceRating;
    protected int soak;
    protected int actualWound;
    protected int actualStrain;
    protected int weight;
    protected int actualWeight;
    protected int actualXp;
    protected int totalXp;
    protected Career career;
    protected Specialization mainSpecialization;
    protected ArrayList<Specialization> secondarySpecializationList;
    protected int secondarySpecializationListSize;
    protected ArrayList<Skill> skillList;
    protected int skillListSize;
    protected ArrayList<Skill> customSkillList;
    protected int customSkillListSize;
    protected ArrayList<Weapon> weaponList;
    protected int weaponListSize;
    protected Armor armor;



    public SWCharacter(String name, Specie specie) {
        this.name = name;
        this.forceRating = 0;
        this.actualWound = 0;
        this.actualStrain = 0;
        this.actualWeight = 0;
        this.actualXp = 0;
        this.skillList = SWFichesApplication.getApp().getSkillList();
        this.skillListSize = skillList.size();
        this.setSpecie(specie);
        this.armor = new Armor();
    }



    //region getter setter adder updater

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
        this.forceRating = specie.isCanHaveForce() ? this.forceRating : 0;
    }


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

    public ArrayList<Specialization> getSecondarySpecializationList() {
        return secondarySpecializationList;
    }

    public void setSecondarySpecializationList(ArrayList<Specialization> secondarySpecializationList) {
        this.secondarySpecializationList = secondarySpecializationList;
        this.secondarySpecializationListSize = secondarySpecializationList.size();
    }

    public int getForceRating() {
        return forceRating;
    }

    public void setForceRating(int forceRating) {
        if (specie.isCanHaveForce())
            this.forceRating = forceRating;
        else
            this.forceRating = 0;
    }

    public int getSoak() {
        return soak + armor.getSoak();
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

    public ArrayList<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(ArrayList<Skill> skillList) {
        this.skillList = skillList;
        this.skillListSize = skillList.size();
    }

    public void updateSkill(int position, Skill skill)
    {
        skillList.set(position, skill);
    }

    public ArrayList<Skill> getCustomSkillList() {
        return customSkillList;
    }

    public void setCustomSkillList(ArrayList<Skill> customSkillList) {
        this.customSkillList = customSkillList;
        this.customSkillListSize = customSkillList.size();
    }

    public ArrayList<Weapon> getWeaponList() {
        return weaponList;
    }

    public void setWeaponList(ArrayList<Weapon> weaponList) {
        this.weaponList = weaponList;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public int getSecondarySpecializationListSize() {
        return secondarySpecializationListSize;
    }

    public void setSecondarySpecializationListSize(int secondarySpecializationListSize) {
        this.secondarySpecializationListSize = secondarySpecializationListSize;
    }

    public int getSkillListSize() {
        return skillListSize;
    }

    public void setSkillListSize(int skillListSize) {
        this.skillListSize = skillListSize;
    }

    public int getCustomSkillListSize() {
        return customSkillListSize;
    }

    public void setCustomSkillListSize(int customSkillListSize) {
        this.customSkillListSize = customSkillListSize;
    }

    public int getWeaponListSize() {
        return weaponListSize;
    }

    public void setWeaponListSize(int weaponListSize) {
        this.weaponListSize = weaponListSize;
    }

    //endregion


    //region Parcelable
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
        dest.writeInt(weight);
        dest.writeInt(actualWeight);
        dest.writeInt(actualXp);
        dest.writeInt(totalXp);
        dest.writeParcelable(career, 1);
        dest.writeTypedList(skillList);
        dest.writeInt(skillListSize);
        dest.writeTypedList(customSkillList);
        dest.writeInt(customSkillListSize);
        dest.writeTypedList(weaponList);
        dest.writeInt(weaponListSize);
        dest.writeParcelable(armor, 1);
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
        weight = in.readInt();
        actualWeight = in.readInt();
        actualXp = in.readInt();
        totalXp = in.readInt();
        career = in.readParcelable(Career.class.getClassLoader());
        skillList = new ArrayList<>();
        in.readTypedList(skillList, Skill.CREATOR);
        skillListSize = in.readInt();
        customSkillList = new ArrayList<>();
        in.readTypedList(customSkillList, Skill.CREATOR);
        customSkillListSize = in.readInt();
        weaponList = new ArrayList<>();
        in.readTypedList(weaponList, Weapon.CREATOR);
        weaponListSize = in.readInt();
        armor = in.readParcelable(Armor.class.getClassLoader());
    }
    //endregion
}
