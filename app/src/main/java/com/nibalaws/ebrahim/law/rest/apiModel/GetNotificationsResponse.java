package com.nibalaws.ebrahim.law.rest.apiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetNotificationsResponse{

	@SerializedName("img")
	@Expose
	private String img;

	@SerializedName("datenoti")
	@Expose
	private String datenoti;

	@SerializedName("link")
	@Expose
	private String link;

	@SerializedName("description")
	@Expose
	private String description;

	@SerializedName("id")
	@Expose
	private String id;

	@SerializedName("type")
	@Expose
	private String type;

	@SerializedName("title")
	@Expose
	private String title;

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setDatenoti(String datenoti){
		this.datenoti = datenoti;
	}

	public String getDatenoti(){
		return datenoti;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return link;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return
			"GetNotificationsResponse{" +
			"img = '" + img + '\'' +
			",datenoti = '" + datenoti + '\'' +
			",link = '" + link + '\'' +
			",description = '" + description + '\'' +
			",id = '" + id + '\'' +
			",type = '" + type + '\'' +
			",title = '" + title + '\'' +
			"}";
		}
}