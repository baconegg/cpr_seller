package org.shinyul.shop;


public class ProductVO {

	// 상품IDX
	protected int productIdx;
	// 상인고유번호
	protected int selIdx;
	// 태그번호
	protected int tagIdx;
	// 판매방식명
	protected int productEvent;
	// 상품명
	protected String productName;
	// 원산지
	protected String productOrigin;
	// 가격
	protected int productPrice;
	// 상품설명
	protected String productInfo;
	// 상품평가
	protected int productLike;
	// 등록일
	protected String productRegDate;
	// 패키지표시
	protected int productPackage;
	// 상품이미지명
	protected String productImg;
	// 상품이미지UUID
	protected String productImgUUID;
	// 태그명
	protected String tagName;
	//상품 상태 -- 삭제여부
	protected int ProductStatus;

	protected int memberIdx;
	
	
	public int getProductIdx() {
		return productIdx;
	}
	public void setProductIdx(int productIdx) {
		this.productIdx = productIdx;
	}
	public int getSelIdx() {
		return selIdx;
	}
	public void setSelIdx(int selIdx) {
		this.selIdx = selIdx;
	}
	public int getTagIdx() {
		return tagIdx;
	}
	public void setTagIdx(int tagIdx) {
		this.tagIdx = tagIdx;
	}
	public int getProductEvent() {
		return productEvent;
	}
	public void setProductEvent(int productEvent) {
		this.productEvent = productEvent;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductOrigin() {
		return productOrigin;
	}
	public void setProductOrigin(String productOrigin) {
		this.productOrigin = productOrigin;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public int getProductLike() {
		return productLike;
	}
	public void setProductLike(int productLike) {
		this.productLike = productLike;
	}
	public String getProductRegDate() {
		return productRegDate;
	}
	public void setProductRegDate(String productRegDate) {
		this.productRegDate = productRegDate;
	}
	public int getProductPackage() {
		return productPackage;
	}
	public void setProductPackage(int productPackage) {
		this.productPackage = productPackage;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getProductImgUUID() {
		return productImgUUID;
	}
	public void setProductImgUUID(String productImgUUID) {
		this.productImgUUID = productImgUUID;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}	
	public int getProductStatus() {
		return ProductStatus;
	}
	public void setProductStatus(int productStatus) {
		ProductStatus = productStatus;
	}	
	public int getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
	@Override
	public String toString() {
		return "ProductVO [productIdx=" + productIdx + ", selIdx=" + selIdx
				+ ", tagIdx=" + tagIdx + ", productEvent=" + productEvent
				+ ", productName=" + productName + ", productOrigin="
				+ productOrigin + ", productPrice=" + productPrice
				+ ", productInfo=" + productInfo + ", productLike="
				+ productLike + ", productRegDate=" + productRegDate
				+ ", productPackage=" + productPackage + ", productImg="
				+ productImg + ", productImgUUID=" + productImgUUID
				+ ", tagName=" + tagName + ", ProductStatus=" + ProductStatus
				+ ", memberIdx=" + memberIdx + "]";
	}
}
