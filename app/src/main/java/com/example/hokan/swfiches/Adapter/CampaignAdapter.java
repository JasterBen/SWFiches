package com.example.hokan.swfiches.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.fragments.CampaignFragment;
import com.example.hokan.swfiches.items.Campaign;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Utilisateur on 03/02/2016.
 */
public class CampaignAdapter extends RecyclerView.Adapter<CampaignAdapter.ViewHolder> {

    protected ArrayList<Campaign> campaignList;
    protected int campainListSize;
    protected WeakReference<Context> ctx = new WeakReference<Context>(null);
    protected CampaignFragment fragment;

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

    public CampaignAdapter(Context context, CampaignFragment frag) {
        campaignList = new ArrayList<>();
        campainListSize = campaignList.size();
        ctx = new WeakReference<Context>(context);
        fragment = frag;
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
        return campainListSize;
    }

    public void addItem(Campaign campaign)
    {
        campaignList.add(campaign);
        campainListSize++;
        notifyDataSetChanged();
    }

    public void removeItem(int position)
    {
        campaignList.remove(position);
        campainListSize--;
        notifyDataSetChanged();
    }

    public Campaign getItem(int position)
    {
        return campaignList.get(position);
    }
}
