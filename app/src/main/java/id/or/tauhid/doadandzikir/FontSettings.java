package id.or.tauhid.doadandzikir;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

// Elemen yang terlibat adalah SizeArabFont dan settings_font

public class FontSettings extends AppCompatActivity {

    private Spinner spinner1, spinnerLatin;
    private SharedPreferences mMyPrefs;
    private SharedPreferences.Editor mMyEdit;
    Context mcontext;
    final String KEY_SAVED_RADIO_BUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_font);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //this line shows back button

        //Display data size teks arab in dropdown list spinner
        final Spinner spinnerBackgroundChange = (Spinner)findViewById(R.id.spinner1);
        final SharedPreferences sharedPref = getSharedPreferences("My_Prefs", Activity.MODE_PRIVATE);
        ArrayAdapter<CharSequence> spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.country_arrays, android.R.layout.simple_spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.textview_with_background);
        spinnerBackgroundChange.setAdapter(spinnerArrayAdapter);
        spinnerBackgroundChange.setSelection(sharedPref.getInt("VALUE", 6));

        //Save spinner Arab font size value
        spinnerBackgroundChange.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) view).setTextColor(Color.parseColor("#226169"));

                switch(position) {
                    case 0:
                        SharedPreferences.Editor editor0 = sharedPref.edit();
                        editor0.putInt("VALUE", spinnerBackgroundChange.getSelectedItemPosition());
                        editor0.commit();

                        Activity activity = (Activity) parent.getContext();
                        TextView dgs = (TextView) activity.findViewById(R.id.tekzArab);
                        dgs.setTextSize(12);
                        break;

                    case 1:
                        SharedPreferences.Editor editor1 = sharedPref.edit();
                        editor1.putInt("VALUE", spinnerBackgroundChange.getSelectedItemPosition());
                        editor1.commit();

                        Activity activitys = (Activity) parent.getContext();
                        TextView dgf = (TextView) activitys.findViewById(R.id.tekzArab);
                        dgf.setTextSize(14);
                        break;

                    case 2:
                        SharedPreferences.Editor editor2 = sharedPref.edit();
                        editor2.putInt("VALUE", spinnerBackgroundChange.getSelectedItemPosition());
                        editor2.commit();

                        Activity activityz = (Activity) parent.getContext();
                        TextView dgy = (TextView) activityz.findViewById(R.id.tekzArab);
                        dgy.setTextSize(16);
                        break;

                    case 3:
                        SharedPreferences.Editor editor3 = sharedPref.edit();
                        editor3.putInt("VALUE", spinnerBackgroundChange.getSelectedItemPosition());
                        editor3.commit();

                        Activity activitye = (Activity) parent.getContext();
                        TextView dgq = (TextView) activitye.findViewById(R.id.tekzArab);
                        dgq.setTextSize(18);
                        break;

                    case 4:
                        SharedPreferences.Editor editor4 = sharedPref.edit();
                        editor4.putInt("VALUE", spinnerBackgroundChange.getSelectedItemPosition());
                        editor4.commit();

                        Activity activityq = (Activity) parent.getContext();
                        TextView dgp = (TextView) activityq.findViewById(R.id.tekzArab);
                        dgp.setTextSize(20);
                        break;

                    case 5:
                        SharedPreferences.Editor editor5 = sharedPref.edit();
                        editor5.putInt("VALUE", spinnerBackgroundChange.getSelectedItemPosition());
                        editor5.commit();

                        Activity activityc = (Activity) parent.getContext();
                        TextView dgn = (TextView) activityc.findViewById(R.id.tekzArab);
                        dgn.setTextSize(22);
                        break;

                    case 6:
                        SharedPreferences.Editor editor6 = sharedPref.edit();
                        editor6.putInt("VALUE", spinnerBackgroundChange.getSelectedItemPosition());
                        editor6.commit();

                        Activity activitym = (Activity) parent.getContext();
                        TextView dgb = (TextView) activitym.findViewById(R.id.tekzArab);
                        dgb.setTextSize(24);
                        break;

                    case 7:
                        SharedPreferences.Editor editor7 = sharedPref.edit();
                        editor7.putInt("VALUE", spinnerBackgroundChange.getSelectedItemPosition());
                        editor7.commit();

                        Activity activityo = (Activity) parent.getContext();
                        TextView dgl = (TextView) activityo.findViewById(R.id.tekzArab);
                        dgl.setTextSize(26);
                        break;

                    default:
                        Activity activityu = (Activity) parent.getContext();
                        TextView dgx = (TextView) activityu.findViewById(R.id.tekzArab);
                        dgx.setTextSize(24);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Set tipe font arab
        final Spinner typeArab = (Spinner)findViewById(R.id.spinner2);
        final SharedPreferences typeArabPref = getSharedPreferences("Type_Arab", Activity.MODE_PRIVATE);
        ArrayAdapter<CharSequence> arrayTypeArab = ArrayAdapter.createFromResource(this, R.array.type_arab, android.R.layout.simple_spinner_item);
        arrayTypeArab.setDropDownViewResource(R.layout.textview_with_background);
        typeArab.setAdapter(arrayTypeArab);
        typeArab.setSelection(typeArabPref.getInt("TYPEARAB", 0));

        //Save spinner tipe font latin value
        typeArab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) view).setTextColor(Color.parseColor("#226169"));

                switch(position) {
                    case 0:
                        SharedPreferences.Editor taeditorl0 = typeArabPref.edit();
                        taeditorl0.putInt("TYPEARAB", typeArab.getSelectedItemPosition());
                        taeditorl0.commit();

                        Activity taactivity0 = (Activity) parent.getContext();
                        TextView tage0 = (TextView) taactivity0.findViewById(R.id.tekzArab);
                        Typeface taf0 = Typeface.createFromAsset(getAssets(),"fonts/teks_arab_1.otf");
                        tage0.setTypeface(taf0);
                        break;

                    case 1:
                        SharedPreferences.Editor taeditorl1 = typeArabPref.edit();
                        taeditorl1.putInt("TYPEARAB", typeArab.getSelectedItemPosition());
                        taeditorl1.commit();

                        Activity taactivity1 = (Activity) parent.getContext();
                        TextView tage1 = (TextView) taactivity1.findViewById(R.id.tekzArab);
                        Typeface taf1 = Typeface.createFromAsset(getAssets(),"fonts/teks_arab_2.ttf");
                        tage1.setTypeface(taf1);
                        break;

                    case 2:
                        SharedPreferences.Editor taeditorl2 = typeArabPref.edit();
                        taeditorl2.putInt("TYPEARAB", typeArab.getSelectedItemPosition());
                        taeditorl2.commit();

                        Activity taactivity2 = (Activity) parent.getContext();
                        TextView tage2 = (TextView) taactivity2.findViewById(R.id.tekzArab);
                        Typeface taf2 = Typeface.createFromAsset(getAssets(),"fonts/teks_arab_3.ttf");
                        tage2.setTypeface(taf2);
                        break;

                    case 3:
                        SharedPreferences.Editor taeditorl3 = typeArabPref.edit();
                        taeditorl3.putInt("TYPEARAB", typeArab.getSelectedItemPosition());
                        taeditorl3.commit();

                        Activity taactivity3 = (Activity) parent.getContext();
                        TextView tage3 = (TextView) taactivity3.findViewById(R.id.tekzArab);
                        Typeface taf3 = Typeface.createFromAsset(getAssets(),"fonts/teks_arab_4.ttf");
                        tage3.setTypeface(taf3);
                        break;

                    default:
                        Activity activityu = (Activity) parent.getContext();
                        TextView lgx = (TextView) activityu.findViewById(R.id.tekzLatin);
                        lgx.setTextSize(24);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Display size teks latin in dropdown list spinner
        final Spinner spinnerLatin = (Spinner)findViewById(R.id.spinnerLatin);
        final SharedPreferences sizeLatinPref = getSharedPreferences("Size_Latin", Activity.MODE_PRIVATE);
        ArrayAdapter<CharSequence> arraySizeLatin = ArrayAdapter.createFromResource(this, R.array.size_arrays, android.R.layout.simple_spinner_item);
        arraySizeLatin.setDropDownViewResource(R.layout.textview_with_background);
        spinnerLatin.setAdapter(arraySizeLatin);
        spinnerLatin.setSelection(sizeLatinPref.getInt("SIZELATIN", 2));

        //Save spinner size teks latin value
        spinnerLatin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) view).setTextColor(Color.parseColor("#226169"));

                switch(position) {
                    case 0:
                        SharedPreferences.Editor latinSize0 = sizeLatinPref.edit();
                        latinSize0.putInt("SIZELATIN", spinnerLatin.getSelectedItemPosition());
                        latinSize0.commit();

                        Activity lsactivity0 = (Activity) parent.getContext();
                        TextView dgs = (TextView) lsactivity0.findViewById(R.id.tekzLatin);
                        dgs.setTextSize(12);
                        break;

                    case 1:
                        SharedPreferences.Editor latinSize1 = sizeLatinPref.edit();
                        latinSize1.putInt("SIZELATIN", spinnerLatin.getSelectedItemPosition());
                        latinSize1.commit();

                        Activity lsactivity1 = (Activity) parent.getContext();
                        TextView lsgs = (TextView) lsactivity1.findViewById(R.id.tekzLatin);
                        lsgs.setTextSize(14);
                        break;

                    case 2:
                        SharedPreferences.Editor latinSize2 = sizeLatinPref.edit();
                        latinSize2.putInt("SIZELATIN", spinnerLatin.getSelectedItemPosition());
                        latinSize2.commit();

                        Activity lsactivity2 = (Activity) parent.getContext();
                        TextView lsgy = (TextView) lsactivity2.findViewById(R.id.tekzLatin);
                        lsgy.setTextSize(16);
                        break;

                    case 3:
                        SharedPreferences.Editor latinSize3 = sizeLatinPref.edit();
                        latinSize3.putInt("SIZELATIN", spinnerLatin.getSelectedItemPosition());
                        latinSize3.commit();

                        Activity lsactivity3 = (Activity) parent.getContext();
                        TextView lsgnm = (TextView) lsactivity3.findViewById(R.id.tekzLatin);
                        lsgnm.setTextSize(18);
                        break;

                    case 4:
                        SharedPreferences.Editor latinSize4 = sizeLatinPref.edit();
                        latinSize4.putInt("SIZELATIN", spinnerLatin.getSelectedItemPosition());
                        latinSize4.commit();

                        Activity lsactivity4 = (Activity) parent.getContext();
                        TextView lsgp = (TextView) lsactivity4.findViewById(R.id.tekzLatin);
                        lsgp.setTextSize(20);
                        break;

                    case 5:
                        SharedPreferences.Editor latinSize5 = sizeLatinPref.edit();
                        latinSize5.putInt("SIZELATIN", spinnerLatin.getSelectedItemPosition());
                        latinSize5.commit();

                        Activity lsactivity5 = (Activity) parent.getContext();
                        TextView lsgo = (TextView) lsactivity5.findViewById(R.id.tekzLatin);
                        lsgo.setTextSize(22);
                        break;

                    case 6:
                        SharedPreferences.Editor latinSize6 = sizeLatinPref.edit();
                        latinSize6.putInt("SIZELATIN", spinnerLatin.getSelectedItemPosition());
                        latinSize6.commit();

                        Activity lsactivity6 = (Activity) parent.getContext();
                        TextView lsgq = (TextView) lsactivity6.findViewById(R.id.tekzLatin);
                        lsgq.setTextSize(24);
                        break;

                    case 7:
                        SharedPreferences.Editor latinSize7 = sizeLatinPref.edit();
                        latinSize7.putInt("SIZELATIN", spinnerLatin.getSelectedItemPosition());
                        latinSize7.commit();

                        Activity lsactivity7 = (Activity) parent.getContext();
                        TextView lsge = (TextView) lsactivity7.findViewById(R.id.tekzLatin);
                        lsge.setTextSize(24);
                        break;

                    default:
                        Activity activityu = (Activity) parent.getContext();
                        TextView dgx = (TextView) activityu.findViewById(R.id.tekzArab);
                        dgx.setTextSize(24);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Display tipe fonts latin in dropdown list spinner
        final Spinner tipeLatin = (Spinner)findViewById(R.id.spinnerTipe);
        final SharedPreferences latinSharedPref = getSharedPreferences("Tipe_Latin", Activity.MODE_PRIVATE);
        ArrayAdapter<CharSequence> arrayTipeLatin = ArrayAdapter.createFromResource(this, R.array.type_latin, android.R.layout.simple_spinner_item);
        arrayTipeLatin.setDropDownViewResource(R.layout.textview_with_background);
        tipeLatin.setAdapter(arrayTipeLatin);
        tipeLatin.setSelection(latinSharedPref.getInt("TIPELATIN", 2));

        //Save spinner tipe font latin value
        tipeLatin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) view).setTextColor(Color.parseColor("#226169"));

                switch(position) {
                    case 0:
                        SharedPreferences.Editor editorl0 = latinSharedPref.edit();
                        editorl0.putInt("TIPELATIN", tipeLatin.getSelectedItemPosition());
                        editorl0.commit();

                        Activity ltactivity0 = (Activity) parent.getContext();
                        TextView lsge0 = (TextView) ltactivity0.findViewById(R.id.tekzLatin);
                        Typeface tf0 = Typeface.createFromAsset(getAssets(),"fonts/teks_latin_1.ttf");
                        lsge0.setTypeface(tf0);
                        break;

                    case 1:
                        SharedPreferences.Editor editorl1 = latinSharedPref.edit();
                        editorl1.putInt("TIPELATIN", tipeLatin.getSelectedItemPosition());
                        editorl1.commit();

                        Activity ltactivity1 = (Activity) parent.getContext();
                        TextView lsge1 = (TextView) ltactivity1.findViewById(R.id.tekzLatin);
                        Typeface tf1 = Typeface.createFromAsset(getAssets(),"fonts/teks_latin_2.ttf");
                        lsge1.setTypeface(tf1);
                        break;

                    case 2:
                        SharedPreferences.Editor editorl2 = latinSharedPref.edit();
                        editorl2.putInt("TIPELATIN", tipeLatin.getSelectedItemPosition());
                        editorl2.commit();

                        Activity ltactivity2 = (Activity) parent.getContext();
                        TextView lsge2 = (TextView) ltactivity2.findViewById(R.id.tekzLatin);
                        Typeface tf2 = Typeface.createFromAsset(getAssets(),"fonts/teks_latin_3.ttf");
                        lsge2.setTypeface(tf2);
                        break;

                    case 3:
                        SharedPreferences.Editor editorl3 = latinSharedPref.edit();
                        editorl3.putInt("TIPELATIN", tipeLatin.getSelectedItemPosition());
                        editorl3.commit();

                        Activity ltactivity3 = (Activity) parent.getContext();
                        TextView lsge3 = (TextView) ltactivity3.findViewById(R.id.tekzLatin);
                        Typeface tf3 = Typeface.createFromAsset(getAssets(),"fonts/teks_latin_4.ttf");
                        lsge3.setTypeface(tf3);
                        break;

                    default:
                        Activity activityu = (Activity) parent.getContext();
                        TextView lgx = (TextView) activityu.findViewById(R.id.tekzLatin);
                        lgx.setTextSize(24);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Get radio button value
        LoadPreferences();
    }

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
        } else {
            if (male.isChecked()) {     // one of the radio buttons is checked
                Toolbar tb1 = (Toolbar) findViewById(R.id.toolbar);
                TextView fa1 = (TextView) findViewById(R.id.fontArab);
                TextView fl1 = (TextView) findViewById(R.id.fontLatin);
                tb1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                fa1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                fl1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
            else if (female.isChecked()) {
                Toolbar tb2 = (Toolbar) findViewById(R.id.toolbar);
                TextView fa1 = (TextView) findViewById(R.id.fontArab);
                TextView fl1 = (TextView) findViewById(R.id.fontLatin);
                tb2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                fa1.setTextColor(getResources().getColor(R.color.colorAccent));
                fl1.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        }
    }

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