package com.example.hokan.swfiches.items;

/**
 * Created by Ben on 18/04/2016.
 */
public class Armor extends BattleStuff {

    protected int soak;
    protected int contactDef;
    protected int rangeDef;


    public Armor(String name, int weight, int actualMod, int maxMod, String special, int soak, int contactDef, int rangeDef) {
        super(name, weight, actualMod, maxMod, special);
        this.soak = soak;
        this.contactDef = contactDef;
        this.rangeDef = rangeDef;
    }


    public int getSoak() {
        return soak;
    }

    public void setSoak(int soak) {
        this.soak = soak;
    }

    public int getContactDef() {
        return contactDef;
    }

    public void setContactDef(int contactDef) {
        this.contactDef = contactDef;
    }

    public int getRangeDef() {
        return rangeDef;
    }

    public void setRangeDef(int rangeDef) {
        this.rangeDef = rangeDef;
    }
}
