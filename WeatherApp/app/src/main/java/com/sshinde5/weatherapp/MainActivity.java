package com.sshinde5.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        final EditText txt = ( EditText ) findViewById( R.id.cityText);
        final EditText txtCountry = ( EditText ) findViewById( R.id.countryText);
        Button submit = ( Button ) findViewById( R.id.button );
        final TextView message = ( TextView ) findViewById( R.id.textView3 );
        txt.requestFocus();
        txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                message.setVisibility( TextView.INVISIBLE );
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        });
        txtCountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                message.setVisibility( TextView.INVISIBLE );
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        });
        submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                String text = txt.getText().toString();
                String textCountry = txtCountry.getText().toString();
                if(!text.matches("[a-zA-Z]+") || !textCountry.matches("[a-zA-Z]+")){
                    message.setText( "Please enter a valid location" );
                    message.setVisibility( TextView.VISIBLE );
                }
                else{
                    Intent intent = new Intent(MainActivity.this, WeatherData.class);
                    intent.putExtra( "location",text );
                    intent.putExtra( "country",textCountry );
                    startActivity( intent );
                }
            }
        });
    }
}
