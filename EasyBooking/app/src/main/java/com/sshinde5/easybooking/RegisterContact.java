package com.sshinde5.easybooking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Shivani Shinde on 03-01-2018.
 */

public class RegisterContact extends MainActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        Intent intent = getIntent();
        String range = intent.getStringExtra("Range");
        String timeSlot = intent.getStringExtra("TimeSlot");
        Toast toast=Toast.makeText(getApplicationContext(),timeSlot,Toast.LENGTH_SHORT);
        toast.show();
    }

}