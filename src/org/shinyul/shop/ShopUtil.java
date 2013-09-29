package org.shinyul.shop;

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

public class ShopUtil extends CommonUtils {

	private static ShopUtil util = new ShopUtil();

	public static ShopUtil getShopUtil() {
		return util;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getShopData(Context context, String path, String selIdx) {
		Log.i(Constants.TAG, "getShopData in");

		String shopParam[] = Constants.SHOPPARAM;
		int i = 0;
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put(shopParam[i++], selIdx);

		return post(path, paramMap, Constants.RECEIVE);
	}
	

}
