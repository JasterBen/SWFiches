package com.example.hokan.swfiches.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hokan.swfiches.R;
import com.example.hokan.swfiches.SWFichesApplication;
import com.example.hokan.swfiches.adapters.PlayerViewPagerAdapter;
import com.example.hokan.swfiches.interfaces.CharacterListInterface;
import com.example.hokan.swfiches.items.SWCharacter;

import java.util.ArrayList;

/**
 * Created by Ben on 18/04/2016.
 */
public class PlayerActivity extends AppCompatActivity implements CharacterListInterface {
    
    public static final String POSITION = "position";
    public static final String CHARACTERLIST = "characterlist";

    protected int characterPosition;
    protected ArrayList<SWCharacter> characterList;
    protected int characterListSize;

    protected ViewPager viewPager;
    protected PlayerViewPagerAdapter playerViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        if (!SWFichesApplication.getApp().isTablet())
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        if (savedInstanceState == null)
        {
            Intent intent = getIntent();
            characterPosition = intent.getIntExtra(POSITION, 0);
            characterList = intent.getParcelableArrayListExtra(CHARACTERLIST);
            characterListSize = characterList.size();

            Toolbar toolbar = (Toolbar) findViewById(R.id.player_activity_toolbar);
            toolbar.setNavigationIcon(R.mipmap.smuggler);
            setSupportActionBar(toolbar);

            viewPager = (ViewPager) findViewById(R.id.player_view_pager);
            playerViewPagerAdapter = new PlayerViewPagerAdapter(getSupportFragmentManager(), this);
            viewPager.setAdapter(playerViewPagerAdapter);
            viewPager.setCurrentItem(characterPosition);
        }
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
//                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/example.pdf");
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                startActivity(intent);
                Toast.makeText(this, "Guillaume, on t'attend !", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }


    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        returnIntent.putParcelableArrayListExtra(CHARACTERLIST, characterList);
        setResult(Activity.RESULT_OK, returnIntent);

        super.finish();
    }

    @Override
    public int getItemCount() {
        return characterListSize;
    }

    @Override
    public SWCharacter getCharacter(int position) {
        return characterList.get(position);
    }
}
