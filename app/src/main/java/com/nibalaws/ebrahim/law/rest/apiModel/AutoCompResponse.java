package com.nibalaws.ebrahim.law.rest.apiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AutoCompResponse{

	@SerializedName("Text")
	@Expose
	private String text;

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"AutoCompResponse{" + 
			"text = '" + text + '\'' + 
			"}";
		}
}