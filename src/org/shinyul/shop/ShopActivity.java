package org.shinyul.shop;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.shinyul.cpr_seller.R;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;
import org.shinyul.util.SendMessageHandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListView;
//현재 이 클래스는 사용안함..
public class ShopActivity extends Activity {

	private Context appContext = null;
	private CommonUtils util;
	private ArrayList<IconTextItem> arrayData;
	private IconTextItem item;
	private IconTextListAdapter adapter;
	private String rcvData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		initialize();
	}

	private void initialize() {
//		this.appContext = getApplicationContext(); 
		this.appContext = this; 
		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
		
		/////////////
		String logInData[] = Constants.LOGINDATA;
		Intent intent = getIntent();
		String selIdx = intent.getStringExtra(logInData[2]);
		//test
		processThread(appContext, selIdx);
	}

	
	
	////////////////////////////////////////////////////////////////////////////////
	// 쓰레드 작업
	public void processThread(Context context, String selIdx) {
		ShopThread thread = new ShopThread(context, new SendMessageHandler(), selIdx);
//		ShopThread thread = new ShopThread(context, SendMessageHandler.getSendMessageHandler(), selIdx);
		thread.setDaemon(true);
		thread.start();
	}
	
	//setting rcvData
	public void setRcvData(String rcvData){
		this.rcvData = rcvData;
	}
	
	//상품 업데이트
	public void updateData() {
		
		arrayData = new ArrayList<IconTextItem>();
		InputStream is =  null;
		Bitmap bm = null;
		try {
			JSONArray jsonArray = new JSONArray(rcvData);
            // 선언
//			JSONObject jsonobject = new JSONObject(rcvData);
			String shopVO[] = Constants.SHOPLISTVO;
			
			String length = String.valueOf(jsonArray.length());
			Log.i("",length);
			
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject ob = jsonArray.getJSONObject(i);
				
				ShopListVO vo = new ShopListVO();

				int j = 0;
				vo.setMobileImage(ob.getString(shopVO[j++]));
				vo.setProductName(ob.getString(shopVO[j++]));
				vo.setProductInfo(ob.getString(shopVO[j++]));
				Log.i("",ob.getString("mobileImage"));				
				
				is = new URL(vo.getMobileImage()).openStream();
				BitmapFactory.Options bfo = new BitmapFactory.Options();

//				bfo.inJustDecodeBounds = true;
//				if(bfo.outHeight * bfo.outWidth >= 200 * 200) {
//					bfo.inSampleSize = (int)Math.pow(2, (int)Math.round(Math.log(100 / (double) Math.max(bfo.outHeight, bfo.outWidth)) / Math.log(0.5)));
//				}
//				bfo.inJustDecodeBounds = false;

				bm = BitmapFactory.decodeStream(new FlushedInputStream(is), null, bfo); 
				Bitmap resizeBm =  Bitmap.createBitmap(bm, 0, 0, 250, 250);

				item = new IconTextItem(resizeBm, vo.getProductName(), vo.getProductInfo());
				arrayData.add(item);
			}
			
			ListView listView = (ListView)findViewById(R.id.Cusom_List);
			adapter = new IconTextListAdapter(appContext,R.drawable.cpr_icon, arrayData);
			listView.setAdapter(adapter);
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(is != null) is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
