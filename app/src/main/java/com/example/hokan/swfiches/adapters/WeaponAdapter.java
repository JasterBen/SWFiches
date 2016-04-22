package com.example.hokan.swfiches.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.fragments.WeaponFragment;
import com.example.hokan.swfiches.interfaces.WeaponListInterface;
import com.example.hokan.swfiches.items.Weapon;

import java.lang.ref.WeakReference;

/**
 * Created by Utilisateur on 22/04/2016.
 */
public class WeaponAdapter extends RecyclerView.Adapter<WeaponAdapter.ViewHolder> {

    protected WeakReference<Context> ctx = new WeakReference<Context>(null);
    protected WeaponFragment weaponFragment;
    protected WeaponListInterface weaponInterface;



    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected View v;
        protected TextView weaponName;
        protected TextView weaponDamage;
        protected TextView weaponCritic;
        protected TextView weaponWeight;
        protected TextView weaponMods;
        protected TextView weaponRange;
        protected TextView weaponSkill;
        protected TextView weaponSpecial;

        public ViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            weaponName = (TextView) itemView.findViewById(R.id.weapon_cell_weapon_name);
            weaponDamage = (TextView) itemView.findViewById(R.id.weapon_cell_weapon_damage);
            weaponCritic = (TextView) itemView.findViewById(R.id.weapon_cell_weapon_critic);
            weaponWeight = (TextView) itemView.findViewById(R.id.weapon_cell_weapon_weight);
            weaponMods = (TextView) itemView.findViewById(R.id.weapon_cell_weapon_mod);
            weaponRange = (TextView) itemView.findViewById(R.id.weapon_cell_weapon_range);
            weaponSkill = (TextView) itemView.findViewById(R.id.weapon_cell_weapon_skill);
            weaponSpecial = (TextView) itemView.findViewById(R.id.weapon_cell_weapon_special);
        }
    }


    public WeaponAdapter(Context context, WeaponFragment weaponFrag, WeaponListInterface wi) {
        this.ctx = new WeakReference<Context>(context);
        this.weaponFragment = weaponFrag;
        this.weaponInterface = wi;
    }


    @Override
    public WeaponAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx.get()).inflate(R.layout.cell_weapon_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WeaponAdapter.ViewHolder holder, final int position) {

        Weapon w = getItem(position);

        holder.weaponName.setText(formatString('n', w));
        holder.weaponDamage.setText(formatString('d', w));
        holder.weaponCritic.setText(formatString('c', w));
        holder.weaponWeight.setText(formatString('w', w));
        holder.weaponMods.setText(formatString('m', w));
        holder.weaponRange.setText(formatString('r', w));
        holder.weaponSkill.setText(w.getSkill() != null ? w.getSkill().getName() : "");
        holder.weaponSpecial.setText(formatString('s', w));

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weaponFragment.onItemClick(null, v, position, v.getId());
            }
        });
        holder.v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return weaponFragment.onItemLongClick(null, v, position, v.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return weaponInterface.getWeaponCount();
    }

    public void addItem(Weapon weapon)
    {
        weaponInterface.addWeapon(weapon);
        notifyDataSetChanged();
    }


    public void removeItem(int position)
    {
        weaponInterface.removeWeapon(position);
        notifyDataSetChanged();
    }

    public Weapon getItem(int position)
    {
        return weaponInterface.getWeapon(position);
    }


    /**
     *
     * @param mode 'n' for name
     *             'd' for damage
     *             'c' for critic
     *             'w' for weight
     *             'm' for mod
     *             'r' for range
     *             's' for special
     * @return
     */
    private String formatString(char mode, Weapon weapon)
    {
        Context context = ctx.get();

        switch (mode) {
            case 'n' :
                return String.format(context.getString(R.string.weapon_frag_weapon_name_format),
                        weapon.getName());
            case 'd' :
                return String.format(context.getString(R.string.weapon_frag_weapon_damage_format),
                        weapon.getDamage());
            case 'c' :
                return String.format(context.getString(R.string.weapon_frag_weapon_critic_format),
                        weapon.getCritic());
            case 'w' :
                return String.format(context.getString(R.string.weapon_frag_weapon_weight_format),
                        weapon.getWeight());
            case 'm' :
                return String.format(context.getString(R.string.weapon_frag_weapon_mod_format),
                        weapon.getActualMod(), weapon.getMaxMod());
            case 'r' :
                return String.format(context.getString(R.string.weapon_frag_weapon_range_format),
                        weapon.getRange());
            case 's' :
                return String.format(context.getString(R.string.weapon_frag_weapon_special_format),
                        weapon.getSpecial());
            default :
                return "";
        }
    }

}
