package org.shinyul.cpr_widget;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class CommonUtils{

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // common - 서버와 통신하는 부분
  /**
   *  안드로이드 단말에서 웹 컨트롤러로 보냄.
   * @param path
   * @param paramMap
   */
  protected String post(String path, Map<String, String> paramMap, String cmd) {
	   
	  	String serverUrl = Constants.SERVERURL+ path;
	    Log.i(Constants.TAG , serverUrl);
	    
	    URL url = null;
		try {
			url = new URL(serverUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		//인자값(파라미터) 만드는 부분
	    String params = makeParams(paramMap);
	       
	    HttpURLConnection conn = null;
	   String reserveList = null;
		try {
			
			//connection 얻기
			conn = getConnection(url,"POST", params.getBytes().length);
			
			//인자값(파라미터) 보내기
			onWrite(conn, params.getBytes());
			
			//값보내고 받는 통신의 성공여부
			int states = conn.getResponseCode();
			if (states != 200){
				throw new IOException("Post failed with error code " + states);
			}else{
				//성공시 값 받기
				if(cmd.equals("onReceiveList")){
					reserveList = onReceiveList(conn);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			if(conn != null) conn.disconnect();
		}
		
		return reserveList;
  }
  
/**
 *  커넥션 얻는 함수
 */
  protected HttpURLConnection getConnection(URL url, String method, int length) throws IOException{
	  	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setFixedLengthStreamingMode(length);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		return conn;
  }
  
  
/**
 *  인자값(파라미터) 설정하는 함수
 */
  protected String makeParams(Map<String, String> paramMap){
		 StringBuilder params  = new StringBuilder();
	    Iterator<Entry<String, String>> it = paramMap.entrySet().iterator();
	    while(it.hasNext()){
	    	Entry<String, String> tmp = it.next();
	    	params.append(tmp.getKey())
	    			 .append("=")
	    			 .append(tmp.getValue());
	    	
	    	if(it.hasNext()){
	    		params.append("&");
	    	}        	
	    };
	    
	    return params.toString();
	}
  
	/**
	 *  인자값(파라미터) 서버로 보내는 함수
	 */
  	protected void onWrite(HttpURLConnection conn, byte[] params) throws IOException{
  		OutputStream out = conn.getOutputStream();
		out.write(params);
		out.flush();
		out.close();
  	}
  
  	/**
  	 *  서버로 부터 리턴값 받는 함수
  	 * @throws IOException 
  	 * @throws JSONException 
  	 */
  	public String onReceiveList(HttpURLConnection conn) throws IOException, JSONException{
  	
  		Log.i(Constants.TAG, "states : 200");
			
		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String temp = "";
		while((temp = reader.readLine()) != null){
			sb.append(temp); 
		}
		String reserveList = sb.toString();
//		Log.i(Constants.TAG, reserveList);
	
		return reserveList;
  	}

  	
  	protected List<ReserveVO> getList(String reserveList){
		////////////////////////////////////////parsing part////////////////////////////////////
  		List<ReserveVO> list = new ArrayList<ReserveVO>();
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(reserveList);
			for(int i=0; i < jsonArray.length(); i++){
				JSONObject obj = jsonArray.getJSONObject(i);
				
//				Log.i(Constants.TAG, obj.getString("reserveIdx"));
				
				ReserveVO vo = new ReserveVO();
				vo.init(obj);
				
				list.add(vo);
			}
			
			
//			Log.i(Constants.TAG, "in getList : " + list);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		////////////////////////////////////////////////////////////////////////////////////////////////
		return list;
  	}
  	
}