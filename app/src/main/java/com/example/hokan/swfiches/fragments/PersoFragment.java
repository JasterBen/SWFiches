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

    private static final int FIRST_SPECIALIZATION_SPINNER_ID = 0;
    private static final int OTHER_SPECIALIZATION_SPINNER_ID = FIRST_SPECIALIZATION_SPINNER_ID + 1;

    private static final int SPE_SKILL_COUNT = 4;
    private static final int MAX_SPE_SKILL = 2;
    private static final int DROID_MAX_CAREER_SKILL = 6;
    private static final int DROID_MAX_SPE_SKILL = 3;

    protected TextView nameTextView;
    protected TextView specieTextView;
    protected TextView careerTextView;
    protected TextView specTextView;
    protected Button addCareer;
    protected Button addSpecialization;
    protected Button addNewSpecialization;

    protected CareerAdapter careerAdapter;
    protected String previousCareer;

    protected SpecializationAdapter specializationAdapter;
    protected Specialization previousSpecialization;

    protected Specie characterSpecie;
    protected Specialization characterOtherSpecialization;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        previousCareer = character.getCareer() != null ? character.getCareer().getName() : "";
        previousSpecialization = character.getMainSpecialization() != null ?
                character.getMainSpecialization() : new Specialization();
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
            displaySecondarySpecializationDialog();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        int viewId = parent.getId();

        if (viewId == R.id.dialog_edit_perso_specie)
        {
            characterSpecie = (Specie) parent.getAdapter().getItem(position);
            Specie characterPreviousSpecie = character.getSpecie();
            character.setSpecie(characterSpecie);

            String droid = SWFichesApplication.getApp().getString(R.string.droid);

            //region force sensitivity security
            //si on devient droide, on réinitilaise tout
            if (characterPreviousSpecie != null &&
                    characterPreviousSpecie.getName().equals(droid) &&
                    !characterSpecie.equals(characterPreviousSpecie))
            {
                character.setCareer(null);
                character.setMainSpecialization(null);
                character.setSecondarySpecializationList(null);
                resetAllSkill();
            }

            Career career = character.getCareer();
            //si on n'a plus d'affinité avec la force, on réinitialise les spé secondaires (par sécurité)
            // et on réinitialise la carrière et la spécialisation si besoin
            if (!characterSpecie.isCanHaveForce())
            {
                resetSecondarySkill();

                if ((career != null && career.isNeedForce()) ||
                        characterSpecie.getName().equals(droid))
                {
                    character.setCareer(null);
                    character.setMainSpecialization(null);
                    resetAllSkill();
                }
            }
            //endregion

            //region updating buttons
            addCareer.setText(character.getCareer() != null ?
                    character.getCareer().getName() : activity.getString(R.string.add_career));

            addSpecialization.setText(character.getMainSpecialization() != null ?
                    character.getMainSpecialization().getName() : activity.getString(R.string.add_first_specialization));
            addSpecialization.setEnabled(character.getCareer() != null);

            addNewSpecialization.setEnabled(character.getMainSpecialization() != null);
            //endregion

            UpdateCharacterSpecie();
        }
        else if (viewId == R.id.dialog_career_spinner)
        {
            character.setCareer((Career) parent.getAdapter().getItem(position));

            boolean[] selectedCareerSkill = character.getSelectedCareerSkill();
            if (selectedCareerSkill == null ||
                    selectedCareerSkill.length == 0 ||
                    (previousCareer != null && !previousCareer.equals(character.getCareer().getName())))
            {
                int size = character.getCareer().getCareerSkills().size();
                character.setSelectedCareerSkill(new boolean[size]);
                Arrays.fill(character.getSelectedCareerSkill(), Boolean.FALSE);
                if (careerAdapter != null)
                    careerAdapter.notifyDataSetChanged();
            }

        }
        else if (viewId == FIRST_SPECIALIZATION_SPINNER_ID)
        {
            character.setMainSpecialization((Specialization) parent.getAdapter().getItem(position));

            boolean[] selectedSpecializationSkill = character.getSelectedSpecializationSkill();
            if (selectedSpecializationSkill == null ||
                    selectedSpecializationSkill.length == 0 ||
                    (previousSpecialization.getName() != null && !previousSpecialization.getName().equals(character.getMainSpecialization().getName())))
            {
                character.setSelectedSpecializationSkill(new boolean[SPE_SKILL_COUNT]);
                Arrays.fill(character.getSelectedSpecializationSkill(), Boolean.FALSE);
                if (specializationAdapter != null)
                    specializationAdapter.notifyDataSetChanged();
            }
        }
        else if (viewId == OTHER_SPECIALIZATION_SPINNER_ID)
        {
            characterOtherSpecialization = (Specialization) parent.getAdapter().getItem(position);
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

        ArrayList<Specie> speciesList = SWFichesApplication.getApp().getSpeciesList();
        Spinner speciesSpinner = (Spinner) dialogContent.findViewById(R.id.dialog_edit_perso_specie);
        ArrayAdapter<Specie> spinnerAdapter = new ArrayAdapter<Specie>(activity,
                android.R.layout.simple_spinner_dropdown_item, speciesList);
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

                UpdateCharacterNameAndCareer();
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
        boolean canCharacterUseForce = characterSpecie.isCanHaveForce();
        ArrayList<Career> tmp = SWFichesApplication.getApp().getCareerList();
        for (Career c : tmp)
        {
            if (c.getSpecializationList().size() >= 3 &&
                    ((canCharacterUseForce) || (!canCharacterUseForce && !c.isNeedForce())))
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

                int nbrSkillChecked = countCheckedSkill(character.getSelectedCareerSkill());
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
        specializationSpinner.setId(FIRST_SPECIALIZATION_SPINNER_ID);
        ArrayAdapter<Specialization> spinnerAdapter =
                new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, character.getCareer().getSpecializationList());
        specializationSpinner.setAdapter(spinnerAdapter);
        int spinnerPosition = spinnerAdapter.getPosition(character.getMainSpecialization());
        specializationSpinner.setSelection(spinnerPosition);
        specializationSpinner.setOnItemSelectedListener(this);

        //TODO : uncomment to test, WARNING not a prod solution, just a debug solution
//        if (character.getMainSpecialization() == null || character.getMainSpecialization() != previousSpecialization)
//        {
//            character.setMainSpecialization(character.getCareer().getSpecializationList().get(0));
//
//            boolean[] selectedSpecializationSkill = character.getSelectedSpecializationSkill();
//            if (selectedSpecializationSkill == null ||
//                    selectedSpecializationSkill.length == 0 ||
//                    (previousSpecialization.getName() != null && !previousSpecialization.getName().equals(character.getMainSpecialization().getName())))
//            {
//                character.setSelectedSpecializationSkill(new boolean[SPE_SKILL_COUNT]);
//                Arrays.fill(character.getSelectedSpecializationSkill(), Boolean.FALSE);
//                if (specializationAdapter != null)
//                    specializationAdapter.notifyDataSetChanged();
//            }
//        }


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

                int nbrSkillChecked = countCheckedSkill(character.getSelectedSpecializationSkill());
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


    private void displaySecondarySpecializationDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.dialog_add_specialization_title);

        final ArrayList<Specialization> otherSpecializationList = new ArrayList<>();
        boolean canCharacterUseForce = characterSpecie.isCanHaveForce();
        boolean characterForceRatingIsOk = character.getForceRating() > 0;
        ArrayList<Career> careerList = SWFichesApplication.getApp().getCareerList();
        //pour toutes les carrières
        for (Career c : careerList)
        {
            //si la carrière est autorisée pour ce personnage
            if ((canCharacterUseForce &&
                    (characterForceRatingIsOk ||
                            (c.isNeedForce() && c.getSpecializationList().size() < 3))) ||
                (!c.isNeedForce()))
            {
                //pour toutes les spécialisations de la carrière
                for (Specialization s : c.getSpecializationList())
                {
                    //si la spécialisation "courante" n'est pas la spécialisation principale du personnage
                    if (!s.getName().equals(character.getMainSpecialization().getName()))
                    {
                        //si la liste des spécialisations secondaires n'est pas null
                        if (character.getSecondarySpecializationList() != null)
                        {
                            int i = 0;
                            int size = character.getSecondarySpecializationListSize();
                            ArrayList<Specialization> specializationsList = character.getSecondarySpecializationList();
                            //tant que la spécialisation "courante" n'est pas dans la liste des spécialisations secondaires on avance dans la liste
                            while (i < size &&
                                    !s.getName().equals(specializationsList.get(i).getName()))
                            {
                                i++;
                            }

                            //si on n'a pas trouvé la spécialisation dans la liste des spécialisations secondaires, on peut l'ajouter
                            if (i >= size)
                                otherSpecializationList.add(s);
                        }
                        else
                        {
                            otherSpecializationList.add(s);
                        }
                    }
                }
            }

        }

        Spinner otherSpecializationSpinner = new Spinner(activity);
        otherSpecializationSpinner.setId(OTHER_SPECIALIZATION_SPINNER_ID);
        ArrayAdapter<Specialization> spinnerAdapter =
                new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, otherSpecializationList);
        otherSpecializationSpinner.setAdapter(spinnerAdapter);
        int spinnerPosition = spinnerAdapter.getPosition(character.getMainSpecialization());
        otherSpecializationSpinner.setSelection(spinnerPosition);
        otherSpecializationSpinner.setOnItemSelectedListener(this);

        builder.setView(otherSpecializationSpinner);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                character.addSpecialization(characterOtherSpecialization);
                setOtherSpecialisationSkill(characterOtherSpecialization);
            }
        });

        builder.setNegativeButton(android.R.string.no, null);

        builder.create().show();
    }

    private int countCheckedSkill(boolean[] checkedSkill)
    {
        int cpt = 0;
        int size = checkedSkill == character.getSelectedCareerSkill() ? getCareerSkillCount() : getSpeSkillCount();
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

        //pour toutes les skills
        for (Skill s : characterSkill)
        {
            //pour toutes les skills de la carrière
            for (int i = 0; i < size; ++i)
            {
                //si le nom de la skill "courante" est celui d'une skill de la carrière
                if (s.getName().equals(careerSkillList.get(i)))
                {
                    s.setIsCareer(true);
                    //si cette skill a été choisie par l'utilisateur
                    if (character.getSelectedCareerSkill()[i])
                    {
                        s.setLevel(s.getLevel() + 1);
                        character.getPreviousCareerSkill().add(s.getName());
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

        ArrayList<String> previousCareerSkill = character.getPreviousCareerSkill();
        int previousCareerSkillListSize = previousCareerSkill != null ? previousCareerSkill.size() : -1;

        //pour toutes les skills de la carrière précédente
        for (int i = 0; i < previousCareerSkillListSize; ++i)
        {
            //pour toutes les skills du personnage
            for (Skill s : characterSkill)
            {
                //si le nom de la skill "courante" est dans les skills de la carrière précédente
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

        //pour toutes les skills
        for (Skill s : characterSkill)
        {
            //pour toutes les skill de la spe
            for (int i = 0; i < size; ++i)
            {
                //si la skill "courante" est dans les skill de spe
                if (s.getName().equals(speSkillList.get(i)))
                {
                    s.setIsCareer(true);
                    //si l'utilisateur a selectionné cette skill
                    if (character.getSelectedSpecializationSkill()[i])
                    {
                        s.setLevel(s.getLevel() + 1);
                        character.getPreviousSpecializationSkill().add(s.getName());
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
        ArrayList<String> previousSpecializationSkill = character.getPreviousSpecializationSkill();
        int previousSpeSkillListSize = previousSpecializationSkill != null ? previousSpecializationSkill.size() : -1;
        ArrayList<String> careerSkill = character.getCareer().getCareerSkills();
        int careerSkillSize = character.getCareer().getCareerSkills().size();

        if (previousSpecialization != null)
        {
            //pour toutes les skills
            for (Skill s : characterSkill)
            {
                //pour toutes les skill de la spe précédente
                for (int j = 0; j < SPE_SKILL_COUNT; ++j)
                {
                    int k = 0;
                    //on cherche la skill de spe "courante" dans les skill de la carrière
                    while (k < careerSkillSize &&
                            previousSpecialization.getSpecializationrSkills() != null &&
                            !previousSpecialization.getSpecializationrSkills().get(j).equals(careerSkill.get(k)))
                    {
                        k++;
                    }

                    //on met isCareer a faux seulement si la compétence de spécialité n'est pas aussi une compétence de carrière
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
     * This function set all the skill of the new specialisation to isCareer = true
     *
     * @param spe the new specialization
     */
    private void setOtherSpecialisationSkill(Specialization spe)
    {
        ArrayList<Skill> characterSkill = character.getSkillList();
        ArrayList<String> speSkillList = spe.getSpecializationrSkills();

        for (String speSkill : speSkillList)
        {
            for (Skill s : characterSkill)
            {
                if (speSkill.equals(s.getName()))
                {
                    s.setIsCareer(true);
                    break;
                }
            }
        }
    }


    private void resetAllSkill()
    {
        ArrayList<Skill> characterSkill = character.getSkillList();
        for (Skill s : characterSkill)
        {
            s.setLevel(0);
            s.setIsCareer(false);
        }
    }


    private void resetSecondarySkill()
    {
        ArrayList<String> careerAndSpecializationSkill = new ArrayList<>();

        //on récupère les skill de la carrière
        if (character.getCareer() != null)
            for (String s : character.getCareer().getCareerSkills())
                careerAndSpecializationSkill.add(s);

        //on récupère les skill de la mainspé
        if (character.getMainSpecialization() != null)
            for (String s : character.getMainSpecialization().getSpecializationrSkills())
                careerAndSpecializationSkill.add(s);

        int size = careerAndSpecializationSkill.size();
        ArrayList<Skill> characterSkill = character.getSkillList();
        ArrayList<Specialization> characterSecondarySpecializationList = character.getSecondarySpecializationList();
        if (characterSecondarySpecializationList != null)
        {
            //pour toutes les spécializations secondaire
            for (Specialization s : characterSecondarySpecializationList)
            {
                //pour toutes les skill de la spécialization "courante"
                for (String skillName : s.getSpecializationrSkills())
                {
                    int i = 0;
                    //on cherche la skill dans la liste des skill de la carrière et de la mainspé
                    while (i < size && !skillName.equals(careerAndSpecializationSkill.get(i)))
                        i++;

                    //on met isCareer à false seulement si la skill n'appartient ni à la carrière ni à la mainspé
                    if (i >= size)
                    {
                        for (Skill skill : characterSkill)
                        {
                            if (skill.getName().equals(skillName))
                            {
                                skill.setIsCareer(false);
                                break;
                            }
                        }
                    }
                }
            }
        }

        character.setSecondarySpecializationList(null);
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
        return character.getSelectedCareerSkill()[position];
    }

    @Override
    public void setCareerSkillChecked(int position, boolean check) {
        character.getSelectedCareerSkill()[position] = check;
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
        return character.getSelectedSpecializationSkill()[position];
    }

    public void setSpeSkillChecked(int position, boolean selectedSpecializationSkill) {
        character.getSelectedSpecializationSkill()[position] = selectedSpecializationSkill;
    }

    @Override
    public int getMaxSpeSkill() {
        return character.getSpecie().getName().equals(activity.getString(R.string.droid)) ?
                DROID_MAX_SPE_SKILL : MAX_SPE_SKILL;
    }
}
