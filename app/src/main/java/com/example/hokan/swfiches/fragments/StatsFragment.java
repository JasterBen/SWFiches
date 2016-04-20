package com.example.hokan.swfiches.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.components.HorizontalDoubleEditTextWithSlash;
import com.example.hokan.swfiches.components.HorizontalNumberPicker;

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

        if (v.getId() == R.id.fragment_stats_frag)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(R.string.dialog_stats_title);

            LayoutInflater inflater = LayoutInflater.from(activity);
            View dialogContent = inflater.inflate(R.layout.dialog_edit_stats, null);

            final HorizontalDoubleEditTextWithSlash woundModifier = (HorizontalDoubleEditTextWithSlash)
                    dialogContent.findViewById(R.id.dialog_edit_stats_wound_modifier);
            woundModifier.setLeftValue(character.getActualWound());
            woundModifier.setRightValue(character.getWound());

            final HorizontalDoubleEditTextWithSlash strainModifier = (HorizontalDoubleEditTextWithSlash)
                    dialogContent.findViewById(R.id.dialog_edit_stats_strain_modifier);
            strainModifier.setLeftValue(character.getActualStrain());
            strainModifier.setRightValue(character.getStrain());

            final HorizontalNumberPicker forceRatePicker = (HorizontalNumberPicker)
                    dialogContent.findViewById(R.id.dialog_edit_stats_force_modifier);
            forceRatePicker.setActualValue(character.getForceRating());
            forceRatePicker.setMinValue(0);
            if (!character.getSpecie().isCanHaveForce())
                forceRatePicker.setMaxValue(0);
            else
                forceRatePicker.setMaxValue(32);

            final HorizontalDoubleEditTextWithSlash weightModifier = (HorizontalDoubleEditTextWithSlash)
                    dialogContent.findViewById(R.id.dialog_edit_stats_weight_modifier);
            weightModifier.setLeftValue(character.getActualWeight());
            weightModifier.setRightValue(character.getWeight());

            final HorizontalDoubleEditTextWithSlash xpModifier = (HorizontalDoubleEditTextWithSlash)
                    dialogContent.findViewById(R.id.dialog_edit_stats_xp_modifier);
            xpModifier.setLeftValue(character.getActualXp());
            xpModifier.setRightValue(character.getTotalXp());

            builder.setView(dialogContent);

            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    character.setActualWound(woundModifier.getLeftValue());
                    character.setWound(woundModifier.getRightValue());
                    character.setActualStrain(strainModifier.getLeftValue());
                    character.setStrain(strainModifier.getRightValue());
                    character.setForceRating(forceRatePicker.getActualValue());
                    character.setActualWeight(weightModifier.getLeftValue());
                    character.setWeight(weightModifier.getRightValue());
                    character.setActualXp(xpModifier.getLeftValue());
                    character.setTotalXp(xpModifier.getRightValue());

                    UpdateCharacterStats();
                }
            });

            builder.create().show();
        }

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
                        character.getArmor().getContactDef(),
                        character.getArmor().getRangeDef());
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
