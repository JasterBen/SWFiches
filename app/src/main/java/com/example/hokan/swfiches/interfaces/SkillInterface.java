package com.example.hokan.swfiches.interfaces;

import com.example.hokan.swfiches.items.Skill;

/**
 * Created by Utilisateur on 22/04/2016.
 */
public interface SkillInterface {

    Skill getSkill(int position);

    int getSkillCount();

    Skill getCustomSkill(int position);

    void addCustomSkill(Skill skill);

    void removeCustomSkill(int position);

    int getCustomSkillCount();

}
