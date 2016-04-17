package com.example.hokan.swfiches.interfaces;

import com.example.hokan.swfiches.items.Campaign;
import com.example.hokan.swfiches.items.SWCharacter;

import java.util.ArrayList;

/**
 * Created by Ben on 17/04/2016.
 */
public interface CampaignListInterface {

    int getItemCount();

    void addItem(Campaign c);

    void removeItem(int position);

    Campaign getItem(int position);

    void changeCharacterList(Campaign c, ArrayList<SWCharacter> list);

}
