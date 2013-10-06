package org.shinyul.webview;

import org.shinyul.cpr_seller.R;
import org.shinyul.login.LogInUtil;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends Activity {

	private Context appContext;
	private CommonUtils util;
	private String logInData;
	private WebView mWebView;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
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
		
		Intent extras = getIntent();
		String selIdx = extras.getStringExtra("selIdx");
		String memberId = extras.getStringExtra("memberId");
		String memberPw = extras.getStringExtra("memberPw");
		
		setLayout();
		 // 웹뷰에서 자바스크립트실행가능
        mWebView.getSettings().setJavaScriptEnabled(true); 
       
        // WebViewClient 지정
        mWebView.setWebViewClient(new WebViewClientClass());
//        mWebView.setWebChromeClient(new WebChromeClient());
        
        // 홈페이지 지정
        mWebView.loadUrl(Constants.URL_SERVER + Constants.URL_WEBVIEW + memberId + "/" + memberPw + "/" + Integer.valueOf(selIdx).intValue());
        
        
	}

	protected void onDestroy() {
		super.onDestroy();
	}
	
	////////////////////////////////////////////////////////////////////
	//menu
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
//		 getMenuInflater().inflate(R.menu.main, menu);
		 
		 menu.add(0,1,0,"자동로그인 해제");
		 menu.add(0,2,0,"새로고침");
		 
		 return true;
	 }
	 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
			
		case 1:
			
			///////////////////////////////////////////////////////////
			//auto login 체크안 되 있을 땐 preferences 지움.. 
			if(Constants.auto_LogIn_Chk == true){
				util = LogInUtil.getLogInUtil();
				((LogInUtil)util).removePreferences(appContext);
				Log.i(Constants.TAG, "remove preferences");
				Constants.auto_LogIn_Chk = false;
				Toast.makeText(appContext, "적용되었습니다.", Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(appContext, "자동로그인중이 아닙니다.", Toast.LENGTH_LONG).show();
			}
			
			break;
			
		case 2:
			mWebView.reload();
			break;
			
		}
		
		return super.onOptionsItemSelected(item);
	}

	/////////////////////////////////////////////////////////////////////
    private void setLayout(){
        mWebView = (WebView) findViewById(R.id.webview);
    }

    ////////////////////////////////////////////////////////////////////
    @Override
   	public void onConfigurationChanged(Configuration newConfig) {
   		super.onConfigurationChanged(newConfig);
   	}
    
	// ///////////////////////////////////////////////////////////////////
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//백 키 터치시 && 이전페이지 볼수 있을 때
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/////////////////////////////////////////////////////////////////////
    private class WebViewClientClass extends WebViewClient { 
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) { 
//        	Toast.makeText(appContext,"[" + url + "]", Toast.LENGTH_LONG).show();
        	
        	if(!(url.endsWith("undefined"))){
        		view.loadUrl(url);
        	}
        	
            return true; 
        } 
    }
}
