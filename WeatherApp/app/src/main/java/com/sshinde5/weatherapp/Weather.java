package com.sshinde5.weatherapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Shivani Shinde on 29-04-2018.
 */

public class Weather extends AsyncTask {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String API_KEY = "/*INSERT KEY */";
    private static String location;

    public Weather( String location ){
        this.location = location;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        HttpURLConnection con = null ;
        InputStream is = null;

        try {
            con = ( HttpURLConnection ) ( new URL( BASE_URL + location + "&appid=" + API_KEY )).openConnection();
            System.out.println();
            con.setRequestMethod( "GET" );
            con.setDoInput( true );
            con.setDoOutput( true );
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ( (line = br.readLine()) != null )
                buffer.append(line + "rn");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch( Throwable t ) {}
            try { con.disconnect(); } catch( Throwable t ) {}
        }

        return null;
    }
}
