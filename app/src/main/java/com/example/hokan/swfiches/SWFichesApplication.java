package com.example.hokan.swfiches;

import android.app.Application;

import com.example.hokan.swfiches.items.Specie;

import java.util.ArrayList;

/**
 * Created by Ben on 17/04/2016.
 */
public class SWFichesApplication extends Application {

    private static SWFichesApplication app;
    private boolean tablet;
    private ArrayList<Specie> speciesList;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        tablet = getResources().getBoolean(R.bool.is_tablet);
        initSpeciesList();
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

    public ArrayList<Specie> getSpeciesList() {
        return speciesList;
    }
}
