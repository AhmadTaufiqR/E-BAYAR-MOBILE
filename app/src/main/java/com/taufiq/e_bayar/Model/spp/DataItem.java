package com.taufiq.e_bayar.Model.spp;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("id_admin")
	private int idAdmin;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("akhir_bayar")
	private String akhirBayar;

	@SerializedName("tipe")
	private String tipe;

	@SerializedName("jumlah_bayar")
	private int jumlahBayar;

	@SerializedName("awal_pembayaran")
	private String awalPembayaran;

	@SerializedName("bulan")
	private String bulan;

	@SerializedName("deleted_at")
	private Object deletedAt;

	private boolean checked = false;

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setIdAdmin(int idAdmin){
		this.idAdmin = idAdmin;
	}

	public int getIdAdmin(){
		return idAdmin;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAkhirBayar(String akhirBayar){
		this.akhirBayar = akhirBayar;
	}

	public String getAkhirBayar(){
		return akhirBayar;
	}

	public void setTipe(String tipe){
		this.tipe = tipe;
	}

	public String getTipe(){
		return tipe;
	}

	public void setJumlahBayar(int jumlahBayar){
		this.jumlahBayar = jumlahBayar;
	}

	public int getJumlahBayar(){
		return jumlahBayar;
	}

	public void setAwalPembayaran(String awalPembayaran){
		this.awalPembayaran = awalPembayaran;
	}

	public String getAwalPembayaran(){
		return awalPembayaran;
	}

	public void setBulan(String bulan){
		this.bulan = bulan;
	}

	public String getBulan(){
		return bulan;
	}

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public boolean isChecked() {
		return checked;
	}

	public boolean setChecked(boolean checked) {
		this.checked = checked;
        return checked;
    }
}