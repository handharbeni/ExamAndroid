package com.mhandharbeni.lmm.examandroid.sqliteexam.model;

/**
 * Created by LMM on 11/17/2017.
 */

public class BiodataModel {
    int nomor;
    String nama, tanggal_lahir, jenis_kelamin, alamat;

    public BiodataModel(int nomor, String nama, String tanggal_lahir, String jenis_kelamin, String alamat) {
        this.nomor = nomor;
        this.nama = nama;
        this.tanggal_lahir = tanggal_lahir;
        this.jenis_kelamin = jenis_kelamin;
        this.alamat = alamat;
    }

    public BiodataModel() {
    }

    public int getNomor() {
        return nomor;
    }

    public void setNomor(int nomor) {
        this.nomor = nomor;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
