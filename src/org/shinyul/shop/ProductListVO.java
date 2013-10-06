package org.shinyul.shop;


public class ProductListVO extends ProductVO {
	
	private int rn;
	private int after;
	private int before;
	private int totalCount;
	
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public int getAfter() {
		return after;
	}
	public void setAfter(int after) {
		this.after = after;
	}
	public int getBefore() {
		return before;
	}
	public void setBefore(int before) {
		this.before = before;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	@Override
	public String toString() {
		return "ListProductVO [rn=" + rn + ", after=" + after + ", before="
				+ before + ", totalCount=" + totalCount + "]";
	}	

}
