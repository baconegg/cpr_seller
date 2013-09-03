package org.shinyul.cpr_widget;

import java.util.ArrayList;
import java.util.List;

import android.appwidget.AppWidgetManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;


public class SendMessageHandler extends Handler {

	private GCMUtil util = GCMUtil.getGCMUtil();
	public ListProvider listProvider;
//	public ArrayList<ListItem> listItemList = new ArrayList<ListItem>();
	public List<ReserveVO> list = new ArrayList<ReserveVO>();
	
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);

		switch (msg.what) {
		case Constants.SEND_THREAD_INFOMATION:

			Log.i(Constants.TAG, "ListProvider 오니?");

			String reserveList = msg.obj.toString();
			List<ReserveVO> list =  util.getList(reserveList);
			
//			listProvider.list = list;
						
			break;

		case Constants.SEND_THREAD_STOP_MESSAGE:

			break;

		default:
			break;
		}

	}

}
