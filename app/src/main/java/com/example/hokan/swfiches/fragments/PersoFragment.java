package com.example.hokan.swfiches.fragments;

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
import android.support.v7.app.AlertDialog;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.adapters.CareerAdapter;
import com.example.hokan.swfiches.adapters.SpecializationAdapter;
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

    private static final int SPECIALIZATION_SPINNER_ID = 0;

    private static final int SPE_SKILL_COUNT = 4;
    private static final int MAX_SPE_SKILL = 2;
    private static final int DROID_MAX_CAREER_SKILL = 6;
    private static final int DROID_MAX_SPE_SKILL = 3;

    private static final String SELECTED_CAREER_SKILL = "scs";
    private static final String PREVIOUS_CAREER = "career";
    private static final String PREVIOUS_CAREER_SKILL = "pcs";
    private static final String SELECTED_SPE_SKILL = "sss";
    private static final String PREVIOUS_SPE = "spe";
    private static final String PREVIOUS_SPE_SKILL = "pss";

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

    protected boolean[] selectedSpecializationSkill;
    protected SpecializationAdapter specializationAdapter;
    protected Specialization previousSpecialization;
    protected ArrayList<String> previousSpecializationSkill;

    protected Specie characterSpecie;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        previousCareerSkill = new ArrayList<>();
        previousSpecializationSkill = new ArrayList<>();
        Bundle bundle = getArguments();
        if (bundle != null)
        {
            selectedCareerSkill = bundle.getBooleanArray(SELECTED_CAREER_SKILL);
            previousCareer = bundle.getString(PREVIOUS_CAREER);
            previousCareerSkill = bundle.getStringArrayList(PREVIOUS_CAREER_SKILL);

            selectedSpecializationSkill = bundle.getBooleanArray(SELECTED_SPE_SKILL);
            previousSpecialization = bundle.getParcelable(PREVIOUS_SPE);
            previousSpecializationSkill = bundle.getStringArrayList(PREVIOUS_SPE_SKILL);
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
            displayMainSpecializationDialog();
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
            //uncomment only for test, this is a trick to test specialization function without this fuc**** setOnItemSelectedListenner being functional
//            character.setMainSpecialization(character.getCareer().getSpecializationList().get(0));

            if (selectedCareerSkill == null ||
                    selectedCareerSkill.length == 0 ||
                    !previousCareer.equals(character.getCareer().getName()))
            {
                int size = character.getCareer().getCareerSkills().size();
                selectedCareerSkill = new boolean[size];
                Arrays.fill(selectedCareerSkill, Boolean.FALSE);
                if (careerAdapter != null)
                    careerAdapter.notifyDataSetChanged();
            }

//            if (selectedSpecializationSkill == null ||
//                    selectedSpecializationSkill.length == 0 ||
//                    (previousSpecialization != null && !previousSpecialization.getName().equals(character.getMainSpecialization().getName())))
//            {
//                selectedSpecializationSkill = new boolean[SPE_SKILL_COUNT];
//                Arrays.fill(selectedSpecializationSkill, Boolean.FALSE);
//                if (specializationAdapter != null)
//                    specializationAdapter.notifyDataSetChanged();
//            }
        }
        else if (viewId == SPECIALIZATION_SPINNER_ID)
        {
            character.setMainSpecialization((Specialization) parent.getAdapter().getItem(position));

            if (selectedSpecializationSkill == null ||
                    selectedSpecializationSkill.length == 0 ||
                    (previousSpecialization != null && !previousSpecialization.getName().equals(character.getMainSpecialization().getName())))
            {
                selectedSpecializationSkill = new boolean[SPE_SKILL_COUNT];
                Arrays.fill(selectedSpecializationSkill, Boolean.FALSE);
                if (specializationAdapter != null)
                    specializationAdapter.notifyDataSetChanged();
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

        Spinner speciesSpinner = (Spinner) dialogContent.findViewById(R.id.dialog_edit_perso_specie);
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

                bundle.putBooleanArray(SELECTED_SPE_SKILL, selectedSpecializationSkill);
                bundle.putStringArrayList(PREVIOUS_SPE_SKILL, previousSpecializationSkill);
                bundle.putParcelable(PREVIOUS_SPE, character.getMainSpecialization());

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
        Spinner careerSpinner = (Spinner) dialogContent.findViewById(R.id.dialog_career_spinner);

        ArrayList<Career> careerList = new ArrayList<>();
        ArrayList<Career> tmp = SWFichesApplication.getApp().getCareerList();
        for (Career c : tmp)
        {
            if (c.getSpecializationList().size() >= 3)
                careerList.add(c);
        }

        ArrayAdapter<Career> spinnerAdapter = new ArrayAdapter<>(activity,
                android.R.layout.simple_spinner_dropdown_item, careerList);
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

                int nbrSkillChecked = countCheckedSkill(selectedCareerSkill);
                int max = getMaxCareerSkill();

                if (nbrSkillChecked == max) {
                    setFreeCareerRank();
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


    private void displayMainSpecializationDialog()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.dialog_add_specialization_title);

        LayoutInflater inflater = LayoutInflater.from(activity);
        View dialogContent = inflater.inflate(R.layout.dialog_career_perso, null);

        //region get element in the view
        Spinner specializationSpinner = (Spinner) dialogContent.findViewById(R.id.dialog_career_spinner);
        specializationSpinner.setId(SPECIALIZATION_SPINNER_ID);
        ArrayAdapter<Specialization> spinnerAdapter =
                new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, character.getCareer().getSpecializationList());
        specializationSpinner.setAdapter(spinnerAdapter);
        int spinnerPosition = spinnerAdapter.getPosition(character.getMainSpecialization());
        specializationSpinner.setSelection(spinnerPosition);
        specializationSpinner.setOnItemSelectedListener(this);



        GridView specializationGrid = (GridView) dialogContent.findViewById(R.id.dialog_career_gridview);
        specializationAdapter = new SpecializationAdapter(activity, this);
        specializationGrid.setAdapter(specializationAdapter);
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
                specTextView.setText(formatString('s'));
                addSpecialization.setText(character.getMainSpecialization().getName());

                int nbrSkillChecked = countCheckedSkill(selectedSpecializationSkill);
                int max = getMaxSpeSkill();

                if (nbrSkillChecked == max) {
                    setFreeSpeRank();
                    dialog.dismiss();
                    addNewSpecialization.setEnabled(true);
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


    private int countCheckedSkill(boolean[] checkedSkill)
    {
        int cpt = 0;
        int size = checkedSkill == selectedCareerSkill ? getCareerSkillCount() : getSpeSkillCount();
        for (int i = 0; i < size; ++i)
            if (checkedSkill[i])
                cpt++;

        return cpt;
    }


    private void setFreeCareerRank()
    {
        int size = character.getCareer().getCareerSkills().size();
        int cpt = 0;
        int max = getMaxCareerSkill();
        ArrayList<String> careerSkillList = character.getCareer().getCareerSkills();
        ArrayList<Skill> characterSkill = character.getSkillList();

        resetRankOfPreviousCareer(characterSkill);

        for (Skill s : characterSkill)
        {
            for (int i = 0; i < size; ++i)
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
        resetRankOfPreviousSpe(characterSkill);

        int previousCareerSkillListSize = previousCareerSkill != null ? previousCareerSkill.size() : -1;

        for (int i = 0; i < previousCareerSkillListSize; ++i)
        {
            for (Skill s : characterSkill)
            {
                if (s.getName().equals(previousCareerSkill.get(i)))
                {
                    s.setLevel(s.getLevel() != 0 ? s.getLevel() - 1 : 0);
                }
                s.setIsCareer(false);
            }
        }
    }


    private void setFreeSpeRank()
    {
        int size = SPE_SKILL_COUNT;
        int cpt = 0;
        int max = getMaxSpeSkill();
        ArrayList<String> speSkillList = character.getMainSpecialization().getSpecializationrSkills();
        ArrayList<Skill> characterSkill = character.getSkillList();

        resetRankOfPreviousSpe(characterSkill);

        for (Skill s : characterSkill)
        {
            for (int i = 0; i < size; ++i)
            {
                if (s.getName().equals(speSkillList.get(i)))
                {
                    s.setIsCareer(true);
                    if (selectedSpecializationSkill[i])
                    {
                        s.setLevel(s.getLevel() + 1);
                        previousSpecializationSkill.add(s.getName());
                        cpt++;
                        if (cpt == max)
                            break;
                    }
                }
            }
        }
        character.setSkillList(characterSkill);
    }


    private void resetRankOfPreviousSpe(ArrayList<Skill> characterSkill)
    {
        int previousSpeSkillListSize = previousSpecializationSkill != null ? previousSpecializationSkill.size() : -1;
        ArrayList<String> careerSkill = character.getCareer().getCareerSkills();
        int careerSkillSize = character.getCareer().getCareerSkills().size();

        if (previousSpecialization != null)
        {
            for (Skill s : characterSkill)
            {
                for (int j = 0; j < SPE_SKILL_COUNT; ++j)
                {
                    //on met isCareer a faux seulement si la compétence de spécialité n'est pas aussi une compétence de carrière
                    int k = 0;
                    while (k < careerSkillSize &&
                            !previousSpecialization.getSpecializationrSkills().get(j).equals(careerSkill.get(k)))
                    {
                        k++;
                    }

                    if (k >= SPE_SKILL_COUNT && s.getName().equals(previousSpecialization.getSpecializationrSkills().get(j)))
                    {
                        s.setIsCareer(false);
                    }
                }
            }
        }

        //si on avait mis un point gratuit dans cette compétence, on l'enlève
        for (int i = 0; i < previousSpeSkillListSize; ++i)
        {
            for (Skill s : characterSkill)
            {
                if (s.getName().equals(previousSpecializationSkill.get(i)))
                {
                    s.setLevel(s.getLevel() != 0 ? s.getLevel() - 1 : 0);
                }
            }
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
                String res = String.format(activity.getString(R.string.format_spezcialization),
                        character.getMainSpecialization() != null ? character.getMainSpecialization().getName() : "");
                if (character.getSecondarySpecializationList() != null) {
                    for (Specialization s : character.getSecondarySpecializationList()) {
                        res += String.format(activity.getString(R.string.format_multiple_specialization), s.getName());
                    }
                }
                return res;
            default :
                return "";
        }
    }


    @Override
    public String getCareerSkill(int position) {
        return character.getCareer().getCareerSkills().get(position);
    }

    @Override
    public int getCareerSkillCount() {
        return character.getCareer() != null ? character.getCareer().getCareerSkills().size() : 0;
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
                DROID_MAX_CAREER_SKILL : (character.getCareer().getCareerSkills().size() / 2));
    }

    @Override
    public String getSpeSkill(int position) {
        return character.getMainSpecialization().getSpecializationrSkills().get(position);
    }

    @Override
    public int getSpeSkillCount() {
        return SPE_SKILL_COUNT;
    }

    public boolean getSpeSkillChecked(int position) {
        return selectedSpecializationSkill[position];
    }

    public void setSpeSkillChecked(int position, boolean selectedSpecializationSkill) {
        this.selectedSpecializationSkill[position] = selectedSpecializationSkill;
    }

    @Override
    public int getMaxSpeSkill() {
        return character.getSpecie().getName().equals(activity.getString(R.string.droid)) ?
                DROID_MAX_SPE_SKILL : MAX_SPE_SKILL;
    }
}
