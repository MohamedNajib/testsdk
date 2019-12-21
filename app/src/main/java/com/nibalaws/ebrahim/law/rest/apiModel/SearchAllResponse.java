package com.nibalaws.ebrahim.law.rest.apiModel;

import com.google.gson.annotations.SerializedName;

public class SearchAllResponse{

	@SerializedName("tree_Type")
	private int treeType;

	@SerializedName("tb_index")
	private String tbIndex;

	@SerializedName("mda_str")
	private Object mdaStr;

	@SerializedName("data")
	private String data;

	@SerializedName("Ahkam_type")
	private int ahkamType;

	@SerializedName("Tash_Type")
	private int tashType;

	@SerializedName("id")
	private int id;

	@SerializedName("hithiat_Type")
	private int hithiatType;

	@SerializedName("Sub_info")
	private String subInfo;

	@SerializedName("url")
	private String url;

	@SerializedName("info")
	private String info;

	public void setTreeType(int treeType){
		this.treeType = treeType;
	}

	public int getTreeType(){
		return treeType;
	}

	public void setTbIndex(String tbIndex){
		this.tbIndex = tbIndex;
	}

	public String getTbIndex(){
		return tbIndex;
	}

	public void setMdaStr(Object mdaStr){
		this.mdaStr = mdaStr;
	}

	public Object getMdaStr(){
		return mdaStr;
	}

	public void setData(String data){
		this.data = data;
	}

	public String getData(){
		return data;
	}

	public void setAhkamType(int ahkamType){
		this.ahkamType = ahkamType;
	}

	public int getAhkamType(){
		return ahkamType;
	}

	public void setTashType(int tashType){
		this.tashType = tashType;
	}

	public int getTashType(){
		return tashType;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setHithiatType(int hithiatType){
		this.hithiatType = hithiatType;
	}

	public int getHithiatType(){
		return hithiatType;
	}

	public void setSubInfo(String subInfo){
		this.subInfo = subInfo;
	}

	public String getSubInfo(){
		return subInfo;
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
			"SearchAllResponse{" + 
			"tree_Type = '" + treeType + '\'' + 
			",tb_index = '" + tbIndex + '\'' + 
			",mda_str = '" + mdaStr + '\'' + 
			",data = '" + data + '\'' + 
			",ahkam_type = '" + ahkamType + '\'' + 
			",tash_Type = '" + tashType + '\'' + 
			",id = '" + id + '\'' + 
			",hithiat_Type = '" + hithiatType + '\'' + 
			",sub_info = '" + subInfo + '\'' + 
			",url = '" + url + '\'' + 
			",info = '" + info + '\'' + 
			"}";
		}
}