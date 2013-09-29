package org.shinyul.shop;

import java.util.ArrayList;

import org.shinyul.cpr_seller.R;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;
import org.shinyul.util.SendMessageHandler;
import org.shinyul.widget.WidgetThread;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class ShopActivity extends Activity {

	private Context appContext = null;
	private CommonUtils util;
	private ArrayList<IconTextItem> arrayData;
	private IconTextItem item;
	private IconTextListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		initialize();
	}

	private void initialize() {
//		this.appContext = getApplicationContext(); 
		this.appContext = this; 
		
		//쓰레드 작업
		
		/////////////
		String logInData[] = Constants.LOGINDATA;
		Intent intent = getIntent();
		String selIdx = intent.getStringExtra(logInData[2]);
		//test
		updateData();
	}

	
	
	////////////////////////////////////////////////////////////////////////////////
	// 쓰레드 작업
	public void processThread(Context context) {
		ShopThread thread = new ShopThread(context, new SendMessageHandler());
		thread.setDaemon(true);
		thread.start();
	}
	
	//상품 업데이트
	private void updateData() {
		
		arrayData = new ArrayList<IconTextItem>();
		
		for(int i = 0; i < 10; i++){
			item = new IconTextItem(R.drawable.cpr_icon, "상품" + i, "상품" + i + " 설명");
			arrayData.add(item);
		}
		
		ListView listView = (ListView)findViewById(R.id.Cusom_List);
		adapter = new IconTextListAdapter(appContext,R.drawable.cpr_icon, arrayData);
		listView.setAdapter(adapter);
		
	}
}
