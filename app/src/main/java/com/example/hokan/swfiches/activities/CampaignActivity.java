package com.example.hokan.swfiches.activities;



import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.fragments.CampaignFragment;

public class CampaignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);

        if (savedInstanceState == null)
        {
            FragmentManager mgr = getSupportFragmentManager();
            FragmentTransaction transaction = mgr.beginTransaction();

            CampaignFragment frag = new CampaignFragment();

            transaction.replace(R.id.home_page_fragment, frag);
            transaction.commit();
        }



    }
}
