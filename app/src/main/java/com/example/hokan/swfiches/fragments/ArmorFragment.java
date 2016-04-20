package com.example.hokan.swfiches.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.components.HorizontalDoubleEditTextWithSeparator;
import com.example.hokan.swfiches.items.Armor;

import org.w3c.dom.Text;

/**
 * Created by Ben on 18/04/2016.
 */
public class ArmorFragment extends PlayerSuperFragment implements View.OnClickListener {

    private TextView nameTextView;
    private TextView soakTextView;
    private TextView defenseTextView;
    private TextView weightTextView;
    private TextView modTextView;
    private TextView specialTextView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_armor, container, false);

        nameTextView = (TextView) v.findViewById(R.id.armor_fragment_armor_name);
        nameTextView.setText(formatString('n'));

        soakTextView = (TextView) v.findViewById(R.id.armor_fragment_armor_soak);
        soakTextView.setText(formatString('s'));

        defenseTextView = (TextView) v.findViewById(R.id.armor_fragment_armor_defense);
        defenseTextView.setText(formatString('d'));

        weightTextView = (TextView) v.findViewById(R.id.armor_fragment_armor_weight);
        weightTextView.setText(formatString('w'));

        modTextView = (TextView) v.findViewById(R.id.armor_fragment_armor_mod);
        modTextView.setText(formatString('m'));

        specialTextView = (TextView) v.findViewById(R.id.armor_fragment_armor_special);
        specialTextView.setText(formatString('z'));

        v.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.armor_fragment_frag)
        {
            final Armor armor = character.getArmor();
            
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(R.string.armor_frag_title);

            LayoutInflater inflater = LayoutInflater.from(activity);
            View dialogContent = inflater.inflate(R.layout.dialog_edit_armor, null);

            final EditText nameEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_armor_name);
            if (!armor.getName().equals(""))
                nameEditText.setText(armor.getName());

            final EditText soakEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_armor_soak);
            if (armor.getSoak() != 0)
                soakEditText.setText(String.valueOf(armor.getSoak()));

            final EditText defCacEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_armor_def_cac);
            if (armor.getContactDef() != 0)
                defCacEditText.setText(String.valueOf(armor.getContactDef()));

            final EditText defRangeEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_armor_def_range);
            if (armor.getRangeDef() != 0)
                defRangeEditText.setText(String.valueOf(armor.getRangeDef()));

            final HorizontalDoubleEditTextWithSeparator modContainer = (HorizontalDoubleEditTextWithSeparator)
                    dialogContent.findViewById(R.id.dialog_edit_armor_mod);
            modContainer.setLeftValue(armor.getActualMod());
            modContainer.setRightValue(armor.getMaxMod());

            final EditText weightEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_armor_weight);
            if (armor.getWeight() != 0)
                weightEditText.setText(String.valueOf(armor.getWeight()));

            final EditText specialEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_armor_special);
            if (!armor.getSpecial().equals(""))
                specialEditText.setText(armor.getSpecial());

            builder.setView(dialogContent);

            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Armor newarmor = new Armor(
                            !nameEditText.getText().toString().equals("")  ? nameEditText.getText().toString() :
                                    armor.getName(),
                            !weightEditText.getText().toString().equals("") ? Integer.parseInt(weightEditText.getText().toString()) :
                                    armor.getWeight(),
                            modContainer.getLeftValue(),
                            modContainer.getRightValue(),
                            !specialEditText.getText().toString().equals("") ? specialEditText.getText().toString() :
                                    armor.getSpecial(),
                            !soakEditText.getText().toString().equals("") ? Integer.parseInt(soakEditText.getText().toString()) :
                                    armor.getSoak(),
                            !defCacEditText.getText().toString().equals("") ? Integer.parseInt(defCacEditText.getText().toString()) :
                                    armor.getContactDef(),
                            !defRangeEditText.getText().toString().equals("") ? Integer.parseInt(defRangeEditText.getText().toString()) :
                                    armor.getRangeDef());

                    character.setArmor(newarmor);

                    UpdateCharacterArmorAndStats();
                }
            });

            builder.create().show();
        }

    }


    /**
     *
     * @param mode 'n' for name
     *             's' for soak
     *             'd' for def
     *             'w' for weight
     *             'm' for mod
     *             'z' for special
     * @return
     */
    private String formatString(char mode)
    {
        Armor armor = character.getArmor();

        switch (mode)
        {
            case 'n' :
                return String.format(getString(R.string.armor_frag_armor_name_format),
                        armor.getName());
            case 's' :
                return String.format(getString(R.string.armor_frag_armor_soak_format),
                        armor.getSoak());
            case 'd' :
                return String.format(getString(R.string.armor_frag_armor_def_format),
                        String.valueOf(armor.getContactDef()),
                        String.valueOf(armor.getRangeDef()));
            case 'w' :
                return String.format(getString(R.string.armor_frag_armor_weight_format),
                        armor.getWeight());
            case 'm' :
                return String.format(getString(R.string.armor_frag_armor_mod_format),
                        String.valueOf(armor.getActualMod()),
                        String.valueOf(armor.getMaxMod()));
            case 'z' :
                return String.format(getString(R.string.armor_frag_armor_special_format),
                        armor.getSpecial());
            default :
                return "";
        }
    }
}
