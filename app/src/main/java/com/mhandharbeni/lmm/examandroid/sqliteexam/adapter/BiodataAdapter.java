package com.mhandharbeni.lmm.examandroid.sqliteexam.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mhandharbeni.lmm.examandroid.R;
import com.mhandharbeni.lmm.examandroid.sqliteexam.model.BiodataModel;

import java.util.ArrayList;

/**
 * Created by LMM on 11/17/2017.
 */

public class BiodataAdapter extends RecyclerView.Adapter<BiodataAdapter.ViewHolder> {
    private ArrayList<BiodataModel> listBiodata;


    public BiodataAdapter(ArrayList<BiodataModel> listBiodata) {
        this.listBiodata = listBiodata;
    }

    @Override
    public BiodataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder vh = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.biodata_layout_item_layout, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(BiodataAdapter.ViewHolder holder, final int position) {
        holder.txtNama.setText(listBiodata.get(position).getNama());
        holder.mainitem.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.setHeaderTitle("MENU");
                contextMenu.add(0, 0, listBiodata.get(position).getNomor(), "lIHAT BIODATA");
                contextMenu.add(0, 0, listBiodata.get(position).getNomor(), "UPDATE BIODATA");
                contextMenu.add(0, 0, listBiodata.get(position).getNomor(), "DELETE BIODATA");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBiodata.size();
    }
    public void updateData(ArrayList<BiodataModel> listUsers){
        listBiodata.clear();
        listBiodata = listUsers;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mainitem;
        TextView txtNama;
        public ViewHolder(View itemView) {
            super(itemView);
            mainitem = itemView.findViewById(R.id.mainitem);
            txtNama = itemView.findViewById(R.id.txtNama);
        }
    }
}
