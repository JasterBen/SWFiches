package com.example.hokan.swfiches.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.fragments.PersoFragment;
import com.example.hokan.swfiches.items.SWCharacter;

/**
 * Created by Ben on 18/04/2016.
 */
public class PlayerActivity extends AppCompatActivity {

    public static final String CHARACTER = "character";
    public static final String POSITION = "position";

    protected SWCharacter character;
    protected int characterPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        if (!SWFichesApplication.getApp().isTablet())
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        if (savedInstanceState == null)
        {
            Intent intent = getIntent();
            character = intent.getParcelableExtra(CHARACTER);
            characterPosition = intent.getIntExtra(POSITION, 0);

            FragmentManager mgr = getSupportFragmentManager();
            FragmentTransaction transaction = mgr.beginTransaction();

            PersoFragment persoFrag = new PersoFragment();

            transaction.replace(R.id.perso_frag_container, persoFrag);
            transaction.commit();
        }
    }


    public SWCharacter getCharacter() {
        return character;
    }

    public void setCharacter(SWCharacter character) {
        this.character = character;
    }


    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(CHARACTER, character);
        returnIntent.putExtra(POSITION, characterPosition);
        setResult(Activity.RESULT_OK, returnIntent);

        super.finish();
    }
}
