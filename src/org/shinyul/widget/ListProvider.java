package org.shinyul.widget;

import java.util.ArrayList;
import java.util.List;
import org.shinyul.cpr_seller.R;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

/**
 * If you are familiar with Adapter of ListView,this is the same as adapter
 * with few changes
 * 
 */
public class ListProvider implements RemoteViewsFactory {
	
	public ArrayList<ListItem> listItemList = new ArrayList<ListItem>();
	private CommonUtils util;
	private Context context = null;
	private int appWidgetId;
	private String reserveList;
	
	protected static String[] head={
		 "제사 음식 - 5색 나물 + 국탕",
		 "제사 음식 - 5색 나물 + 국탕",
		 "제사 음식 - 5색 나물 + 국탕",
		 "제사 음식 - 5색 나물 + 국탕",
		 "제사 음식 - 5색 나물 + 국탕"		
	};

	protected static String[] content={
		 "주문자 : uuid813",
		 "주문자 : scott",
		 "주문자 : akb",
		 "주문자 : otaku48",
		 "주문자 : teagu9"
	};
	
	public ListProvider(Context context, Intent intent) {
		this.context = context;
//		appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
//				AppWidgetManager.INVALID_APPWIDGET_ID);
		
		Bundle bundle  =  intent.getExtras();
		appWidgetId = bundle.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		reserveList =  bundle.getString("reserveList", "예약이 없습니다");		
		
		Log.i(Constants.TAG, "msg listprovider : " +  reserveList); 
		
		populateListItem();
		
	}
	
	
	private void populateListItem() {
		
		util = WidgetUtil.getWidgetUtil();
		List<ReserveVO> list =  ((WidgetUtil) util).getReserveData(reserveList); 
		
		Log.i(Constants.TAG, "msg list : " +  list);
		
		if(list.size() != 0){
			for (int i = 0; i < list.size() ; i++) {
				ListItem listItem = new ListItem();
				listItem.heading = list.get(i).getProductName();
//				listItem.content = list.get(i).getProductInfo();
				listItem.content = list.get(i).getReserveMemo();
				listItemList.add(listItem);
			}
		}else{
			ListItem listItem = new ListItem();
			listItem.heading = reserveList;
			listItem.content = reserveList;
			listItemList.add(listItem);
		}
	}

	@Override
	public int getCount() {
		return listItemList.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/*
	 *Similar to getView of Adapter where instead of View
	 *we return RemoteViews 
	 * 
	 */
	@Override
	public RemoteViews getViewAt(int position) {
		final RemoteViews remoteView = new RemoteViews(
				context.getPackageName(), R.layout.list_row);
	
		ListItem listItem = listItemList.get(position);
		remoteView.setTextViewText(R.id.heading, listItem.heading);
		remoteView.setTextViewText(R.id.content, listItem.content);
//		remoteView.setImageViewResource(viewId, srcId)
		return remoteView;
	}
	

	@Override
	public RemoteViews getLoadingView() {
		return null;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public void onCreate() {
	}

	@Override
	public void onDataSetChanged() {
	}

	@Override
	public void onDestroy() {
	}

}
