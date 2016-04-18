package com.example.hokan.swfiches.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.fragments.PersoFragment;
import com.example.hokan.swfiches.items.SWCharacter;

/**
 * Created by Ben on 18/04/2016.
 */
public class PlayerActivity extends AppCompatActivity {

    public static final String CHARACTER = "character";

    //TODO WARNING character seulement en copie local
    protected SWCharacter character;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        if (savedInstanceState == null)
        {
            Intent intent = getIntent();
            character = intent.getParcelableExtra(CHARACTER);

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
}
