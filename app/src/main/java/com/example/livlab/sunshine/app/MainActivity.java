package com.example.livlab.sunshine.app;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            /*
            //**Se crea ArrayList y se agregan datos FORMA JA
            ArrayList<String> listForecast = new ArrayList<String>();
            listForecast.add("Monday - Sunny 10/25");
            listForecast.add("Thuerday - Rain 10/20");
            listForecast.add("Wednesday - Snow 5/15");
            listForecast.add("Thuesday - Clear 10/33");
            listForecast.add("Friday - Sunny 10/40");
            listForecast.add("Saturday - Sunny 12/25");
            listForecast.add("Sunday - Sunny 10/25");
            //**
            */

            //***Se crea ArrayList y se agregan datos FORMA UDACITY
            String[] ForecastArray  = {
                    "Monday - Sunny 10/25",
                    "Thuerday - Rain 10/20",
                    "Wednesday - Snow 5/15",
                    "Thuesday - Clear 10/33",
                    "Friday - Sunny 10/40",
                    "Saturday - Sunny 12/25",
                    "Sunday - Sunny 10/25"
            };
            List<String> weekForecast = new ArrayList<String>(Arrays.asList(ForecastArray));

            ArrayAdapter arregloDatos;
            arregloDatos = new ArrayAdapter(

                    getActivity(),
                    R.layout.list_item_forecast,
                    R.id.list_item_forecast_textview,
                    weekForecast);

            ListView myLista = (ListView)rootView.findViewById(R.id.listview_forecast);
            
            myLista.setAdapter(arregloDatos);







            return rootView;
        }
    }
}
