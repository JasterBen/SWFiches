package com.example.hokan.swfiches.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

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

}
