package com.example.hokan.swfiches;

import android.app.Application;

import com.example.hokan.swfiches.items.Career;
import com.example.hokan.swfiches.items.Skill;
import com.example.hokan.swfiches.items.Specie;

import java.util.ArrayList;

/**
 * Created by Ben on 17/04/2016.
 */
public class SWFichesApplication extends Application {

    private static SWFichesApplication app;
    private boolean tablet;
    private ArrayList<Specie> speciesList;
    private ArrayList<Career> careerList;
    private ArrayList<Skill> skillList;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        tablet = getResources().getBoolean(R.bool.is_tablet);
        initSpeciesList();
        initCareerList();
        initSkillList();
    }

    public static SWFichesApplication getApp() {
        return app;
    }

    public boolean isTablet() {
        return tablet;
    }

    private void initSpeciesList()
    {
        speciesList = new ArrayList<>();
        speciesList.add(new Specie("Humain", 2, 2, 2, 2, 2, 2, 10, 10, 110, true));
        speciesList.add(new Specie("Humain corellien", 2, 2, 2, 2, 2, 2, 10, 10, 110, true));
        speciesList.add(new Specie("Droide", 1, 1, 1, 1, 1, 1, 10, 10, 175, false));
        speciesList.add(new Specie("Drall", 1, 1, 4, 2, 2, 2, 8, 12, 90, true));
    }

    private void initCareerList()
    {
        careerList = new ArrayList<>();
        careerList.add(new Career("Smuggler"));
        careerList.add(new Career("Bounty Hunter"));
        careerList.add(new Career("Spy"));

        ArrayList<Skill> careerSkillList = new ArrayList<>();
        careerSkillList.add(new Skill("Astrogation", 'i'));
        careerSkillList.add(new Skill("Melee", 'b'));
        careerSkillList.add(new Skill("Perception", 'c'));
        careerSkillList.add(new Skill("Brawl", 'b'));
        careerSkillList.add(new Skill("Gunnery", 'a'));
        careerSkillList.add(new Skill("Ranged Heavy", 'a'));
        careerSkillList.add(new Skill("Ranged Light", 'a'));
        careerSkillList.add(new Skill("Lightsaber (Br)", 'b'));
        careerList.add(new Career("Test", careerSkillList));
    }

    private void initSkillList()
    {
        skillList = new ArrayList<>();
        skillList.add(new Skill("Astrogation", 'i'));
        skillList.add(new Skill("Melee", 'b'));
        skillList.add(new Skill("Perception", 'c'));
        skillList.add(new Skill("Brawl", 'b'));
        skillList.add(new Skill("Gunnery", 'a'));
        skillList.add(new Skill("Ranged Heavy", 'a'));
        skillList.add(new Skill("Ranged Light", 'a'));
        skillList.add(new Skill("Lightsaber (Br)", 'b'));
        skillList.add(new Skill("Lightsaber (Ag)", 'a'));
        skillList.add(new Skill("Lightsaber (Int)", 'i'));
        skillList.add(new Skill("Lightsaber (Cun)", 'c'));
        skillList.add(new Skill("Lightsaber (Will)", 'w'));
        skillList.add(new Skill("Lightsaber (Pr)", 'p'));
    }

    public ArrayList<Specie> getSpeciesList() {
        return speciesList;
    }

    public ArrayList<Career> getCareerList() {
        return careerList;
    }

    public ArrayList<Skill> getSkillList() {
        return skillList;
    }

    public Skill getSkill(String name)
    {
        for (Skill skill : skillList)
            if (skill.getName().equals(name))
                return skill;

        return null;
    }
}
