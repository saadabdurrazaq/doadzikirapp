package id.or.tauhid.doadandzikir;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ChildYukul extends AppCompatActivity implements YukulAdapter.ContactsAdapterListener {
    private static final String TAG = ChildYukul.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<YukulModel> yukulModelList;
    private YukulAdapter mAdapter;
    private SearchView searchView;
    final String KEY_SAVED_RADIO_BUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 2000;
    public static final int READ_TIMEOUT = 2000;

    // url to fetch contacts json
    private static final String URL = "https://api.kafeinkode.com/childmakan.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_yukul);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        //toolbar logo and desc
        Toolbar topToolBar = (Toolbar) findViewById(R.id.toolbarYukul);
        setSupportActionBar(topToolBar); //munculkan menu ke toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //this line shows back button

        recyclerView = findViewById(R.id.recycler_view);
        yukulModelList = new ArrayList<>();
        mAdapter = new YukulAdapter(this, yukulModelList, this);

        // white background notification bar
        whiteNotificationBar(recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new YukulDekor(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);

        //Make call to AsyncTask
        new AsyncLogin().execute();

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
            Toolbar tb = (Toolbar) findViewById(R.id.toolbarYukul);
            tb.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        else {
            if (male.isChecked()) {     // one of the radio buttons is checked
                Toolbar tb1 = (Toolbar) findViewById(R.id.toolbarYukul);
                tb1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            }
            else if (female.isChecked()) {
                Toolbar tb2 = (Toolbar) findViewById(R.id.toolbarYukul);
                tb2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        }
    }


    private class AsyncLogin extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(ChildYukul.this);
        HttpURLConnection conn;
        java.net.URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tMencoba terhubung ke internet...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("https://api.kafeinkode.com/childmakan.json");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {
                    return("koneksi gagal");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }

        }

        /**
         * fetches json by making http calls
         */
        protected void onPostExecute(String result) {

            JsonArrayRequest request = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    pdLoading.dismiss();
                    List<YukulModel> items = new Gson().fromJson(response.toString(), new TypeToken<List<YukulModel>>() {
                    }.getType());

                    // adding contacts to contacts list
                    yukulModelList.clear();
                    yukulModelList.addAll(items);

                    // refreshing recycler view
                    mAdapter.notifyDataSetChanged();

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    pdLoading.dismiss();
                    // error in getting json
                    Log.e(TAG, "Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(), "Tidak bisa menampilkan data. Periksa kembali sambungan internet Anda", Toast.LENGTH_LONG).show();

                    AlertDialog alertDialog = new AlertDialog.Builder(ChildYukul.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Data Tidak bisa ditampilkan. Periksa kembali sambungan internet Anda");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            });

            YukulSearch.getInstance().addToRequestQueue(request);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_yukul, menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable_yukul configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        //Menu
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        else
        if (id == R.id.about_us) {
            startActivity(new Intent(this, AboutUs.class));
            return true;
        }
        else
        if (id == R.id.home) {
            startActivity(new Intent(this, HomeActivity.class));
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onContactSelected(YukulModel yukulModel) {
        Toast.makeText(getApplicationContext(), "Selected: " + yukulModel.getName(), Toast.LENGTH_LONG).show();
    }
}
