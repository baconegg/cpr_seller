package org.shinyul.shop;

import org.shinyul.gcm.GCMUtil;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;
import org.shinyul.util.SendMessageHandler;

import android.content.Context;
import android.os.Message;

public class ShopThread extends Thread implements Runnable {

	private SendMessageHandler handler;
	private CommonUtils util;
	private Context context;

	// //////////////////////////////////////////////////////////
	public ShopThread(Context context, SendMessageHandler handler) {
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
		msg.what = Constants.SEND_THREAD_INFOMATION_SHOP;

		// 메시지 정보 설정 (int 형식)
		// msg.arg1 = Integer.valueOf(getNowDateTime());

		// 메시지 정보 설정2 (int 형식)
		// msg.arg2 = i;

		// 메시지 정보 설정3 (Object 형식)
		/*
		 * String hi = new String("Count Thared 가 동작하고 있습니다."); msg.obj = hi;
		 */
		/////////////
		//쓰레드 이용
		//웹상 이미지 주소를 받기
		//받은 주소를 이용해서 이미지 그려주는 작업
		util = ShopUtil.getShopUtil();
		
		
//		msg.obj = ;
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
