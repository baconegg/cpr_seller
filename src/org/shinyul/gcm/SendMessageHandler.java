package org.shinyul.gcm;

import java.util.ArrayList;
import java.util.List;

import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;
import org.shinyul.widget.ListProvider;
import org.shinyul.widget.ReserveVO;
import org.shinyul.widget.WidgetProvider;
import org.shinyul.widget.WidgetUtil;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;


public class SendMessageHandler extends Handler {

	private CommonUtils util;
	public List<ReserveVO> list = new ArrayList<ReserveVO>();
	
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		
		switch (msg.what) {
		case Constants.SEND_THREAD_INFOMATION:
			util = WidgetUtil.getWidgetUtil();
			Log.i(Constants.TAG, "ListProvider 오니?");
			
			String reserveList = msg.obj.toString();
			break;

		case Constants.SEND_THREAD_STOP_MESSAGE:

			break;

		default:
			break;
		}

	}

}
