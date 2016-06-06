package com.example.hokan.swfiches.activities;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.fragments.CampaignListFragment;
import com.example.hokan.swfiches.interfaces.CampaignListInterface;
import com.example.hokan.swfiches.items.Campaign;
import com.example.hokan.swfiches.items.SWCharacter;
import com.example.hokan.swfiches.services.InternalStorageService;

import java.util.ArrayList;
import java.util.Collections;

public class CampaignActivity extends SWFichesActivity implements CampaignListInterface {

    public final static String CAMPAIGN = "campaign";
    public final static int REQUEST_CODE = 1;

    protected ArrayList<Campaign> campaignList;
    protected int campainListSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);

        if (!SWFichesApplication.getApp().isTablet())
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        if (savedInstanceState == null)
        {
            campaignList = new ArrayList<>();

            if (InternalStorageService.savesExists()) {
                campaignList.addAll(InternalStorageService.loadCampaignCollection());
            }

            campainListSize = campaignList.size();

            initToolbar();

            FragmentManager mgr = getSupportFragmentManager();
            FragmentTransaction transaction = mgr.beginTransaction();

            CampaignListFragment frag = new CampaignListFragment();

            transaction.replace(R.id.home_page_fragment, frag);
            transaction.commit();
        }
    }


    public ArrayList<Campaign> getCampaignList() {
        return campaignList;
    }

    public void setCampaignList(ArrayList<Campaign> campaignList) {
        this.campaignList = campaignList;
        campainListSize = this.campaignList.size();
    }

    public int getCampainListSize() {
        return campainListSize;
    }

    @Override
    public int getItemCount() {
        return campainListSize;
    }

    public void addItem(Campaign campaign)
    {
        campaignList.add(campaign);
        campainListSize++;
    }

    @Override
    public void removeItem(int position)
    {
        campaignList.remove(position);
        campainListSize--;
    }

    public Campaign getItem(int position)
    {
        return campaignList.get(position);
    }

    @Override
    public void changeCharacterList(Campaign c, ArrayList<SWCharacter> list) {
        c.setCharacterList(list);
    }

    @Override
    protected void onStop() {
        InternalStorageService.saveCampaignsCollection(campaignList);
        super.onStop();
    }
}
