package id.or.tauhid.doadandzikir;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.Preferences;

// SettingsActivity melibatkan list_layout.xml, activity_settings.xml, CustomList.java

public class SettingsActivity extends AppCompatActivity {

    private ListView listView;
    private ListView listViewEx;
    private SharedPreferences sharedPreferences;

    private String names[] = {
            "Teks"
    };

    private String desc[] = {
            "Atur fonts dan ukuran teks"
    };

    private Integer imageid[] = {
            R.drawable.text
    };

    final String KEY_SAVED_RADIO_BUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //this line shows back button

        // ListView
        CustomList customList = new CustomList(this, names, desc, imageid);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(customList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                 if (position == 0) {
                    Intent myIntent = new Intent(view.getContext(), FontSettings.class);
                    view.getContext().startActivity(myIntent);
                }
            }
        });

        //Download button
        Button tombol = (Button) findViewById(R.id.tombolDownload);
        tombol.setOnClickListener(tombolClickListener);

        //Radio Button
        LoadPreferences();

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioSex);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioSex);
                RadioButton checkedRadioButton = (RadioButton) radioGroup.findViewById(checkedId);
                int checkedIndex = radioGroup.indexOfChild(checkedRadioButton);

                SavePreferences(KEY_SAVED_RADIO_BUTTON_INDEX, checkedIndex);

                switch(checkedId) {
                    case R.id.theme1:
                        Toolbar tb1 = (Toolbar) findViewById(R.id.toolbar);
                        tb1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        Toast.makeText(getApplicationContext(),"Theme 1 dipilih. Tekan refresh di halaman home untuk melihat perubahan", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.theme2:
                        Toolbar tb2 = (Toolbar) findViewById(R.id.toolbar);
                        tb2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        Toast.makeText(getApplicationContext(),"Theme 2 dipilih. Tekan refresh di halaman home untuk melihat perubahan", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    } //onCreate

    private View.OnClickListener tombolClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // Do button click handling here
            Toast.makeText(getApplicationContext(),"Audio belum dapat didownload untuk saat ini", Toast.LENGTH_SHORT).show();
        }
    };


    private void SavePreferences(String key, int value){
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private void LoadPreferences(){
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioSex);
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
        int savedRadioIndex = sharedPreferences.getInt(KEY_SAVED_RADIO_BUTTON_INDEX, 0);
        RadioButton savedCheckedRadioButton = (RadioButton)radioGroup.getChildAt(savedRadioIndex);
        savedCheckedRadioButton.setChecked(true);

        RadioGroup genderGroup = (RadioGroup) findViewById(R.id.radioSex);
        RadioButton male = (RadioButton) findViewById(R.id.theme1);
        RadioButton female = (RadioButton) findViewById(R.id.theme2);

        if (genderGroup.getCheckedRadioButtonId() == -1) {
            Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
            tb.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        } else {
            if (male.isChecked()) {     // one of the radio buttons is checked
                Toolbar tb1 = (Toolbar) findViewById(R.id.toolbar);
                tb1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            }
            else if (female.isChecked()) {
                Toolbar tb2 = (Toolbar) findViewById(R.id.toolbar);
                tb2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        }
    }


    // Agar back button pada halaman induk settings berfungsi
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}