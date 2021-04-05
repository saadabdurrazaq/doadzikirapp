package id.or.tauhid.doadandzikir;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DoaViewHolder extends RecyclerView.ViewHolder {

    public TextView mTitle;
    public TextView mLatin;
    public TextView mArti;
    public TextView mSumber;
    public TextView readmore;
    public TextView readless;
    public Button tombolbaca;
    public Button tombolshare;
    public Button tombolsalin;
    private RelativeLayout rl2;
    public Button teksbaca;
    private AdapterDoa myAdapter;

    public DoaViewHolder(View itemView, AdapterDoa myAdapter) {

        super(itemView);
        this.myAdapter = myAdapter;

        tombolbaca = (Button) itemView.findViewById(R.id.buttonbaca);
        tombolshare = (Button) itemView.findViewById(R.id.buttonshare);
        tombolsalin = (Button) itemView.findViewById(R.id.buttoncopy);
        mTitle = (TextView) itemView.findViewById(R.id.titleTextView);
        mLatin = (TextView) itemView.findViewById(R.id.latinDoa);
        mArti = (TextView) itemView.findViewById(R.id.artiDoa);
        mSumber = (TextView) itemView.findViewById(R.id.sumberDoa);
        readmore = (TextView) itemView.findViewById(R.id.readmore);
        readless = (TextView) itemView.findViewById(R.id.readless);
        rl2 = (RelativeLayout) itemView.findViewById(R.id.relmasjid);

        //Set Arab font size via spinner
        String[] sizeArab = itemView.getResources().getStringArray(R.array.country_arrays);
        SharedPreferences sp = itemView.getContext().getSharedPreferences("My_Prefs", Activity.MODE_PRIVATE);
        int sizeItemSelected = sp.getInt("VALUE", 6);

        String sizeToSetTextViewSizeTo = sizeArab[sizeItemSelected];
        int tvSize = Integer.parseInt(sizeToSetTextViewSizeTo);

        mTitle.setTextSize(tvSize);


        //Set latin font size
        String[] sizeArray = itemView.getResources().getStringArray(R.array.size_arrays);
        SharedPreferences spl = itemView.getContext().getSharedPreferences("Size_Latin", Activity.MODE_PRIVATE);
        int sizeLatinSelected = spl.getInt("SIZELATIN", 2);

        String setSizeLatin = sizeArray[sizeLatinSelected];
        int lSize = Integer.parseInt(setSizeLatin);

        mLatin.setTextSize(lSize);
        mArti.setTextSize(lSize);
        mSumber.setTextSize(lSize);


        //Set tipe font arab
        String[] tipeArabArray = itemView.getResources().getStringArray(R.array.type_arab);
        SharedPreferences tpl = itemView.getContext().getSharedPreferences("Type_Arab", Activity.MODE_PRIVATE);
        int tipeArabSelected = tpl.getInt("TYPEARAB", 0);

        String setTipeArab = tipeArabArray[tipeArabSelected];

        if (setTipeArab.equals("Type 1")) {

            Typeface taf0 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/teks_arab_1.otf");
            mTitle.setTypeface(taf0);
        }
        else
        if (setTipeArab.equals("Type 2")) {

            Typeface taf2 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/teks_arab_2.ttf");
            mTitle.setTypeface(taf2);
        }
        else
        if (setTipeArab.equals("Type 3")) {

            Typeface taf3 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/teks_arab_3.ttf");
            mTitle.setTypeface(taf3);
        }
        else
        if (setTipeArab.equals("Type 4")) {

            Typeface taf4 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/teks_arab_4.ttf");
            mTitle.setTypeface(taf4);
        }


        //Set tipe font latin
        String[] tipeLatinArray = itemView.getResources().getStringArray(R.array.type_latin);
        SharedPreferences tpfl = itemView.getContext().getSharedPreferences("Tipe_Latin", Activity.MODE_PRIVATE);
        int tipeLatinSelected = tpfl.getInt("TIPELATIN", 2);

        String setTipeLatin = tipeLatinArray[tipeLatinSelected];

        if (setTipeLatin.equals("Type 1")) {

            Typeface tlf0 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/teks_latin_1.ttf");
            mLatin.setTypeface(tlf0);
            mArti.setTypeface(tlf0);
            mSumber.setTypeface(tlf0);
        }
        else
        if (setTipeLatin.equals("Type 2")) {

            Typeface tlf2 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/teks_latin_2.ttf");
            mLatin.setTypeface(tlf2);
            mArti.setTypeface(tlf2);
            mSumber.setTypeface(tlf2);
        }
        else
        if (setTipeLatin.equals("Type 3")) {

            Typeface tlf3 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/teks_latin_3.ttf");
            mLatin.setTypeface(tlf3);
            mArti.setTypeface(tlf3);
            mSumber.setTypeface(tlf3);
        }
        else
        if (setTipeLatin.equals("Type 4")) {

            Typeface tlf4 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/teks_latin_4.ttf");
            mLatin.setTypeface(tlf4);
            mArti.setTypeface(tlf4);
            mSumber.setTypeface(tlf4);
        }


        // Show hide read more and read less
        mLatin.setVisibility(TextView.GONE);
        mArti.setVisibility(TextView.GONE);
        mSumber.setVisibility(TextView.GONE);
        readless.setVisibility(TextView.GONE);

        // Create click on every objek
        tombolbaca.setOnClickListener(bacaClickListener);
        tombolshare.setOnClickListener(shareClickListener);
        tombolsalin.setOnClickListener(salinClickListener);
        readmore.setOnClickListener(moreClickListener);
        readless.setOnClickListener(lessClickListener);
        itemView.setOnClickListener(mainViewClickListener);
    }

    private View.OnClickListener moreClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // Do button click handling here
            mLatin.setVisibility(TextView.VISIBLE);
            mArti.setVisibility(TextView.VISIBLE);
            mSumber.setVisibility(TextView.VISIBLE);
            readmore.setVisibility(TextView.GONE);
            readless.setVisibility(TextView.VISIBLE);
        }
    };

    private View.OnClickListener lessClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // Do button click handling here
            mLatin.setVisibility(TextView.GONE);
            mArti.setVisibility(TextView.GONE);
            mSumber.setVisibility(TextView.GONE);
            readmore.setVisibility(TextView.VISIBLE);
            readless.setVisibility(TextView.GONE);
        }
    };

    private View.OnClickListener bacaClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            teksbaca = (Button) v.findViewById(R.id.buttonbaca);
            ModelDoa modelDoa = myAdapter.getItem(getAdapterPosition());

            if (modelDoa != null) {
                modelDoa.setReadCount(modelDoa.getReadCount() - 1);
                teksbaca.setText("Baca " + modelDoa.getReadCount() + "x");

                if (modelDoa.getReadCount() <= 0) {
                    myAdapter.deleteItem(getAdapterPosition());
                }

            }

        } // onclick
    };

    private View.OnClickListener shareClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // Do button click handling here
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, mTitle.getText().toString() + "\n \n download aplikasinya di: http://www.tauhid.or.id" );
                sendIntent.setType("text/plain");
                Intent.createChooser(sendIntent,"Share via");
                v.getContext().startActivity(sendIntent);
        }
    };

    private View.OnClickListener salinClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
           Context context = v.getContext();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                final android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                final android.content.ClipData clipData = android.content.ClipData.newPlainText("Copy", mTitle.getText() + "\n \n download aplikasinya di: http://www.tauhid.or.id");
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(context, "Berhasil disalin", Toast.LENGTH_SHORT).show();

            } else {
                final android.text.ClipboardManager clipboardManager = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setText(mTitle.getText() + "\n \n download aplikasinya di: http://www.tauhid.or.id");
                Toast.makeText(context, "Berhasil disalin", Toast.LENGTH_SHORT).show();

            }
        }

    };

    private View.OnClickListener mainViewClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // Do button click handling here
        }
    };


}

