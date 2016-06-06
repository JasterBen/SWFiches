package com.example.hokan.swfiches.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.adapters.PlayerViewPagerAdapter;
import com.example.hokan.swfiches.interfaces.CharacterListInterface;
import com.example.hokan.swfiches.items.Campaign;
import com.example.hokan.swfiches.items.SWCharacter;
import com.example.hokan.swfiches.services.InternalStorageService;

import java.util.ArrayList;

/**
 * Created by Ben on 18/04/2016.
 */
public class PlayerActivity extends SWFichesActivity implements CharacterListInterface {
    
    public static final String POSITION = "position";
    public static final String CHARACTERLIST = "characterlist";
    public static final String CAMPAIGN = "campaign";

    protected Campaign campaign;
    protected int characterPosition;
    protected ArrayList<SWCharacter> characterList;
    protected int characterListSize;

    protected ViewPager viewPager;
    protected PlayerViewPagerAdapter playerViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        if (!SWFichesApplication.getApp().isTablet())
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        if (savedInstanceState == null)
        {
            Intent intent = getIntent();
            characterPosition = intent.getIntExtra(POSITION, 0);
            campaign = intent.getParcelableExtra(CAMPAIGN);
            characterList = campaign.getCharacterList();
            characterListSize = characterList.size();

            initToolbar();

            viewPager = (ViewPager) findViewById(R.id.player_view_pager);
            playerViewPagerAdapter = new PlayerViewPagerAdapter(getSupportFragmentManager(), this);
            viewPager.setAdapter(playerViewPagerAdapter);
            viewPager.setCurrentItem(characterPosition);
        }
    }


    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        returnIntent.putParcelableArrayListExtra(CHARACTERLIST, characterList);
        setResult(Activity.RESULT_OK, returnIntent);

        super.finish();
    }

    @Override
    public int getItemCount() {
        return characterListSize;
    }

    @Override
    public SWCharacter getCharacter(int position) {
        return characterList.get(position);
    }

    @Override
    protected void onStop() {
        InternalStorageService.saveCampaign(campaign);
        super.onStop();
    }
}
