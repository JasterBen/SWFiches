package com.example.hokan.swfiches.items;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class Campaign implements Parcelable, Serializable {

    protected String name;
    protected ArrayList<SWCharacter> characterList;
    protected int characterListSize;

    public Campaign(String campaignName) {
        name = campaignName;
        this.characterList = new ArrayList<>();
        characterListSize = characterList.size();
    }

    //region getter setter adder
    public void addCharacter(SWCharacter character)
    {
        this.characterList.add(character);
        characterListSize++;
    }

    public ArrayList<SWCharacter> getCharacterList() {
        return characterList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCharacterList(ArrayList<SWCharacter> characterList) {
        this.characterList = characterList;
        characterListSize = this.characterList.size();
    }

    public int getCharacterListSize() {
        return characterListSize;
    }

    //endregion

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(characterListSize);
        dest.writeTypedList(characterList);
    }

    public static final Parcelable.Creator<Campaign> CREATOR
            = new Parcelable.Creator<Campaign>() {
        public Campaign createFromParcel(Parcel in) {
            return new Campaign(in);
        }

        public Campaign[] newArray(int size) {
            return new Campaign[size];
        }
    };

    private Campaign(Parcel in) {
        name = in.readString();
        characterListSize = in.readInt();
        characterList = new ArrayList<>();
        in.readTypedList(characterList, SWCharacter.CREATOR);
    }

}
