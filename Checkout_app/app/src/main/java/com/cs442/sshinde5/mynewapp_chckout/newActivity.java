package com.cs442.sshinde5.mynewapp_chckout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class newActivity extends Fragment {
    public TextView tvdetails;
    public TextView tvitem;
    public TextView tvdesc;
    public TextView tvorder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_new, container, false);
        Button b = (Button) view.findViewById(R.id.tvbutton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MainPanelFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.LL1, fragment);
                fragmentTransaction.commit();
            }
        });
        int pos = getArguments().getInt("user");
        int val = getArguments().getInt("value");
        tvdetails = (TextView) view.findViewById(R.id.tvdetails);
        tvitem = (TextView) view.findViewById(R.id.tvitem);
        int cost = val * User.getUsers().get(pos).price;
        tvitem.setText(Integer.valueOf(User.getUsers().get(pos).ID)+ ". " +User.getUsers().get(pos).name+ " $" +cost);
        tvdesc = (TextView) view.findViewById(R.id.tvdesc);
        tvdesc.setText(User.getUsers().get(pos).desc);
        tvorder = (TextView) view.findViewById(R.id.tvorder);
        tvorder.setText("Order quantity: "+val+" x  $"+User.getUsers().get(pos).price);
        return view;
    }
}

