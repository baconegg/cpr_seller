package org.shinyul.login;

import org.shinyul.cpr_widget.R;
import org.shinyul.gcm.GCMUtil;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Context appContext = null;
	private CommonUtils util;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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

		// login 작업...
		Button btnLogIN = (Button) findViewById(R.id.btnLogIn);
		btnLogIN.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// login 성공 후 부르도록 해도 되지 않을까??
				((GCMUtil) util).startGCM(appContext);

				// 상점 리스트 액티비티 부르자...

			}
		});
	}

	protected void onDestroy() {
		util = GCMUtil.getGCMUtil();
		((GCMUtil) util).deleteRegId(this.appContext);
		Log.i(Constants.TAG, "mainActivity Destroy");
		super.onDestroy();
	}

}
