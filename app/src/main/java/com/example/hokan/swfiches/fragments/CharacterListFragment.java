package com.example.hokan.swfiches.fragments;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.adapters.CharacterListAdapter;
import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.activities.CampaignActivity;
import com.example.hokan.swfiches.items.Campaign;
import com.example.hokan.swfiches.items.SWCharacter;
import com.example.hokan.swfiches.items.Specie;

/**
 * Created by Ben on 17/04/2016.
 */
public class CharacterListFragment extends Fragment implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    protected CharacterListAdapter adapter;
    protected CampaignActivity activity;
    protected Campaign campaign;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (CampaignActivity) getActivity();

        Bundle bundle = getArguments();
        if (bundle != null)
        {
            campaign = bundle.getParcelable(CampaignActivity.CAMPAIGN);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_character_list, container, false);

        TextView title = (TextView) v.findViewById(R.id.character_list_campaign_name);
        title.setText(campaign.getName());

        Button addCharacter = (Button) v.findViewById(R.id.add_character_button);
        addCharacter.setOnClickListener(this);

        RecyclerView recycler = (RecyclerView) v.findViewById(R.id.character_list);

        recycler.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new CharacterListAdapter(activity, this, activity);
        recycler.setAdapter(adapter);

        return v;
    }


    public Campaign getCampaign() {
        return campaign;
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

        LayoutInflater inflater = LayoutInflater.from(activity);
        View v = inflater.inflate(R.layout.dialog_create_character, null);

        final String name = String.format(getString(R.string.character_default_name), adapter.getItemCount() + 1);
        final SWCharacter character = new SWCharacter(name);

        final EditText input = (EditText) v.findViewById(R.id.dialog_create_character_name);
        input.setHint(name);

        final Spinner speciesSpinner = (Spinner) v.findViewById(R.id.dialog_create_character_species);

        ArrayAdapter<Specie> spinnerAdapter = new ArrayAdapter<Specie>(activity,
                android.R.layout.simple_spinner_dropdown_item, SWFichesApplication.getApp().getSpeciesList());
        speciesSpinner.setAdapter(spinnerAdapter);
        speciesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                character.setSpecie((Specie) parent.getAdapter().getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        builder.setView(v);

        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newName = !input.getText().toString().equals("") ?
                        input.getText().toString() : name;

                character.setName(newName);
                adapter.addItem(character);
            }
        });

        builder.setNegativeButton(android.R.string.no, null);
        builder.create().show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //lancer l'activité suivante qui affiche la fiche perso
        SWCharacter c = adapter.getItem(position);
        Specie s = c.getSpecie();
        String format = "Espèce : %s\nVig : %d\nWound : %d\nCan use the force : %s";
        Toast.makeText(activity,
                String.format(format, s.getName(), s.getBrawn(), (s.getWound() + s.getBrawn()),
                        s.isCanHaveForce() ? "Vrai" : "Faux"), Toast.LENGTH_SHORT).show();

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
