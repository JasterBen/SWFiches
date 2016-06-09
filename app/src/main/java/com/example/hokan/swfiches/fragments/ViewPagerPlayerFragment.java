package com.example.hokan.swfiches.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.activities.PlayerActivity;
import com.example.hokan.swfiches.items.SWCharacter;

/**
 * Created by Ben on 18/05/2016.
 */
public class ViewPagerPlayerFragment extends Fragment {

    protected SWCharacter character;
    protected int position;
    private boolean areSkillsVisible;
    private boolean areCustomSkillsVisible;
    private boolean areWeaponVisible;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null)
        {
            PlayerActivity activity = (PlayerActivity) getActivity();
            position = bundle.getInt(PlayerActivity.POSITION);
            character = activity.getCharacter(position);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_pager_player, container, false);

        if (savedInstanceState == null)
        {
            FragmentManager mgr = getChildFragmentManager();
            FragmentTransaction transaction = mgr.beginTransaction();

            PersoFragment persoFrag = new PersoFragment();
            CharacteristicFragment characFrag = new CharacteristicFragment();
            StatsFragment statFrag = new StatsFragment();
            SkillsFragment skillsFrag = new SkillsFragment();
            //TreeFragment treeFrag = new TreeFragment();
            WeaponFragment weaponFrag = new WeaponFragment();
            ArmorFragment armorFrag = new ArmorFragment();
            /*StuffFragment stuffFrag = new StuffFragment();
            PsychoFragment psychoFrag = new PsychoFragment();*/

            transaction.replace(R.id.perso_frag_container, persoFrag);
            transaction.replace(R.id.charac_frag_container, characFrag);
            transaction.replace(R.id.stat_frag_container, statFrag);
            transaction.replace(R.id.skill_frag_container, skillsFrag);
            transaction.replace(R.id.weapon_frag_container, weaponFrag);
            transaction.replace(R.id.armor_frag_container, armorFrag);
            transaction.commit();
        }

        return v;
    }

    public SWCharacter getCharacter() {
        return character;
    }

    public boolean getAreSkillsVisible() {
        return areSkillsVisible;
    }

    public void setAreSkillsVisible(boolean areSkillsVisible) {
        this.areSkillsVisible = areSkillsVisible;
    }

    public boolean getAreCustomSkillsVisible() {
        return areCustomSkillsVisible;
    }

    public void setAreCustomSkillsVisible(boolean areCustomSkillsVisible) {
        this.areCustomSkillsVisible = areCustomSkillsVisible;
    }

    public boolean getAreWeaponVisible() {
        return areWeaponVisible;
    }

    public void setAreWeaponVisible(boolean areWeaponVisible) {
        this.areWeaponVisible = areWeaponVisible;
    }
}
