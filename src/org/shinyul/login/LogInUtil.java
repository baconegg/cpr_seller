package org.shinyul.login;

import java.lang.reflect.GenericArrayType;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;
import org.shinyul.widget.ReserveVO;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class LogInUtil extends CommonUtils {

	private static LogInUtil util = new LogInUtil();

	public static LogInUtil getLogInUtil() {
		return util;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String logInChk(Context context, String path, String memberId, String memberPw) {
		Log.i(Constants.TAG, "logInChk in");

		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("memberId", memberId);
		paramMap.put("memberPw", memberPw);

		Log.i(Constants.TAG, "enter the logInChk before");

		return post(path, paramMap, "onReceiveData");
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 파싱
	public Map<String, String> getLogInData(String rcvData){
		Map<String, String> data = new HashMap<String, String>();
		JSONArray jsonArray = exchangeData(rcvData);
		
		int idx = 0;
		data.put("selIdx", jsonArray.getString(idx++));
		data.put("memberId", jsonArray.getString(idx++));
		data.put("memberIdx", jsonArray.getString(idx++));
		data.put("memberName", jsonArray.getString(idx++));
		data.put("chk", jsonArray.getString(idx++));
		data.put("memberLev", jsonArray.getString(idx++));
			
		return data;
	}
	
	// /////////////////////////////////////////////////////////////////////////////////////////////
	// 값 저장하기
	public void savePreferences(Context context, Map<String, String> data, String rcvData) {

		util = LogInUtil.getLogInUtil();
		data = ((LogInUtil) util).getLogInData(rcvData);

		SharedPreferences pref = context.getSharedPreferences("cpr", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt("chk", Integer.valueOf(data.get("chk")).intValue());
		editor.putInt("selIdx", Integer.valueOf(data.get("selIdx")).intValue());
		editor.putString("memberId", data.get("memberId"));
		editor.putString("memberName", data.get("memberName"));
		editor.putInt("memberIdx", Integer.valueOf(data.get("memberIdx")).intValue());
		editor.putInt("memberLev", Integer.valueOf(data.get("memberLev")).intValue());
		editor.commit();
	}
}
