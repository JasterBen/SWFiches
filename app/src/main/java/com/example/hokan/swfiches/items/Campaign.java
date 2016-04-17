package com.example.hokan.swfiches.items;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class Campaign {

    protected String name;
    protected ArrayList<SWCharacter> characters;

    public Campaign(String campaignName) {
        name = campaignName;
        this.characters = new ArrayList<>();
    }

    public void addCharacter(SWCharacter character)
    {
        this.characters.add(character);
    }

    public ArrayList<SWCharacter> getCharacters() {
        return characters;
    }

    public String getName() {
        return name;
    }
}
