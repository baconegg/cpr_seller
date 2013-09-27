package org.shinyul.gcm;

import org.shinyul.cpr_seller.R;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;
import org.shinyul.util.SendMessageHandler;
import org.shinyul.widget.WidgetThread;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
import com.google.android.gcm.GCMRegistrar;

public class GCMIntentService extends GCMBaseIntentService {

	private CommonUtils util;
	
	public GCMIntentService() {
		super(Constants.PROJECT_ID);
	}

	protected void onError(Context paramContext, String paramString) {
	}

	protected void onMessage(Context context, Intent paramIntent) {
		
		Log.i("GCM", "오냐안오녀?");
		String gcmMms[] = Constants.GCMMMS;
		int i = 0;
		
		String protocol = paramIntent.getStringExtra(gcmMms[i++]);
		String content = paramIntent.getExtras().getString(gcmMms[i++]);
		String memberName = paramIntent.getExtras().getString(gcmMms[i++]);
		String productName = paramIntent.getExtras().getString(gcmMms[i++]);
		String reserveQty = paramIntent.getExtras().getString(gcmMms[i++]);
		String reserveReceiveTime = paramIntent.getExtras().getString(gcmMms[i++]);
		String reserveMemo = paramIntent.getExtras().getString(gcmMms[i++]);
		String totalPrice = paramIntent.getExtras().getString(gcmMms[i++]);
		String productInfo = paramIntent.getExtras().getString(gcmMms[i++]);
		String title = memberName + " 님이 " + productName + "을 " + reserveQty + "개 주문하였습니다.";
		String text = "요청사항 : " + reserveMemo;
		
		Log.i("GCM", "protocol : " + protocol);
		Log.i("GCM", "content : " + content);
		Log.i("GCM", "memberName : " + memberName);
		Log.i("GCM", "productName : " + productName);
		Log.i("GCM", "reserveQty : " + reserveQty);
		Log.i("GCM", "reserveReceiveTime : " + reserveReceiveTime);
		Log.i("GCM", "reserveMemo : " + reserveMemo);
		Log.i("GCM", "totalPrice : " + totalPrice);
		Log.i("GCM", "productInfo : " + productInfo);
		
		NotificationManager noticeManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		Intent alertIntent = new Intent("android.intent.action.VIEW", Uri.parse(Constants.SERVERURL));
		
		alertIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		Notification notice = new NotificationCompat.Builder(getApplicationContext())
													.setSmallIcon(R.drawable.cpr_icon)
													.setTicker(content)
													.setContentTitle(title)
													.setContentText(text)
													.setContentIntent(pIntent)
													.setAutoCancel(true)
													.build();
		
		notice.defaults |= Notification.DEFAULT_ALL;
		
//		notice.flags |= Notification.FLAG_INSISTENT;
//		notice.sound = Uri.parse("android.resource://com.example.accompany/" + R.raw.newstories);
		
		noticeManager.notify(0, notice);
		
		processThread(context);
		
	}

	@Override
	protected void onRegistered(Context context, String regId) {
		util = GCMUtil.getGCMUtil();
		((GCMUtil)util).regId(getApplicationContext(), regId, Constants.URL_ADDREGID);
	}

	@Override
	protected void onUnregistered(Context context, String regId) {
		
		Log.i("GCMIntentService", "Device unregistered");
		
		if(GCMRegistrar.isRegisteredOnServer(context)){
			util = GCMUtil.getGCMUtil();
			((GCMUtil) util).unregister(context, regId);
		} else {
			// This callback results from the call to unregister made on
			// ServerUtilities when the registration to the server failed.
			Log.i(TAG, "Ignoring unregister callback");
		}
	}
	
	// //////////////////////////////////////////////////////////////////////////
	// 쓰레드 작업으로 위젯에 데이터를 보내는 작업을 하는 함수
	public void processThread(Context context) {
		WidgetThread thread = new WidgetThread(context, new SendMessageHandler(), 1, 3);
		thread.setDaemon(true);
		thread.start();
	}
}