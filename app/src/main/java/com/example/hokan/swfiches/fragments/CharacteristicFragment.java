package com.example.hokan.swfiches.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.activities.PlayerActivity;
import com.example.hokan.swfiches.items.SWCharacter;

/**
 * Created by Ben on 18/04/2016.
 */
public class CharacteristicFragment extends Fragment implements View.OnClickListener {

    protected PlayerActivity activity;
    protected SWCharacter character;
    protected TextView brawnTextView;
    protected TextView agilityTextView;
    protected TextView intelligenceTextView;
    protected TextView cunningTextView;
    protected TextView willpowerTextView;
    protected TextView presenceTextView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (PlayerActivity) getActivity();
        character = activity.getCharacter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_characteristic, container, false);

        brawnTextView = (TextView) v.findViewById(R.id.fragment_characteristic_brawn);
        brawnTextView.setText(R.string.format_brawn);

        agilityTextView = (TextView) v.findViewById(R.id.fragment_characteristic_agility);
        agilityTextView.setText(R.string.format_agility);

        intelligenceTextView = (TextView) v.findViewById(R.id.fragment_characteristic_intelligence);
        intelligenceTextView.setText(R.string.format_intelligence);

        cunningTextView = (TextView) v.findViewById(R.id.fragment_characteristic_cunning);
        cunningTextView.setText(R.string.format_cunning);

        willpowerTextView = (TextView) v.findViewById(R.id.fragment_characteristic_willpower);
        willpowerTextView.setText(R.string.format_willpower);

        presenceTextView = (TextView) v.findViewById(R.id.fragment_characteristic_presence);
        presenceTextView.setText(R.string.format_presence);

        v.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.fragment_characteristic_frag)
        {
            Toast.makeText(activity, "prout", Toast.LENGTH_SHORT).show();
        }
    }
}
