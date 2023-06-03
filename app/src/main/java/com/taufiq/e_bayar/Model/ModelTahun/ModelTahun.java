package com.taufiq.e_bayar.Model.ModelTahun;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ModelTahun{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<DataTahun> data;

	@SerializedName("message")
	private String message;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(List<DataTahun> data){
		this.data = data;
	}

	public List<DataTahun> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}