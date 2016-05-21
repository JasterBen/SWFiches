package com.example.hokan.swfiches.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.components.DicePoolView;
import com.example.hokan.swfiches.fragments.SkillsFragment;
import com.example.hokan.swfiches.interfaces.SkillInterface;
import com.example.hokan.swfiches.items.SWCharacter;
import com.example.hokan.swfiches.items.Skill;

import java.lang.ref.WeakReference;

/**
 * Created by Utilisateur on 22/04/2016.
 */
public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.ViewHolder> {

    protected WeakReference<Context> ctx = new WeakReference<Context>(null);
    protected SkillsFragment skillFragment;
    protected SkillInterface skillInterface;



    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected View v;
        protected TextView nameTextView;
        protected TextView careerTextView;
        protected TextView rankTextView;
        protected DicePoolView dicePoolView;

        public ViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            nameTextView = (TextView) itemView.findViewById(R.id.cell_skill_skill_name);
            careerTextView = (TextView) itemView.findViewById(R.id.cell_skill_is_career);
            rankTextView = (TextView) itemView.findViewById(R.id.cell_skill_rank);
            dicePoolView = (DicePoolView) itemView.findViewById(R.id.cell_skill_dice_pool);
        }
    }


    public SkillAdapter(Context context, SkillsFragment skillFrag, SkillInterface si) {
        this.ctx = new WeakReference<Context>(context);
        this.skillFragment = skillFrag;
        this.skillInterface = si;
    }

    @Override
    public SkillAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx.get()).inflate(R.layout.cell_skill_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SkillAdapter.ViewHolder holder, final int position) {

        Skill skill = getItem(position);
        int skillLevel = skill.getLevel();
        int characLevel = getCharacLevel(skill.getCharacteristic());

        holder.nameTextView.setText(skill.getName());
        holder.careerTextView.setText(skill.isCareer() ? "c" : "");
        holder.rankTextView.setText(String.valueOf(skillLevel));
        holder.dicePoolView.setSkillLevel(skillLevel);
        holder.dicePoolView.setCharacLevel(characLevel);

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skillFragment.onItemClick(null, v, position, v.getId());
            }
        });
    }

    protected int getCharacLevel(char characteristic)
    {
        SWCharacter character = skillFragment.getCharacter();

        switch (characteristic)
        {
            case 'b' :
                return character.getBrawn();
            case 'a' :
                return character.getAgility();
            case 'i' :
                return character.getIntellect();
            case 'c' :
                return character.getCunning();
            case 'w' :
                return character.getWillpower();
            case 'p' :
                return character.getPresence();
            default:
                return 0;
        }
    }


    @Override
    public int getItemCount() {
        return skillInterface.getSkillCount();
    }


    public Skill getItem(int position) {
        return skillInterface.getSkill(position);
    }


}
