package org.shinyul.cpr_widget;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReserveVO {

	private int reserveIdx; // 예약번호
	private String reserveTime; // 예약시간
	private int productIdx; // 상품번호
	private String productName; // 상품 이름
	private String productInfo; // 상품 정보
	private int productPrice; // 상품가격
	private int reserveQty; // 예약 수량
	private String reserveReceiveTime; //수취시간
	private String reserveMemo; // 예약 메모
	private int reserveFlag; // 예약 진행상황
	private int customerIdx; // 주문자
	private int memberIdx; // 회원번호
	private String memberId; // 회원 아이디
	private String memberName; // 회원 이름
	private String memberTel; // 회원 번호
	private int memberLev; // 회원 레벨 (상인/손님)
	private int selIdx; // 해당 상품 상인
	private int total; 
	private int totalPrice; // 총 가격
	private String marId; // 시장 아이디
	private String selStore; // 상점
	private String productImgUuid; // 상품이미지
	
	
	
	
		
	public void init(JSONObject obj) {
		try {
			this.reserveIdx =  obj.getInt("reserveIdx");	
			this.reserveTime = obj.getString("reserveTime");
			this.productIdx = obj.getInt("productIdx");	
			this.productName = obj.getString("productName");
			this.productInfo = obj.getString("productInfo");
			this.productPrice = obj.getInt("productPrice");	;
			this.reserveQty = obj.getInt("reserveQty");	;
			this.reserveReceiveTime = obj.getString("reserveReceiveTime");
			this.reserveMemo = obj.getString("reserveMemo");
			this.reserveFlag = obj.getInt("reserveFlag");	;
			this.customerIdx = obj.getInt("customerIdx");	;
			this.memberIdx = obj.getInt("memberIdx");	;
			this.memberId = obj.getString("memberId");
			this.memberName = obj.getString("memberName");
			this.memberTel = obj.getString("memberTel");
			this.memberLev = obj.getInt("memberLev");	;
			this.selIdx = obj.getInt("selIdx");	;
			this.total = obj.getInt("total");	;
			this.totalPrice = obj.getInt("totalPrice");	;
			this.marId = obj.getString("marId");
			this.selStore = obj.getString("selStore");
			this.productImgUuid = obj.getString("productImgUuid");
		
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
	public int getReserveIdx() {
		return reserveIdx;
	}
	public void setReserveIdx(int reserveIdx) {
		this.reserveIdx = reserveIdx;
	}
	public String getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}
	public int getProductIdx() {
		return productIdx;
	}
	public void setProductIdx(int productIdx) {
		this.productIdx = productIdx;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getReserveQty() {
		return reserveQty;
	}
	public void setReserveQty(int reserveQty) {
		this.reserveQty = reserveQty;
	}
	public String getReserveReceiveTime() {
		return reserveReceiveTime;
	}
	public void setReserveReceiveTime(String reserveReceiveTime) {
		this.reserveReceiveTime = reserveReceiveTime;
	}
	public String getReserveMemo() {
		return reserveMemo;
	}
	public void setReserveMemo(String reserveMemo) {
		this.reserveMemo = reserveMemo;
	}
	public int getReserveFlag() {
		return reserveFlag;
	}
	public void setReserveFlag(int reserveFlag) {
		this.reserveFlag = reserveFlag;
	}
	public int getCustomerIdx() {
		return customerIdx;
	}
	public void setCustomerIdx(int customerIdx) {
		this.customerIdx = customerIdx;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}
	
	public int getMemberLev() {
		return memberLev;
	}
	public void setMemberLev(int memberLev) {
		this.memberLev = memberLev;
	}
	public int getSelIdx() {
		return selIdx;
	}
	public void setSelIdx(int selIdx) {
		this.selIdx = selIdx;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getMarId() {
		return marId;
	}
	public void setMarId(String marId) {
		this.marId = marId;
	}
	public String getSelStore() {
		return selStore;
	}
	public void setSelStore(String selStore) {
		this.selStore = selStore;
	}
	public String getProductImgUuid() {
		return productImgUuid;
	}
	public void setProductImgUuid(String productImgUuid) {
		this.productImgUuid = productImgUuid;
	}
	
	@Override
	public String toString() {
		return "ReserveVO [reserveIdx=" + reserveIdx + ", reserveTime="
				+ reserveTime + ", productIdx=" + productIdx + ", productName="
				+ productName + ", productInfo=" + productInfo
				+ ", productPrice=" + productPrice + ", reserveQty="
				+ reserveQty + ", reserveReceiveTime=" + reserveReceiveTime
				+ ", reserveMemo=" + reserveMemo + ", reserveFlag="
				+ reserveFlag + ", customerIdx=" + customerIdx + ", memberIdx="
				+ memberIdx + ", memberId=" + memberId + ", memberName="
				+ memberName + ", memberTel=" + memberTel + ", memberLev="
				+ memberLev + ", selIdx=" + selIdx + ", total=" + total
				+ ", totalPrice=" + totalPrice + ", marId=" + marId
				+ ", selStore=" + selStore + ", productImgUuid="
				+ productImgUuid + "]";
	}
}
