package com.example.hokan.swfiches.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.hokan.swfiches.Adapter.CampaignAdapter;
import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.activities.CharacterListActivity;
import com.example.hokan.swfiches.items.Campaign;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class CampaignFragment extends Fragment implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private final static int EDITTEXT_ID = 0;

    protected CampaignAdapter campaignAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_campaign_list, container, false);

        Context context = getContext();

        Button addCampaign = (Button) v.findViewById(R.id.add_campaign_button);
        addCampaign.setOnClickListener(this);

        RecyclerView recycler = (RecyclerView) v.findViewById(R.id.campaign_list);

        recycler.setLayoutManager(new LinearLayoutManager(context));
        campaignAdapter = new CampaignAdapter(context, this);
        recycler.setAdapter(campaignAdapter);

        return v;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.add_campaign_button)
        {
            createNewCampaign();
        }
    }

    private void createNewCampaign()
    {
        Activity activity = getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.add_campaign_dialog_title);
        builder.setMessage(R.string.add_campaign_dialog_message);

        final EditText input = new EditText(activity);
        input.setId(EDITTEXT_ID);
        builder.setView(input);

        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = !input.getText().toString().equals("") ?
                        input.getText().toString() :
                        String.format(getString(R.string.campagne_default_title), campaignAdapter.getItemCount() + 1);

                Campaign campaign = new Campaign(name);
                campaignAdapter.addItem(campaign);
            }
        });

        builder.setNegativeButton(android.R.string.no, null);
        builder.create().show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO: lancer l'activité suivante AVEC LA BONNE CAMPAGNE !!!
        Campaign c = campaignAdapter.getItem(position);
        //Toast.makeText(getContext(), c.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), CharacterListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.remove_campaign_dialog_title);
        builder.setMessage(R.string.remove_campaign_dialog_message);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                campaignAdapter.removeItem(position);
            }
        });
        builder.setNegativeButton(android.R.string.no, null);
        builder.create().show();

        return false;
    }
}
