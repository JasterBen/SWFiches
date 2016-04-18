package com.example.hokan.swfiches.items;

/**
 * Created by Ben on 18/04/2016.
 */
public class Weapon extends BattleStuff {

    protected int damage;
    protected int critic;
    protected String range;
    protected Skill skill;


    public Weapon(String name, int weight, int actualMod, int maxMod, String special, int damage, int critic, String range, Skill skill) {
        super(name, weight, actualMod, maxMod, special);
        this.damage = damage;
        this.critic = critic;
        this.range = range;
        this.skill = skill;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCritic() {
        return critic;
    }

    public void setCritic(int critic) {
        this.critic = critic;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
