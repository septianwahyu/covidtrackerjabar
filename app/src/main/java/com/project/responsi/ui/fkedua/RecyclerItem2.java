package com.project.responsi.ui.fkedua;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.project.responsi.R;
import com.project.responsi.dbHelp;

import java.util.ArrayList;

public class RecyclerItem2 extends RecyclerView.Adapter<RecyclerItem2.ViewHolderItem> {
    Context mContext;
    ItemClickListener mItemClickListen;
    ArrayList<DataRowRC> mrujukanCovid;
    dbHelp helper;

    public RecyclerItem2(Context context, ArrayList<DataRowRC> rujukanCovid, ItemClickListener listener) {
        mContext = context;
        mItemClickListen = listener;
        mrujukanCovid = rujukanCovid;
        helper = new dbHelp(mContext);
    }

    interface ItemClickListener {
        void OnItemClick(int position, View itemView);
    }

    @Override
    public int getItemCount() {
        return mrujukanCovid.size();
    }

    @Override
    public ViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_list_item2, parent, false);
        return new ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderItem holder, final int position) {
        final DataRowRC dataRowRC = mrujukanCovid.get(position);
        holder.tv_urut.setText(dataRowRC._id);
        holder.tv_nama.setText(dataRowRC.M_NAMA);
        holder.tv_alamat.setText(dataRowRC.M_ALAMAT);
    }

    class ViewHolderItem extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {
        CardView cv_data;
        TextView tv_urut;
        TextView tv_nama, tv_alamat;

        public ViewHolderItem(View view) {
            super(view);
            cv_data = (CardView) view.findViewById(R.id.cv_data);
            tv_urut = (TextView) view.findViewById(R.id.tv_urut);
            tv_nama = (TextView) view.findViewById(R.id.tv_nama);
            tv_alamat = (TextView) view.findViewById(R.id.tv_alamat);

            cv_data.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mItemClickListen.OnItemClick(getAdapterPosition(), v);
        }
    }
}