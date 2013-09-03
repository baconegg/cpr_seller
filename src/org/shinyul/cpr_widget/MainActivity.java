package org.shinyul.cpr_widget;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	
	  private Context appContext = null;
	  private GCMUtil util = GCMUtil.getGCMUtil();
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 initialize();
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

	private void initialize()
	  {
	    this.appContext = getApplicationContext();
	    util.startGCM(this.appContext);
	  }

	protected void onDestroy()
	  {
		util.deleteRegId(this.appContext);
	    Log.i("GCM", "mainActivity Destroy");
	    super.onDestroy();
	  }
	
}
