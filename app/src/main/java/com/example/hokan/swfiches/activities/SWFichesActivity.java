package com.example.hokan.swfiches.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.items.Specialization;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;

/**
 * Created by Utilisateur on 04/06/2016.
 */
public abstract class SWFichesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int WRITE_EXTERNAL_STORAGE_PERMISSION_CODE = 1;

    protected String filePath;
    protected String filename;
    protected String folderPath;


    public void initToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_toolbar);
        toolbar.setNavigationIcon(R.mipmap.smuggler);

        Resources resources = getResources();
        toolbar.setBackgroundColor(resources.getColor(R.color.toolbar_background));
        toolbar.setTitleTextColor(resources.getColor(R.color.toolbar_text_color));
        toolbar.setSubtitleTextColor(resources.getColor(R.color.toolbar_text_color));

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

        ArrayList<Specialization> specializationList = SWFichesApplication.getApp().getSpecializationList();
        ArrayList<String> spinnerList = new ArrayList<>();
        for (Specialization s : specializationList)
            spinnerList.add(s.getListName());

        Spinner spinner = (Spinner) dialogContent.findViewById(R.id.select_specialization_tree_spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                spinnerList);
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
                isWriteStoragePermissionGranted();
                new DownloadFile().execute();
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.not_on_wifi_title);
                builder.setMessage(R.string.not_on_wifi_message);

                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isWriteStoragePermissionGranted();
                        new DownloadFile().execute();
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
        File file = new File(filePath);

        if (file.exists())
        {
            PackageManager packageManager = SWFichesApplication.getApp().getPackageManager();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            if (packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0) {
                startActivity(intent);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.no_pdf_reader_installed);
                builder.setPositiveButton(android.R.string.ok, null);
                builder.create().show();
            }

        }
        else
        {
            checkConnection();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        filename = ((String) parent.getItemAtPosition(position))
                .replaceAll(" ", "_")
                .replaceAll("’", "_");

        filename = Normalizer.normalize(filename, Normalizer.Form.NFD);
        filename = filename.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        folderPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + getString(R.string.path);
        String format = folderPath + "%s.pdf";
        filePath = String.format(format, filename);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    public void isWriteStoragePermissionGranted() {

        String writeExternalStoragePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;

        if (Build.VERSION.SDK_INT >= 23 &&
                checkSelfPermission(writeExternalStoragePermission) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,
                        new String[]{writeExternalStoragePermission},
                        WRITE_EXTERNAL_STORAGE_PERMISSION_CODE);
            }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            new DownloadFile().execute();
    }

    private class DownloadFile extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... params) {
            File folder = new File(folderPath);
            if (!folder.exists())
                folder.mkdirs();


            //File pdfFile = new File(filePath);
            File pdfFile = new File(folderPath, filename + ".pdf");
            try {
                pdfFile.createNewFile();

                String format = getString(R.string.url_path) + "%s.pdf";

                URL url = new URL(String.format(format, filename));

                BufferedInputStream inputStream = new BufferedInputStream(url.openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);

                byte[] buffer = new byte[1024];
                int bufferLength = 0;
                while ((bufferLength = inputStream.read(buffer, 0, 1024)) > -1)
                    fileOutputStream.write(buffer, 0, bufferLength);

                fileOutputStream.close();
                inputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            File file = new File(filePath);

            if (file.exists())
            {
                PackageManager packageManager = SWFichesApplication.getApp().getPackageManager();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                if (packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0) {
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setMessage(R.string.no_pdf_reader_installed);
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.create().show();
                }

            }
        }
    }
}