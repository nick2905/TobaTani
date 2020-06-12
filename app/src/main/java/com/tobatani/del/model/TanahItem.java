package com.tobatani.del.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TanahItem implements Parcelable {
    private String nameTanah, indexTanah, deskripsiTanah, dataTanaman, dataWilayah;
    private int imgTanah;

    public TanahItem() {
    }

    public TanahItem(String nameTanah, String indexTanah, String deskripsiTanah, String dataTanaman, String dataWilayah, int imgTanah) {
        this.nameTanah = nameTanah;
        this.indexTanah = indexTanah;
        this.deskripsiTanah = deskripsiTanah;
        this.dataTanaman = dataTanaman;
        this.dataWilayah = dataWilayah;
        this.imgTanah = imgTanah;
    }

    public String getNameTanah() {
        return nameTanah;
    }

    public void setNameTanah(String nameTanah) {
        this.nameTanah = nameTanah;
    }

    public String getIndexTanah() {
        return indexTanah;
    }

    public void setIndexTanah(String indexTanah) {
        this.indexTanah = indexTanah;
    }

    public String getDeskripsiTanah() {
        return deskripsiTanah;
    }

    public void setDeskripsiTanah(String deskripsiTanah) {
        this.deskripsiTanah = deskripsiTanah;
    }

    public String getDataTanaman() {
        return dataTanaman;
    }

    public void setDataTanaman(String dataTanaman) {
        this.dataTanaman = dataTanaman;
    }

    public String getDataWilayah() {
        return dataWilayah;
    }

    public void setDataWilayah(String dataWilayah) {
        this.dataWilayah = dataWilayah;
    }

    public int getImgTanah() {
        return imgTanah;
    }

    public void setImgTanah(int imgTanah) {
        this.imgTanah = imgTanah;
    }

    protected TanahItem(Parcel in) {
        nameTanah = in.readString();
        indexTanah = in.readString();
        deskripsiTanah = in.readString();
        dataTanaman = in.readString();
        dataWilayah = in.readString();
        imgTanah = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameTanah);
        dest.writeString(indexTanah);
        dest.writeString(deskripsiTanah);
        dest.writeString(dataTanaman);
        dest.writeString(dataWilayah);
        dest.writeInt(imgTanah);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TanahItem> CREATOR = new Creator<TanahItem>() {
        @Override
        public TanahItem createFromParcel(Parcel in) {
            return new TanahItem(in);
        }

        @Override
        public TanahItem[] newArray(int size) {
            return new TanahItem[size];
        }
    };
}
