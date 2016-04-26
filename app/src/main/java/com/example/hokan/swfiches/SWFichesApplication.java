package com.example.hokan.swfiches;

import android.app.Application;
import android.os.AsyncTask;

import com.example.hokan.swfiches.items.Career;
import com.example.hokan.swfiches.items.Skill;
import com.example.hokan.swfiches.items.Specie;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Ben on 17/04/2016.
 */

// TODO http://blog.nkdroidsolutions.com/json-parsing-from-assets-using-gson-in-android-tutorial/

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
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                Type listType = new TypeToken<ArrayList<Specie>>(){}.getType();
                speciesList = new GsonBuilder().create().fromJson(loadJSONFromAsset(), listType);
                return null;
            }
        }.execute();
    }

    private void initCareerList()
    {
        careerList = new ArrayList<>();
        careerList.add(new Career("Smuggler"));
        careerList.add(new Career("Bounty Hunter"));
        careerList.add(new Career("Spy"));

        ArrayList<String> careerSkillList = new ArrayList<>();
        careerSkillList.add("Astrogation");
        careerSkillList.add("Melee");
        careerSkillList.add("Perception");
        careerSkillList.add("Brawl");
        careerSkillList.add("Gunnery");
        careerSkillList.add("Ranged Heavy");
        careerSkillList.add("Ranged Light");
        careerSkillList.add("Lightsaber (Br)");
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


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("species.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
