package org.shinyul.util;

import java.util.Map;

import org.shinyul.gcm.GCMUtil;
import org.shinyul.login.LogInUtil;
import org.shinyul.login.MainActivity;
import org.shinyul.widget.WidgetProvider;

import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class SendMessageHandler extends Handler {

	private Context context;
	private String rcvData;
	private CommonUtils util;
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		
		switch (msg.what) {
		case Constants.SEND_THREAD_STOP_MESSAGE:

			break;
			
		case Constants.SEND_THREAD_INFOMATION_WIDGET:
			Log.i(Constants.TAG, "SEND_THREAD_INFOMATION_WIDGET 오니?");
			
			rcvData = msg.obj.toString();

			AppWidgetManager manager = AppWidgetManager.getInstance(context);
			WidgetProvider provider = new WidgetProvider();
			provider.updateData(rcvData);
			provider.onUpdate(context, manager, manager.getAppWidgetIds(new ComponentName(context, WidgetProvider.class)));
			break;
		
		case Constants.SEND_THREAD_INFOMATION_LOGIN:
			Log.i(Constants.TAG, "SEND_THREAD_INFOMATION_LOGIN 오니?");
			
			rcvData = msg.obj.toString();
			
			((MainActivity)context).updateData(rcvData);
			((MainActivity)context).toGCM(context);
			break;

		default:
			break;
		}
		Log.i(Constants.TAG, rcvData);
	}
}
