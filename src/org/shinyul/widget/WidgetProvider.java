package org.shinyul.widget;

import org.shinyul.cpr_widget.R;
import org.shinyul.util.CommonUtils;
import org.shinyul.util.Constants;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;


/**
 * widget 동작 및 생명주기에 관한 method를 모아놓은 class. AppWidgetProvider 상속받아 사용함.
 */
public class WidgetProvider extends AppWidgetProvider {
	
	private String reserveList;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// 

		Log.i(TAG, "=================== onReceive() ===================");
		
		super.onReceive(context, intent);
	}

	private final String TAG = "Widget";
	private CommonUtils util;
	
	// application 관련 정보에 접근하거나, application과 연관된 레벨의 함수를 호출할 때 사용.
		// 1. 자신이 어떤 application을 나타내고 있는지 알려주는 ID역할
		// 2. ActivityManagerService 에 접근할 수 있도록 하는 통로 역할 
	 Context context;
//	 SendMessageHandler handler;
	 
	/**
	 * APP Widget이 "처음" 생성될때 불림. (단, 첫번째 위젯을 띄울때만 호출됨.)
	 */
	@Override
	public void onEnabled(Context context) {
	
		Log.i(TAG, "=================== onEnabled() ===================");

		super.onEnabled(context);
		
	}


	/**
	 *  APP Widget의 속성(Meta data)에서 지정해준 updatePeriodMillis 값에 따라 주기적으로 호출.
	 1. 처음 widget이 화면에 붙을 때 init()작업을 해주기 위해서도 call 됨.
	 2. 보통 Handler를 넣어주어서 작업을 수행함.	
	 */
	
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
	
		Log.i(TAG, "=================== onUpdate() ===================");
		this.context = context;
		
//		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_widget);
		
		// widget마다 고유의 id가 부여되기 때문에 widget의 변화가 생겼을 경우 
		// 현재 사용자가 사용하고 있는 모든 widget을 update 해줘여 함.
		Log.i(Constants.TAG, "msg : " + appWidgetIds.length);
		for(int i = 0; i < appWidgetIds.length; i++){
				
			int appWidgetId = appWidgetIds[i];
			
			// view를 만들기 위한 설계도
			RemoteViews remoteViews = updateWidgetListView(context, appWidgetId, reserveList);
			
			appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
		}
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
				
	}

	
	/**
	 * 3. GCMIntentService의 핸들러가 보낸 데이터를 받음.
	 * 받은 데이터를 위젯으로 보냄.
	*/
	private RemoteViews updateWidgetListView(Context context, int appWidgetId, String reserveList) {
		
		Log.i("","===============updateWidgetListView() : "+ reserveList);
		
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_widget);
						
		// RemoteViews Service 는 ListView Adapter가 필요함.
		Intent svcIntent = new Intent(context, WidgetService.class);
				
		// RemoteViews Service 에 widget id 추가...
		svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		
		//reserveList 추가
		svcIntent.putExtra("reserveList", reserveList);
		
		// intent로 가는 경로 설정		
		svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
		
		// 위젯의 listview를 위한 adapter 설정
		remoteViews.setRemoteAdapter(appWidgetId, R.id.listViewWidget, svcIntent);
		//remoteViews.setRemoteAdapter(appWidgetId, svcIntent);
		
		//setting an empty view in case of no data
		remoteViews.setEmptyView(R.id.listViewWidget, R.id.empty_view);
		
		return remoteViews;
	}

	/**
	 *  APP Widget이 Widget host(런처)로부터 삭제될때 불림.
	 */	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
	
		Log.i(TAG, "=================== onDeleted() ===================");
		super.onDeleted(context, appWidgetIds);
	}

	/**
	 * APP Widget이 "삭제"될때 불림.(단, 가장 마지막에 남아있는 App Widget이 detach 되었을 때만 호출됨.)
	 */
	@Override
	public void onDisabled(Context context) {
		Log.i(TAG, "=================== onDisabled() ===================");
		super.onDisabled(context);
	}
	
	/////////////////////////////////////////////////////////////////////////
	//데이터 업데이트
	public void updateData(String reserveList){
		this.reserveList = reserveList;
		//reserveList = pref.getString("reserveList","[{reserveIdx:0,'productName':'예약이 없습니다NAME.','productInfo':'예약이 없습니다INFO'}]");
	}
}
