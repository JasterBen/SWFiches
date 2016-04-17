package com.example.hokan.swfiches.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.fragments.CampaignFragment;
import com.example.hokan.swfiches.fragments.CharacterListFragment;

/**
 * Created by Ben on 17/04/2016.
 */
public class CharacterListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        if (savedInstanceState == null)
        {
            FragmentManager mgr = getSupportFragmentManager();
            FragmentTransaction transaction = mgr.beginTransaction();

            CharacterListFragment frag = new CharacterListFragment();

            transaction.replace(R.id.character_list_page_fragment, frag);
            transaction.commit();
        }

    }
}
