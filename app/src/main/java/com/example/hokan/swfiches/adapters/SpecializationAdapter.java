package com.example.hokan.swfiches.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.interfaces.CareerSkillInterface;
import com.example.hokan.swfiches.items.Specialization;

import java.lang.ref.WeakReference;

/**
 * Created by Ben on 27/04/2016.
 */
public class SpecializationAdapter extends BaseAdapter {

    protected WeakReference<Context> ctx = new WeakReference<Context>(null);
    protected CareerSkillInterface careerSkillInterface;


    public class ViewHolder {

        protected int id;
        protected CheckBox skillCheckBox;
    }


    public SpecializationAdapter(Context context, CareerSkillInterface csi) {
        this.ctx = new WeakReference<Context>(context);
        this.careerSkillInterface = csi;
    }


    @Override
    public int getCount() {
        return careerSkillInterface.getSpeSkillCount();
    }

    @Override
    public Object getItem(int position) {
        return careerSkillInterface.getSpeSkill(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(ctx.get());
            convertView = inflater.inflate(R.layout.cell_checkbox, null);
            holder.skillCheckBox = (CheckBox) convertView.findViewById(R.id.checkbox);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.skillCheckBox.setId(position);
        holder.skillCheckBox.setText(((String)getItem(position)));
        holder.skillCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                int id = cb.getId();

                if (careerSkillInterface.getSpeSkillChecked(id))
                {
                    cb.setChecked(false);
                    careerSkillInterface.setSpeSkillChecked(id, false);
                }
                else
                {
                    cb.setChecked(true);
                    careerSkillInterface.setSpeSkillChecked(id, true);
                }
            }
        });
        holder.skillCheckBox.setChecked(careerSkillInterface.getSpeSkillChecked(position));
        holder.id = position;

        return convertView;
    }



}

