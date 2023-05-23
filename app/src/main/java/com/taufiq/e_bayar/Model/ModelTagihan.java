package com.taufiq.e_bayar.Model;

public class ModelTagihan{

    String id;
    Integer price;
    Integer quantity;
    String name;


    boolean checked;





    public ModelTagihan(String id, Integer price, Integer quantity, String name) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.checked = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getJumlah() {
        return quantity;
    }

    public void setJumlah(Integer jumlah) {
        this.quantity = jumlah;
    }

    public String getBulan() {
        return name;
    }

    public void setBulan(String bulan) {
        this.name = bulan;
    }

    public Integer getHarga() {
        return price;
    }

    public void setHarga(Integer harga) {
        this.price = harga;
    }


    public boolean isChecked() {
        return checked;
    }

    public boolean setChecked(boolean checked) {
        this.checked = checked;
        return checked;
    }
}
