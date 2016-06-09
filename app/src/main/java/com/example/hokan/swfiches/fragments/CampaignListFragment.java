package com.example.hokan.swfiches.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.activities.CampaignActivity;
import com.example.hokan.swfiches.adapters.CampaignAdapter;
import com.example.hokan.swfiches.items.Campaign;
import com.example.hokan.swfiches.services.InternalStorageService;

/**
 * Created by Utilisateur on 02/02/2016.
 */
public class CampaignListFragment extends Fragment implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    protected CampaignAdapter campaignAdapter;
    protected CampaignActivity act;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act = (CampaignActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_campaign_list, container, false);

        Button addCampaign = (Button) v.findViewById(R.id.add_campaign_button);
        addCampaign.setOnClickListener(this);

        RecyclerView recycler = (RecyclerView) v.findViewById(R.id.campaign_list);

        recycler.setLayoutManager(new LinearLayoutManager(act));
        campaignAdapter = new CampaignAdapter(act, this, act);
        recycler.setAdapter(campaignAdapter);

        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        act.getSupportActionBar().setSubtitle("");
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
        final Activity activity = getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.add_campaign_dialog_title);

        LayoutInflater inflater = LayoutInflater.from(activity);
        View dialogContent = inflater.inflate(R.layout.dialog_create_campaign, null);

        final String title = String.format(getString(R.string.campagne_default_title), campaignAdapter.getItemCount() + 1);

        final EditText input = (EditText) dialogContent.findViewById(R.id.dialog_create_campaign_name);
        input.setHint(title);
        builder.setView(dialogContent);

        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.setNegativeButton(android.R.string.no, null);
        final AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = !input.getText().toString().equals("") ?
                        input.getText().toString() : title;

                Campaign campaign = new Campaign(name);
                if (((CampaignActivity) activity).getCampaignList().contains(campaign))
                {
                    AlertDialog.Builder b = new AlertDialog.Builder(activity);
                    b.setMessage(R.string.existing_campaign_dialog_message);
                    b.setPositiveButton(android.R.string.ok, null);
                    b.create().show();
                }
                else
                {
                    campaignAdapter.addItem(campaign);
                    dialog.dismiss();
                }

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Campaign c = campaignAdapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable(CampaignActivity.CAMPAIGN, c);

        CampaignActivity act =  (CampaignActivity) getActivity();
        FragmentManager mgr = act.getSupportFragmentManager();
        FragmentTransaction transaction = mgr.beginTransaction();

        CharacterListFragment frag = new CharacterListFragment();
        frag.setArguments(bundle);
        transaction.replace(R.id.home_page_fragment, frag);
        transaction.addToBackStack("campaign_fragment");
        transaction.commit();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        // Delete Campaign filesave
        Campaign toDelete = campaignAdapter.getItem(position);
        InternalStorageService.deleteCampaign(toDelete.getName());
        // Delete Campaign from campaign list
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
