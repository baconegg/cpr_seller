package org.shinyul.shop;

import java.util.ArrayList;

import org.shinyul.cpr_seller.R;
import org.shinyul.util.Constants;
import org.shinyul.util.Constants.Menu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class IconTextListAdapter extends ArrayAdapter<IconTextItem> implements OnClickListener{

	private Context context;
	private ArrayList<IconTextItem> items;
	
	public IconTextListAdapter(Context context,  int textViewResourceId, ArrayList<IconTextItem> items) {
		super(context, textViewResourceId, items);
	    this.items = items;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view =  convertView;
		if(view == null){
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = vi.inflate(R.layout.listitem, null);
		} 
		
		IconTextItem item = items.get(position);
		if(item != null){
			//하나의 이미지뷰와 2개의 텍스트뷰 정보를 받아오자.
			ImageView imageView = (ImageView)view.findViewById(R.id.custom_list_image);
			TextView textViewMain = (TextView)view.findViewById(R.id.custom_list_title_main);
            TextView textViewSub = (TextView)view.findViewById(R.id.custom_list_title_sub);
             
            // 현재 item의 position에 맞는 이미지와 글을 넣어준다.          
            imageView.setBackgroundResource(item.getImageID());
            textViewMain.setText(item.getMainTitle());
            textViewSub.setText(item.getSubTitle());
		}
		
		view.setTag(position);
		view.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View v) {

		//위에서 저장한 tag(position) 꺼내기
		int position = ((Integer)v.getTag()).intValue();
		
		//리스트에서 position에 맞춰 작업시키기
		Menu menu[] = Constants.Menu.values(); 
					  	
//		Toast.makeText(context, "position : " + menu[position], Toast.LENGTH_LONG).show();
		
		Intent intent = new Intent();
		switch(menu[position]){
		
		case GOTO_SHOP:
			//다음 액티비티로 넘길 Bundle데이터 만들기
			intent.setClass(context, ShopActivity.class);
			break;
		
		case PRODUCT_ADD:
			break;
		
		case RESERVATION_CERTAIN:
			break;
			
		case PACKAGE_MATCH:
			break;
		
		case SELLER_INFO:
			break;
		
		}
		context.startActivity(intent);
		
	}

	
}
