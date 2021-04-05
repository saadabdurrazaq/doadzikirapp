package id.or.tauhid.doadandzikir;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

// AboutUs melibatkan list_about_us.xml, activity_about_us.xml, ListAboutUs.java

public class AboutUs extends AppCompatActivity {

    private ListView listView;
    private String names[] = {
            "Contact us",
            "Rate us on the PlayStore"
    };


    private Integer imageid[] = {
            R.drawable.email,
            R.drawable.ps
    };

    final String KEY_SAVED_RADIO_BUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //this line shows back button
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView image = (ImageView) findViewById(R.id.LogoAbout);
        image.setImageResource(R.drawable.ikon);

        TextView abouDesc = (TextView) findViewById(R.id.teksDesk);
        abouDesc.setText(R.string.desc_about);

        ListAboutUs listAboutUs = new ListAboutUs(this, names, imageid);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(listAboutUs);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto:redaksi.tauhiorid@gmail.com"));
                    startActivity(emailIntent);
                }
                else if (position == 1) {
                    String url = "http://www.tauhid.or.id";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            }
        });

        //Radio Button
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
            Toolbar tb = (Toolbar) findViewById(R.id.toolbarTidur);
            tb.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        else {
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