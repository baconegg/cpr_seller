package org.shinyul.shop;

import android.graphics.drawable.Drawable;

public class IconTextItem {

	private int imageID;
	private String mainTitle;
	private String subTitle;
	
	public IconTextItem(int imageID, String mainTitle, String subTitle) {
		super();
		this.imageID = imageID;
		this.mainTitle = mainTitle;
		this.subTitle = subTitle;
	}

	public int getImageID() {
		return imageID;
	}
	public void setImageID(int imageID) {
		this.imageID = imageID;
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
