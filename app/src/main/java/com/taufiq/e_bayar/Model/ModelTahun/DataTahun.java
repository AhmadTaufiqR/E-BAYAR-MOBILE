package com.taufiq.e_bayar.Model.ModelTahun;

import com.google.gson.annotations.SerializedName;

public class DataTahun {

	@SerializedName("tahun")
	private String tahun;

	public void setTahun(String tahun){
		this.tahun = tahun;
	}

	public String getTahun(){
		return tahun;
	}

	public DataTahun(String tahun) {
		this.tahun = tahun;
	}
}