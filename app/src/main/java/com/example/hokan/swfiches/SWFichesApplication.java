package com.example.hokan.swfiches;

import android.app.Application;

/**
 * Created by Ben on 17/04/2016.
 */
public class SWFichesApplication extends Application {

    private static SWFichesApplication app;
    private boolean tablet;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        tablet = getResources().getBoolean(R.bool.is_tablet);
    }

    public static SWFichesApplication getApp() {
        return app;
    }

    public boolean isTablet() {
        return tablet;
    }
}
