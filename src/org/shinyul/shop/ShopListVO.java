package org.shinyul.shop;

public class ShopListVO extends ProductListVO {

	//mobile용 
	private String mobileImage;
	
	public String getMobileImage() {
		return mobileImage;
	}
	public void setMobileImage(String mobileImage) {
		this.mobileImage = mobileImage;
	}
	
	@Override
	public String toString() {
		return "ShopListVO [mobileImage=" + mobileImage + "]";
	}
}
