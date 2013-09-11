package org.shinyul.login;

import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;
import org.shinyul.util.SendMessageHandler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class LogInThread extends Thread implements Runnable {

	private Context context;
	private SendMessageHandler handler;
	private CommonUtils util;
	private String memberId;
	private String memberPw;
	

	// //////////////////////////////////////////////////////////
	public LogInThread(Context context, SendMessageHandler handler, String memberId, String memberPw) {
		super();
		this.context = context;
		this.handler = handler;
		this.memberId = memberId;
		this.memberPw = memberPw;
		
		handler.setContext(context);
	}
	// //////////////////////////////////////////////////////////////////////////

	@Override
	public void run() {
		super.run();
		
		Message msg = handler.obtainMessage();

		// 메시지 ID 설정
		msg.what = Constants.SEND_THREAD_INFOMATION_LOGIN;

		// 메시지 정보 설정 (int 형식)
		// msg.arg1 = Integer.valueOf(getNowDateTime());

		// 메시지 정보 설정2 (int 형식)
		// msg.arg2 = i;

		// 메시지 정보 설정3 (Object 형식)
		/*
		 * String hi = new String("Count Thared 가 동작하고 있습니다."); msg.obj = hi;
		 */

		util = LogInUtil.getLogInUtil();
		String LogInData = ((LogInUtil)util).logInChk(context, "logIn/", memberId, memberPw);
		
		msg.obj = LogInData;
		handler.sendMessage(msg);
		
		// 1초 딜레이
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		 msg = handler.obtainMessage();
		 msg.what = Constants.SEND_THREAD_STOP_MESSAGE;
		 handler.sendMessage(msg);

	}
}
