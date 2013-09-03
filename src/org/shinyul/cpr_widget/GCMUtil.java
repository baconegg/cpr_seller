package org.shinyul.cpr_widget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;

public class GCMUtil extends CommonUtils {

	private static GCMUtil util = new GCMUtil();

	public static GCMUtil getGCMUtil() {
		return util;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// gcm part
	/**
	 * GCM 사용하기 위해 regId체크 및 등록
	 * 
	 * @param context
	 */
	public void startGCM(Context context) {

		/**
		 * GCM Service가 이용 가능한 Device인지 체크한다. api 8(Android 2.2) 미만인 경우나
		 * GCMService를 이용할 수 없는 디바이스의 경우 오류를 발생시키니 반드시 예외처리하도록 한다.
		 */
		try {
			GCMRegistrar.checkDevice(context);
		} catch (Exception e) {
			Log.e(Constants.TAG, "This device can't use GCM");
			return;
		}

		/**
		 * 2.SharedPreference에 저장된 RegistrationID가 있는지 확인한다. 없는 경우 null이 아닌 ""이
		 * 리턴
		 */
		String regId = GCMRegistrar.getRegistrationId(context);

		/**
		 * Registration Id가 없는 경우(어플리케이션 최초 설치로 발급받은 적이 없거나, 삭제 후 재설치 등
		 * SharedPreference에 저장된 Registration Id가 없는 경우가 이에 해당한다.)
		 */
		if (isEmpty(regId)) {
			/**
			 * 3.RegstrationId가 없는 경우 GCM Server로 Regsitration ID를 발급 요청한다. 발급
			 * 요청이 정상적으로 이루어진 경우 Registration ID는 SharedPreference에 저장되며,
			 * GCMIntentService.class의 onRegistered를 콜백한다.
			 */
			GCMRegistrar.register(context, Constants.PROJECT_ID);
			Toast.makeText(context, "GCM ID를 새로 등록하였습니다.", Toast.LENGTH_LONG)
					.show();
		} else {
			Log.i(Constants.TAG, "Exist Registration Id: " + regId);
			Toast.makeText(context, "GCM ID가 등록되어있습니다.", Toast.LENGTH_SHORT)
					.show();
		}
	}

	/**
	 * 
	 * 수정해야함!!!!!!!!
	 * 
	 * @param context
	 * @param regId
	 */
	public void unregister(Context context, String regId) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("regId", regId);
		post("url넣어주셈", params, "");
		GCMRegistrar.setRegisteredOnServer(context, false);

	}

	/**
	 * 앱 삭제시 RegId해지
	 * 
	 * @param context
	 */
	public void deleteRegId(Context context) {
		Log.i("GCM", "GCM레지스터리 해제");
		GCMRegistrar.onDestroy(context);
	}

	public String getPhoneNumber(Context paramContext) {

		return ((TelephonyManager) paramContext.getSystemService("phone"))
				.getLine1Number();
	}

	public boolean isEmpty(String value) {

		boolean isEmpty = false;

		if ((value == null) || value.trim() == "")
			isEmpty = true;

		return isEmpty;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 실제로 GCMIntentService에서 호출하는 부분...

	/**
	 * RegId 등록시 호출
	 * 
	 * @param context
	 * @param regId
	 * @param path
	 */
	public void regId(Context context, String regId, String path) {
		String phoneNumber = getPhoneNumber(context);

		HashMap<String, String> paramMap = new HashMap<String, String>();

		paramMap.put("regId", regId);
		paramMap.put("phoneNumber", phoneNumber);

		Log.i("GCM", "regId : " + regId);
		Log.i("GCM", "phoneNumber : " + phoneNumber);

		post(path, paramMap, "");

	}

	/**
	 * 1. reserveList를 받기위해
	 * 
	 * @param page
	 * @param selIdx
	 *            보냄. 2. resrveList 받아옴.
	 */
	public String receiveList(Context context, Integer page, Integer selIdx,
			String path) {
		Log.i(Constants.TAG, "reserveList in");

		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("page", String.valueOf(page));
		paramMap.put("selIdx", String.valueOf(selIdx));

		Log.i(Constants.TAG, "enter the onReceive before");

		return post(path, paramMap, "onReceiveList");
	}

}
