package org.shinyul.widget;

import org.shinyul.cpr_seller.GCMUtil;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;
import org.shinyul.util.SendMessageHandler;

import android.content.Context;
import android.os.Message;

public class WidgetThread extends Thread implements Runnable {

	private SendMessageHandler handler;
	private CommonUtils util;
	private int page;
	private int selIdx;
	private Context context;

	// //////////////////////////////////////////////////////////
	public WidgetThread(Context context, SendMessageHandler handler, int page, int selIdx) {
		super();
		this.context = context;
		this.handler = handler;
		this.page = page;
		this.selIdx = selIdx;
		
		handler.setContext(context);
	}
	// //////////////////////////////////////////////////////////////////////////

	@Override
	public void run() {
		super.run();

		Message msg = handler.obtainMessage();

		// 메시지 ID 설정
		msg.what = Constants.SEND_THREAD_INFOMATION_WIDGET;

		// 메시지 정보 설정 (int 형식)
		// msg.arg1 = Integer.valueOf(getNowDateTime());

		// 메시지 정보 설정2 (int 형식)
		// msg.arg2 = i;

		// 메시지 정보 설정3 (Object 형식)
		/*
		 * String hi = new String("Count Thared 가 동작하고 있습니다."); msg.obj = hi;
		 */
		util = WidgetUtil.getWidgetUtil();
		String reserveList = ((WidgetUtil)util).receiveList(context, page, selIdx, Constants.URL_WIDGET_LIST);
		
		msg.obj = reserveList;
		handler.sendMessage(msg);
		
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
