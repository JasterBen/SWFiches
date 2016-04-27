package com.example.hokan.swfiches.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.components.HorizontalNumberPicker;

/**
 * Created by Ben on 18/04/2016.
 */
public class CharacteristicFragment extends PlayerSuperFragment implements View.OnClickListener {

    private final static int MAXVALUE = 6;

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
            createCharacFragment();
        }
    }


    private void createCharacFragment()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.dialog_charac_title);

        LayoutInflater inflater = LayoutInflater.from(activity);
        View dialogContent = inflater.inflate(R.layout.dialog_edit_characteristic, null);

        //region get element in the view
        final HorizontalNumberPicker brawnPicker = (HorizontalNumberPicker)
                dialogContent.findViewById(R.id.dialog_edit_characteristic_brawn_picker);
        brawnPicker.setActualValue(character.getBrawn());
        brawnPicker.setMinValue(character.getSpecie().getBrawn());
        brawnPicker.setMaxValue(MAXVALUE);


        final HorizontalNumberPicker agilityPicker = (HorizontalNumberPicker)
                dialogContent.findViewById(R.id.dialog_edit_characteristic_agility_picker);
        agilityPicker.setActualValue(character.getAgility());
        agilityPicker.setMinValue(character.getSpecie().getAgility());
        agilityPicker.setMaxValue(MAXVALUE);


        final HorizontalNumberPicker intellectPicker = (HorizontalNumberPicker)
                dialogContent.findViewById(R.id.dialog_edit_characteristic_intelligence_picker);
        intellectPicker.setActualValue(character.getIntellect());
        intellectPicker.setMinValue(character.getSpecie().getIntellect());
        intellectPicker.setMaxValue(MAXVALUE);


        final HorizontalNumberPicker cunningPicker = (HorizontalNumberPicker)
                dialogContent.findViewById(R.id.dialog_edit_characteristic_cunning_picker);
        cunningPicker.setActualValue(character.getCunning());
        cunningPicker.setMinValue(character.getSpecie().getCunning());
        cunningPicker.setMaxValue(MAXVALUE);


        final HorizontalNumberPicker willpowerPicker = (HorizontalNumberPicker)
                dialogContent.findViewById(R.id.dialog_edit_characteristic_willpower_picker);
        willpowerPicker.setActualValue(character.getWillpower());
        willpowerPicker.setMinValue(character.getSpecie().getWillpower());
        willpowerPicker.setMaxValue(MAXVALUE);


        final HorizontalNumberPicker presencePicker = (HorizontalNumberPicker)
                dialogContent.findViewById(R.id.dialog_edit_characteristic_presence_picker);
        presencePicker.setActualValue(character.getPresence());
        presencePicker.setMinValue(character.getSpecie().getPresence());
        presencePicker.setMaxValue(MAXVALUE);
        //endregion

        builder.setView(dialogContent);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                character.setBrawn(brawnPicker.getActualValue());
                character.setAgility(agilityPicker.getActualValue());
                character.setIntellect(intellectPicker.getActualValue());
                character.setCunning(cunningPicker.getActualValue());
                character.setWillpower(willpowerPicker.getActualValue());
                character.setPresence(presencePicker.getActualValue());

                UpdateCharacterCharacs();
            }
        });

        builder.setNegativeButton(android.R.string.no, null);

        builder.create().show();
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
