package com.project.responsi.ui.fpertama;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.project.responsi.R;
import com.project.responsi.dbHelp;

import java.util.ArrayList;

public class RecyclerItem extends RecyclerView.Adapter<com.project.responsi.ui.fpertama.RecyclerItem.ViewHolderItem> {
    Context mContext;
    ItemClickListener mItemClickListen;
    ArrayList<DataRowKC> mkasusCovid;
    dbHelp helper;

    public RecyclerItem(Context context, ArrayList<DataRowKC> kasusCovid, ItemClickListener listener) {
        mContext = context;
        mItemClickListen = listener;
        mkasusCovid = kasusCovid;
        helper = new dbHelp(mContext);
    }

    interface ItemClickListener {
        void OnItemClick(int position, View itemView);
    }

    @Override
    public int getItemCount() {
        return mkasusCovid.size();
    }

    @Override
    public ViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_list_item, parent, false);
        return new ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderItem holder, final int position) {
        final DataRowKC dataRowKC = mkasusCovid.get(position);
        holder.tv_urut.setText(dataRowKC._id);
        holder.tv_tanggal.setText(dataRowKC.M_TANGGAL);
        holder.tv_sembuh.setText(dataRowKC.M_SEMBUH);
        holder.tv_meninggal.setText(dataRowKC.M_MENINGGAL);
        holder.tv_konfirmasi.setText(dataRowKC.M_KONFIRMASI);

    }

    class ViewHolderItem extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {
        CardView cv_data;
        TextView tv_urut;
        TextView tv_tanggal, tv_sembuh, tv_meninggal, tv_konfirmasi;

        public ViewHolderItem(View view) {
            super(view);
            cv_data = (CardView) view.findViewById(R.id.cv_data);
            tv_urut = (TextView) view.findViewById(R.id.tv_urut);
            tv_tanggal = (TextView) view.findViewById(R.id.tv_tanggal);
            tv_sembuh = (TextView) view.findViewById(R.id.tv_sembuh);
            tv_meninggal = (TextView) view.findViewById(R.id.tv_meninggal);
            tv_konfirmasi = (TextView) view.findViewById(R.id.tv_konfirmasi);

            cv_data.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mItemClickListen.OnItemClick(getAdapterPosition(), v);
        }


    }
}