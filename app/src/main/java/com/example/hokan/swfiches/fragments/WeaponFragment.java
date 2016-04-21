package com.example.hokan.swfiches.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.components.HorizontalDoubleEditTextWithSeparator;
import com.example.hokan.swfiches.items.Skill;
import com.example.hokan.swfiches.items.Weapon;

import java.util.ArrayList;

/**
 * Created by Ben on 18/04/2016.
 */
public class WeaponFragment extends PlayerSuperFragment implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    private Skill skill;
    private String range;


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

        if (v.getId() == R.id.weapon_frag_button)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(R.string.weapon_frag_title);

            LayoutInflater inflater = LayoutInflater.from(activity);
            View dialogContent = inflater.inflate(R.layout.dialog_edit_weapon, null);

            final EditText nameEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_weapon_name);
            final EditText damageEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_weapon_damage);
            final EditText criticEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_weapon_critic);
            final EditText weightEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_weapon_weight);
            final EditText specialEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_weapon_special);

            Spinner rangeSpinner = (Spinner) dialogContent.findViewById(R.id.dialog_edit_weapon_range_spinner);
            /*ArrayAdapter<String> rangeAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item);
            rangeSpinner.setAdapter(rangeAdapter);*/
            rangeSpinner.setOnItemSelectedListener(this);
            
            Spinner skillSpinner = (Spinner) dialogContent.findViewById(R.id.dialog_edit_weapon_skill_spinner);
            /*ArrayAdapter<String> skillAdapter = new ArrayAdapter<String>(activity,
                    android.R.layout.simple_spinner_dropdown_item);
            skillSpinner.setAdapter(skillAdapter);*/
            skillSpinner.setOnItemSelectedListener(this);

            final HorizontalDoubleEditTextWithSeparator modContainer =
                    (HorizontalDoubleEditTextWithSeparator) dialogContent.findViewById(R.id.dialog_edit_weapon_mod);

            builder.setView(dialogContent);

            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (character.getWeaponList() == null)
                        character.setWeaponList(new ArrayList<Weapon>());

                    Weapon weapon = new Weapon(nameEditText.getText().toString(),
                            getWeaponIntValue(weightEditText),
                            modContainer.getLeftValue(),
                            modContainer.getRightValue(),
                            specialEditText.getText().toString(),
                            getWeaponIntValue(damageEditText),
                            getWeaponIntValue(criticEditText),
                            range,
                            skill);

                    character.addWeapon(weapon);
                }
            });

            builder.setNegativeButton(android.R.string.no, null);

            builder.create().show();

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (view.getId() == R.id.dialog_edit_weapon_range_spinner)
        {
            range = (String) parent.getAdapter().getItem(position);
        }

        if (view.getId() == R.id.dialog_edit_weapon_skill_spinner)
        {
            String skillName = (String) parent.getAdapter().getItem(position);
            skill = SWFichesApplication.getApp().getSkill(skillName);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
