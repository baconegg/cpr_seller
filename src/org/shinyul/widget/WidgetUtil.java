package org.shinyul.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;

import android.content.Context;
import android.util.Log;

public class WidgetUtil extends CommonUtils {

	private static  WidgetUtil util = new WidgetUtil();

	public static WidgetUtil getWidgetUtil() {
		return util;
	}

	/**
	 * 1. reserveList를 받기위해
	 * 
	 * @param page
	 * @param selIdx
	 *            보냄. 2. resrveList 받아옴.
	 */
	public String receiveList(Context context, Integer page, Integer selIdx, String path) {
		Log.i(Constants.TAG, "reserveList in");

		String widgetParam[] = Constants.WIDGETPARAM;
		int i = 0;
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put(widgetParam[i++], String.valueOf(page));
		paramMap.put(widgetParam[i++], String.valueOf(selIdx));

		Log.i(Constants.TAG, "enter the onReceive before");

		return post(path, paramMap, Constants.RECEIVE);
	}
	
	/////////////////////////////////////////////////////////////////////
	//파싱
	public List<ReserveVO> getReserveData(String rcvData) {
		List<ReserveVO> list = new ArrayList<ReserveVO>();
		try {
			JSONArray jsonArray = util.exchangeData(rcvData);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject obj = jsonArray.getJSONObject(i);
				// Log.i(Constants.TAG, obj.getString("reserveIdx"));
				ReserveVO vo = voInit(obj);
				list.add(vo);
			}
			// Log.i(Constants.TAG, "in getList : " + list);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

	/////////////////////////////////////////////////////////////////////
	//vo setting
	public ReserveVO voInit(JSONObject obj) {
		ReserveVO vo = new ReserveVO();
		String reserveVO[] = Constants.RESERVEVO;
		int i = 0;
		
		try {
			vo.setReserveIdx(obj.getInt(reserveVO[i++]));
			vo.setProductName(obj.getString(reserveVO[i++]));
			vo.setProductInfo(obj.getString(reserveVO[i++]));
			vo.setReserveTime(obj.getString(reserveVO[i++]));
			vo.setProductIdx(obj.getInt(reserveVO[i++]));
			vo.setProductPrice(obj.getInt(reserveVO[i++]));
			vo.setReserveQty(obj.getInt(reserveVO[i++]));
			vo.setReserveReceiveTime(obj.getString(reserveVO[i++]));
			vo.setReserveMemo(obj.getString(reserveVO[i++]));
			vo.setReserveFlag(obj.getInt(reserveVO[i++]));
			vo.setCustomerIdx(obj.getInt(reserveVO[i++]));
			vo.setMemberIdx(obj.getInt(reserveVO[i++]));
			vo.setMemberId(obj.getString(reserveVO[i++]));
			vo.setMemberName(obj.getString(reserveVO[i++]));
			vo.setMemberTel(obj.getString(reserveVO[i++]));
			vo.setMemberLev(obj.getInt(reserveVO[i++]));
			vo.setSelIdx(obj.getInt(reserveVO[i++]));
			vo.setTotal(obj.getInt(reserveVO[i++]));
			vo.setTotalPrice(obj.getInt(reserveVO[i++]));
			vo.setMarId(obj.getString(reserveVO[i++]));
			vo.setSelStore(obj.getString(reserveVO[i++]));
			vo.setProductImgUuid(obj.getString(reserveVO[i++]));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return vo;
	}
}
