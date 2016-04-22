package com.example.hokan.swfiches.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.adapters.SkillAdapter;
import com.example.hokan.swfiches.interfaces.SkillInterface;
import com.example.hokan.swfiches.items.Skill;

/**
 * Created by Ben on 18/04/2016.
 */
public class SkillsFragment extends PlayerSuperFragment implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, SkillInterface {


    private RecyclerView customSkillRecyclerView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_skills, container, false);

        RecyclerView skillRecyClerView = (RecyclerView) v.findViewById(R.id.skill_fragment_skills_recycler_view);

        skillRecyClerView.setLayoutManager(new LinearLayoutManager(activity));
        SkillAdapter skillAdapter = new SkillAdapter(activity, this, this);
        skillRecyClerView.setAdapter(skillAdapter);

        Button b = (Button) v.findViewById(R.id.skill_fragment_ad_custom_skill_button);
        b.setOnClickListener(this);

        customSkillRecyclerView = (RecyclerView) v.findViewById(R.id.skill_frag_custom_skill_recycler_view);
        if (character.getCustomSkillListSize() == 0)
            customSkillRecyclerView.setVisibility(View.GONE);


        return v;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.skill_fragment_ad_custom_skill_button)
        {



        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
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
