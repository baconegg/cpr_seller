package org.shinyul.login;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.shinyul.gcm.GCMUtil;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

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
		
		JSONObject tmp = jsonArray.getJSONObject(0);
		
		Iterator<String> keys = tmp.keys();
		while(keys.hasNext()){
			String key = keys.next();
			data.put(key, tmp.getString(key));
		}
			
		return data;
	}
	
	// /////////////////////////////////////////////////////////////////////////////////////////////
	// 값 저장하기
	public Map<String, String> savePreferences(Context context, String rcvData) {
		util = LogInUtil.getLogInUtil();
		Map<String, String> data = ((LogInUtil) util).getLogInData(rcvData);

		SharedPreferences pref = context.getSharedPreferences("cpr", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt("chk", Integer.valueOf(data.get("chk")).intValue());
		editor.putInt("selIdx", Integer.valueOf(data.get("selIdx")).intValue());
		editor.putString("memberId", data.get("memberId"));
		editor.putString("memberName", data.get("memberName"));
		editor.putInt("memberIdx", Integer.valueOf(data.get("memberIdx")).intValue());
		editor.putInt("memberLev", Integer.valueOf(data.get("memberLev")).intValue());
		editor.commit();
		
		return data;
	}


}
