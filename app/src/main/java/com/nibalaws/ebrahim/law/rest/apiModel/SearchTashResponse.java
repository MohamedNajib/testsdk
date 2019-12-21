package com.nibalaws.ebrahim.law.rest.apiModel;

import com.google.gson.annotations.SerializedName;

public class SearchTashResponse{

	@SerializedName("lai7a_url")
	private String lai7aUrl;

	@SerializedName("mda_tit")
	private String mdaTit;

	@SerializedName("Edit_url")
	private String editUrl;

	@SerializedName("id")
	private String id;

	@SerializedName("pic_url")
	private String picUrl;

	@SerializedName("url")
	private String url;

	@SerializedName("mda_id")
	private String mdaId;

	@SerializedName("info")
	private String info;

	public void setLai7aUrl(String lai7aUrl){
		this.lai7aUrl = lai7aUrl;
	}

	public String getLai7aUrl(){
		return lai7aUrl;
	}

	public void setMdaTit(String mdaTit){
		this.mdaTit = mdaTit;
	}

	public String getMdaTit(){
		return mdaTit;
	}

	public void setEditUrl(String editUrl){
		this.editUrl = editUrl;
	}

	public String getEditUrl(){
		return editUrl;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setPicUrl(String picUrl){
		this.picUrl = picUrl;
	}

	public String getPicUrl(){
		return picUrl;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setMdaId(String mdaId){
		this.mdaId = mdaId;
	}

	public String getMdaId(){
		return mdaId;
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
			"SearchTashResponse{" +
			"lai7a_url = '" + lai7aUrl + '\'' +
			",mda_tit = '" + mdaTit + '\'' +
			",edit_url = '" + editUrl + '\'' +
			",id = '" + id + '\'' +
			",pic_url = '" + picUrl + '\'' +
			",url = '" + url + '\'' +
			",mda_id = '" + mdaId + '\'' +
			",info = '" + info + '\'' +
			"}";
		}
}