package com.example.hokan.swfiches.activities;



import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.fragments.CampaignListFragment;
import com.example.hokan.swfiches.interfaces.CampaignListInterface;
import com.example.hokan.swfiches.items.Campaign;
import com.example.hokan.swfiches.items.SWCharacter;

import java.util.ArrayList;

public class CampaignActivity extends AppCompatActivity implements CampaignListInterface {

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
            campainListSize = campaignList.size();

            Toolbar toolbar = (Toolbar) findViewById(R.id.campaign_activity_toolbar);
            toolbar.setNavigationIcon(R.mipmap.smuggler);
            setSupportActionBar(toolbar);

            FragmentManager mgr = getSupportFragmentManager();
            FragmentTransaction transaction = mgr.beginTransaction();

            CampaignListFragment frag = new CampaignListFragment();

            transaction.replace(R.id.home_page_fragment, frag);
            transaction.commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.show_trees) :
//                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/example.pdf");
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                startActivity(intent);
                Toast.makeText(this, "Guillaume, on t'attend !", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
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
