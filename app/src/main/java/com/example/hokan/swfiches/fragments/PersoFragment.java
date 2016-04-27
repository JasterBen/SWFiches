package com.example.hokan.swfiches.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.adapters.CareerAdapter;
import com.example.hokan.swfiches.interfaces.CareerSkillInterface;
import com.example.hokan.swfiches.items.Career;
import com.example.hokan.swfiches.items.Skill;
import com.example.hokan.swfiches.items.Specialization;
import com.example.hokan.swfiches.items.Specie;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ben on 18/04/2016.
 */
public class PersoFragment extends PlayerSuperFragment implements View.OnClickListener,
        CareerSkillInterface, AdapterView.OnItemSelectedListener {

    private static final int DROID_MAX_CAREER_SKILL = 6;
    private static final String SELECTED_CAREER_SKILL = "scs";
    private static final String PREVIOUS_CAREER = "career";
    private static final String PREVIOUS_CAREER_SKILL = "pcs";

    protected TextView nameTextView;
    protected TextView specieTextView;
    protected TextView careerTextView;
    protected TextView specTextView;
    protected Button addCareer;
    protected Button addSpecialization;
    protected Button addNewSpecialization;

    protected boolean[] selectedCareerSkill;
    protected CareerAdapter careerAdapter;
    protected String previousCareer = "";
    protected ArrayList<String> previousCareerSkill;

    protected Specie characterSpecie;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        previousCareerSkill = new ArrayList<>();
        Bundle bundle = getArguments();
        if (bundle != null)
        {
            selectedCareerSkill = bundle.getBooleanArray(SELECTED_CAREER_SKILL);
            previousCareer = bundle.getString(PREVIOUS_CAREER);
            previousCareerSkill = bundle.getStringArrayList(PREVIOUS_CAREER_SKILL);
        }
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
            displayPersoDialog();
        }
        else if (id == R.id.dialog_edit_perso_career)
        {
            displayCareerDialog();
        }
        else if (id == R.id.dialog_edit_perso_specialization)
        {
            //TODO : create dialog
            addNewSpecialization.setEnabled(true);
        }
        else if (id == R.id.dialog_edit_perso_add_specialization)
        {
            // TODO

            if (character.getCustomSkillList() == null)
                character.setCustomSkillList(new ArrayList<Skill>());
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        int viewId = parent.getId();

        if (viewId == R.id.dialog_edit_perso_specie)
        {
            characterSpecie = (Specie) parent.getAdapter().getItem(position);
            character.setSpecie(characterSpecie);

            UpdateCharacterSpecie();
        }
        else if (viewId == R.id.dialog_career_spinner)
        {
            character.setCareer((Career) parent.getAdapter().getItem(position));

            if (selectedCareerSkill == null || selectedCareerSkill.length == 0 ||
                    !previousCareer.equals(character.getCareer().getName()))
            {
                int size = character.getCareer().getCareerSkillSize();
                selectedCareerSkill = new boolean[size];
                Arrays.fill(selectedCareerSkill, Boolean.FALSE);
                if (careerAdapter != null)
                    careerAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void displayPersoDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.dialog_edit_perso_title);

        LayoutInflater inflater = LayoutInflater.from(activity);
        View dialogContent = inflater.inflate(R.layout.dialog_edit_perso, null);

        //region get element in the view
        final EditText nameEditText = (EditText) dialogContent.findViewById(R.id.dialog_edit_perso_name);
        nameEditText.setText(character.getName());

        final Spinner speciesSpinner = (Spinner) dialogContent.findViewById(R.id.dialog_edit_perso_specie);
        ArrayAdapter<Specie> spinnerAdapter = new ArrayAdapter<Specie>(activity,
                android.R.layout.simple_spinner_dropdown_item, SWFichesApplication.getApp().getSpeciesList());
        speciesSpinner.setAdapter(spinnerAdapter);
        // TODO : bug fix, spinner pas sur la bonne position à la première ouverture
        int spinnerPosition = spinnerAdapter.getPosition(character.getSpecie());
        speciesSpinner.setSelection(spinnerPosition);
        speciesSpinner.setOnItemSelectedListener(this);


        addCareer = (Button) dialogContent.findViewById(R.id.dialog_edit_perso_career);
        addCareer.setText(character.getCareer() != null ?
                character.getCareer().getName() : getString(R.string.add_career));
        addCareer.setOnClickListener(this);

        addSpecialization = (Button) dialogContent.findViewById(R.id.dialog_edit_perso_specialization);
        addSpecialization.setText(character.getMainSpecialization() != null ?
                character.getMainSpecialization().getName() : getString(R.string.add_first_specialization));
        addSpecialization.setOnClickListener(this);
        addSpecialization.setEnabled(character.getCareer() != null);

        addNewSpecialization = (Button) dialogContent.findViewById(R.id.dialog_edit_perso_add_specialization);
        addNewSpecialization.setOnClickListener(this);
        addNewSpecialization.setEnabled(character.getMainSpecialization() != null);
        //endregion

        builder.setView(dialogContent);

        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                character.setName(nameEditText.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putBooleanArray(SELECTED_CAREER_SKILL, selectedCareerSkill);
                bundle.putStringArrayList(PREVIOUS_CAREER_SKILL, previousCareerSkill);
                if (character.getCareer() != null)
                    bundle.putString(PREVIOUS_CAREER, character.getCareer().getName());

                UpdateCharacterNameAndCareer(bundle);
            }
        });

        builder.setNegativeButton(android.R.string.no, null);

        builder.create().show();
    }



    private void displayCareerDialog()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.dialog_add_career_title);

        LayoutInflater inflater = LayoutInflater.from(activity);
        View dialogContent = inflater.inflate(R.layout.dialog_career_perso, null);

        //region get element in the view
        final Spinner careerSpinner = (Spinner) dialogContent.findViewById(R.id.dialog_career_spinner);
        ArrayAdapter<Career> spinnerAdapter = new ArrayAdapter<>(activity,
                android.R.layout.simple_spinner_dropdown_item, SWFichesApplication.getApp().getCareerList());
        careerSpinner.setAdapter(spinnerAdapter);
        int spinnerPosition = spinnerAdapter.getPosition(character.getCareer());
        careerSpinner.setSelection(spinnerPosition);
        careerSpinner.setOnItemSelectedListener(this);

        GridView careerGrid = (GridView) dialogContent.findViewById(R.id.dialog_career_gridview);
        careerAdapter = new CareerAdapter(activity, this);
        careerGrid.setAdapter(careerAdapter);
        //endregion

        builder.setView(dialogContent);

        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.setNegativeButton(android.R.string.no, null);

        final AlertDialog dialog = builder.create();
        dialog.show();

        //region override the onclick listener to dismiss the dialog only if skillchecked number is good
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                careerTextView.setText(formatString('c'));
                addCareer.setText(character.getCareer().getName());

                int nbrSkillChecked = countCheckedSkill();
                int max = getMaxCareerSkill();

                if (nbrSkillChecked == max) {
                    setFreeRank();
                    dialog.dismiss();
                    addSpecialization.setEnabled(true);
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                    if (nbrSkillChecked < max) {
                        builder.setMessage(String.format(activity.getString(R.string.dialog_not_enough_skill_check_message), max));
                        builder.setPositiveButton(android.R.string.ok, null);
                    } else {
                        builder.setMessage(String.format(activity.getString(R.string.dialog_too_many_skill_check_message), max));
                        builder.setPositiveButton(android.R.string.ok, null);
                    }

                    builder.create().show();
                }
            }
        });
        //endregion
    }


    private int countCheckedSkill()
    {
        int cpt = 0;
        int size = getCareerSkillCount();
        for (int i = 0; i < size; i++)
            if (selectedCareerSkill[i])
                cpt++;

        return cpt;
    }


    private void setFreeRank()
    {
        int size = character.getCareer().getCareerSkillSize();
        int cpt = 0;
        int max = getMaxCareerSkill();
        ArrayList<String> careerSkillList = character.getCareer().getCarreerSkills();
        ArrayList<Skill> characterSkill = character.getSkillList();

        resetRankOfPreviousCareer(characterSkill);

        for (Skill s : characterSkill)
        {
            for (int i = 0; i < size; i++)
            {
                if (s.getName().equals(careerSkillList.get(i)))
                {
                    s.setIsCareer(true);
                    if (selectedCareerSkill[i])
                    {
                        s.setLevel(s.getLevel() + 1);
                        previousCareerSkill.add(s.getName());
                        cpt++;
                        if (cpt == max)
                            break;
                    }
                }
            }
        }
        character.setSkillList(characterSkill);
    }


    private void resetRankOfPreviousCareer(ArrayList<Skill> characterSkill)
    {
        int previousCareerSkillListSize = previousCareerSkill != null ? previousCareerSkill.size() : -1;

        for (Skill s : characterSkill)
        {
            for (int i = 0; i < previousCareerSkillListSize; i++)
            {
                if (s.getName().equals(previousCareerSkill.get(i)))
                {
                    s.setLevel(s.getLevel() != 0 ? s.getLevel() - 1 : 0);
                }
            }
            s.setIsCareer(false);
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
                return String.format(activity.getString(R.string.format_career),
                        character.getCareer() != null ? character.getCareer().getName() : "");
            case 's':
                String res = String.format(getString(R.string.format_spezcialization),
                        character.getMainSpecialization() != null ? character.getMainSpecialization().getName() : "");
                if (character.getSecondarySpecializationList() != null) {
                    for (Specialization s : character.getSecondarySpecializationList()) {
                        res += String.format(getString(R.string.format_multiple_specialization), s.getName());
                    }
                }
                return res;
            default :
                return "";
        }
    }


    @Override
    public String getCareerSkill(int position) {
        return character.getCareer().getCarreerSkills().get(position);
    }

    @Override
    public int getCareerSkillCount() {
        return character.getCareer() != null ? character.getCareer().getCareerSkillSize() : 0;
    }

    @Override
    public boolean getCareerSkillChecked(int position) {
        return selectedCareerSkill[position];
    }

    @Override
    public void setCareerSkillChecked(int position, boolean check) {
        selectedCareerSkill[position] = check;
    }

    @Override
    public int getMaxCareerSkill() {
        return (character.getSpecie().getName().equals(activity.getString(R.string.droid)) ?
                DROID_MAX_CAREER_SKILL : (character.getCareer().getCareerSkillSize() / 2));
    }

    @Override
    public String getSpeSkill(int position) {
        return null;
    }

    @Override
    public int getSpeSkillCount() {
        return 0;
    }

}
