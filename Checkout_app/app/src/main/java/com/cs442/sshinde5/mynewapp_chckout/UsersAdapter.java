package com.cs442.sshinde5.mynewapp_chckout;

/**
 * Created by Shivani Shinde on 01-10-2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class UsersAdapter extends ArrayAdapter<User> {

    Context context;
    int resource;
    public int val[]= new int[6];
    ArrayList<User> arrayOfUsers = new ArrayList<>();
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    public static int temp=0;
    static User user;

    public UsersAdapter(Context context, int resource,ArrayList<User> arrayofUsers) {
        super(context, resource,arrayofUsers);
        this.context = context;
        this.resource = resource;
        this.arrayOfUsers = arrayofUsers;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);
        }
        user = (User) getItem(position);


        // Lookup view for data population
        TextView tvID = (TextView) convertView.findViewById(R.id.tvID);
        //final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
        final Button select = (Button) convertView.findViewById(R.id.select);
        Button unselect = (Button) convertView.findViewById(R.id.unselect);
        Button cart = (Button) convertView.findViewById(R.id.cart);

        //Button details = (Button) convertView.findViewById(R.id.details);
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.tvprice);
        final TextView tvcount = (TextView) convertView.findViewById(R.id.tvcount);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = (User) getItem(position);
                user.setCount(user.getCount()+1);
                val[position] = user.getCount();
                temp = Integer.parseInt((String)MainPanelFragment.txtview1.getText()) + (user.getPrice());
                //user.setValue();
                MainPanelFragment.txtview1.setText(String.valueOf(temp));
                tvcount.setText(String.valueOf(user.count));


            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = (User) getItem(position);
                /*Intent i = new Intent(getContext(),DetailActivity.class);
                i.putExtra("user",position);
                i.putExtra("val",val[position]);
                getContext().startActivity(i);
                */
                mFragmentTransaction = MainActivity.mFragmentManager.beginTransaction();
                Bundle bundle= new Bundle();
                bundle.putInt("user",position);
                bundle.putInt("value",val[position]);
                newActivity newActivity = new newActivity();
                newActivity.setArguments(bundle);
                mFragmentTransaction.replace(R.id.LL1,newActivity, "second").addToBackStack("first").commit();
            }
        });

        // Populate the data into the template view using the data object
        tvID.setText(user.ID);
        tvName.setText(user.name);
        tvPrice.setText(String.valueOf(user.price));
        tvcount.setText(String.valueOf(user.count));
        // Return the completed view to render on screen
        return convertView;
    }
}