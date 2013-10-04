package org.shinyul.shop;

import android.graphics.Bitmap;

public class IconTextItem {

	private Bitmap bm;
	private String mainTitle;
	private String subTitle;
	
	public IconTextItem(Bitmap bm, String mainTitle, String subTitle) {
		super();
		this.bm = bm;
		this.mainTitle = mainTitle;
		this.subTitle = subTitle;
	}
	
	public Bitmap getBm() {
		return bm;
	}
	public void setBm(Bitmap bm) {
		this.bm = bm;
	}
	public String getMainTitle() {
		return mainTitle;
	}
	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
}
