package com.example.hokan.swfiches.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.activities.PlayerActivity;
import com.example.hokan.swfiches.items.SWCharacter;

/**
 * Created by Ben on 19/04/2016.
 */
public abstract class SWFichesFragment extends Fragment {

    protected PlayerActivity activity;
    protected SWCharacter character;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (PlayerActivity) getActivity();
        ViewPagerPlayerFragment frag = (ViewPagerPlayerFragment) getParentFragment();
        character = frag.getCharacter();
    }


    public String getValueOrDefault(EditText editText, String defaultValue)
    {
        return !editText.getText().toString().equals("") ?
                editText.getText().toString() : defaultValue;
    }

    public int getIntValueOrDefault(EditText editText, int defaultValue)
    {
        return !editText.getText().toString().equals("") ?
                Integer.parseInt(editText.getText().toString()) : defaultValue;
    }

    public int getWeaponIntValue(EditText editText)
    {
        return (!editText.getText().toString().equals("") ?
                Integer.parseInt(editText.getText().toString()) : 0);
    }


    public void UpdateCharacterNameAndCareer()
    {
        FragmentManager mgr = getParentFragment().getChildFragmentManager();
        FragmentTransaction transaction = mgr.beginTransaction();

        PersoFragment persoFrag = new PersoFragment();
        SkillsFragment skillFrag = new SkillsFragment();
        StatsFragment statFrag = new StatsFragment();

        transaction.replace(R.id.stat_frag_container, statFrag);
        transaction.replace(R.id.perso_frag_container, persoFrag);
        transaction.replace(R.id.skill_frag_container, skillFrag);
        transaction.commit();
    }

    public void UpdateCharacterSpecie()
    {
        FragmentManager mgr = getParentFragment().getChildFragmentManager();
        FragmentTransaction transaction = mgr.beginTransaction();

        CharacteristicFragment characFrag = new CharacteristicFragment();
        StatsFragment statFrag = new StatsFragment();
        PersoFragment persoFrag = new PersoFragment();
        SkillsFragment skillFrag = new SkillsFragment();
        WeaponFragment weaponFrag = new WeaponFragment();

        transaction.replace(R.id.perso_frag_container, persoFrag);
        transaction.replace(R.id.charac_frag_container, characFrag);
        transaction.replace(R.id.stat_frag_container, statFrag);
        transaction.replace(R.id.skill_frag_container, skillFrag);
        transaction.replace(R.id.weapon_frag_container, weaponFrag);
        transaction.commit();
    }

    public void UpdateCharacterCharacs()
    {
        FragmentManager mgr = getParentFragment().getChildFragmentManager();
        FragmentTransaction transaction = mgr.beginTransaction();

        CharacteristicFragment characFrag = new CharacteristicFragment();
        StatsFragment statFrag = new StatsFragment();
        WeaponFragment weaponFrag = new WeaponFragment();
        SkillsFragment skillFrag = new SkillsFragment();

        transaction.replace(R.id.charac_frag_container, characFrag);
        transaction.replace(R.id.stat_frag_container, statFrag);
        transaction.replace(R.id.weapon_frag_container, weaponFrag);
        transaction.replace(R.id.skill_frag_container, skillFrag);
        transaction.commit();
    }

    public void UpdateCharacterStats()
    {
        FragmentManager mgr = getParentFragment().getChildFragmentManager();
        FragmentTransaction transaction = mgr.beginTransaction();

        StatsFragment statFrag = new StatsFragment();

        transaction.replace(R.id.stat_frag_container, statFrag);
        transaction.commit();
    }


    public void UpdateCharacterSkill()
    {
        FragmentManager mgr = getParentFragment().getChildFragmentManager();
        FragmentTransaction transaction = mgr.beginTransaction();

        SkillsFragment skillFrag = new SkillsFragment();
        WeaponFragment weaponFrag = new WeaponFragment();

        transaction.replace(R.id.skill_frag_container, skillFrag);
        transaction.replace(R.id.weapon_frag_container, weaponFrag);

        transaction.commit();
    }


    public void UpdateCharacterWeapon()
    {
        FragmentManager mgr = getParentFragment().getChildFragmentManager();
        FragmentTransaction transaction = mgr.beginTransaction();

        WeaponFragment weaponFrag = new WeaponFragment();

        transaction.replace(R.id.weapon_frag_container, weaponFrag);
        transaction.commit();
    }

    public void UpdateCharacterArmorAndStats()
    {
        FragmentManager mgr = getParentFragment().getChildFragmentManager();
        FragmentTransaction transaction = mgr.beginTransaction();

        StatsFragment statFrag = new StatsFragment();
        ArmorFragment armorFrag = new ArmorFragment();

        transaction.replace(R.id.stat_frag_container, statFrag);
        transaction.replace(R.id.armor_frag_container, armorFrag);
        transaction.commit();
    }

    public SWCharacter getCharacter() {
        return character;
    }
}
