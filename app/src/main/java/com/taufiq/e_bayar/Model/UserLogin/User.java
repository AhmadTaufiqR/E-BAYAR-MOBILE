package com.taufiq.e_bayar.Model.UserLogin;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("siswa")
	private Data data;

	@SerializedName("message")
	private String message;

	@SerializedName("token")
	private String token;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}
}