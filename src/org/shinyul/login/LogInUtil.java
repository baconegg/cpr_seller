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
import android.os.Bundle;
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

		String logInChk[] = Constants.LOGINCHK;
		int i = 0;
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put(logInChk[i++], memberId);
		paramMap.put(logInChk[i++], memberPw);

		Log.i(Constants.TAG, "enter the logInChk before");

		return post(path, paramMap, Constants.RECEIVE);
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

		String logInData[] = Constants.LOGINDATA;
		int i = 0;
		
		SharedPreferences pref = context.getSharedPreferences(logInData[i++], context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(logInData[i], Integer.valueOf(data.get(logInData[i++])).intValue());
		editor.putInt(logInData[i], Integer.valueOf(data.get(logInData[i++])).intValue());
		editor.putString(logInData[i], data.get(logInData[i++]));
		editor.putString(logInData[i], data.get(logInData[i++]));
		editor.putInt(logInData[i], Integer.valueOf(data.get(logInData[i++])).intValue());
		editor.putInt(logInData[i], Integer.valueOf(data.get(logInData[i++])).intValue());
		editor.commit();
		
		return data;
	}

	//값저장하기 번들편..
	public Bundle saveBundle(String rcvData){
		Bundle extras = new Bundle();
		JSONArray jsonArray = exchangeData(rcvData);
		
		JSONObject tmp = jsonArray.getJSONObject(0);
		
		Iterator<String> keys = tmp.keys();
		while(keys.hasNext()){
			String key = keys.next();
			extras.putString(key, tmp.getString(key));
		}
			
		return extras;
	}

}
