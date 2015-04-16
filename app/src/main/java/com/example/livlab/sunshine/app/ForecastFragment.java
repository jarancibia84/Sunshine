package com.example.livlab.sunshine.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    public ForecastFragment() {
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

    public class FetchWeatherclass extends AsyncTask<String, Void, Void>{

        private final String LOG_TAG = FetchWeatherclass.class.getSimpleName();

        @Override
        protected Void doInBackground(String... params) {
            //HTTP conection
            String urlRequest = "http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&cnt=7&units=metric";
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }


            return null;
        }
    }
}
