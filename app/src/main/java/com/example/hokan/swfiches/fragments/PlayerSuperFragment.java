package com.example.hokan.swfiches.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.activities.PlayerActivity;
import com.example.hokan.swfiches.items.SWCharacter;

/**
 * Created by Ben on 19/04/2016.
 */
public abstract class PlayerSuperFragment extends Fragment {

    protected PlayerActivity activity;
    protected SWCharacter character;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (PlayerActivity) getActivity();
        character = activity.getCharacter();

    }


    public void UpdateCharacterStatsAndCharacs()
    {
        FragmentManager mgr = activity.getSupportFragmentManager();
        FragmentTransaction transaction = mgr.beginTransaction();

        CharacteristicFragment characFrag = new CharacteristicFragment();
        StatsFragment statFrag = new StatsFragment();

        transaction.replace(R.id.charac_frag_container, characFrag);
        transaction.replace(R.id.stat_frag_container, statFrag);
        transaction.commit();
    }

    public void UpdateCharacterStats()
    {
        FragmentManager mgr = activity.getSupportFragmentManager();
        FragmentTransaction transaction = mgr.beginTransaction();

        StatsFragment statFrag = new StatsFragment();

        transaction.replace(R.id.stat_frag_container, statFrag);
        transaction.commit();
    }

}
