package com.nibalaws.ebrahim.law.rest.apiModel;

import com.google.gson.annotations.SerializedName;

public class SearchAhkamResponse{

	@SerializedName("details")
	private String details;

	@SerializedName("id")
	private String id;

	@SerializedName("url")
	private String url;

	@SerializedName("info")
	private String info;

	public void setDetails(String details){
		this.details = details;
	}

	public String getDetails(){
		return details;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setInfo(String info){
		this.info = info;
	}

	public String getInfo(){
		return info;
	}

	@Override
 	public String toString(){
		return 
			"SearchAhkamResponse{" + 
			"details = '" + details + '\'' + 
			",id = '" + id + '\'' + 
			",url = '" + url + '\'' + 
			",info = '" + info + '\'' + 
			"}";
		}
}