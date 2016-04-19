package com.example.hokan.swfiches.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hokan.swfiches.R;

/**
 * Created by Ben on 18/04/2016.
 */
public class StatsFragment extends PlayerSuperFragment implements View.OnClickListener {

    protected TextView soakTextView;
    protected TextView woundTextView;
    protected TextView strainTextView;
    protected TextView defenseTextView;
    protected TextView forceRateTextView;
    protected TextView weightTextView;
    protected TextView xpTextView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_stats, container, false);

        soakTextView = (TextView) v.findViewById(R.id.fragment_stats_soak);
        soakTextView.setText(formatString('s'));

        woundTextView = (TextView) v.findViewById(R.id.fragment_stats_wound);
        woundTextView.setText(formatString('w'));

        strainTextView = (TextView) v.findViewById(R.id.fragment_stats_strain);
        strainTextView.setText(formatString('z'));

        defenseTextView = (TextView) v.findViewById(R.id.fragment_stats_def);
        defenseTextView.setText(formatString('d'));

        forceRateTextView = (TextView) v.findViewById(R.id.fragment_stats_force_rate);
        forceRateTextView.setText(formatString('f'));

        weightTextView = (TextView) v.findViewById(R.id.fragment_stats_weight);
        weightTextView.setText(formatString('p'));

        xpTextView = (TextView) v.findViewById(R.id.fragment_stats_xp);
        xpTextView.setText(formatString('x'));


        v.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(activity, "plif", Toast.LENGTH_SHORT).show();
    }


    /**
     *
     * @param mode 's' for soak
     *             'w' for wound
     *             'z' for strain
     *             'd' for defense
     *             'f' for force rate
     *             'p' for weight
     *             'x' for xp
     * @return
     */
    private String formatString(char mode)
    {
        switch (mode) {
            case 's':
                return String.format(getString(R.string.format_soak),
                        character.getSoak() != -1 ? character.getSoak() : "");
            case 'w':
                return String.format(getString(R.string.format_wound),
                        character.getActualWound(),
                        (character.getWound() != -1 ? character.getWound() : ""));
            case 'z':
                return String.format(getString(R.string.format_strain),
                        character.getActualStrain(),
                        (character.getStrain() != -1 ? character.getStrain() : ""));
            case 'd':
                return String.format(getString(R.string.format_defense),
                        character.getContactDefense(),
                        character.getRangeDefense());
            case 'f' :
                return String.format(getString(R.string.format_force_rate),
                        character.getForceRating());
            case 'p' :
                return String.format(getString(R.string.format_weight),
                        character.getActualWeight(),
                        (character.getWeight() != -1 ? character.getWeight() : ""));
            case 'x' :
                return String.format(getString(R.string.format_xp),
                        character.getActualXp(),
                        (character.getTotalXp() != -1 ? character.getTotalXp() : ""));
            default :
                return "";
        }
    }
}
