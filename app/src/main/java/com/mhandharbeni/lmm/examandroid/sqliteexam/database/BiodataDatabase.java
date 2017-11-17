package com.mhandharbeni.lmm.examandroid.sqliteexam.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mhandharbeni.lmm.examandroid.sqliteexam.model.BiodataModel;

/**
 * Created by LMM on 11/17/2017.
 */

public class BiodataDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "SQLITEEXAM";
    private static final String TABLE_NAME = "BIODATA";
    public static final String FIELD_NOMOR = "nomor";
    public static final String FIELD_NAMA = "nama";
    public static final String FIELD_TANGGALLAHIR = "tanggal_lahir";
    public static final String FIELD_JENISKELAMIN = "jenis_kelamin";
    public static final String FIELD_ALAMAT = "alamat";

    private SQLiteDatabase db;

    public BiodataDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" (" +
                FIELD_NOMOR+" INTEGER PRIMARY KEY,"+
                FIELD_NAMA+" TEXT,"+
                FIELD_TANGGALLAHIR+" TEXT,"+
                FIELD_JENISKELAMIN+" TEXT,"+
                FIELD_ALAMAT+" TEXT"+
                ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Boolean create(BiodataModel biodataModel){
        ContentValues values = new ContentValues();
        values.put(FIELD_NOMOR, biodataModel.getNomor());
        values.put(FIELD_NAMA, biodataModel.getNama());
        values.put(FIELD_TANGGALLAHIR, biodataModel.getTanggal_lahir());
        values.put(FIELD_JENISKELAMIN, biodataModel.getJenis_kelamin());
        values.put(FIELD_ALAMAT, biodataModel.getAlamat());

        long insert = db.insert(TABLE_NAME, null, values);
        if (insert == biodataModel.getNomor()){
            return true;
        }
        return false;
    }
    public Cursor read(){
        return db.query(TABLE_NAME, new String[]{FIELD_NOMOR, FIELD_NAMA, FIELD_TANGGALLAHIR, FIELD_JENISKELAMIN, FIELD_ALAMAT}, null, null, null, null, null);
    }
    public Cursor read(String[] filter){
        return db.query(TABLE_NAME, new String[]{FIELD_NOMOR, FIELD_NAMA, FIELD_TANGGALLAHIR, FIELD_JENISKELAMIN, FIELD_ALAMAT}, FIELD_NOMOR+"=?", filter, null, null, null);
    }
    public Boolean update(BiodataModel biodataModel){
        ContentValues values = new ContentValues();
        values.put(FIELD_NAMA, biodataModel.getNama());
        values.put(FIELD_TANGGALLAHIR, biodataModel.getTanggal_lahir());
        values.put(FIELD_JENISKELAMIN, biodataModel.getJenis_kelamin());
        values.put(FIELD_ALAMAT, biodataModel.getAlamat());
        int update = db.update(TABLE_NAME,  values, FIELD_NOMOR+"=?", new String[]{String.valueOf(biodataModel.getNomor())});
        if (update == 1){
            return true;
        }
        return false;
    }
    public Boolean delete(BiodataModel biodataModel){
        int delete = db.delete(TABLE_NAME, FIELD_NOMOR+"=?", new String[]{String.valueOf(biodataModel.getNomor())});
        if (delete == 1){
            return true;
        }
        return false;
    }
    public void closeSQL(){
        if (db!=null || db.isOpen()){
            db.close();
        }
    }
}
