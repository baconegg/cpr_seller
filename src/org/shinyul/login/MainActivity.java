package org.shinyul.login;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.shinyul.cpr_seller.R;
import org.shinyul.gcm.GCMUtil;
import org.shinyul.menu.MenuActivity;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;
import org.shinyul.util.SendMessageHandler;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Context appContext = null;
	private CommonUtils util;
	private String logInData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		initialize();
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

	private void initialize() {
//		this.appContext = getApplicationContext(); 
		this.appContext = this; 
		logInBtn();
	}

	protected void onDestroy() {
		util = GCMUtil.getGCMUtil();
		((GCMUtil) util).deleteRegId(this.appContext);
		Log.i(Constants.TAG, "mainActivity Destroy");
		super.onDestroy();
	}

	////////////////////////////////////////////////////////////////////////////
	private void logInBtn(){
		// login 작업...
		Button btnLogIN = (Button)findViewById(R.id.btnLogIn);
		btnLogIN.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				//logIn작업...
				TextView ID = (TextView)findViewById(R.id.memberId);
				TextView PW = (TextView)findViewById(R.id.memberPw);
				String memberId = ID.getText().toString();
				String memberPw = PW.getText().toString();
				
				LogInThread thread = new LogInThread(appContext, new SendMessageHandler(), memberId, memberPw);
				thread.setDaemon(true);
				thread.start();
			}
		});
	}
	
	/////////////////////////////////////////////////////////////////////////
	//데이터 업데이트
	public void updateData(String rcvData){
		this.logInData = rcvData;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//사용
	public void toGCM(Context context) {

		util = LogInUtil.getLogInUtil();
		Map<String, String> data = ((LogInUtil) util).savePreferences(context, logInData);
		//////////////////
		if("0".equals(data.get("chk"))){
			// login성공
			CommonUtils	utils = GCMUtil.getGCMUtil();
			((GCMUtil)utils).startGCM(context);
			
			Bundle extras = ((LogInUtil)util).saveBundle(logInData);
			Intent intent = new Intent(appContext, MenuActivity.class);
			intent.putExtras(extras);
			startActivity(intent);
			
		} else {
			// 실패
			Toast.makeText(context, "ID/PW가 틀립니다.", Toast.LENGTH_LONG).show();
		}
	}
}
