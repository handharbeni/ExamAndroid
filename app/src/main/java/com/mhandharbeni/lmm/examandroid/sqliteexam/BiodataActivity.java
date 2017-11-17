package com.mhandharbeni.lmm.examandroid.sqliteexam;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mhandharbeni.lmm.examandroid.R;
import com.mhandharbeni.lmm.examandroid.sqliteexam.adapter.BiodataAdapter;
import com.mhandharbeni.lmm.examandroid.sqliteexam.database.BiodataDatabase;
import com.mhandharbeni.lmm.examandroid.sqliteexam.model.BiodataModel;

import java.util.ArrayList;

/**
 * Created by LMM on 11/17/2017.
 */

public class BiodataActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mainlist;
    private Button addBiodata;

    private BiodataAdapter adapter;
    private ArrayList<BiodataModel> listBiodata;
    private BiodataDatabase biodataDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetch_modules();
        setContentView(R.layout.biodata_layout_activity);

        fetch_component();
        fetch_data();
    }
    private void fetch_modules(){
        listBiodata = new ArrayList<>();
        biodataDatabase = new BiodataDatabase(this);
        adapter = new BiodataAdapter(listBiodata);
    }
    private void fetch_component(){
        addBiodata = findViewById(R.id.addBiodata);
        addBiodata.setOnClickListener(this);

        mainlist = findViewById(R.id.mainlist);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mainlist.setLayoutManager(llm);
        mainlist.setAdapter(adapter);
    }
    private void fetch_data(){
        listBiodata = new ArrayList<>();
        Cursor cursor = biodataDatabase.read();
        if (cursor != null){
            if (cursor.moveToNext()){
                cursor.moveToFirst();
                do {
                    listBiodata.add(new BiodataModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
                }while (cursor.moveToNext());
                adapter.updateData(listBiodata);
            }else{
                adapter.updateData(listBiodata);
            }
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().toString().equalsIgnoreCase("LIHAT BIODATA")){
            build_view_dialog(item.getOrder());
        }else if (item.getTitle().toString().equalsIgnoreCase("UPDATE BIODATA")){
            build_update_dialog(item.getOrder());
        }else if (item.getTitle().toString().equalsIgnoreCase("DELETE BIODATA")) {
            build_delete_dialog(item.getOrder());
        }
        return super.onContextItemSelected(item);
    }
    private Boolean vavlidate_input(EditText editText){
        return !editText.getText().toString().isEmpty();
    }
    private void build_add_dialog(){
        final EditText txtNomor, txtNama, txtTanggalLahir, txtJenisKelamin, txtALamat;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.biodata_layout_custom_view_dialog, null);

        txtNomor = view.findViewById(R.id.txtNomor);
        txtNama = view.findViewById(R.id.txtNama);
        txtTanggalLahir = view.findViewById(R.id.txtTanggalLahir);
        txtJenisKelamin = view.findViewById(R.id.txtJenisKelamin);
        txtALamat = view.findViewById(R.id.txtAlamat);

        builder.setView(view);
        builder.setTitle("ADD BIODATA");
        builder.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setPositiveButton("SIMPAN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (vavlidate_input(txtNomor) && vavlidate_input(txtNama) && vavlidate_input(txtTanggalLahir) && vavlidate_input(txtJenisKelamin) && vavlidate_input(txtALamat)){
                    create_biodata(txtNomor.getText().toString(), txtNama.getText().toString(), txtTanggalLahir.getText().toString(), txtJenisKelamin.getText().toString(), txtALamat.getText().toString());
                }else{
                    Toast.makeText(BiodataActivity.this, "Masih Ada Inputan Yang Kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void build_view_dialog(int id){
        final EditText txtNomor, txtNama, txtTanggalLahir, txtJenisKelamin, txtALamat;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.biodata_layout_custom_view_dialog, null);

        txtNomor = view.findViewById(R.id.txtNomor);
        txtNama = view.findViewById(R.id.txtNama);
        txtTanggalLahir = view.findViewById(R.id.txtTanggalLahir);
        txtJenisKelamin = view.findViewById(R.id.txtJenisKelamin);
        txtALamat = view.findViewById(R.id.txtAlamat);

        Cursor cursor = get_biodata(String.valueOf(id));
        if (cursor!=null){
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                txtNomor.setText(cursor.getString(0));
                txtNama.setText(cursor.getString(1));
                txtTanggalLahir.setText(cursor.getString(2));
                txtJenisKelamin.setText(cursor.getString(3));
                txtALamat.setText(cursor.getString(4));
                txtNomor.setEnabled(false);
                txtNama.setEnabled(false);
                txtTanggalLahir.setEnabled(false);
                txtJenisKelamin.setEnabled(false);
                txtALamat.setEnabled(false);
            }
        }

        builder.setView(view);
        builder.setTitle("VIEW BIODATA");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void build_update_dialog(int id){
        final EditText txtNomor, txtNama, txtTanggalLahir, txtJenisKelamin, txtALamat;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.biodata_layout_custom_view_dialog, null);

        txtNomor = view.findViewById(R.id.txtNomor);
        txtNama = view.findViewById(R.id.txtNama);
        txtTanggalLahir = view.findViewById(R.id.txtTanggalLahir);
        txtJenisKelamin = view.findViewById(R.id.txtJenisKelamin);
        txtALamat = view.findViewById(R.id.txtAlamat);

        Cursor cursor = get_biodata(String.valueOf(id));
        if (cursor!=null){
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                txtNomor.setText(cursor.getString(0));
                txtNama.setText(cursor.getString(1));
                txtTanggalLahir.setText(cursor.getString(2));
                txtJenisKelamin.setText(cursor.getString(3));
                txtALamat.setText(cursor.getString(4));
                txtNomor.setEnabled(false);
            }
        }

        builder.setView(view);
        builder.setTitle("UPDATE BIODATA");
        builder.setPositiveButton("UBAH", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                update_biodata(txtNomor.getText().toString(), txtNama.getText().toString(), txtTanggalLahir.getText().toString(), txtJenisKelamin.getText().toString(), txtALamat.getText().toString());
            }
        });
        builder.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void build_delete_dialog(final int id){
        String nama = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Cursor cursor = get_biodata(String.valueOf(id));
        if (cursor!=null){
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                nama = cursor.getString(1);
            }
        }
        builder.setTitle("DELETE BIODATA "+nama+" ?");
        builder.setPositiveButton("HAPUS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                delete(String.valueOf(id));
            }
        });
        builder.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void create_biodata(String nomor, String nama, String tanggalLahir, String jenisKelamin, String alamat){
        BiodataModel biodataModel = new BiodataModel();
        biodataModel.setNomor(Integer.valueOf(nomor));
        biodataModel.setNama(nama);
        biodataModel.setTanggal_lahir(tanggalLahir);
        biodataModel.setJenis_kelamin(jenisKelamin);
        biodataModel.setAlamat(alamat);

        Boolean create = biodataDatabase.create(biodataModel);
        if (create){
            Toast.makeText(this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Gagal Disimpan", Toast.LENGTH_SHORT).show();
        }
        fetch_data();
    }
    private void update_biodata(String nomor, String nama, String tanggalLahir, String jenisKelamin, String alamat){
        BiodataModel biodataModel = new BiodataModel();
        biodataModel.setNomor(Integer.valueOf(nomor));
        biodataModel.setNama(nama);
        biodataModel.setTanggal_lahir(tanggalLahir);
        biodataModel.setJenis_kelamin(jenisKelamin);
        biodataModel.setAlamat(alamat);

        Boolean update = biodataDatabase.update(biodataModel);
        if (update){
            Toast.makeText(this, "Berhasil Diupdate", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Gagal Diupdate", Toast.LENGTH_SHORT).show();
        }
        fetch_data();
    }
    private Cursor get_biodata(String nomor){
        return biodataDatabase.read(new String[]{nomor});
    }

    private void delete(String nomor){
        BiodataModel biodataModel = new BiodataModel();
        biodataModel.setNomor(Integer.valueOf(nomor));

        Boolean delete = biodataDatabase.delete(biodataModel);
        if (delete){
            Toast.makeText(this, "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Gagal Dihapus", Toast.LENGTH_SHORT).show();
        }
        fetch_data();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addBiodata){
            build_add_dialog();
        }
    }

    @Override
    protected void onPause() {
        biodataDatabase.closeSQL();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (biodataDatabase != null) {
            biodataDatabase = new BiodataDatabase(this);
        }
    }

    @Override
    protected void onStop() {
        biodataDatabase.closeSQL();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        biodataDatabase.closeSQL();
        super.onDestroy();
    }
}
