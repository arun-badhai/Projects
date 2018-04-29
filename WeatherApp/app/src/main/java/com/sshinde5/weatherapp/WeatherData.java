package com.sshinde5.weatherapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherData extends AppCompatActivity {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String API_KEY = "264b46455920e68cd6f12c8dce58e71a";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_weather );
        String city = getIntent().getStringExtra( "location" );
        String country = getIntent().getStringExtra( "country" );
        String data = getData( city+","+country );

    }

    public String getData( String location ) {
        HttpURLConnection con = null ;
        InputStream is = null;

        try {
            con = ( HttpURLConnection ) ( new URL( BASE_URL + location + "&appid=" + API_KEY )).openConnection();
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
