package id.or.tauhid.doadandzikir;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String[] stringArray = getResources().getStringArray(R.array.country_arrays);
        SharedPreferences sp = getSharedPreferences("My_Prefs", Activity.MODE_PRIVATE);
        int sizeItemSelected = sp.getInt("VALUE", 24);

        String sizeToSetTextViewSizeTo = stringArray[sizeItemSelected];
        int tvSize = Integer.parseInt(sizeToSetTextViewSizeTo);

        TextView titleTV = (TextView) findViewById(R.id.custom_font);
        titleTV.setTextSize(tvSize);
    }
}
