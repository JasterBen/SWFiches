package com.example.hokan.swfiches.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.items.Specialization;

import java.io.File;

/**
 * Created by Utilisateur on 04/06/2016.
 */
public abstract class SWFichesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    protected File file;
    protected String filename;


    public void initToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.campaign_activity_toolbar);
        toolbar.setNavigationIcon(R.mipmap.smuggler);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.show_trees) :
                createTreeShowingDialog();
                break;
        }
        return true;
    }


    public void createTreeShowingDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.see_specialization_tree_title);

        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogContent = inflater.inflate(R.layout.dialog_see_tree, null);

        Spinner spinner = (Spinner) dialogContent.findViewById(R.id.select_specialization_tree_spinner);
        ArrayAdapter<Specialization> spinnerAdapter = new ArrayAdapter<Specialization>(this,
                android.R.layout.simple_spinner_dropdown_item,
                SWFichesApplication.getApp().getSpecializationList());
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);

        builder.setView(dialogContent);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openfile();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();
    }


    public void checkConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting())
        {
            if (info.getType() == ConnectivityManager.TYPE_WIFI)
            {
                //TODO : launch asynctask
                Toast.makeText(this, "launch asyntask", Toast.LENGTH_SHORT).show();
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.not_on_wifi_title);
                builder.setMessage(R.string.not_on_wifi_message);

                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO : launch asynctask
                        Toast.makeText(getApplicationContext(), "launch asyntask", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton(android.R.string.no, null);
                builder.create().show();
            }
        }
        else {
            Toast.makeText(this, getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
        }
    }


    public void openfile()
    {
        if (file.exists())
        {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }
        else
        {
            checkConnection();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        filename = ((Specialization) parent.getItemAtPosition(position)).getName();
        File externStorage = Environment.getExternalStorageDirectory();

        String format = externStorage.getAbsolutePath() + getString(R.string.path) + "%s.pdf";
        file = new File(String.format(format, filename));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}