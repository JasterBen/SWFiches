package com.example.hokan.swfiches;

import android.app.Application;
import android.os.AsyncTask;

import com.example.hokan.swfiches.items.Career;
import com.example.hokan.swfiches.items.Skill;
import com.example.hokan.swfiches.items.Specialization;
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
        initSkillList();
        initSpeciesList();
        initCareerList();
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
                speciesList = new GsonBuilder().create().fromJson(loadJSONFromAsset(getString(R.string.species_json_name)), listType);
                return null;
            }
        }.execute();
    }

    private void initCareerList()
    {
        careerList = new ArrayList<>();

        ArrayList<String> careerSkillList = new ArrayList<>();
        careerSkillList.add("Astrogation");
        careerSkillList.add("Melee");
        careerSkillList.add("Perception");
        careerSkillList.add("Brawl");
        careerSkillList.add("Gunnery");
        careerSkillList.add("Ranged Heavy");
        careerSkillList.add("Ranged Light");
        careerSkillList.add("Lightsaber (Br)");
        ArrayList<String> speSkillList = new ArrayList<>();
        speSkillList.add("Astrogation");
        speSkillList.add("Lightsaber (Cun)");
        speSkillList.add("Perception");
        speSkillList.add("Lightsaber (Pr)");
        ArrayList<Specialization> speList = new ArrayList<>();
        speList.add(new Specialization("Spe1", speSkillList));
        speList.add(new Specialization("Spe2", speSkillList));
        speList.add(new Specialization("Spe3", speSkillList));
        careerList.add(new Career("Test", careerSkillList, speList));
        ArrayList<String> careerSkillList2 = new ArrayList<>();
        careerSkillList2.add("Astrogation");
        careerSkillList2.add("Melee");
        careerSkillList2.add("Lightsaber (Cun)");
        careerSkillList2.add("Perception");
        careerSkillList2.add("Lightsaber (Pr)");
        careerSkillList2.add("Ranged Heavy");
        careerSkillList2.add("Ranged Light");
        careerSkillList2.add("Lightsaber (Int)");
        careerList.add(new Career("Test2", careerSkillList2, speList));
    }

    private void initSkillList()
    {
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                Type listType = new TypeToken<ArrayList<Skill>>(){}.getType();
                skillList = new GsonBuilder().create().fromJson(loadJSONFromAsset(getString(R.string.skill_json_name)), listType);
                return null;
            }
        }.execute();

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


    public String loadJSONFromAsset(String jsonName) {
        String json = null;
        try {
            InputStream is = getAssets().open(jsonName);
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
