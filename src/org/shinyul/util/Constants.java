package org.shinyul.util;

public class Constants {
	
	public static final String PROJECT_ID = "993491838738";
	public static final String SERVERURL = "http://192.168.0.19:8080/cpr/";
	
	public static final String TAG = "GCM";
	
	public static final String RECEIVE = "onReceiveData";
	
	//URL
	public static  final String URL_ADDREGID = "gcm/addRegId";
	public static  final String URL_LOGIN = "logIn/";
	public static  final String URL_WIDGET_LIST = "widget/list";
	
	//handler
	public static final int SEND_THREAD_STOP_MESSAGE = 0;
	public static final int SEND_THREAD_INFOMATION_WIDGET = 1;
	public static final int SEND_THREAD_INFOMATION_LOGIN = 2;
	
	//상수설정
	//gcm
	public static final String REGID[] = {"regId","memberIdx","phoneNumber"};
	public static final String GCMMMS[] = {"protocol","content","memberName","productName","reserveQty","reserveReceiveTime","reserveMemo","totalPrice","productInfo"};
	//widget
	public static final String WIDGETPARAM[] = {"page","selIdx"};
	public static final String RESERVEVO[] = {"reserveIdx","productName","productInfo","reserveTime","productIdx","productPrice","reserveQty","reserveReceiveTime","reserveMemo",
											  "reserveFlag","customerIdx","memberIdx","memberId","memberName","memberTel","memberLev","selIdx","total","total","totalPrice","marId","selStore","productImgUuid"};
	//login
	public static final String LOGINCHK[] = {"memberId","memberPw"};
	public static final String LOGINDATA[] = {"cpr","chk","selIdx","memberId","memberName","memberIdx","memberLev"};
	//menu
	public static final String MENU[] = {"상품보기","상품등록","예약확인","패키지매칭","개인정보"};    
	public static enum Menu {GOTO_SHOP, PRODUCT_ADD, RESERVATION_CERTAIN, PACKAGE_MATCH, SELLER_INFO}; 
	//shop
	public static final String SHOPPARAM[] = {"selIdx"};
	
	/*public static final String ACTION_EVENT = "org.shinyul.cpr_widget.widget.ACTION_EVENT";*/
}