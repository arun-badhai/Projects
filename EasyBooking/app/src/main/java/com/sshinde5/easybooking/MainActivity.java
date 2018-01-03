package com.sshinde5.easybooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner1, spinner2;
    private Button btnSubmit;
    private DateFormat df;
    private Calendar cal;
    private int startTime;
    private int startMin;
    private int startNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        textView1.setText("AVAILABLE TIME SLOTS FOR VISIT");
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText("PLEASE SELECT THE TIME RANGE FOR VISIT");
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setText("PLEASE SELECT THE EXACT TIME FOR VISIT");
        df = new SimpleDateFormat("HH:mm");
        cal = Calendar.getInstance();
        startTime = cal.get(Calendar.HOUR_OF_DAY);
        if(startTime < 8 || startTime > 20){
            startNum = 12;
            cal.set(Calendar.HOUR_OF_DAY, 8);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
        }
        else{
            startNum = 20-startTime-1;
            startMin = cal.get(Calendar.MINUTE);
            int count = 60 - startMin;
            cal.add(Calendar.MINUTE, count);
        }

        TimeSlotsList();

        //addListenerOnButton();
        spinner1.setOnItemSelectedListener(this);
        addListenerOnButton();
    }

    public void TimeSlotsList(){
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setVisibility(View.VISIBLE);
        List<String> list = new ArrayList<String>();
        if(cal.get(Calendar.MINUTE)%2 != 0){
            cal.add(Calendar.MINUTE, 1);
        }
        for(int i=0;i<startNum;i++){
            list.add(df.format(cal.getTime()));
            cal.add(Calendar.HOUR, 1);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        String sp1 = String.valueOf(spinner1.getSelectedItem());
        String[] array = sp1.split(":");
        int time = Integer.parseInt(array[0]);
        cal.set(Calendar.HOUR_OF_DAY, time);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        addItemsOnSpinner2(cal.get(Calendar.HOUR_OF_DAY));
    }

    public void addItemsOnSpinner2(int number) {
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setVisibility(View.VISIBLE);
        List<String> list = new ArrayList<String>();
        while (cal.get(Calendar.HOUR_OF_DAY) == number) {
            list.add(df.format(cal.getTime()));
            cal.add(Calendar.MINUTE, 2);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addListenerOnButton() {

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    /*Toast.makeText(MainActivity.this,
                            "Selected!!",
                            Toast.LENGTH_SHORT).show();*/
                Intent intent = new Intent(MainActivity.this, RegisterContact.class);
                Bundle extras = new Bundle();
                extras.putString("Range",String.valueOf(spinner1.getSelectedItem()));
                extras.putString("TimeSlot",String.valueOf(spinner2.getSelectedItem()));
                intent.putExtras(extras);
                startActivity(intent);

            }

        });
    }
}
