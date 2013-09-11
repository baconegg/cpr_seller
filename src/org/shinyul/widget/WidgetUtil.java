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

		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("page", String.valueOf(page));
		paramMap.put("selIdx", String.valueOf(selIdx));

		Log.i(Constants.TAG, "enter the onReceive before");

		return post(path, paramMap, "onReceiveData");
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
		try {
			vo.setReserveIdx(obj.getInt("reserveIdx"));
			vo.setProductName(obj.getString("productName"));
			vo.setProductInfo(obj.getString("productInfo"));
			vo.setReserveTime(obj.getString("reserveTime"));
			vo.setProductIdx(obj.getInt("productIdx"));
			vo.setProductPrice(obj.getInt("productPrice"));
			vo.setReserveQty(obj.getInt("reserveQty"));
			vo.setReserveReceiveTime(obj.getString("reserveReceiveTime"));
			vo.setReserveMemo(obj.getString("reserveMemo"));
			vo.setReserveFlag(obj.getInt("reserveFlag"));
			vo.setCustomerIdx(obj.getInt("customerIdx"));
			vo.setMemberIdx(obj.getInt("memberIdx"));
			vo.setMemberId(obj.getString("memberId"));
			vo.setMemberName(obj.getString("memberName"));
			vo.setMemberTel(obj.getString("memberTel"));
			vo.setMemberLev(obj.getInt("memberLev"));
			vo.setSelIdx(obj.getInt("selIdx"));
			vo.setTotal(obj.getInt("total"));
			vo.setTotalPrice(obj.getInt("totalPrice"));
			vo.setMarId(obj.getString("marId"));
			vo.setSelStore(obj.getString("selStore"));
			vo.setProductImgUuid(obj.getString("productImgUuid"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return vo;
	}
}
