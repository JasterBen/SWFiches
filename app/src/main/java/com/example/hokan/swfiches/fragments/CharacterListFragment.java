package com.example.hokan.swfiches.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hokan.swfiches.Adapter.CharacterListAdapter;
import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.activities.CharacterListActivity;
import com.example.hokan.swfiches.items.SWCharacter;

/**
 * Created by Ben on 17/04/2016.
 */
public class CharacterListFragment extends Fragment implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    private final static int EDITTEXT_ID = 0;

    protected CharacterListAdapter adapter;
    protected CharacterListActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (CharacterListActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_character_list, container, false);

        TextView title = (TextView) v.findViewById(R.id.character_list_campaign_name);
        title.setText(activity.getCampaign().getName());

        Button addCharacter = (Button) v.findViewById(R.id.add_character_button);
        addCharacter.setOnClickListener(this);

        RecyclerView recycler = (RecyclerView) v.findViewById(R.id.character_list);

        recycler.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new CharacterListAdapter(activity, this);
        recycler.setAdapter(adapter);

        return v;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.add_character_button)
        {
            createNewCharacter();
        }
    }

    private void createNewCharacter()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.add_character_dialog_title);
        builder.setMessage(R.string.add_character_dialog_message);

        final EditText input = new EditText(activity);
        input.setId(EDITTEXT_ID);
        builder.setView(input);

        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = !input.getText().toString().equals("") ?
                        input.getText().toString() :
                        String.format(getString(R.string.character_default_name), adapter.getItemCount() + 1);

                SWCharacter character = new SWCharacter(name);
                adapter.addItem(character);
            }
        });

        builder.setNegativeButton(android.R.string.no, null);
        builder.create().show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //lancer l'activité suivante

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.remove_character_dialog_title);
        builder.setMessage(R.string.remove_character_dialog_message);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.removeItem(position);
            }
        });
        builder.setNegativeButton(android.R.string.no, null);
        builder.create().show();

        return false;
    }

}
