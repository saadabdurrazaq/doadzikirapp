package id.or.tauhid.doadandzikir;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class DoaSebelumMakan extends AppCompatActivity {

    final String KEY_SAVED_RADIO_BUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doa_sebelummakan);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //this line shows back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Munculkan title bar ketika discroll
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Doa Sebelum Makan");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

        List<ModelDoa> rowListItem =  getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DoaSebelumMakan.this);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        AdapterDoa rcAdapter = new AdapterDoa(rowListItem);
        mRecyclerView.setAdapter(rcAdapter);

        //Get radio button value
        LoadPreferences();

    } //OnCreate

    private void LoadPreferences(){
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_settings, null,false);
        RadioGroup radioGroup = (RadioGroup)contentView.findViewById(R.id.radioSex);
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
        int savedRadioIndex = sharedPreferences.getInt(KEY_SAVED_RADIO_BUTTON_INDEX, 0);
        RadioButton savedCheckedRadioButton = (RadioButton)radioGroup.getChildAt(savedRadioIndex);
        savedCheckedRadioButton.setChecked(true);

        RadioGroup genderGroup = (RadioGroup) contentView.findViewById(R.id.radioSex);
        RadioButton male = (RadioButton) contentView.findViewById(R.id.theme1);
        RadioButton female = (RadioButton) contentView.findViewById(R.id.theme2);

        if (genderGroup.getCheckedRadioButtonId() == -1) {
            Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
            tb.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        else {
            if (male.isChecked()) {     // one of the radio buttons is checked
                CollapsingToolbarLayout tb1 = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
                tb1.setContentScrimColor(getResources().getColor(R.color.colorPrimaryDark));
            }
            else if (female.isChecked()) {
                CollapsingToolbarLayout tb2 = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
                tb2.setContentScrimColor(getResources().getColor(R.color.colorAccent));
            }
        }
    }

    private List<ModelDoa> getData() {

        String[] data = getResources().getStringArray(R.array.doasebelummakan);
        String[] latin = getResources().getStringArray(R.array.latinsebelummakan);
        String[] arti = getResources().getStringArray(R.array.artisebelummakan);
        String[] sumber = getResources().getStringArray(R.array.sumbersebelummakan);
        String[] baca = getResources().getStringArray(R.array.bacasebelummakan);

        List<ModelDoa> list = new ArrayList<ModelDoa>();

        for (int i = 0; i < data.length; i++) {
            list.add(new ModelDoa(data[i], latin[i], arti[i], sumber[i], baca[i], ModelDoa.BACA_DOA));
        }

        return list;
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

