package com.example.hokan.swfiches.adapters;

import android.content.Context;
import android.view.View;

import com.example.hokan.swfiches.fragments.SkillsFragment;
import com.example.hokan.swfiches.interfaces.SkillInterface;
import com.example.hokan.swfiches.items.Skill;

/**
 * Created by Utilisateur on 22/04/2016.
 */
public class CustomSkillAdapter extends SkillAdapter {


    public CustomSkillAdapter(Context context, SkillsFragment skillFrag, SkillInterface si) {
        super(context, skillFrag, si);
    }

    @Override
    public void onBindViewHolder(CustomSkillAdapter.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        holder.v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return skillFragment.onItemLongClick(null, v, position, v.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return skillInterface.getCustomSkillCount();
    }


    public Skill getItem(int position) {
        return skillInterface.getCustomSkill(position);
    }


    public void addSkill(Skill skill)
    {
        skillInterface.addCustomSkill(skill);
        notifyDataSetChanged();
    }


    public void removeSkill(int position)
    {
        skillInterface.removeCustomSkill(position);
        notifyDataSetChanged();
    }

}
