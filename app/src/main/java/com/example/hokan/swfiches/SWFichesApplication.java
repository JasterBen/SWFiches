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
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                Type listType = new TypeToken<ArrayList<Career>>(){}.getType();
                careerList = new GsonBuilder().create().fromJson(loadJSONFromAsset(getString(R.string.career_json_name)), listType);
                return null;
            }
        }.execute();
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
