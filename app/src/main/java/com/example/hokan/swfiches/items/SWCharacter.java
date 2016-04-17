package com.example.hokan.swfiches.items;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class SWCharacter {

    protected String name;
    protected Specie specie;
    protected Carreer carreer;
    protected ArrayList<Skill> skills;

    public SWCharacter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public Carreer getCarreer() {
        return carreer;
    }

    public void setCarreer(Carreer carreer) {
        this.carreer = carreer;
    }
}
