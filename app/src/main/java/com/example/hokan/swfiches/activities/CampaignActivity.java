package com.example.hokan.swfiches.activities;



import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.fragments.CampaignFragment;
import com.example.hokan.swfiches.interfaces.CampaignListInterface;
import com.example.hokan.swfiches.items.Campaign;
import com.example.hokan.swfiches.items.SWCharacter;

import java.util.ArrayList;

public class CampaignActivity extends AppCompatActivity implements CampaignListInterface {

    public final static String CAMPAIGN = "campaign";

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
            campainListSize = campaignList.size();

            FragmentManager mgr = getSupportFragmentManager();
            FragmentTransaction transaction = mgr.beginTransaction();

            CampaignFragment frag = new CampaignFragment();

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
}
