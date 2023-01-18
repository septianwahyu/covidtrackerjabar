package com.project.responsi.ui.fpertama;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.project.responsi.R;
import com.project.responsi.dbHelp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.project.responsi.config.Config.BASE_URL;
import static com.project.responsi.config.Config.KASUS_COVID;

public class FragmentPertama extends Fragment implements RecyclerItem.ItemClickListener{

    dbHelp helper;
    RecyclerView rv_item;
    RecyclerItem adapter;
    ArrayList<DataRowKC> mKasusCovid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pertama, container, false);

        rv_item = (RecyclerView) root.findViewById(R.id.rv_item);

        helper = new dbHelp(getActivity());

        mKasusCovid = getDataSet();
        adapter = new RecyclerItem(getActivity(), mKasusCovid, (RecyclerItem.ItemClickListener) this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_item.hasFixedSize();
        rv_item.setLayoutManager(layoutManager);
        rv_item.setAdapter(adapter);

        showMessage();
        return root;
    }

    ArrayList<DataRowKC> getDataSet() {
        ArrayList<DataRowKC> dataSet = new ArrayList<>();

        Cursor c = helper.selectKC();
        while (c.moveToNext()) {
            DataRowKC data = new DataRowKC();
            data.setData(
                    c.getString(c.getColumnIndex(helper.TANGGAL)),
                    c.getString(c.getColumnIndex(helper.SEMBUH)),
                    c.getString(c.getColumnIndex(helper.MENINGGAL)),
                    c.getString(c.getColumnIndex(helper.KONFIRMASI)),
                    c.getString(c.getColumnIndex(helper._id))
            );
            dataSet.add(data);
        }
        return dataSet;
    }

    void showMessage() {
        mKasusCovid.clear();
        mKasusCovid.addAll(getDataSet());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnItemClick(int position, View itemView) {
        Log.i("FragmentPertama", "OnItemClick: Do something");
    }
}