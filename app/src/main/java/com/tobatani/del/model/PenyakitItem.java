package com.tobatani.del.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PenyakitItem implements Parcelable {
    String namaPenyakit, namaLatin, deskripsiPenyakit, ciriPenyakit, solusiPenyakit, serangTanamanPenyakit;
    int imgPenyakit;

    public String getNamaPenyakit() {
        return namaPenyakit;
    }

    public void setNamaPenyakit(String namaPenyakit) {
        this.namaPenyakit = namaPenyakit;
    }

    public String getNamaLatin() {
        return namaLatin;
    }

    public void setNamaLatin(String namaLatin) {
        this.namaLatin = namaLatin;
    }

    public String getDeskripsiPenyakit() {
        return deskripsiPenyakit;
    }

    public void setDeskripsiPenyakit(String deskripsiPenyakit) {
        this.deskripsiPenyakit = deskripsiPenyakit;
    }

    public String getCiriPenyakit() {
        return ciriPenyakit;
    }

    public void setCiriPenyakit(String ciriPenyakit) {
        this.ciriPenyakit = ciriPenyakit;
    }

    public String getSolusiPenyakit() {
        return solusiPenyakit;
    }

    public void setSolusiPenyakit(String solusiPenyakit) {
        this.solusiPenyakit = solusiPenyakit;
    }

    public String getSerangTanamanPenyakit() {
        return serangTanamanPenyakit;
    }

    public void setSerangTanamanPenyakit(String serangTanamanPenyakit) {
        this.serangTanamanPenyakit = serangTanamanPenyakit;
    }

    public int getImgPenyakit() {
        return imgPenyakit;
    }

    public void setImgPenyakit(int imgPenyakit) {
        this.imgPenyakit = imgPenyakit;
    }

    public PenyakitItem() {

    }

    public PenyakitItem(String namaPenyakit, String namaLatin, String deskripsiPenyakit, String ciriPenyakit, String solusiPenyakit, String serangTanamanPenyakit, int imgPenyakit) {
        this.namaPenyakit = namaPenyakit;
        this.namaLatin = namaLatin;
        this.deskripsiPenyakit = deskripsiPenyakit;
        this.ciriPenyakit = ciriPenyakit;
        this.solusiPenyakit = solusiPenyakit;
        this.serangTanamanPenyakit = serangTanamanPenyakit;
        this.imgPenyakit = imgPenyakit;
    }

    protected PenyakitItem(Parcel in) {
        namaPenyakit = in.readString();
        namaLatin = in.readString();
        deskripsiPenyakit = in.readString();
        ciriPenyakit = in.readString();
        solusiPenyakit = in.readString();
        serangTanamanPenyakit = in.readString();
        imgPenyakit = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(namaPenyakit);
        dest.writeString(namaLatin);
        dest.writeString(deskripsiPenyakit);
        dest.writeString(ciriPenyakit);
        dest.writeString(solusiPenyakit);
        dest.writeString(serangTanamanPenyakit);
        dest.writeInt(imgPenyakit);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PenyakitItem> CREATOR = new Creator<PenyakitItem>() {
        @Override
        public PenyakitItem createFromParcel(Parcel in) {
            return new PenyakitItem(in);
        }

        @Override
        public PenyakitItem[] newArray(int size) {
            return new PenyakitItem[size];
        }
    };
}
