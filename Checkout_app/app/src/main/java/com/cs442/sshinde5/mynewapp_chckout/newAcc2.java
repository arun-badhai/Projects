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

public class newAcc2 extends Fragment {
    public TextView tvpay;
    public TextView tvcost;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newacc2, container, false);
        int cost = getArguments().getInt("cost");
        Button b1 = (Button) view.findViewById(R.id.confirm);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MainPanelFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.LL1, fragment);
                fragmentTransaction.commit();
            }
        });
        tvpay = (TextView) view.findViewById(R.id.pay);
        tvcost = (TextView) view.findViewById(R.id.cost);
        tvcost.setText("$"+cost);
        return view;
    }
}

