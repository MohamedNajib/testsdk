package com.nibalaws.ebrahim.law.rest.apiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search {

	@SerializedName("id")
	@Expose
	private String id;

	@SerializedName("url")
	@Expose
	private String url;

	@SerializedName("info")
	@Expose
	private String info;

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
			"Search{" +
			"id = '" + id + '\'' +
			",url = '" + url + '\'' +
			",info = '" + info + '\'' +
			"}";
		}
}