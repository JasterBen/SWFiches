package com.example.hokan.swfiches.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.activities.PlayerActivity;
import com.example.hokan.swfiches.items.Career;
import com.example.hokan.swfiches.items.SWCharacter;
import com.example.hokan.swfiches.items.Specialization;
import com.example.hokan.swfiches.items.Specie;

/**
 * Created by Ben on 18/04/2016.
 */
public class PersoFragment extends Fragment implements View.OnClickListener {

    protected TextView nameTextView;
    protected TextView specieTextView;
    protected TextView careerTextView;
    protected TextView specTextView;
    protected PlayerActivity activity;
    protected SWCharacter character;
    protected Button addCareer;
    protected Button addSpecialization;
    protected Button addNewSpecialization;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (PlayerActivity) getActivity();
        character = activity.getCharacter();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_perso, container, false);

        nameTextView = (TextView) v.findViewById(R.id.fragment_perso_name);
        nameTextView.setText(formatString('n'));

        specieTextView = (TextView) v.findViewById(R.id.fragment_perso_specie);
        specieTextView.setText(formatString('r'));

        careerTextView = (TextView) v.findViewById(R.id.fragment_perso_career);
        careerTextView.setText(formatString('c'));

        specTextView = (TextView) v.findViewById(R.id.fragment_perso_specialization);
        specTextView.setText(formatString('s'));

        v.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.fragment_perso_frag)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(R.string.dialog_edit_perso_title);

            LayoutInflater inflater = LayoutInflater.from(activity);
            View dialogContent = inflater.inflate(R.layout.dialog_edit_perso, null);

            final EditText nameEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_perso_name);
            nameEditText.setText(character.getName());

            //region spinner
            final Spinner speciesSpinner = (Spinner) dialogContent.findViewById(R.id.dialog_edit_perso_specie);

            ArrayAdapter<Specie> spinnerAdapter = new ArrayAdapter<Specie>(activity,
                    android.R.layout.simple_spinner_dropdown_item, SWFichesApplication.getApp().getSpeciesList());
            speciesSpinner.setAdapter(spinnerAdapter);
            // TODO : bug fix, spinner pas sur la bonne position à la première ouverture
            int spinnerPosition = spinnerAdapter.getPosition(character.getSpecie());
            speciesSpinner.setSelection(spinnerPosition);
            speciesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    character.setSpecie((Specie) parent.getAdapter().getItem(position));
                    specieTextView.setText(formatString('r'));

                    FragmentManager mgr = activity.getSupportFragmentManager();
                    FragmentTransaction transaction = mgr.beginTransaction();

                    CharacteristicFragment characFrag = new CharacteristicFragment();

                    transaction.replace(R.id.charac_frag_container, characFrag);
                    transaction.commit();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            //endregion

            addCareer = (Button) dialogContent.findViewById(R.id.dialog_edit_perso_career);
            addCareer.setText(character.getCareer() != null ?
                    character.getCareer().getName() : getString(R.string.add_career));
            addCareer.setOnClickListener(this);

            addSpecialization = (Button) dialogContent.findViewById(R.id.dialog_edit_perso_specialization);
            addSpecialization.setText(character.getMainSpecialization() != null ?
                    character.getMainSpecialization().getName() : getString(R.string.add_first_specialization));
            addSpecialization.setOnClickListener(this);

            addNewSpecialization = (Button) dialogContent.findViewById(R.id.dialog_edit_perso_add_specialization);
            addNewSpecialization.setOnClickListener(this);

            builder.setView(dialogContent);

            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    character.setName(nameEditText.getText().toString());
                    nameTextView.setText(formatString('n'));
                }
            });

            builder.create().show();

        }
        else if (id == R.id.dialog_edit_perso_career)
        {
            //TODO : add gridview of checkbox

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(R.string.dialog_add_career_title);

            LayoutInflater inflater = LayoutInflater.from(activity);
            View dialogContent = inflater.inflate(R.layout.dialog_career_perso, null);

            //region spinner
            final Spinner careerSpinner = (Spinner) dialogContent.findViewById(R.id.dialog_career_spinner);

            ArrayAdapter<Career> spinnerAdapter = new ArrayAdapter<>(activity,
                    android.R.layout.simple_spinner_dropdown_item, SWFichesApplication.getApp().getCareerList());
            careerSpinner.setAdapter(spinnerAdapter);
            careerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    character.setCareer((Career) parent.getAdapter().getItem(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            //endregion

            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    careerTextView.setText(formatString('c'));
                    addCareer.setText(character.getCareer().getName());
                }
            });

            builder.setView(dialogContent);
            builder.create().show();

        }
        else if (id == R.id.dialog_edit_perso_specialization)
        {
            //TODO : create dialog
            addNewSpecialization.setEnabled(true);
        }
        else if (id == R.id.dialog_edit_perso_add_specialization)
        {
            // TODO
        }
    }


    /**
     * @param mode 'n' for name,
     *             'r' for specie,
     *             'c' for career,
     *             's' for spezialisation
     * @return
     */
    private String formatString(char mode)
    {
        switch (mode) {
            case 'n':
                return String.format(getString(R.string.format_name),
                        character.getName() != null ? character.getName() : "");
            case 'r':
                return String.format(getString(R.string.format_specie),
                        character.getSpecie() != null ? character.getSpecie().getName() : "");
            case 'c':
                return String.format(getString(R.string.format_career),
                        character.getCareer() != null ? character.getCareer().getName() : "");
            case 's':
                String res = String.format(getString(R.string.format_spezcialization),
                        character.getMainSpecialization() != null ? character.getMainSpecialization().getName() : "");
                if (character.getSecondarySpecializations() != null) {
                    for (Specialization s : character.getSecondarySpecializations()) {
                        res += String.format(getString(R.string.format_multiple_specialization), s.getName());
                    }
                }
                return res;
            default :
                return "";
        }
    }
}
