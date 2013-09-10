package org.shinyul.gcm;

import org.shinyul.widget.WidgetProvider;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;

public class PrivateThread extends Thread implements Runnable {

	private Handler handler;
	private GCMUtil util = GCMUtil.getGCMUtil();

	public Context context;

	// //////////////////////////////////////////////////////////
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	// //////////////////////////////////////////////////////////////////////////

	@Override
	public void run() {
		super.run();

//		Message msg = handler.obtainMessage();

		// 메시지 ID 설정
//		msg.what = Constants.SEND_THREAD_INFOMATION;

		// 메시지 정보 설정 (int 형식)
		// msg.arg1 = Integer.valueOf(getNowDateTime());

		// 메시지 정보 설정2 (int 형식)
		// msg.arg2 = i;

		// 메시지 정보 설정3 (Object 형식)
		/*
		 * String hi = new String("Count Thared 가 동작하고 있습니다."); msg.obj = hi;
		 */

		String reserveList = util.receiveList(context, 1, 46, "reserve/widgetlist");
		
//		msg.obj = reserveList;
//		handler.sendMessage(msg);
		
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		WidgetProvider provider = new WidgetProvider();
		provider.updateData(reserveList);
		provider.onUpdate(context, manager, manager.getAppWidgetIds(new ComponentName(context, WidgetProvider.class)));
		
		// 1초 딜레이
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

//		 msg = handler.obtainMessage();
//		 msg.what = Constants.SEND_THREAD_STOP_MESSAGE;
//		 handler.sendMessage(msg);

	}

}
