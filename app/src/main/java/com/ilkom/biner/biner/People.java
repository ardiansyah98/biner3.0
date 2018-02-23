package com.ilkom.biner.biner;

/**
 * Created by Ardiansyah on 10/02/2018.
 */

public class People {
    String nama;
    int usia;

    public People(String nama, int usia) {
        this.nama = nama;
        this.usia = usia;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }
}
