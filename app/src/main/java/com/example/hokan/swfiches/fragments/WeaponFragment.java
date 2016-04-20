package com.example.hokan.swfiches.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hokan.swfiches.R;

/**
 * Created by Ben on 18/04/2016.
 */
public class WeaponFragment extends PlayerSuperFragment implements View.OnClickListener {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weapon, container, false);

        Button b = (Button) v.findViewById(R.id.weapon_frag_button);
        b.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {


    }
}
