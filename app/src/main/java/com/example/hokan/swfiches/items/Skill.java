package com.example.hokan.swfiches.items;

/**
 * Created by Utilisateur on 03/02/2016.
 */
public class Skill {

    protected String name;
    protected int level;
    /**
     * characteristic
     * 'b' Brawn
     * 'a' Agility
     * 'i' Intelligence
     * 'c' Cunning
     * 'w' Willpower
     * 'p' Presence
     **/
    protected char characteristic;
    protected boolean isCareer;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int lvl) {
        if (lvl > 5)
            lvl = 5;
        this.level = lvl;
    }

    public char getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(char characteristic) {
        this.characteristic = characteristic;
    }

    public boolean isCareer() {
        return isCareer;
    }

    public void setIsCareer(boolean isCareer) {
        this.isCareer = isCareer;
    }
}
