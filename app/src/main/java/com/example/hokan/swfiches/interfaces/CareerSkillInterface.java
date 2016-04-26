package com.example.hokan.swfiches.interfaces;

/**
 * Created by Ben on 25/04/2016.
 */
public interface CareerSkillInterface {

    String getCareerSkill(int position);

    int getCareerSkillCount();

    boolean getCareerSkillChecked(int position);

    void setCareerSkillChecked(int position, boolean check);

    int getMaxCareerSkill();

    String getSpeSkill(int position);

    int getSpeSkillCount();
}
