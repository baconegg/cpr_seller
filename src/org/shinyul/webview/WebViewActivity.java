package org.shinyul.webview;

import org.shinyul.cpr_seller.LogInUtil;
import org.shinyul.cpr_seller.R;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends Activity {

	private Context appContext;
	private CommonUtils util;
	private String logInData;
	private WebView mWebView;
		
	private Handler mHandler;
	private boolean mFlag = false;
	
	private static final int FILECHOOSER_RESULTCODE = 1;
	private ValueCallback<Uri> mUploadMessage = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		
		//종료 handler//
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg){
				if(msg.what == 0){
					mFlag = false;
				}
			}
		};		
		//////////////////////
		
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
        mWebView.getSettings().setPluginState(PluginState. ON);
        // WebViewClient 지정
        mWebView.setWebChromeClient(new WebChromeClient(){
            // For Android < 3.0
		    public void openFileChooser( ValueCallback<Uri> uploadMsg ){
		        openFileChooser( uploadMsg, "" );
		    }
		    // For Android 3.0+
		    public void openFileChooser( ValueCallback<Uri> uploadMsg, String acceptType ){  
		        mUploadMessage = uploadMsg;  
		        Intent i = new Intent(Intent.ACTION_GET_CONTENT);  
		        i.addCategory(Intent.CATEGORY_OPENABLE);  
		        i.setType("image/*");  
		        startActivityForResult( Intent.createChooser( i, "File Chooser" ), WebViewActivity.FILECHOOSER_RESULTCODE );  
		    }
		    // For Android 4.1+
		    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
		        openFileChooser( uploadMsg, "" );
		    }
        });
        mWebView.setWebViewClient(new WebViewClientClass());
        
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
//		//백 키 터치시 && 이전페이지 볼수 있을 때
//		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
//			mWebView.goBack();
//			return true;
//		}
		
		//백키 이벤트를 가로채서 플래그값 확인 후 처리..
		//플래그 값이 true인 상태에서 2초 이내에 백키를 누르면 액티비티 종료.
		if(keyCode == KeyEvent.KEYCODE_BACK){
		
			if(!mFlag){
				Toast.makeText(appContext, "뒤로 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG).show();
				mFlag = true;
				mHandler.sendEmptyMessageDelayed(0, 2000);
				return false;
			}else{
				moveTaskToBack(true);
				finish();
			}
		}
				
		return super.onKeyDown(keyCode, event);
	}

	//선택 된 파일에 대한 데이터 받기
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
//		Toast.makeText(appContext,"[" + requestCode + "]", Toast.LENGTH_LONG).show();
		if (requestCode == FILECHOOSER_RESULTCODE && mUploadMessage != null) {
			Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
			mUploadMessage.onReceiveValue(result);
			mUploadMessage = null;
		}
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
