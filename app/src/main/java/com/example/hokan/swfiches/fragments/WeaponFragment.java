package com.example.hokan.swfiches.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.adapters.WeaponAdapter;
import com.example.hokan.swfiches.components.HorizontalDoubleEditTextWithSeparator;
import com.example.hokan.swfiches.interfaces.WeaponListInterface;
import com.example.hokan.swfiches.items.Skill;
import com.example.hokan.swfiches.items.Weapon;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ben on 18/04/2016.
 */
public class WeaponFragment extends PlayerSuperFragment implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener,
        WeaponListInterface, AdapterView.OnItemSelectedListener {

    private Skill skill;
    private String range;

    private WeaponAdapter weaponAdapter;
    private RecyclerView weaponListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weapon, container, false);

        weaponListView = (RecyclerView) v.findViewById(R.id.weapon_frag_recycler_view);

        weaponListView.setLayoutManager(new LinearLayoutManager(activity));
        weaponAdapter = new WeaponAdapter(activity, this, this);
        weaponListView.setAdapter(weaponAdapter);

        if (character.getWeaponListSize() == 0)
            weaponListView.setVisibility(View.GONE);

        Button b = (Button) v.findViewById(R.id.weapon_frag_button);
        b.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.weapon_frag_button)
        {
            createEditWeaponDialog(null);
        }

    }


    /**
     *
     * @param w if we create a weapon it is null, else it's the weapon we are updating
     */
    private void createEditWeaponDialog(final Weapon w)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.weapon_frag_title);

        LayoutInflater inflater = LayoutInflater.from(activity);
        View dialogContent = inflater.inflate(R.layout.dialog_edit_weapon, null);

        //region get element in the view
        final EditText nameEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_weapon_name);
        if (w != null && !w.getName().equals(""))
            nameEditText.setText(w.getName());

        final EditText damageEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_weapon_damage);
        if (w != null && w.getDamage() != 0)
            damageEditText.setText(String.valueOf(w.getDamage()));

        final EditText criticEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_weapon_critic);
        if (w != null && w.getCritic() != 0)
            criticEditText.setText(String.valueOf(w.getCritic()));

        final EditText weightEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_weapon_weight);
        if (w != null && w.getWeight() != 0)
            weightEditText.setText(String.valueOf(w.getWeight()));

        final EditText specialEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_weapon_special);
        if (w != null && !w.getSpecial().equals(""))
            specialEditText.setText(w.getSpecial());


        Spinner rangeSpinner = (Spinner) dialogContent.findViewById(R.id.dialog_edit_weapon_range_spinner);
        ArrayAdapter<String> rangeAdapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_dropdown_item,
                Arrays.asList(getResources().getStringArray(R.array.weapon_frag_dialog_spinner_range)));
        rangeSpinner.setAdapter(rangeAdapter);
        if (w != null)
        {
            int spinnerPosition = rangeAdapter.getPosition(w.getRange());
            rangeSpinner.setSelection(spinnerPosition);
        }
        rangeSpinner.setOnItemSelectedListener(this);


        Spinner skillSpinner = (Spinner) dialogContent.findViewById(R.id.dialog_edit_weapon_skill_spinner);
        ArrayAdapter<String> skillAdapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_dropdown_item,
                Arrays.asList(getResources().getStringArray(R.array.weapon_frag_dialog_spinner_skill)));
        skillSpinner.setAdapter(skillAdapter);
        if (w != null)
        {
            int spinnerPosition = skillAdapter.getPosition(w.getSkill().getName());
            skillSpinner.setSelection(spinnerPosition);
        }
        skillSpinner.setOnItemSelectedListener(this);


        final HorizontalDoubleEditTextWithSeparator modContainer =
                (HorizontalDoubleEditTextWithSeparator) dialogContent.findViewById(R.id.dialog_edit_weapon_mod);
        if (w != null)
        {
            if (w.getActualMod() != 0)
                modContainer.setLeftValue(w.getActualMod());
            if (w.getMaxMod() != 0)
                modContainer.setRightValue(w.getMaxMod());
        }
        //endregion

        builder.setView(dialogContent);

        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (w == null)
                {
                    //region create a new weapon
                    if (character.getWeaponList() == null)
                    {
                        character.setWeaponList(new ArrayList<Weapon>());
                    }

                    Weapon weapon = new Weapon(nameEditText.getText().toString(),
                            getWeaponIntValue(weightEditText),
                            modContainer.getLeftValue(),
                            modContainer.getRightValue(),
                            specialEditText.getText().toString(),
                            getWeaponIntValue(damageEditText),
                            getWeaponIntValue(criticEditText),
                            range,
                            skill);

                    weaponAdapter.addItem(weapon);
                    //endregion
                }
                else {
                    //region update an existing weapon
                    w.setName(getValueOrDefault(nameEditText, w.getName()));
                    w.setDamage(getIntValueOrDefault(damageEditText, w.getDamage()));
                    w.setCritic(getIntValueOrDefault(criticEditText, w.getCritic()));
                    w.setWeight(getIntValueOrDefault(weightEditText, w.getWeight()));
                    w.setActualMod(modContainer.getLeftValue());
                    w.setMaxMod(modContainer.getRightValue());
                    w.setRange(range);
                    w.setSkill(skill);
                    w.setSpecial(getValueOrDefault(specialEditText, w.getSpecial()));

                    weaponAdapter.notifyDataSetChanged();
                    //endregion
                }

                UpdateCharacterWeapon();
            }
        });

        builder.setNegativeButton(android.R.string.no, null);

        builder.create().show();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        createEditWeaponDialog(weaponAdapter.getItem(position));
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.weapon_remove_dialog_title);
        builder.setMessage(R.string.weapon_remove_dialog_content);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                weaponAdapter.removeItem(position);
            }
        });

        builder.setNegativeButton(android.R.string.no, null);

        builder.create().show();

        return false;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        int viewId = parent.getId();

        if (viewId == R.id.dialog_edit_weapon_range_spinner)
        {
            range = (String) parent.getAdapter().getItem(position);
        }
        else if (viewId == R.id.dialog_edit_weapon_skill_spinner)
        {
            String skillName = (String) parent.getAdapter().getItem(position);
            skill = SWFichesApplication.getApp().getSkill(skillName);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public int getWeaponCount() {
        return character.getWeaponListSize();
    }

    @Override
    public void addWeapon(Weapon w) {
        if (character.getWeaponListSize() == 0)
            weaponListView.setVisibility(View.VISIBLE);

        character.getWeaponList().add(w);
        character.setWeaponListSize(character.getWeaponListSize() + 1);
    }

    @Override
    public void removeWeapon(int position) {
        character.getWeaponList().remove(position);
        character.setWeaponListSize(character.getWeaponListSize() - 1);

        if (character.getWeaponListSize() == 0)
            weaponListView.setVisibility(View.GONE);
    }

    @Override
    public Weapon getWeapon(int position) {
        return character.getWeaponList().get(position);
    }


}
