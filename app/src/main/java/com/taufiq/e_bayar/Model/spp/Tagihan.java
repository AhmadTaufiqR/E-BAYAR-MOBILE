package com.taufiq.e_bayar.Model.spp;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Tagihan{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private ArrayList<DataItem> data;

	@SerializedName("message")
	private String message;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(ArrayList<DataItem> data){
		this.data = data;
	}

	public ArrayList<DataItem> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}