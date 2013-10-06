package org.shinyul.login;

import java.util.Map;

import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;
import org.shinyul.util.SendMessageHandler;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class AutoLogInThread extends Thread implements Runnable {

	private Context context;
	private SendMessageHandler handler;
	private CommonUtils util;
	

	// //////////////////////////////////////////////////////////
	public AutoLogInThread(Context context, SendMessageHandler handler) {
		super();
		this.context = context;
		this.handler = handler;
				
		handler.setContext(context);
	}
	// //////////////////////////////////////////////////////////////////////////

	@Override
	public void run() {
		super.run();
		
		Message msg = handler.obtainMessage();

		// 메시지 ID 설정
		msg.what = Constants.SEND_THREAD_INFOMATION_AUTO_LOGIN;

		// 메시지 정보 설정 (int 형식)
		// msg.arg1 = Integer.valueOf(getNowDateTime());

		// 메시지 정보 설정2 (int 형식)
		// msg.arg2 = i;

		// 메시지 정보 설정3 (Object 형식)
		/*
		 * String hi = new String("Count Thared 가 동작하고 있습니다."); msg.obj = hi;
		 */
		
		util = LogInUtil.getLogInUtil();
		Map<String, String> preData = ((LogInUtil)util).checkPreferences(context);
		
		if(preData.isEmpty()){
			preData.put("noData", "noData");
		}
		
		msg.obj = preData;
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
