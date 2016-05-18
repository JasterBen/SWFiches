package com.example.hokan.swfiches.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.adapters.PlayerViewPagerAdapter;
import com.example.hokan.swfiches.fragments.ArmorFragment;
import com.example.hokan.swfiches.fragments.CharacteristicFragment;
import com.example.hokan.swfiches.fragments.PersoFragment;
import com.example.hokan.swfiches.fragments.PsychoFragment;
import com.example.hokan.swfiches.fragments.SkillsFragment;
import com.example.hokan.swfiches.fragments.StatsFragment;
import com.example.hokan.swfiches.fragments.StuffFragment;
import com.example.hokan.swfiches.fragments.TreeFragment;
import com.example.hokan.swfiches.fragments.WeaponFragment;
import com.example.hokan.swfiches.interfaces.CharacterListInterface;
import com.example.hokan.swfiches.items.SWCharacter;

import java.util.ArrayList;

/**
 * Created by Ben on 18/04/2016.
 */
public class PlayerActivity extends AppCompatActivity implements CharacterListInterface {

    //public static final String CHARACTER = "character";
    public static final String POSITION = "position";
    public static final String CHARACTERLIST = "characterlist";

    //protected SWCharacter character;
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
            //character = intent.getParcelableExtra(CHARACTER);
            characterPosition = intent.getIntExtra(POSITION, 0);
            characterList = intent.getParcelableArrayListExtra(CHARACTERLIST);
            characterListSize = characterList.size();
            //character = characterList.get(characterPosition);

            viewPager = (ViewPager) findViewById(R.id.player_view_pager);
            playerViewPagerAdapter = new PlayerViewPagerAdapter(getSupportFragmentManager(), this, this);
            viewPager.setAdapter(playerViewPagerAdapter);
            viewPager.setCurrentItem(characterPosition);
        }
    }

    public int getCharacterPosition() {
        return characterPosition;
    }

    public void setCharacterPosition(int characterPosition) {
        this.characterPosition = characterPosition;
    }

    public ArrayList<SWCharacter> getCharacterList() {
        return characterList;
    }

    //    public SWCharacter getCharacter() {
//        return character;
//    }
//
//    public void setCharacter(SWCharacter character) {
//        this.character = character;
//    }


    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        /*returnIntent.putExtra(CHARACTER, character);
        returnIntent.putExtra(POSITION, characterPosition);*/
        returnIntent.putParcelableArrayListExtra(CHARACTERLIST, characterList);
        setResult(Activity.RESULT_OK, returnIntent);

        super.finish();
    }

    @Override
    public int getItemCount() {
        return characterListSize;
    }

    @Override
    public SWCharacter getItem(int position) {
        return characterList.get(position);
    }
}
