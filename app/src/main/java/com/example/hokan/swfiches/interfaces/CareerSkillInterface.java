package com.example.hokan.swfiches.interfaces;

import com.example.hokan.swfiches.items.Skill;

/**
 * Created by Ben on 25/04/2016.
 */
public interface CareerSkillInterface {

    Skill getCareerSkill(int position);

    int getCareerSkillCount();

    boolean getCareerSkillChecked(int position);

    void setCareerSkillChecked(int position, boolean check);

    Skill getSpeSkill(int position);

    int getSpeSkillCount();
}
