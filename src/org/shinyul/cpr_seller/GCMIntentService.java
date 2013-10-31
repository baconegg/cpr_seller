package org.shinyul.cpr_seller;

import org.shinyul.cpr_seller.R;
import org.shinyul.cpr_seller.R.drawable;
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
import android.widget.Toast;

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
		String selIdx = paramIntent.getExtras().getString(gcmMms[i++]);
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
		
		Intent alertIntent = new Intent("android.intent.action.VIEW", Uri.parse(Constants.URL_SERVER));
		
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
		
		processThread(context, Integer.parseInt(selIdx));
		
	}

	/**단말에서 GCM 서비스 등록 했을 때 등록 id를 받는다*/
	protected void onRegistered(Context context, String regId) {
//		Toast.makeText(context, "onRegistered regId : " + regId, Toast.LENGTH_SHORT).show();
		util = GCMUtil.getGCMUtil();
		((GCMUtil)util).regId(getApplicationContext(), regId, Constants.URL_ADDREGID);
		Toast.makeText(context, "GCM ID를  등록하였습니다. : " + regId, Toast.LENGTH_LONG).show();
		
		
		///////////////////////////////////////////////////////////
		//auto login 체크안 되 있을 땐 preferences 지움.. 
		if(Constants.auto_LogIn_Chk == false){ 
			util = LogInUtil.getLogInUtil();
			((LogInUtil)util).removePreferences(context);
			Log.i(Constants.TAG, "remove preferences");
		}
		///////////////////////////////////////////////////////////
		
	}

	/**단말에서 GCM 서비스 등록 해지를 하면 해지된 등록 id를 받는다*/
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
	public void processThread(Context context, int selIdx) {
		
//		WidgetThread thread = new WidgetThread(context, new SendMessageHandler(), 1, selIdx);
		WidgetThread thread = new WidgetThread(context, SendMessageHandler.getSendMessageHandler(), 1, selIdx);
//		thread.setDaemon(true);
		thread.start();
	}
}