package com.example.hokan.swfiches.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.fragments.CharacterListFragment;
import com.example.hokan.swfiches.items.SWCharacter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Ben on 17/04/2016.
 */
public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.ViewHolder> {

    protected ArrayList<SWCharacter> characterList;
    protected int characterListSize;
    protected WeakReference<Context> ctx = new WeakReference<Context>(null);
    protected CharacterListFragment fragment;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected View v;
        protected ImageView characterImage;
        protected TextView characterName;

        public ViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            characterImage = (ImageView) itemView.findViewById(R.id.character_cell_illustration);
            characterName = (TextView) itemView.findViewById(R.id.character_cell_name);
        }

    }

    public CharacterListAdapter(Context context, CharacterListFragment frag) {
        characterList = new ArrayList<>();
        characterListSize = characterList.size();
        ctx = new WeakReference<Context>(context);
        fragment = frag;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx.get()).inflate(R.layout.cell_character_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.characterName.setText(characterList.get(position).getName());

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
        return characterListSize;
    }

    public void addItem(SWCharacter character)
    {
        characterList.add(character);
        characterListSize++;
        notifyDataSetChanged();
    }

    public void removeItem(int position)
    {
        characterList.remove(position);
        characterListSize--;
        notifyDataSetChanged();
    }

    public SWCharacter getItem(int position)
    {
        return characterList.get(position);
    }
}
