package com.example.hokan.swfiches.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.fragments.CampaignFragment;
import com.example.hokan.swfiches.fragments.CharacterListFragment;
import com.example.hokan.swfiches.items.Campaign;

/**
 * Created by Ben on 17/04/2016.
 */
public class CharacterListActivity extends AppCompatActivity {

    protected Campaign campaign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        if (!SWFichesApplication.getApp().isTablet())
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        if (savedInstanceState == null)
        {
            Intent intent = getIntent();
            if (intent != null)
            {
                campaign = intent.getParcelableExtra(CampaignFragment.CAMPAIGN);
            }

            FragmentManager mgr = getSupportFragmentManager();
            FragmentTransaction transaction = mgr.beginTransaction();

            CharacterListFragment frag = new CharacterListFragment();

            transaction.replace(R.id.character_list_page_fragment, frag);
            transaction.commit();
        }

    }

    public Campaign getCampaign() {
        return campaign;
    }
}
