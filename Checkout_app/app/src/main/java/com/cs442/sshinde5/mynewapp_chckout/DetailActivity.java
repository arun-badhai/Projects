package com.cs442.sshinde5.mynewapp_chckout;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Shivani Shinde on 02-10-2016.
 */
public class DetailActivity extends AppCompatActivity {

    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        Bundle bundle= new Bundle();
        bundle.putInt("user",getIntent().getIntExtra("user",0));
        bundle.putInt("value",getIntent().getIntExtra("value",0));
        newActivity newActivity = new newActivity();
        newActivity.setArguments(bundle);
        mFragmentTransaction.replace(R.id.LL1,newActivity, "second").addToBackStack("first").commit();
        //mFragmentTransaction.replace(R.id.LL1,new newActivity(),"first").commit();

    }
}
