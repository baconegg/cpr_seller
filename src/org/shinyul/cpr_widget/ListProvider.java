package org.shinyul.cpr_widget;

import java.util.ArrayList;
import java.util.List;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;
import android.widget.Toast;

/**
 * If you are familiar with Adapter of ListView,this is the same as adapter
 * with few changes
 * 
 */
public class ListProvider implements RemoteViewsFactory {
	
	public ArrayList<ListItem> listItemList = new ArrayList<ListItem>();
//	public List<ReserveVO> list;
	private GCMUtil util = GCMUtil.getGCMUtil();
	private Context context = null;
	private int appWidgetId;
	
	
	
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
		appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
				AppWidgetManager.INVALID_APPWIDGET_ID);
		
//		processThread();
		
		populateListItem();
		
	}
	
	private void processThread() {
		
//		PrivateThread thread = new PrivateThread();
//		SendMessageHandler handler = new SendMessageHandler();
//		handler.listProvider = this;
//		thread.setHandler(handler);
//		thread.context = context;
//		thread.start();
		
	}
	
	
	
	private void populateListItem() {
		
		
//		String reserveList = util.receiveList(context, 1, 46, "reserve/widgetlist");
//		List<ReserveVO> list =  util.getList(reserveList);
		
		SharedPreferences pref = context.getSharedPreferences("pref", context.MODE_PRIVATE);
		String reserveList = pref.getString("reserveList", "없당");
		List<ReserveVO> list =  util.getList(reserveList);
		
		for (int i = 0; i < list.size() ; i++) {
			Log.i(Constants.TAG, "msg 2 : " +  list.get(i).getMarId());
			ListItem listItem = new ListItem();
			listItem.heading = list.get(i).getProductName();
			listItem.content = list.get(i).getProductInfo();
			listItemList.add(listItem);
			
			Toast.makeText(context, list.get(i).getProductInfo(), Toast.LENGTH_SHORT).show();
		}
			 
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
	
        
//        for (int i = 0; i < 5; i++) {
//			ListItem listItem = new ListItem();
//			listItem.heading = i + head[i];
//			listItem.content = content[i];
//			listItemList.add(listItem);
//		}
			 
        
	/*	String reserveList = util.receiveList(context, 1, 46, "reserve/widgetlist");
		List<ReserveVO> list =  util.getList(reserveList);
		
		for (int i = 0; i < list.size() ; i++) {
			Log.i(Constants.TAG, "msg 2 : " +  list.get(i).getMarId());
			ListItem listItem = new ListItem();
			listItem.heading = list.get(i).getProductName();
			listItem.content = list.get(i).getProductInfo();
			listItemList.add(listItem);
			
			Toast.makeText(context, list.get(i).getProductInfo(), Toast.LENGTH_SHORT).show();
		}*/
		
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

	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	Handler handler = new Handler() {
//
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//
//			switch (msg.what) {
//			case Constants.SEND_THREAD_INFOMATION:
//
//				Log.i(Constants.TAG, "ListProvider 오니?");
//
////				Log.i(Constants.TAG, "msg : " + msg.obj.toString());
//				String reserveList = msg.obj.toString();
//				List<ReserveVO> list =  util.getList(reserveList);
//				
//				ListItem listItem = null;
//				for (int i = 0; i < list.size() ; i++) {
//					listItem = new ListItem();
//					listItem.heading = list.get(i).getProductName();
//					listItem.content = list.get(i).getProductInfo();
//					Log.i(Constants.TAG, "msg " + i + " : "+  listItem.heading);
//					
//					Toast.makeText(context, list.get(i).getProductInfo(), Toast.LENGTH_SHORT).show();
//					
//					listItemList.add(listItem);
//					
//					
//				}
//				
//				
//				break;
//
//			case Constants.SEND_THREAD_STOP_MESSAGE:
//
//				break;
//
//			default:
//				break;
//			}
//
//		}
//	};
	
}
