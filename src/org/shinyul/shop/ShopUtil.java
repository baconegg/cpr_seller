package org.shinyul.shop;

import java.util.HashMap;

import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;

import android.content.Context;
import android.util.Log;

public class ShopUtil extends CommonUtils {

	private static ShopUtil util = new ShopUtil();

	public static ShopUtil getShopUtil() {
		return util;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getShopData(Context context, String path, String selIdx, String page) {
		Log.i(Constants.TAG, "getShopData in");

		String shopParam[] = Constants.SHOPPARAM;
		int i = 0;
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put(shopParam[i++], selIdx);
		paramMap.put(shopParam[i++], page);

		return post(path, paramMap, Constants.RECEIVE);
	}
	
}
