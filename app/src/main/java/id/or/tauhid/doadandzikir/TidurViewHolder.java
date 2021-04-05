package id.or.tauhid.doadandzikir;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TidurViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView nomor;
    public ImageView mFavorite;
    final String KEY_SAVED_RADIO_BUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";

    public TidurViewHolder(View view) {
        super(view);
        name = view.findViewById(R.id.name);
        nomor = view.findViewById(R.id.nomor);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send selected contact in callback
                if ( 0 == getAdapterPosition() ) {

                    Intent myIntent = new Intent(view.getContext(), DoaMauTidur.class);
                    view.getContext().startActivity(myIntent);

                }
                else if ( 1 == getAdapterPosition() )
                {
                    Intent myIntent = new Intent(view.getContext(), DoaBangunt.class);
                    view.getContext().startActivity(myIntent);
                }
                else if ( 2 == getAdapterPosition() )
                {
                    Intent myIntent = new Intent(view.getContext(), DoaJimak.class);
                    view.getContext().startActivity(myIntent);
                }
            }
        });

        //Get radio button value
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cV = inflater.inflate(R.layout.activity_settings, null,false);
        RadioGroup radioGroup = (RadioGroup)cV.findViewById(R.id.radioSex);
        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("MY_SHARED_PREF", Activity.MODE_PRIVATE);
        int savedRadioIndex = sharedPreferences.getInt(KEY_SAVED_RADIO_BUTTON_INDEX, 0);
        RadioButton savedCheckedRadioButton = (RadioButton)radioGroup.getChildAt(savedRadioIndex);
        savedCheckedRadioButton.setChecked(true);

        RadioGroup genderGroup = (RadioGroup) cV.findViewById(R.id.radioSex);
        RadioButton male = (RadioButton) cV.findViewById(R.id.theme1);
        RadioButton female = (RadioButton) cV.findViewById(R.id.theme2);

        if (genderGroup.getCheckedRadioButtonId() == -1) {
            nomor.setBackgroundColor(view.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            if (male.isChecked()) {     // one of the radio buttons is checked
                nomor.setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.rounded_drawable));
            }
            else if (female.isChecked()) {
                nomor.setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.rounded_drawable_red));
            }
        }

    } //TidurViewHolder(View view)
}
