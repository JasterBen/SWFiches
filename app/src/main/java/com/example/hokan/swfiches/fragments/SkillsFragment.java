package com.example.hokan.swfiches.fragments;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v7.app.AlertDialog;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.adapters.CustomSkillAdapter;
import com.example.hokan.swfiches.adapters.SkillAdapter;
import com.example.hokan.swfiches.components.HorizontalNumberPicker;
import com.example.hokan.swfiches.interfaces.SkillInterface;
import com.example.hokan.swfiches.items.Skill;

import java.util.Arrays;

/**
 * Created by Ben on 18/04/2016.
 */
public class SkillsFragment extends PlayerSuperFragment implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, SkillInterface,
        AdapterView.OnItemSelectedListener {


    private RecyclerView customSkillRecyclerView;
    private RecyclerView skillRecyClerView;
    private CustomSkillAdapter customSkillAdapter;
    private SkillAdapter skillAdapter;

    private String charac;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_skills, container, false);

        skillRecyClerView = (RecyclerView) v.findViewById(R.id.skill_fragment_skills_recycler_view);

        skillRecyClerView.setLayoutManager(new LinearLayoutManager(activity));
        skillAdapter = new SkillAdapter(activity, this, this);
        skillRecyClerView.setAdapter(skillAdapter);

        Button b = (Button) v.findViewById(R.id.skill_fragment_ad_custom_skill_button);
        b.setOnClickListener(this);

        customSkillRecyclerView = (RecyclerView) v.findViewById(R.id.skill_frag_custom_skill_recycler_view);

        customSkillRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        customSkillAdapter = new CustomSkillAdapter(activity, this, this);
        customSkillRecyclerView.setAdapter(customSkillAdapter);

        if (character.getCustomSkillListSize() == 0)
            customSkillRecyclerView.setVisibility(View.GONE);


        return v;
    }



    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.skill_fragment_ad_custom_skill_button)
        {
            createSkillDialog();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //get the skill in the good adapter
        final Skill skill = view.getParent().equals(customSkillRecyclerView) ?
                customSkillAdapter.getItem(position) : skillAdapter.getItem(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(skill.getName());

        LayoutInflater inflater = LayoutInflater.from(activity);
        View dialogContent = inflater.inflate(R.layout.dialog_edit_skill, null);

        //region get element in the view
        final CheckBox isCareerCheckBox = (CheckBox)
                dialogContent.findViewById(R.id.dialog_edit_skill_is_career_checkbox);
        isCareerCheckBox.setChecked(skill.isCareer());

        final HorizontalNumberPicker rankPicker = (HorizontalNumberPicker)
                dialogContent.findViewById(R.id.dialog_edit_skill_rank_picker);
        rankPicker.setActualValue(skill.getLevel());
        rankPicker.setMaxValue(5);
        rankPicker.setMinValue(0);

        HorizontalNumberPicker bonusPicker = (HorizontalNumberPicker)
                dialogContent.findViewById(R.id.dialog_edit_skill_bonus_picker);
        bonusPicker.setActualValue(skill.getBonus());
        bonusPicker.setMaxValue(4);
        bonusPicker.setMinValue(0);

        HorizontalNumberPicker malusPicker = (HorizontalNumberPicker)
                dialogContent.findViewById(R.id.dialog_edit_skill_malus_picker);
        malusPicker.setActualValue(skill.getMalus());
        malusPicker.setMinValue(0);
        malusPicker.setMaxValue(4);
        //endregion

        builder.setView(dialogContent);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                skill.setIsCareer(isCareerCheckBox.isChecked());
                skill.setLevel(rankPicker.getActualValue());
                skill.setBonus(rankPicker.getActualValue());
                skill.setMalus(rankPicker.getActualValue());

                UpdateCharacterSkill();
            }
        });

        builder.setNegativeButton(android.R.string.no, null);

        builder.create().show();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.remove_skill_dialog_title);
        builder.setMessage(R.string.remove_skill_dialog_message);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                customSkillAdapter.removeSkill(position);
            }
        });

        builder.setNegativeButton(android.R.string.no, null);

        builder.create().show();

        return false;

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.dialog_add_skill_charac_spinner)
        {
            charac = parent.getAdapter().getItem(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void createSkillDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.dialog_add_skill_title);

        LayoutInflater inflater = LayoutInflater.from(activity);
        View dialogContent = inflater.inflate(R.layout.dialog_add_skill, null);

        //region get element in the view
        final EditText nameEditText = (EditText)
                dialogContent.findViewById(R.id.dialog_add_skill_name);

        final CheckBox careerCheckBox = (CheckBox)
                dialogContent.findViewById(R.id.dialog_add_skill_is_career_checkbox);

        final Spinner characSpinner = (Spinner)
                dialogContent.findViewById(R.id.dialog_add_skill_charac_spinner);
        ArrayAdapter<String> characAdapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_dropdown_item,
                Arrays.asList(getResources().getStringArray(R.array.skill_frag_dialog_spinner_charac)));
        characSpinner.setAdapter(characAdapter);
        characSpinner.setOnItemSelectedListener(this);

        final HorizontalNumberPicker rankPicker = (HorizontalNumberPicker)
                dialogContent.findViewById(R.id.dialog_add_skill_rank_picker);
        rankPicker.setActualValue(0);
        rankPicker.setMaxValue(5);
        rankPicker.setMinValue(0);
        //endregion

        builder.setView(dialogContent);


        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Skill skill = new Skill(nameEditText.getText().toString(),
                        rankPicker.getActualValue(),
                        setSkillCharac(),
                        careerCheckBox.isChecked());

                customSkillAdapter.addSkill(skill);

                UpdateCharacterSkill();
            }
        });

        builder.setNegativeButton(android.R.string.no, null);

        builder.create().show();
    }


    private char setSkillCharac()
    {
        String[] characArray = getResources().getStringArray(R.array.skill_frag_dialog_spinner_charac);

        if (charac.equals(characArray[0]))
            return 'b';
        if (charac.equals(characArray[1]))
            return 'a';
        if (charac.equals(characArray[2]))
            return 'i';
        if (charac.equals(characArray[3]))
            return 'c';
        if (charac.equals(characArray[4]))
            return 'w';
        if (charac.equals(characArray[5]))
            return 'p';

        return 'n';
    }


    @Override
    public Skill getCustomSkill(int position) {
        return character.getCustomSkillList().get(position);
    }

    @Override
    public void addCustomSkill(Skill skill) {
        if (character.getCustomSkillListSize() == 0)
            customSkillRecyclerView.setVisibility(View.VISIBLE);

        character.getCustomSkillList().add(skill);
        character.setCustomSkillListSize(character.getCustomSkillListSize() + 1);
    }

    @Override
    public void removeCustomSkill(int position) {
        character.getCustomSkillList().remove(position);
        character.setCustomSkillListSize(character.getCustomSkillListSize() - 1);

        if (character.getCustomSkillListSize() == 0)
            customSkillRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public int getCustomSkillCount() {
        return character.getCustomSkillListSize();
    }

    @Override
    public Skill getSkill(int position) {
        return character.getSkillList().get(position);
    }

    @Override
    public int getSkillCount() {
        return character.getSkillListSize();
    }
}
