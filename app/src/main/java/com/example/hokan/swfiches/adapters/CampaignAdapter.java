package com.example.hokan.swfiches.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.fragments.CampaignListFragment;
import com.example.hokan.swfiches.interfaces.CampaignListInterface;
import com.example.hokan.swfiches.items.Campaign;

import java.lang.ref.WeakReference;

/**
 * Created by Utilisateur on 03/02/2016.
 */
public class CampaignAdapter extends RecyclerView.Adapter<CampaignAdapter.ViewHolder> {

    protected WeakReference<Context> ctx = new WeakReference<Context>(null);
    protected CampaignListFragment fragment;
    protected CampaignListInterface campaignListInterface;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected View v;
        protected ImageView campaignImage;
        protected TextView campaignTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            campaignImage = (ImageView) itemView.findViewById(R.id.campaign_cell_illustration);
            campaignTitle = (TextView) itemView.findViewById(R.id.campaign_cell_title);
        }
    }

    public CampaignAdapter(Context context, CampaignListFragment frag, CampaignListInterface cli) {
        ctx = new WeakReference<Context>(context);
        fragment = frag;
        campaignListInterface = cli;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx.get()).inflate(R.layout.cell_campaign, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.campaignTitle.setText(getItem(position).getName());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.onItemClick(null, v, position, v.getId());
            }
        });
        holder.v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return fragment.onItemLongClick(null, v, position, v.getId());
            }
        });

    }


    @Override
    public int getItemCount() {
        return campaignListInterface.getItemCount();
    }

    public void addItem(Campaign campaign)
    {
        campaignListInterface.addItem(campaign);
        notifyDataSetChanged();
    }


    public void removeItem(int position)
    {
        campaignListInterface.removeItem(position);
        notifyDataSetChanged();
    }

    public Campaign getItem(int position)
    {
        return campaignListInterface.getItem(position);
    }
}
