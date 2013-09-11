package org.shinyul.login;

import java.util.Map;

import org.shinyul.cpr_seller.R;
import org.shinyul.gcm.GCMUtil;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;
import org.shinyul.util.SendMessageHandler;
import android.app.Activity;
import android.content.Context;
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
	private Map<String, String> data;
	
	
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
		this.appContext = getApplicationContext(); 
//		this.appContext = this; 
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
				
				new LogInThread(appContext, new SendMessageHandler(), memberId, memberPw).start();
			}
		});
	}
	
	public void toGCM(String rcvData){
			
		util = LogInUtil.getLogInUtil();
		((LogInUtil)util).savePreferences(appContext, data, rcvData);
		
		if(data.containsKey("0")){
			//login성공
			util = GCMUtil.getGCMUtil();
			((GCMUtil)util).startGCM(appContext);
		}else{
			//실패
			Toast.makeText(appContext, "ID/PW가 틀립니다.", Toast.LENGTH_LONG).show();
		}
										
		// 상점 리스트 액티비티 부르자...
	}
}
