package com.project.responsi.ui.fkedua;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.project.responsi.R;
import com.project.responsi.dbHelp;
import com.project.responsi.ui.fpertama.DataRowKC;
import com.project.responsi.ui.fpertama.RecyclerItem;

import java.util.ArrayList;

public class FragmentKedua extends Fragment implements RecyclerItem2.ItemClickListener{

    dbHelp helper;
    RecyclerView rv_item;
    RecyclerItem2 adapter;
    ArrayList<DataRowRC> mRujukanCovid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kedua, container, false);

        rv_item = (RecyclerView) root.findViewById(R.id.rv_item2);

        helper = new dbHelp(getActivity());

        mRujukanCovid = getDataSet();
        adapter = new RecyclerItem2(getActivity(), mRujukanCovid, (RecyclerItem2.ItemClickListener) this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_item.hasFixedSize();
        rv_item.setLayoutManager(layoutManager);
        rv_item.setAdapter(adapter);

        showMessage();

        return root;
    }

    ArrayList<DataRowRC> getDataSet() {
        ArrayList<DataRowRC> dataSet = new ArrayList<>();

        Cursor c = helper.selectRC();
        while (c.moveToNext()) {
            DataRowRC data = new DataRowRC();
            data.setData(
                    c.getString(c.getColumnIndex(helper.NAMA)),
                    c.getString(c.getColumnIndex(helper.ALAMAT)),
                    c.getString(c.getColumnIndex(helper.LONGITUDE)),
                    c.getString(c.getColumnIndex(helper.LATITUDE)),
                    c.getString(c.getColumnIndex(helper._id))
            );
            dataSet.add(data);
        }
        return dataSet;
    }

    void showMessage() {
        mRujukanCovid.clear();
        mRujukanCovid.addAll(getDataSet());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnItemClick(int position, View itemView) {
        String lat = "", lng = "";
        Cursor c;
        c = helper.selectLocRC();
        if (c.moveToPosition(position)) {
            lng = c.getString(0);
            lat = c.getString(1);
        }
        String strUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lng + " (" + "RS Rujukan Covid" + ")";
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));

        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);
    }
}