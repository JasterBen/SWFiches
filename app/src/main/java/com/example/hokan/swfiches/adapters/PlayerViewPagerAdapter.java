package com.example.hokan.swfiches.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hokan.swfiches.activities.PlayerActivity;
import com.example.hokan.swfiches.fragments.ViewPagerPlayerFragment;
import com.example.hokan.swfiches.interfaces.CharacterListInterface;

import java.lang.ref.WeakReference;

/**
 * Created by Ben on 18/05/2016.
 */
public class PlayerViewPagerAdapter extends FragmentStatePagerAdapter {

    protected CharacterListInterface characterListInterface;
    protected WeakReference<PlayerActivity> activity = new WeakReference<PlayerActivity>(null);

    public PlayerViewPagerAdapter(FragmentManager fm, CharacterListInterface cli, PlayerActivity act) {
        super(fm);
        characterListInterface = cli;
        activity = new WeakReference<PlayerActivity>(act);
    }

    @Override
    public Fragment getItem(int position) {
        ViewPagerPlayerFragment fragment = new ViewPagerPlayerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PlayerActivity.POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return characterListInterface.getItemCount();
    }
}
