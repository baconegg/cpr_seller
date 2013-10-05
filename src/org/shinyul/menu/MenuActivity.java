package org.shinyul.menu;

import java.util.ArrayList;

import org.shinyul.cpr_seller.R;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
//현재 이 클래스는 사용안함..
public class MenuActivity extends Activity {

	private Context appContext = null;
	private CommonUtils util;
	private String logInData;
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
		
		arrayData = new ArrayList<IconTextItem>();
		
		for(int i = 0; i < Constants.MENU.length; i++){
			item = new IconTextItem(R.drawable.cpr_icon, Constants.MENU[i], "메뉴" + i + " 설명");
			arrayData.add(item);
		}
		
		ListView listView = (ListView)findViewById(R.id.Cusom_List);
		adapter = new IconTextListAdapter(appContext, getIntent(), R.drawable.cpr_icon, arrayData);
		listView.setAdapter(adapter);
		
	}
}
