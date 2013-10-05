package org.shinyul.login;

import java.util.Map;

import org.shinyul.cpr_seller.R;
import org.shinyul.gcm.GCMUtil;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;
import org.shinyul.util.SendMessageHandler;
import org.shinyul.webview.WebViewActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Context appContext;
	private CommonUtils util;
	private String logInData;
	private String memberId;
	private String memberPw;
	private TextView ID;
	private TextView PW;
	private CheckBox logInChk;
	private Button btnLogIN;
	private Map<String,String> preData;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		initialize();
	}

	private void initialize() {
//		this.appContext = getApplicationContext(); 
		this.appContext = this;
		
		btnLogIN = (Button)findViewById(R.id.btnLogIn);
		ID = (TextView)findViewById(R.id.memberId);
		PW = (TextView)findViewById(R.id.memberPw);
		logInChk = (CheckBox)findViewById(R.id.autoLogIn);
		
		//auto login check///////////////////////////////////////////
		processThread(appContext);
		
	}

	protected void onDestroy() {

		util = GCMUtil.getGCMUtil();
		((GCMUtil) util).deleteRegId(this.appContext);
		Log.i(Constants.TAG, "mainActivity Destroy");
		super.onDestroy();
	}

	////////////////////////////////////////////////////////////////////////////
	//버튼 이벤트..
	private void logInBtnEvent(){
		// login 작업...
		btnLogIN.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				//logIn시 필요한 변수 연결
				memberId = ID.getText().toString();
				memberPw = PW.getText().toString();
				
				LogInThread thread = new LogInThread(appContext, new SendMessageHandler(), memberId, memberPw);
				thread.setDaemon(true);
				thread.start();
			}
		});
	}
	
	/////////////////////////////////////////////////////////////////////////
	public void logIn(){

		//버튼 이벤트 연결
		logInBtnEvent();
		
		String chk[] = Constants.LOGINCHK;
		if(preData.containsKey(chk[1]) && preData.containsKey(chk[0])){
			//check박스 활성화
			Constants.AUTO_LOGIN_CHK = true;
			logInChk.setChecked(Constants.AUTO_LOGIN_CHK);
			
			//ID, PW 셋팅
			ID.setText(preData.get(chk[0]));
			PW.setText(preData.get(chk[1]));
			btnLogIN.performClick();
		}else{
			if(logInChk.isChecked()){
				Constants.AUTO_LOGIN_CHK = true;
			}
		}
		/////////////////////////////////////////////////////////////
	}
	
	/////////////////////////////////////////////////////////////////////////
	//pre파일 업데이트..
	public void updatePreData(Map<String, String> preData){
		this.preData = preData;
	}
	
	//데이터 업데이트
	public void updateData(String rcvData){
		this.logInData = rcvData;
	}
	
	// //////////////////////////////////////////////////////////////////////////
	// 쓰레드 작업
	public void processThread(Context context) {
		AutoLogInThread thread = new AutoLogInThread(context, new SendMessageHandler());
		thread.setDaemon(true);
		thread.start();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//사용
	public void toGCM(Context context) {

		util = LogInUtil.getLogInUtil();
		Map<String, String> data = ((LogInUtil) util).savePreferences(context, logInData, memberPw);
		
		//////////////////
		if("0".equals(data.get("chk"))){
			// login성공
			CommonUtils	utils = GCMUtil.getGCMUtil();
			((GCMUtil)utils).startGCM(context);
			
			///////////////////////////////////////////////////////////
			//auto login 체크안 되 있을 땐 preferences 지움.. 
			if(Constants.AUTO_LOGIN_CHK == false){
				util = LogInUtil.getLogInUtil();
				((LogInUtil)util).removePreferences(context);
				Log.i(Constants.TAG, "remove preferences");
				Constants.AUTO_LOGIN_CHK = false;
			}
			///////////////////////////////////////////////////////////
			
			Bundle extras = ((LogInUtil)util).saveBundle(logInData);
			extras.putString("memberPw", memberPw);
//			Intent intent = new Intent(appContext, MenuActivity.class);
			Intent intent = new Intent(appContext, WebViewActivity.class);
			intent.putExtras(extras);
			startActivity(intent);
			
		} else {
			// 실패
			Toast.makeText(context, "ID/PW가 틀립니다.", Toast.LENGTH_LONG).show();
		}
	}
}
