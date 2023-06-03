package com.taufiq.e_bayar.Model.UserLogin;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("id_angkatan")
	private int idAngkatan;

	@SerializedName("no_telephone")
	private String noTelephone;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("gambar")
	private String gambar;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("password")
	private String password;

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("id_kelas")
	private int idKelas;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setIdAngkatan(int idAngkatan){
		this.idAngkatan = idAngkatan;
	}

	public int getIdAngkatan(){
		return idAngkatan;
	}

	public void setNoTelephone(String noTelephone){
		this.noTelephone = noTelephone;
	}

	public String getNoTelephone(){
		return noTelephone;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setGambar(String gambar){
		this.gambar = gambar;
	}

	public String getGambar(){
		return gambar;
	}

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setIdKelas(int idKelas){
		this.idKelas = idKelas;
	}

	public int getIdKelas(){
		return idKelas;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEmail(String email){ this.email = email; }

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}