package com.cs442.sshinde5.mynewapp_chckout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

public class MainPanelFragment extends Fragment {
    UsersAdapter adapter;
    public ListView listView;
    public TextView txtview;
    public static TextView txtview1;
    public TextView txtview2;
    public Button finalcart;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    //final Context context = this;
    ArrayList<User> arrayOfUsers = new ArrayList<>();

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_panel, container, false);
        arrayOfUsers = User.getUsers();

        txtview = (TextView) view.findViewById(R.id.txtview);
        txtview1 = (TextView)view.findViewById(R.id.txtview1);
        txtview2 = (TextView) view.findViewById(R.id.txtview2);
        finalcart = (Button) view.findViewById(R.id.finalcart);
        txtview1.setText("0");
        finalcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentTransaction = MainActivity.mFragmentManager.beginTransaction();
                Bundle bundle= new Bundle();
                int cost = UsersAdapter.temp;
                bundle.putInt("cost",cost);
                newAcc2 newAcc2 = new newAcc2();
                newAcc2.setArguments(bundle);
                mFragmentTransaction.replace(R.id.LL1,newAcc2).addToBackStack(null).commit();
            }
        });
        listView = (ListView) view.findViewById(R.id.lvUsers);
        adapter = new UsersAdapter(getActivity(),R.layout.item_layout,arrayOfUsers);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }
}

