package com.example.hokan.swfiches.interfaces;

import com.example.hokan.swfiches.items.Weapon;

/**
 * Created by Utilisateur on 22/04/2016.
 */
public interface WeaponListInterface {

    int getWeaponCount();

    void addWeapon(Weapon w);

    void removeWeapon(int position);

    Weapon getWeapon(int position);

}
