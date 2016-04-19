package com.example.hokan.swfiches.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.activities.PlayerActivity;
import com.example.hokan.swfiches.components.HorizontalNumberPicker;
import com.example.hokan.swfiches.items.SWCharacter;

/**
 * Created by Ben on 18/04/2016.
 */
public class CharacteristicFragment extends PlayerSuperFragment implements View.OnClickListener {

    protected TextView brawnTextView;
    protected TextView agilityTextView;
    protected TextView intelligenceTextView;
    protected TextView cunningTextView;
    protected TextView willpowerTextView;
    protected TextView presenceTextView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_characteristic, container, false);

        brawnTextView = (TextView) v.findViewById(R.id.fragment_characteristic_brawn);
        brawnTextView.setText(formatString('b'));

        agilityTextView = (TextView) v.findViewById(R.id.fragment_characteristic_agility);
        agilityTextView.setText(formatString('a'));

        intelligenceTextView = (TextView) v.findViewById(R.id.fragment_characteristic_intelligence);
        intelligenceTextView.setText(formatString('i'));

        cunningTextView = (TextView) v.findViewById(R.id.fragment_characteristic_cunning);
        cunningTextView.setText(formatString('c'));

        willpowerTextView = (TextView) v.findViewById(R.id.fragment_characteristic_willpower);
        willpowerTextView.setText(formatString('w'));

        presenceTextView = (TextView) v.findViewById(R.id.fragment_characteristic_presence);
        presenceTextView.setText(formatString('p'));

        v.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.fragment_characteristic_frag)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(R.string.dialog_charac_title);

            LayoutInflater inflater = LayoutInflater.from(activity);
            View dialogContent = inflater.inflate(R.layout.dialog_edit_characteristic, null);

            HorizontalNumberPicker brawnPicker = (HorizontalNumberPicker)
                    v.findViewById(R.id.dialog_edit_characteristic_brawn_picker);
            if (brawnPicker != null)
            {
                brawnPicker.setActualValue(character.getBrawn());
                brawnPicker.setMinValue(Math.max(character.getBrawn(), character.getSpecie().getBrawn()));
            }


            HorizontalNumberPicker agilityPicker = (HorizontalNumberPicker)
                    v.findViewById(R.id.dialog_edit_characteristic_agility_picker);
            if (agilityPicker != null)
            {
                agilityPicker.setActualValue(character.getAgility());
                agilityPicker.setMinValue(Math.max(character.getAgility(), character.getSpecie().getAgility()));
            }


            HorizontalNumberPicker intellectPicker = (HorizontalNumberPicker)
                    v.findViewById(R.id.dialog_edit_characteristic_intelligence_picker);
            if (intellectPicker != null)
            {
                intellectPicker.setActualValue(character.getIntellect());
                intellectPicker.setMinValue(Math.max(character.getIntellect(), character.getSpecie().getIntellect()));
            }


            HorizontalNumberPicker cunningPicker = (HorizontalNumberPicker)
                    v.findViewById(R.id.dialog_edit_characteristic_cunning_picker);
            if (cunningPicker != null)
            {
                cunningPicker.setActualValue(character.getCunning());
                cunningPicker.setMinValue(Math.max(character.getCunning(), character.getSpecie().getCunning()));
            }


            HorizontalNumberPicker willpowerPicker = (HorizontalNumberPicker)
                    v.findViewById(R.id.dialog_edit_characteristic_willpower_picker);
            if (willpowerPicker != null)
            {
                willpowerPicker.setActualValue(character.getWillpower());
                willpowerPicker.setMinValue(Math.max(character.getWillpower(), character.getSpecie().getWillpower()));
            }


            HorizontalNumberPicker presencePicker = (HorizontalNumberPicker)
                    v.findViewById(R.id.dialog_edit_characteristic_presence_picker);
            if (presencePicker != null)
            {
                presencePicker.setActualValue(character.getPresence());
                presencePicker.setMinValue(Math.max(character.getPresence(), character.getSpecie().getPresence()));
            }


            builder.setView(dialogContent);
            builder.create().show();
        }
    }


    /**
     *
     * @param mode 'b' for brawn,
     *             'a' for agility,
     *             'i' for intellect,
     *             'c' for cunning,
     *             'w' for willpower,
     *             'p' for presence
     * @return
     */
    private String formatString(char mode)
    {
        switch (mode) {
            case 'b':
                return String.format(getString(R.string.format_brawn),
                        character.getBrawn() != -1 ? character.getBrawn() : "");
            case 'a':
                return String.format(getString(R.string.format_agility),
                        character.getAgility() != -1 ? character.getAgility() : "");
            case 'i':
                return String.format(getString(R.string.format_intelligence),
                        character.getIntellect() != -1 ? character.getIntellect() : "");
            case 'c':
                return String.format(getString(R.string.format_cunning),
                        character.getCunning() != -1 ? character.getCunning() : "");
            case 'w' :
                return String.format(getString(R.string.format_willpower),
                        character.getWillpower() != -1 ? character.getWillpower() : "");
            case 'p' :
                return String.format(getString(R.string.format_presence),
                        character.getPresence() != -1 ? character.getPresence() : "");
            default :
                return "";
        }
    }
}
