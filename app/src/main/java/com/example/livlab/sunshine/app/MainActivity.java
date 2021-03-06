package com.example.livlab.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(LOG_TAG, " is onCreate");
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }

    }

    @Override
     protected void onStop() {
        Log.v(LOG_TAG, " is onStop");
        super.onStop();
    }

    @Override
    protected void onStart() {
        Log.v(LOG_TAG, " is onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.v(LOG_TAG, " is onResume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.v(LOG_TAG, " is onDestroy");
        super.onDestroy();
    }

    @Override
        protected void onPause() {
        Log.v(LOG_TAG, " is onPause");
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            return true;
        } else if (id == R.id.actions_map) {
            openPreferredLocationInMap();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationInMap() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String location = pref.getString(getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));
        Intent intent = new Intent(Intent.ACTION_VIEW);

        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                               .appendQueryParameter("q", location)
                               .build();

        /******JA STYLE*******
        String geoAddress = "geo:0,0?q=" + location; //JA Style
        Uri geo = Uri.parse(geoAddress);
         ********/
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else {
            Log.d(LOG_TAG, "Couldn't call " + location + ", no receiving apps installed!");
        }


    }
}
