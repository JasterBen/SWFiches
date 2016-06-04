package com.example.hokan.swfiches.services;

import android.content.Context;

import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.items.Campaign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Guillaus
 */
public class InternalStorageService {

    private static String CAMPAIGNS_DIR = "campaigns";

    //region Utility
    private static String getFilePath(String campaignName) {
        return String.format("%s%s%s", CAMPAIGNS_DIR, File.separator, campaignName);
    }

    private static Boolean fileExists(String fileName, Boolean campaign) {

        if (campaign) {
            File directory = SWFichesApplication.getApp().getApplicationContext().getFileStreamPath(CAMPAIGNS_DIR);

            if (directory.exists()) {
                File campaignFile = new File(directory, fileName);
                return campaignFile.exists();
            }

            return false;
        }
        else {
            File file = SWFichesApplication.getApp().getApplicationContext().getFileStreamPath(fileName);
            return file.exists();
        }
    }

    private static void createCampaignDirIfNotExists() {
        File campaignDirectory  = SWFichesApplication.getApp().getApplicationContext().getFileStreamPath(CAMPAIGNS_DIR);

        if (!campaignDirectory.exists())
            campaignDirectory.mkdir();
    }

    private static void WriteObject(FileOutputStream fileOutputStream, Object object) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.flush();
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
            //fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    public static boolean savesExists() {
        File campaignDirectory  = SWFichesApplication.getApp().getApplicationContext().getFileStreamPath(CAMPAIGNS_DIR);
        return campaignDirectory.exists();
    }

    //region Save Campaigns
    public static void saveCampaign(Campaign campaign) {

        if (campaign == null)
            return;

        SWFichesApplication app = SWFichesApplication.getApp();
        String filename = getFilePath(campaign.getName());

        // A rapid check of the campaigns directory
        createCampaignDirIfNotExists();

        try {
            // If the file doesn't exists, it needs to be created
            if (!fileExists(campaign.getName(), true)) {
                File newFile = new File(app.getApplicationContext().getFilesDir(), filename);
                newFile.createNewFile();
            }
            // The file exists or has been created, we can now write the object
            FileOutputStream outputStream = new FileOutputStream(filename);
            WriteObject(outputStream, campaign);

        } catch (IOException e) {
            // TODO : Handle Exceptions
            e.printStackTrace();
        }
    }

    public static void saveCampaignsCollection(Collection<Campaign> campaignCollection) {
        for (Campaign campaign : campaignCollection) {
            saveCampaign(campaign);
        }
    }
    //endregion

    //region Load Campaigns
    public static Campaign loadCampaign(String campaignName) {
        SWFichesApplication app = SWFichesApplication.getApp();
        String filename = getFilePath(campaignName);

        if (fileExists(campaignName, true)) {

            try {
                FileInputStream fileInputStream = new FileInputStream(filename);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                return (Campaign) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static Collection<Campaign> loadCampaignCollection() {
        // get list of filenames for "BaseContext/campaigns/*
        // and load everyone of them
        return Collections.emptyList();
    }
    //endregion
}
